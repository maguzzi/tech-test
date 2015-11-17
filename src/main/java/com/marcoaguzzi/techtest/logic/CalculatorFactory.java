package com.marcoaguzzi.techtest.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.marcoaguzzi.techtest.constants.TechTestConstants;
import com.marcoaguzzi.techtest.model.ShoppingCart;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

import util.MathUtils;

/**
 * this class serves as a component utility for calculation methods that
 * should be applied to single beans. it has been written to decouple the logic from the model
 * @author marcoaguzzi
 *
 */
public class CalculatorFactory {
	private static CalculatorFactory _instance;
	
	private Logger log = Logger.getLogger(getClass());
	
	private CalculatorFactory() {
	}

	public static CalculatorFactory getInstance() {
		if (_instance==null) {
			_instance=new CalculatorFactory();
		}
		return _instance;
	}

	/**
	 * calculation process for the single voice of a shopping cart.
	 * overallprice = price * quantity
	 * taxes = (taxrate*overallprice), the rounded up
	 * pricewithtaxes = taxes+overallprice
	 * @param sce input data to be filled
	 * @param taxRate the tax rate to use
	 */
	public void applyTaxRateAndComputePrice(ShoppingCartEntry sce,BigDecimal taxRate) {
		BigDecimal overallPrice = sce.getPrice().multiply(sce.getQuantity());
		sce.setAppliedTaxes(MathUtils.getRoundedValueToNearest(TechTestConstants.NEAREST_ROUND,taxRate.multiply(overallPrice).divide(new BigDecimal("100.0"),2,RoundingMode.HALF_UP)));
		sce.setPriceWithTaxes(overallPrice.add(sce.getAppliedTaxes()));	
		log.trace(String.format("category: %s imported %s price: %.2f applied taxes: %.2f priceWithTaxes: %.2f", sce.getCategory(),sce.getImported(),sce.getPrice(),sce.getAppliedTaxes(),sce.getPriceWithTaxes()));
	}
	
	/**
	 * cycles on the entries of the shopping cart
	 * @param sc the shopping cart
	 */
	public void calculateDetails(ShoppingCart sc) {
		log.debug(String.format("start details calculation on shopping cart %02d",sc.getId()));
		Iterator<ShoppingCartEntry> entriesIterator = sc.getEntriesIterator();
		while (entriesIterator.hasNext()) {
			ShoppingCartEntry entry = entriesIterator.next();
			ICalculationMethod cm = CalculationMethodFactory.getInstance().getCalculationMethod(entry);
			cm.calculate(entry);
		}
		sc.setCartIsReadyForTotal(true);
		log.debug(String.format("end details calculation on shopping cart %02d",sc.getId()));
	}

	/**
	 * do the sums. If any of the shopping cart entry has not been calculated, an exception is thrown
	 * @param sc
	 */
	public void calculateTotal(ShoppingCart sc) throws CartIsUnreadyForTotalException {	
		if (!sc.getCartIsReadyForTotal()) {
			throw new CartIsUnreadyForTotalException();
		}
		log.debug(String.format("start total calculation on shopping cart %02d",sc.getId()));
		sc.setTotal(BigDecimal.ZERO);
		sc.setSalesTaxes(BigDecimal.ZERO);
		Iterator<ShoppingCartEntry> entriesIterator = sc.getEntriesIterator();
		while (entriesIterator.hasNext()) {
			ShoppingCartEntry sce = entriesIterator.next();
			sc.setTotal(sc.getTotal().add(sce.getPriceWithTaxes()));
			sc.setSalesTaxes(sc.getSalesTaxes().add(sce.getAppliedTaxes()));
			log.trace(String.format("total %.2f taxes %.2f", sc.getTotal(),sc.getSalesTaxes()));
		}
		log.debug(String.format("end total calculation on shopping cart %02d",sc.getId()));
	}

}
