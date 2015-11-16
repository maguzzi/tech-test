package com.marcoaguzzi.techtest.logic;

import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

/**
 * command pattern interface for calculation method
 * 
 * @author marcoaguzzi
 *
 */
public interface ICalculationMethod {
	/**
	 * do the calculation on shopping cart entry and retrieves the data
	 * @param shoppingCartEntry
	 */
	void calculate(ShoppingCartEntry shoppingCartEntry);
}
