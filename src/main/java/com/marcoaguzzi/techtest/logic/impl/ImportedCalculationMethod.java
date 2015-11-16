package com.marcoaguzzi.techtest.logic.impl;

import java.math.BigDecimal;
import java.util.Locale;

import com.marcoaguzzi.techtest.logic.CalculationMethodFactory;
import com.marcoaguzzi.techtest.logic.CalculatorFactory;
import com.marcoaguzzi.techtest.logic.ICalculationMethod;
import com.marcoaguzzi.techtest.logic.TaxFactory;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

/**
 * Imported calculation method: in addition to VAT, an import duty is applied
 * 
 * @author marcoaguzzi
 *
 */
public class ImportedCalculationMethod implements ICalculationMethod {

	/**
	 * @param shoppingCartEntry the shopping cart entry on which calculation are applied
	 */
	public void calculate(ShoppingCartEntry shoppingCartEntry) {
		Locale locale = shoppingCartEntry.getShoppingCart().getLocale();
		BigDecimal taxRate = BigDecimal.ZERO;
		if (!CalculationMethodFactory.getInstance().getExemption(shoppingCartEntry.getCategory())) {
			BigDecimal vatRate =  TaxFactory.getInstance().getValueAddedTaxRate(locale);
			taxRate = taxRate.add(vatRate);
		} 
		taxRate=taxRate.add(TaxFactory.getInstance().getImportedTaxRate(locale));
		
		CalculatorFactory.getInstance().applyTaxRateAndComputePrice(shoppingCartEntry,taxRate);
		
		
	}

}
