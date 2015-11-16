package com.marcoaguzzi.techtest.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.marcoaguzzi.techtest.logic.impl.ImportedCalculationMethod;
import com.marcoaguzzi.techtest.logic.impl.StandardCalculationMethod;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

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
	
	public Boolean getExemption(String category) {
		Boolean exempt = Arrays.asList(new String[]{"FOOD","MEDICAL","BOOKS"}).contains(category);
		log.debug(String.format("category %s is %s an exempt",category,exempt?"":"not"));
		return exempt;
	}
	
	private BigDecimal ceil(BigDecimal bd) {
		return bd.setScale(0, RoundingMode.CEILING);
	}
	
	public BigDecimal getRoundedValueToNearest(String nearestS,BigDecimal value) {
		BigDecimal nearest = new BigDecimal(nearestS);
		return ceil(value.divide(nearest)).multiply(nearest);
	}
	
	public ICalculationMethod getCalculationMethod(ShoppingCartEntry shoppingCartEntry) {
		
		if (shoppingCartEntry.getImported()) {
			return new ImportedCalculationMethod();
		} else {
			return new StandardCalculationMethod();
		}
	}
}
