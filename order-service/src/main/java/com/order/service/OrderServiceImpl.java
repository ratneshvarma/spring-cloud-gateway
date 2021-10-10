package com.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.dto.OrderDTO;
import com.order.dto.OrderItemDTO;
import com.order.entity.Order;
import com.order.entity.OrderItem;
import com.order.exception.OrderItemNotFoundException;
import com.order.exception.OrderNotFoundException;
import com.order.repository.ItemRepository;
import com.order.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ItemAPI itemAPI;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private Resilience4JCircuitBreakerFactory circuitBreakerFactory;
	
	
	@Override
	public void createOrder(OrderDTO orderDTO) throws OrderItemNotFoundException {

		orderRepository.saveAndFlush(new Order(orderDTO.getCustomerName(), orderDTO.getShippingAddress(), 0.0));

	}

	@Override
	public Order updateOrder(Integer orderId, OrderDTO order) throws OrderNotFoundException {
		return orderRepository.findById(orderId).map(o -> {
			o.setCustomerName(order.getCustomerName());
			o.setShippingAddress(order.getShippingAddress());
			return orderRepository.save(o);
		}).orElseThrow(() -> new OrderNotFoundException("Order id "+orderId+" not found"));

	}

	@Override
	public OrderDTO getOrder(Integer orderId) throws OrderNotFoundException {
		if (!orderRepository.existsById(orderId)) {
			throw new OrderNotFoundException("Order id "+orderId+" not found");
		}

		Order order = orderRepository.findByOrderId(orderId);
		return orderAssembler(order);

	}

	@Override
	public List<OrderDTO> getOrders() throws OrderNotFoundException {
		List<Order> orders = orderRepository.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();
		for (Order order : orders) {
			orderDTOs.add(orderAssembler(order));
		}
if (orderDTOs.isEmpty()) {
	throw new OrderNotFoundException("No order not found");
}
		return orderDTOs;

	}

	@Override
	public void deleteOrder(Integer orderId) throws OrderNotFoundException {
		if (!orderRepository.existsById(orderId)) {
			throw new OrderNotFoundException("Invalid order id "+orderId);
		}
		orderRepository.deleteById(orderId);
	}

	@Override
	public void createItem(Integer orderId, OrderItemDTO item) throws OrderNotFoundException, OrderItemNotFoundException {
		if (!orderRepository.existsById(orderId)) {
			throw new OrderNotFoundException("Invalid order id "+orderId);
		}
		Order order = orderRepository.findByOrderId(orderId);
		
		Resilience4JCircuitBreaker circuitBreaker = circuitBreakerFactory.create("item-service");
		ResponseEntity<OrderItemDTO> res = circuitBreaker.run(() -> itemAPI.getItem(item.getProductCode()),throwable -> fallbackItem() );
		
		OrderItemDTO itemDTO = res.getBody();
		if(itemDTO == null) {
			throw new OrderItemNotFoundException("Invalid product code "+item.getProductCode());
		}
		OrderItem orderItem = new OrderItem(itemDTO.getProductCode(), itemDTO.getProductName(), item.getQuantity(),
				itemDTO.getUnitPrice());
		orderItem.setOrder(order);
		Double total = order.getItems().stream().mapToDouble(product -> product.getQuantity() * product.getUnitPrice())
				.sum();
		itemRepository.save(orderItem);

		total = total + (item.getQuantity() * itemDTO.getUnitPrice());
		order.setTotal(total);
		orderRepository.saveAndFlush(order);

	}
	
	public ResponseEntity<OrderItemDTO>  fallbackItem(){
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public void updateItem(Integer orderId, Integer itemId, OrderItemDTO item) throws OrderNotFoundException{
		OrderItem orderItem = itemRepository.findByItemIdAndOrder_OrderId(itemId, orderId);
		if (orderItem == null) {
			throw new OrderNotFoundException("Invalid order id "+orderId);
		}
		orderItem.setQuantity(item.getQuantity());
		itemRepository.saveAndFlush(orderItem);
		Order order = orderRepository.findByOrderId(orderId);
		Double total = order.getItems().stream().mapToDouble(product -> product.getQuantity() * product.getUnitPrice())
				.sum();
		order.setTotal(total);
		orderRepository.saveAndFlush(order);

	}

	@Override
	public List<OrderItemDTO> getItems(Integer orderId) throws OrderNotFoundException {
		List<OrderItem> items = itemRepository.findByOrder_OrderId(orderId);
		if (items.isEmpty()) {
			throw new OrderNotFoundException("Invalid order id "+orderId);
		}
		List<OrderItemDTO> itemDTOs = new ArrayList<>();
		for (OrderItem item : items) {

			itemDTOs.add(new OrderItemDTO(item.getItemId(), item.getProductCode(), item.getProductName(),
					item.getQuantity(), item.getUnitPrice()));
		}

		return itemDTOs;
	}

	@Override
	public void deleteItem(Integer orderId, Integer itemId) throws OrderNotFoundException {
		itemRepository.deleteById(itemId);
		Order order = orderRepository.findByOrderId(orderId);
		if (order == null) {
			throw new OrderNotFoundException("Invalid order id "+orderId);
		}
		Double total = order.getItems().stream().mapToDouble(product -> product.getQuantity() * product.getUnitPrice())
				.sum();
		order.setTotal(total);
		orderRepository.saveAndFlush(order);

	}

	private OrderDTO orderAssembler(Order order) {
		List<OrderItemDTO> items = new ArrayList<>();
		for (OrderItem item : order.getItems()) {
			items.add(new OrderItemDTO(item.getItemId(), item.getProductCode(), item.getProductName(),
					item.getQuantity(), item.getUnitPrice()));
		}

		return new OrderDTO(order.getOrderId(), order.getCustomerName(), order.getShippingAddress(), items,
				order.getTotal());

	}

}
