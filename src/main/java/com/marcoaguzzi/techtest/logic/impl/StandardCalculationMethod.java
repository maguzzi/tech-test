package com.marcoaguzzi.techtest.logic.impl;

import java.math.BigDecimal;

import com.marcoaguzzi.techtest.logic.CalculationMethodFactory;
import com.marcoaguzzi.techtest.logic.ICalculationMethod;
import com.marcoaguzzi.techtest.logic.TaxFactory;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

public class StandardCalculationMethod implements ICalculationMethod {

	public void calculate(ShoppingCartEntry shoppingCartEntry) {
		BigDecimal taxRate = BigDecimal.ZERO;
		if (!CalculationMethodFactory.getInstance().getExemption(shoppingCartEntry.getCategory())) {
			taxRate = taxRate.add(TaxFactory.getInstance().getValueAddedTaxRate(shoppingCartEntry.getShoppingCart().getLocale()));
		} 
		shoppingCartEntry.applyTaxRateAndComputePrice(taxRate);
	}

}
