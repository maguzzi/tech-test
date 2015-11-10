package com.marcoaguzzi.techtest.model;

import java.math.BigDecimal;

public class ShoppingCartEntry {

	private BigDecimal quantity;
	private String description;
	private BigDecimal price;
	private BigDecimal priceWithTaxes;
	private Boolean imported;
	private Boolean food;

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceWithTaxes() {
		return priceWithTaxes;
	}

	public void setPriceWithTaxes(BigDecimal priceWithTaxes) {
		this.priceWithTaxes = priceWithTaxes;
	}

	public Boolean getImported() {
		return imported;
	}

	public void setImported(Boolean imported) {
		this.imported = imported;
	}

	public Boolean getFood() {
		return food;
	}

	public void setFood(Boolean food) {
		this.food = food;
	}

}
