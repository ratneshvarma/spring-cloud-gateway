package com.order.exception;

public class OrderItemNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;

	public OrderItemNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
