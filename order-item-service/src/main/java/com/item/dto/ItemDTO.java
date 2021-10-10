package com.item.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ItemDTO {

	@NotNull(message = "Product code is mandatory")
	private String productCode;
	@NotNull(message = "Product name is mandatory")
	private String productName;
	@NotNull(message = "Quantity is mandatory")
	@Min(value = 1, message = "Quantity is required")
	private Integer quantity;
	@NotNull(message = "Unit price is mandatory")
	private Float unitPrice;

	public ItemDTO() {
		super();
	}

	public ItemDTO(@NotNull(message = "Product code is mandatory") String productCode,
			@NotNull(message = "Product name is mandatory") String productName,
			@NotNull(message = "Quantity is mandatory") @Min(value = 1, message = "Quantity is required") Integer quantity,
			@NotNull(message = "Unit price is mandatory") Float unitPrice) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
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

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

}
