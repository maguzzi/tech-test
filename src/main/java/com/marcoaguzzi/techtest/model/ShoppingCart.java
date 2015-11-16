package com.marcoaguzzi.techtest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * the shopping list container, the locale is set in this bean
 * because every shopping cart could be from a different user and hence in a different
 * language with different tax rates
 * @author marcoaguzzi
 *
 */
public class ShoppingCart {
	private int id;
	private List<ShoppingCartEntry> entries;
	private BigDecimal salesTaxes;
	private BigDecimal total;
	private Locale locale;

	public ShoppingCart(Locale locale) {
		entries = new ArrayList<ShoppingCartEntry>();
		salesTaxes = BigDecimal.ZERO;
		total = BigDecimal.ZERO;
		this.locale = locale;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ShoppingCartEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<ShoppingCartEntry> entries) {
		this.entries = entries;
	}

	public BigDecimal getSalesTaxes() {
		return salesTaxes;
	}

	public void setSalesTaxes(BigDecimal salesTaxes) {
		this.salesTaxes = salesTaxes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void addEntry(ShoppingCartEntry entry) {
		entries.add(entry);
	}

	public Locale getLocale() {
		return this.locale;
	}
}
