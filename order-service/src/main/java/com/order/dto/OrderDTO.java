package com.order.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class OrderDTO {
	private Integer id;
	@NotNull(message = "Customer name is mandatory")
	private String customerName;
	@NotNull(message = "ShippingAddress is mandatory")
	private String shippingAddress;
	private List<OrderItemDTO> items;
	private Double total;
		
	public OrderDTO() {
		super();
	}
	
	public OrderDTO(Integer id, @NotNull(message = "Customer name is mandatory") String customerName,
			@NotNull(message = "ShippingAddress is mandatory") String shippingAddress, List<OrderItemDTO> items,
			Double total) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.shippingAddress = shippingAddress;
		this.items = items;
		this.total = total;
	}

	public OrderDTO(@NotNull(message = "Customer name is mandatory") String customerName,
			@NotNull(message = "ShippingAddress is mandatory") String shippingAddress, Double total) {
		super();
		this.customerName = customerName;
		this.shippingAddress = shippingAddress;
		this.total = total;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public List<OrderItemDTO> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
}
