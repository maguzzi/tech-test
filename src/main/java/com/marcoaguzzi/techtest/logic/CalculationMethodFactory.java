package com.marcoaguzzi.techtest.logic;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.marcoaguzzi.techtest.constants.Categories;
import com.marcoaguzzi.techtest.logic.impl.ImportedCalculationMethod;
import com.marcoaguzzi.techtest.logic.impl.StandardCalculationMethod;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

/**
 * This class serves as a factory with common methods needed to achieve the logic
 * of the calculation. Non - changing methods are exposed directly from the factory,
 * calculation method, which change depending on the Shopping Cart content, are served
 * with the command pattern 
 * 
 * @author marcoaguzzi
 *
 */
public class CalculationMethodFactory {
	
	private static CalculationMethodFactory _instance;
	
	private Logger log = Logger.getLogger(getClass());
	
	private CalculationMethodFactory() {
		
	}
	
	public static CalculationMethodFactory getInstance() {
		if (_instance==null) {
			_instance = new CalculationMethodFactory();
		} 
		return _instance;
	}
	
	/**
	 * this method could serve as a look - up table to discriminate which category benefits for the exempt and which not.
	 * @param category
	 * @return
	 */
	public Boolean getExemption(String category) {
		Boolean exempt = Arrays.asList(new String[]{Categories.FOOD.name(),Categories.MEDICAL.name(),Categories.BOOKS.name()}).contains(category);
		log.debug(String.format("category %s is %s an exempt",category,exempt?"":"not"));
		return exempt;
	}
	
	/**
	 * Retrieves the correct calculation method instance, given the shopping cart entry data 
	 * @param shoppingCartEntry input data
	 * @return the calculation method
	 */
	public ICalculationMethod getCalculationMethod(ShoppingCartEntry shoppingCartEntry) {		
		if (shoppingCartEntry.getImported()) {
			return new ImportedCalculationMethod();
		} else {
			return new StandardCalculationMethod();
		}
	}
}
