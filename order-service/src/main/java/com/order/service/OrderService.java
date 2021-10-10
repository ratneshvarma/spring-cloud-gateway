package com.order.service;


import java.util.List;

import com.order.dto.OrderDTO;
import com.order.dto.OrderItemDTO;
import com.order.entity.Order;
import com.order.exception.OrderItemNotFoundException;
import com.order.exception.OrderNotFoundException;

public interface OrderService {
	public void createOrder(OrderDTO order) throws OrderItemNotFoundException;
	public Order updateOrder(Integer orderId, OrderDTO order) throws OrderNotFoundException;
	public OrderDTO getOrder(Integer orderId)throws OrderNotFoundException;
	public List<OrderDTO> getOrders()throws OrderNotFoundException;
	public void deleteOrder(Integer orderId)throws OrderNotFoundException;
	
	public void createItem(Integer orderId, OrderItemDTO item) throws OrderNotFoundException, OrderItemNotFoundException;
	public void updateItem(Integer orderId,Integer itemId, OrderItemDTO item) throws OrderNotFoundException;
	public List<OrderItemDTO> getItems(Integer orderId)throws OrderNotFoundException;
	public void deleteItem(Integer orderId,Integer itemId) throws OrderNotFoundException, OrderItemNotFoundException;
	

}
