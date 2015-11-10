package com.marcoaguzzi.techtest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private List<ShoppingCartEntry> entries = new ArrayList<ShoppingCartEntry>();
	private BigDecimal salesTaxes;
	private BigDecimal total;

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

}
