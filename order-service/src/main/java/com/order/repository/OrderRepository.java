package com.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	Order findByOrderId(Integer orderId);

}
