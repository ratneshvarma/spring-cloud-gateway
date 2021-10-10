package com.order.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItemDTO {
	private Integer itemId;
	@NotNull(message = "Product code is mandatory")
	private String productCode;
	private String productName;
	@NotNull(message = "Quantity is mandatory")
	@Min(value = 1, message = "Quantity is required")
	private Integer quantity;
	private Double unitPrice;

	public OrderItemDTO() {
		super();
	}

	public OrderItemDTO(Integer itemId,@NotNull(message = "Product code is mandatory") String productCode,
			String productName,
			@NotNull(message = "Quantity is mandatory") @Min(value = 1, message = "Quantity is required") Integer quantity,
			Double unitPrice) {
		super();
		this.itemId = itemId;
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
