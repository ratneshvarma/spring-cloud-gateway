package com.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.entity.OrderItem;

public interface ItemRepository extends JpaRepository<OrderItem, Integer> {

	OrderItem findByItemIdAndOrder_OrderId(Integer itemId, Integer orderId);
	List<OrderItem> findByOrder_OrderId(Integer orderId);
	void deleteByItemIdAndOrder_OrderId(Integer itemId, Integer orderId);
	
}
