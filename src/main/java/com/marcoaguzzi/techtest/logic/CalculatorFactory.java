package com.marcoaguzzi.techtest.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import com.marcoaguzzi.techtest.constants.TechTestConstants;
import com.marcoaguzzi.techtest.model.ShoppingCart;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

/**
 * this class serves as a component utility for calculation methods that
 * should be applied to single beans. it has been written to decouple the logic from the model
 * @author marcoaguzzi
 *
 */
public class CalculatorFactory {
	private static CalculatorFactory _instance;
	
	private Logger log = Logger.getLogger(getClass());
	
	private CalculatorFactory() {
	}

	public static CalculatorFactory getInstance() {
		if (_instance==null) {
			_instance=new CalculatorFactory();
		}
		return _instance;
	}

	/**
	 * calculation process for the single voice of a shopping cart.
	 * overallprice = price * quantity
	 * taxes = (taxrate*overallprice), the rounded up
	 * pricewithtaxes = taxes+overallprice
	 * @param sce input data to be filled
	 * @param taxRate the tax rate to use
	 */
	public void applyTaxRateAndComputePrice(ShoppingCartEntry sce,BigDecimal taxRate) {
		BigDecimal overallPrice = sce.getPrice().multiply(sce.getQuantity());
		sce.setAppliedTaxes(CalculationMethodFactory.getInstance().getRoundedValueToNearest(TechTestConstants.NEAREST_ROUND,taxRate.multiply(overallPrice).divide(new BigDecimal("100.0"),2,RoundingMode.HALF_UP)));
		sce.setPriceWithTaxes(overallPrice.add(sce.getAppliedTaxes()));	
		log.trace(String.format("category: %s imported %s price: %.2f applied taxes: %.2f priceWithTaxes: %.2f", sce.getCategory(),sce.getImported(),sce.getPrice(),sce.getAppliedTaxes(),sce.getPriceWithTaxes()));
	}
	
	/**
	 * cycles on the entries of the shopping cart
	 * @param sc the shopping cart
	 */
	public void calculateDetails(ShoppingCart sc) {
		for (ShoppingCartEntry sce : sc.getEntries()) {
			ICalculationMethod cm = CalculationMethodFactory.getInstance().getCalculationMethod(sce);
			cm.calculate(sce);
		}
	}

	/**
	 * do the sums.
	 * @param sc
	 */
	public void calculateTotal(ShoppingCart sc) {		
		for (ShoppingCartEntry sce : sc.getEntries()) {
			sc.setTotal(sc.getTotal().add(sce.getPriceWithTaxes()));
			sc.setSalesTaxes(sc.getSalesTaxes().add(sce.getAppliedTaxes()));
		}
	}

	/**
	 * this method is written mainly for tests purposes, but can be viewed as a general 
	 * marshaller to pass data through a more complex application
	 * @param sc
	 * @return
	 */
	public String marshalShoppingCart(ShoppingCart sc) {
		StringBuffer sb = new StringBuffer();
		DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance(sc.getLocale());
		numberFormat.applyLocalizedPattern(TechTestConstants.NUMBER_PATTERN);

		sb.append("Shopping Cart ").append(sc.getId()).append(" ").append(TechTestConstants.CARRIAGE_RETURN);
		for (ShoppingCartEntry sce : sc.getEntries()) {
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
		sb.append(sce.getQuantity()).append(";");
		sb.append(sce.getImported()).append(";");
		sb.append(sce.getCategory()).append(";");
		sb.append(sce.getDescription()).append(";");
		
		DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance(sce.getShoppingCart().getLocale());
		numberFormat.applyLocalizedPattern(TechTestConstants.NUMBER_PATTERN);
				
		sb.append(numberFormat.format(sce.getPriceWithTaxes())).append(TechTestConstants.CARRIAGE_RETURN);
		return sb.toString();
	}
}
