package com.marcoaguzzi.techtest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import com.marcoaguzzi.techtest.constants.TechTestConstants;
import com.marcoaguzzi.techtest.logic.CalculationMethodFactory;

public class ShoppingCartEntry {

	private Logger log = Logger.getLogger(ShoppingCartEntry.class);
	
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

	public void applyTaxRateAndComputePrice(BigDecimal taxRate) {
		appliedTaxes = CalculationMethodFactory.getInstance().getRoundedValueToNearest(TechTestConstants.NEAREST_ROUND,taxRate.multiply(price).divide(new BigDecimal("100.0"),2,RoundingMode.HALF_UP));
		priceWithTaxes = priceWithTaxes.add(appliedTaxes);
		
		log.trace(String.format("category: %s imported %s price: %.2f applied taxes: %.2f priceWithTaxes: %.2f", category,imported,price,appliedTaxes,priceWithTaxes));
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

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(quantity).append(";");
		sb.append(imported).append(";");
		sb.append(category).append(";");
		sb.append(description).append(";");
		
		DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance(shoppingCart.getLocale());
		numberFormat.applyLocalizedPattern("0.00");
				
		sb.append(numberFormat.format(priceWithTaxes)).append(TechTestConstants.CARRIAGE_RETURN);
		return sb.toString();
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	

}
