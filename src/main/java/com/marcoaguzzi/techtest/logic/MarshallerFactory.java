package com.marcoaguzzi.techtest.logic;

import java.text.DecimalFormat;
import java.util.Iterator;

import com.marcoaguzzi.techtest.constants.TechTestConstants;
import com.marcoaguzzi.techtest.model.ShoppingCart;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

/**
 * Marshalling methods
 * @author marcoaguzzi
 *
 */
public class MarshallerFactory {
	private static MarshallerFactory _instance;
	private MarshallerFactory() {
		
	}
	public static MarshallerFactory getInstance() {
		if (_instance==null) {
			_instance = new MarshallerFactory();
		}
		return _instance;
	}
	
	/**
	 * this method is written mainly for tests purposes, but can be viewed as a general 
	 * marshaller to pass data through a more complex application. Please note that locale
	 * is also used to correctly format the decimals
	 * @param sc
	 * @return
	 */
	public String marshalShoppingCart(ShoppingCart sc) {
		StringBuffer sb = new StringBuffer();
		DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance(sc.getLocale());
		numberFormat.applyPattern(TechTestConstants.NUMBER_PATTERN);

		sb.append("Shopping Cart ").append(sc.getId()).append(" ").append(TechTestConstants.CARRIAGE_RETURN);
		Iterator<ShoppingCartEntry> entriesIterator = sc.getEntriesIterator();
		while (entriesIterator.hasNext()) {
			ShoppingCartEntry sce = entriesIterator.next();
			sb.append(marshalShoppingCartEntry(sce));
		}

		sb.append("Sales Taxes = ").append(numberFormat.format(sc.getSalesTaxes())).append(TechTestConstants.CARRIAGE_RETURN);
		sb.append("Total = ").append(numberFormat.format(sc.getTotal())).append(TechTestConstants.CARRIAGE_RETURN);
		return sb.toString();
	}
	
	/**
	 * this method is written mainly for tests purposes, but can be viewed as a general 
	 * marshaller to pass data through a more complex application
	 * @param sc
	 * @return
	 */
	public String marshalShoppingCartEntry(ShoppingCartEntry sce) {
		StringBuffer sb = new StringBuffer();
		
		DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance(sce.getShoppingCart().getLocale());
		numberFormat.applyPattern(TechTestConstants.NUMBER_PATTERN);
		
		sb.append(numberFormat.format(sce.getQuantity())).append(";");
		
		sb.append(sce.getImported()).append(";");
		sb.append(sce.getCategory()).append(";");
		sb.append(sce.getDescription()).append(";");
		
		sb.append(numberFormat.format(sce.getPriceWithTaxes())).append(TechTestConstants.CARRIAGE_RETURN);
		return sb.toString();
	}
}
