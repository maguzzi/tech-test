package com.marcoaguzzi.techtest.model;

import java.math.BigDecimal;

/**
 * The model for the single entry of a cart. It contains also a pointer to its parent container,
 * in order to retrieve the locale and avoid redundancy. It has been speculated that any entry
 * of a shopping cart has the same locale
 * @author marcoaguzzi
 *
 */
public class ShoppingCartEntry {

	private BigDecimal quantity;
	private String description;
	private BigDecimal price;
	private BigDecimal priceWithTaxes;
	private BigDecimal appliedTaxes;
	private Boolean imported;
	private String category;
	private ShoppingCart shoppingCart;
	
	public ShoppingCartEntry() {
		quantity = BigDecimal.ZERO;
		quantity.setScale(2);
		price = BigDecimal.ZERO;
		price.setScale(2);
		priceWithTaxes = BigDecimal.ZERO;
		priceWithTaxes.setScale(2);
		appliedTaxes = BigDecimal.ZERO;
		appliedTaxes.setScale(2);
		
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getAppliedTaxes() {
		return appliedTaxes;
	}

	public void setAppliedTaxes(BigDecimal appliedTaxes) {
		this.appliedTaxes = appliedTaxes;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	

}
