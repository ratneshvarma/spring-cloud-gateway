package com.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.dto.OrderDTO;
import com.order.dto.OrderItemDTO;
import com.order.dto.OrderResponse;
import com.order.exception.OrderItemNotFoundException;
import com.order.exception.OrderNotFoundException;
import com.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	static final String SUCCESS = "Success";
	
	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderDTO order) throws OrderItemNotFoundException {
		orderService.createOrder(order);
		OrderResponse response = new OrderResponse(HttpStatus.OK.value(), SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}")
	public ResponseEntity<OrderResponse> updateOrder(@PathVariable (value = "orderId") Integer orderId,
			@Valid @RequestBody OrderDTO order) throws OrderNotFoundException {
		orderService.updateOrder(orderId, order);
		OrderResponse response = new OrderResponse(HttpStatus.OK.value(), SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getOrders() throws OrderNotFoundException {
		List<OrderDTO> orders = orderService.getOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
		
	}
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable (value = "orderId") Integer orderId
			) throws OrderNotFoundException {
		OrderDTO order = orderService.getOrder(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<OrderResponse> deleteOrder(@PathVariable (value = "orderId") Integer orderId
			) throws OrderNotFoundException {
		orderService.deleteOrder(orderId);
		OrderResponse response = new OrderResponse(HttpStatus.OK.value(), SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/{orderId}/items")
	public ResponseEntity<OrderResponse> createItem(@PathVariable (value = "orderId") Integer orderId,
			@Valid @RequestBody OrderItemDTO item) throws OrderNotFoundException, OrderItemNotFoundException {
		orderService.createItem(orderId,item);
		OrderResponse response = new OrderResponse(HttpStatus.OK.value(), SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@PutMapping("/{orderId}/items/{itemId}")
	public ResponseEntity<OrderResponse> updateItem(@PathVariable (value = "orderId") Integer orderId,
			@PathVariable (value = "itemId") Integer itemId,
			@Valid @RequestBody OrderItemDTO item) throws OrderNotFoundException {
		orderService.updateItem(orderId, itemId, item);
		OrderResponse response = new OrderResponse(HttpStatus.OK.value(), SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/{orderId}/items")
	public ResponseEntity<List<OrderItemDTO>> getItems(@PathVariable (value = "orderId") Integer orderId) throws OrderNotFoundException {
		List<OrderItemDTO> items = orderService.getItems(orderId);
		return new ResponseEntity<>(items, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{orderId}/items/{itemId}")
	public ResponseEntity<OrderResponse> deleteItem(@PathVariable (value = "orderId") Integer orderId,
			@PathVariable (value = "itemId") Integer itemId) throws OrderNotFoundException, OrderItemNotFoundException {
		orderService.deleteItem(orderId, itemId);
		OrderResponse response = new OrderResponse(HttpStatus.OK.value(), SUCCESS);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
