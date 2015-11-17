package com.marcoaguzzi.techtest.logic;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.marcoaguzzi.techtest.constants.Categories;
import com.marcoaguzzi.techtest.constants.TechTestConstants;
import com.marcoaguzzi.techtest.model.ShoppingCart;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

public class CalculatorFactoryTest {

	private Logger log = Logger.getLogger(getClass());
	
	private ShoppingCart sc;
	private ShoppingCartEntry sce;
	
	@Before 
	public void setUp() throws Exception {
		sc = new ShoppingCart(Locale.ITALY);
		sce = new ShoppingCartEntry();
		sce.setPrice(new BigDecimal("10.00"));
		sce.setCategory("OTHER");
		sce.setQuantity(new BigDecimal("2.00"));
		sce.setImported(false);
		sce.setShoppingCart(sc);
		sc.addEntry(sce);
	}
	
	@Test
	/**
	 * testing correct locales, quantity,and other tax rates
	 * @throws Exception
	 */
	public void testCalculateDetails() throws Exception {
		CalculatorFactory.getInstance().calculateDetails(sc);		
		Assert.assertEquals("2,00;false;OTHER;null;24,00"+TechTestConstants.CARRIAGE_RETURN, MarshallerFactory.getInstance().marshalShoppingCartEntry(sce));
	}
	
	@Test
	public void testCalculateTotalNotReady() throws Exception {
		try {
			CalculatorFactory.getInstance().calculateTotal(sc);
			Assert.fail("Should have thrown exception");
		} catch (CartIsUnreadyForTotalException ciufte) {
			log.error(ciufte.getMessage());
			Assert.assertEquals(BigDecimal.ZERO, sc.getSalesTaxes());
			Assert.assertEquals(BigDecimal.ZERO, sc.getTotal());
		}
		
	}
	
	@Test
	public void testCalculateTotal() throws Exception {
		try {
			CalculatorFactory.getInstance().calculateDetails(sc);
			CalculatorFactory.getInstance().calculateTotal(sc);
			
			Assert.assertEquals(new BigDecimal("24.00").longValue(), sc.getTotal().longValue());
			Assert.assertEquals(new BigDecimal("4.00").longValue(), sc.getSalesTaxes().longValue());
			
		} catch (CartIsUnreadyForTotalException ciufte) {
			Assert.fail("Process is ok, should not have thrown an exception");
		}
	}
	
	@Test
	public void testCalculateAddAndCalculateTotal() throws Exception {
		try {
			CalculatorFactory.getInstance().calculateDetails(sc);
			
			ShoppingCartEntry sceNew = new ShoppingCartEntry();
			sceNew.setCategory(Categories.BOOKS.name());
			sceNew.setDescription("Another thing");
			sceNew.setImported(false);
			sceNew.setPrice(new BigDecimal("5.0"));
			sceNew.setQuantity(new BigDecimal("1.0"));
			
			CalculatorFactory.getInstance().calculateDetails(sc);
			CalculatorFactory.getInstance().calculateTotal(sc);
			
			Assert.assertEquals(new BigDecimal("24.00").longValue(), sc.getTotal().longValue());
			Assert.assertEquals(new BigDecimal("4.00").longValue(), sc.getSalesTaxes().longValue());
			
			sc.addEntry(sceNew);
			CalculatorFactory.getInstance().calculateDetails(sc); // otherwise an exception would be thrown
			CalculatorFactory.getInstance().calculateTotal(sc);
			
			Assert.assertEquals(new BigDecimal("29.00").longValue(), sc.getTotal().longValue());
			Assert.assertEquals(new BigDecimal("4.00").longValue(), sc.getSalesTaxes().longValue());
			
			sc.removeEntry(sce);
			CalculatorFactory.getInstance().calculateDetails(sc); // otherwise an exception would be thrown
			CalculatorFactory.getInstance().calculateTotal(sc);
			
			Assert.assertEquals(new BigDecimal("5.00").longValue(), sc.getTotal().longValue());
			Assert.assertEquals(new BigDecimal("0.00").longValue(), sc.getSalesTaxes().longValue());
			
		} catch (CartIsUnreadyForTotalException ciufte) {
			Assert.fail("Process is ok, should not have thrown an exception");
		}
	}
}
