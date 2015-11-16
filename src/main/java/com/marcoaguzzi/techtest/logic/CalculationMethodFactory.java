package com.marcoaguzzi.techtest.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.apache.log4j.Logger;

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
		Boolean exempt = Arrays.asList(new String[]{"FOOD","MEDICAL","BOOKS"}).contains(category);
		log.debug(String.format("category %s is %s an exempt",category,exempt?"":"not"));
		return exempt;
	}
	
	/**
	 * Ceiling with BigDecimals
	 * @param bd input data
	 * @return
	 */
	private BigDecimal ceil(BigDecimal bd) {
		return bd.setScale(0, RoundingMode.CEILING);
	}
	
	/**
	 * used to perform the roundup to the nearest x (in the exercise, 0.05)
	 * @param nearestS
	 * @param value input data
	 * @return rounded value
	 */
	public BigDecimal getRoundedValueToNearest(String nearestS,BigDecimal value) {
		BigDecimal nearest = new BigDecimal(nearestS);
		return ceil(value.divide(nearest)).multiply(nearest);
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
