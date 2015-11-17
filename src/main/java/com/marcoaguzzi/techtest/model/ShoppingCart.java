package com.marcoaguzzi.techtest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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
	private Boolean cartIsReadyForTotal;
	private BigDecimal salesTaxes;
	private BigDecimal total;
	private Locale locale;

	public ShoppingCart(Locale locale) {
		entries = new ArrayList<ShoppingCartEntry>();
		salesTaxes = BigDecimal.ZERO;
		total = BigDecimal.ZERO;
		cartIsReadyForTotal = Boolean.FALSE;
		this.locale = locale;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		cartIsReadyForTotal = Boolean.FALSE;
	}
	
	public void removeEntry(ShoppingCartEntry entry) {
		entries.remove(entry);
		cartIsReadyForTotal = Boolean.FALSE;
	}

	public Locale getLocale() {
		return this.locale;
	}

	public Boolean getCartIsReadyForTotal() {
		return cartIsReadyForTotal;
	}

	public void setCartIsReadyForTotal(Boolean cartIsReadyForTotal) {
		this.cartIsReadyForTotal = cartIsReadyForTotal;
	}	
	
	public Iterator<ShoppingCartEntry> getEntriesIterator() {
		return entries.iterator();
	}
}
