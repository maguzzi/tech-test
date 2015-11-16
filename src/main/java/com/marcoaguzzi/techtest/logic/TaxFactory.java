package com.marcoaguzzi.techtest.logic;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * this factory could be used as a lookup table for managing different countries
 * with different taxes, all the process does not know about which tax should be applied
 * but asks it to this class
 * 
 * @author marcoaguzzi
 *
 */
public class TaxFactory {
	private static TaxFactory _instance;
	
	private Logger log = Logger.getLogger(getClass());
	
	private TaxFactory() {
		
	}
	
	public static TaxFactory getInstance() {
		if (_instance==null) {
			_instance = new TaxFactory();
		}
		return _instance;
	}
	
	/**
	 * Gives the VAT given the locale
	 * @param locale 
	 * @return the input locale
	 */
	public BigDecimal getValueAddedTaxRate(Locale locale) {
		
		BigDecimal vatAddedTaxRate;
		
		switch (locale.getLanguage().toUpperCase()) {
		case "IT":
			vatAddedTaxRate = new BigDecimal("20");
			break;
		case "EN":
			vatAddedTaxRate = new BigDecimal("10");
			break;
		default:
			vatAddedTaxRate = new BigDecimal("0");
		}
		log.debug(String.format("locale: %s vat: %.2f",locale,vatAddedTaxRate));
		return vatAddedTaxRate;
	}
	
	/**
	 * Gives the import duty given the locale
	 * @param locale input locale
	 * @return
	 */
	public BigDecimal getImportedTaxRate(Locale locale) {
		
		BigDecimal importedTaxRate;
		
		switch (locale.getLanguage().toUpperCase()) {
		case "IT":
			importedTaxRate = new BigDecimal("6");
			break;
		case "EN":
			importedTaxRate = new BigDecimal("5");
			break;
		default:
			importedTaxRate = new BigDecimal("0");
		}
		log.debug(String.format("locale: %s imported tax: %.2f",locale,importedTaxRate));
	
		return importedTaxRate;
	}
}
