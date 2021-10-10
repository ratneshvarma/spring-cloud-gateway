package com.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
	    @Id
	    @Column(name="product_code")
	    private String productCode;
	    @Column(name="product_name")
	    private String productName;
	    private Integer quantity;
	    @Column(name="unit_price")
	    private Float unitPrice;
	    
	    public Item() {
			super();
	    }

		public Item(String productCode, String productName, Integer quantity, Float unitPrice) {
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
