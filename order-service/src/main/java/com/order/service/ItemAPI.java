package com.order.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.dto.OrderItemDTO;


@FeignClient(name="item-service") //service name should be same as name of service (item-service)
public interface ItemAPI {

	@GetMapping("/items/{productCode}")
	public ResponseEntity<OrderItemDTO> getItem(@PathVariable("productCode") String productCode);
	
	@GetMapping("/items")
	public ResponseEntity<List<OrderItemDTO>> getItems();

}
