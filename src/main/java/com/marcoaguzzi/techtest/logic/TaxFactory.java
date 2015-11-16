package com.marcoaguzzi.techtest.logic;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.log4j.Logger;

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
