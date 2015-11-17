package com.marcoaguzzi.techtest.logic;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.marcoaguzzi.techtest.constants.TechTestConstants;
import com.marcoaguzzi.techtest.logic.impl.ImportedCalculationMethod;
import com.marcoaguzzi.techtest.logic.impl.StandardCalculationMethod;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

import util.MathUtils;

public class CalculationMethodFactoryTest {
	
	@Test
	public void testRoundToNearest() throws Exception {
		
		testRoundNumber("1.40","1.40");
		testRoundNumber("1.45","1.41");
		testRoundNumber("1.45","1.42");
		testRoundNumber("1.45","1.43");
		testRoundNumber("1.45","1.44");
		testRoundNumber("1.45","1.45");
		testRoundNumber("1.50","1.46");
		testRoundNumber("1.50","1.47");
		testRoundNumber("1.50","1.48");
		testRoundNumber("1.50","1.49");
		testRoundNumber("1.50","1.50");
		
	}

	private void testRoundNumber(String expected,String start) {
		BigDecimal actual = MathUtils.getRoundedValueToNearest(TechTestConstants.NEAREST_ROUND,
				new BigDecimal(start));
		Assert.assertEquals(new BigDecimal(expected), actual);
	}
	
	public void testGetExemption(String category) throws Exception {
		Assert.assertTrue(CalculationMethodFactory.getInstance().getExemption("BOOKS"));
		Assert.assertTrue(CalculationMethodFactory.getInstance().getExemption("FOOD"));
		Assert.assertTrue(CalculationMethodFactory.getInstance().getExemption("food"));
		Assert.assertTrue(CalculationMethodFactory.getInstance().getExemption("MEDICAL"));
		Assert.assertFalse(CalculationMethodFactory.getInstance().getExemption("OTHER"));
		Assert.assertFalse(CalculationMethodFactory.getInstance().getExemption("foodie"));
		Assert.assertFalse(CalculationMethodFactory.getInstance().getExemption("BOO"));
	}
	
	public void testGetCalculationMethod() throws Exception {
		
		ShoppingCartEntry sce1 = new ShoppingCartEntry();
		sce1.setImported(true);
		ShoppingCartEntry sce2 = new ShoppingCartEntry();
		sce2.setImported(true);
		
		ICalculationMethod calculationMethod1 = CalculationMethodFactory.getInstance().getCalculationMethod(sce1);
		ICalculationMethod calculationMethod2 = CalculationMethodFactory.getInstance().getCalculationMethod(sce2);
		
		Assert.assertTrue(calculationMethod1 instanceof ImportedCalculationMethod);
		Assert.assertTrue(calculationMethod2 instanceof StandardCalculationMethod);
	}
}
