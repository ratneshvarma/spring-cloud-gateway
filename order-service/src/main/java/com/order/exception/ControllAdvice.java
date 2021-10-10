package com.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.order.dto.OrderResponse;

@RestControllerAdvice
public class ControllAdvice {

	@ExceptionHandler(value = { OrderNotFoundException.class })
	public ResponseEntity<OrderResponse> orderNotFound(OrderNotFoundException e) {

		return new ResponseEntity<>(new OrderResponse(404, e.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { OrderItemNotFoundException.class })
	public ResponseEntity<OrderResponse> itemNotFound(OrderItemNotFoundException e) {

		return new ResponseEntity<>(new OrderResponse(404, e.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<OrderResponse> exception(Exception e) {

		return new ResponseEntity<>(new OrderResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
