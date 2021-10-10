package com.order.exception;

public class OrderNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public OrderNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
