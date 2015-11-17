package com.marcoaguzzi.techtest.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility methods
 * @author marcoaguzzi
 *
 */
public class MathUtils {
	/**
	 * Ceiling with BigDecimals
	 * @param bd input data
	 * @return
	 */
	private static BigDecimal ceil(BigDecimal bd) {
		return bd.setScale(0, RoundingMode.CEILING);
	}
	
	/**
	 * used to perform the roundup to the nearest x (in the exercise, 0.05)
	 * @param nearestS
	 * @param value input data
	 * @return rounded value
	 */
	public static BigDecimal getRoundedValueToNearest(String nearestS,BigDecimal value) {
		BigDecimal nearest = new BigDecimal(nearestS);
		return ceil(value.divide(nearest)).multiply(nearest);
	}
}
