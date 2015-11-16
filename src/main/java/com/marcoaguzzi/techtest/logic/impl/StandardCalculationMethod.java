package com.marcoaguzzi.techtest.logic.impl;

import java.math.BigDecimal;

import com.marcoaguzzi.techtest.logic.CalculationMethodFactory;
import com.marcoaguzzi.techtest.logic.CalculatorFactory;
import com.marcoaguzzi.techtest.logic.ICalculationMethod;
import com.marcoaguzzi.techtest.logic.TaxFactory;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

/**
 * Standard calculation method, only VAT, if applicable
 * 
 * @author marcoaguzzi
 */
public class StandardCalculationMethod implements ICalculationMethod {

	/**
	 * @param shoppingCartEntry the shopping cart entry on which calculation are applied
	 */
	public void calculate(ShoppingCartEntry shoppingCartEntry) {
		BigDecimal taxRate = BigDecimal.ZERO;
		if (!CalculationMethodFactory.getInstance().getExemption(shoppingCartEntry.getCategory())) {
			taxRate = taxRate.add(TaxFactory.getInstance().getValueAddedTaxRate(shoppingCartEntry.getShoppingCart().getLocale()));
		} 
		CalculatorFactory.getInstance().applyTaxRateAndComputePrice(shoppingCartEntry,taxRate);
	}

}
