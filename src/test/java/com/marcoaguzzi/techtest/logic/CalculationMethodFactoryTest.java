package com.marcoaguzzi.techtest.logic;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.marcoaguzzi.techtest.constants.TechTestConstants;

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
		BigDecimal actual = CalculationMethodFactory.getInstance().getRoundedValueToNearest(TechTestConstants.NEAREST_ROUND,
				new BigDecimal(start));
		Assert.assertEquals(new BigDecimal(expected), actual);
	}
}
