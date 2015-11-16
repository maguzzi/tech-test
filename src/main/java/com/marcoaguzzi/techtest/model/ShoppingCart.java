package com.marcoaguzzi.techtest.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.marcoaguzzi.techtest.constants.TechTestConstants;

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

	public void calculateTotal() {		
		for (ShoppingCartEntry sc : entries) {
			total = total.add(sc.getPriceWithTaxes());
			salesTaxes = salesTaxes.add(sc.getAppliedTaxes());
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Shopping Cart ").append(id).append(" ").append(TechTestConstants.CARRIAGE_RETURN);
		for (ShoppingCartEntry sce : entries) {
			sb.append(sce.toString());
		}
		
		DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance(locale);
		numberFormat.applyLocalizedPattern("0.00");
		
		sb.append("Sales Taxes = ").append(numberFormat.format(salesTaxes)).append(TechTestConstants.CARRIAGE_RETURN);
		sb.append("Total = ").append(numberFormat.format(total)).append(TechTestConstants.CARRIAGE_RETURN);
		return sb.toString();
	}
	
	public Locale getLocale() {
		return this.locale;
	}
}
