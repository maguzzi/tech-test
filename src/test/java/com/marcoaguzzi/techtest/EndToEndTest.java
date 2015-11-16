package com.marcoaguzzi.techtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.marcoaguzzi.techtest.logic.CalculationMethodFactory;
import com.marcoaguzzi.techtest.logic.ICalculationMethod;
import com.marcoaguzzi.techtest.model.ShoppingCart;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

import util.TechTestInputReader;

/**
 * Given the input data provided in the input file, a test is run to produce the output
 * data, as instructed in the output test file. As a "black box" end to end test, the two
 * files are read and each line is compared
 * 
 * @author marcoaguzzi
 *
 */
public class EndToEndTest {

	private List<ShoppingCart> shoppingCarts;
	
	@Test
	public void test() throws Exception {
		File inputDataFile = new File("./src/test/resources/input_data.csv");
		shoppingCarts = TechTestInputReader.readInputFile(inputDataFile);
		
		for (ShoppingCart sc : shoppingCarts) {
			for (ShoppingCartEntry sce : sc.getEntries()) {
				ICalculationMethod cm = CalculationMethodFactory.getInstance().getCalculationMethod(sce);
				cm.calculate(sce);
			}
			sc.calculateTotal();		
		}
		
		File outputActualDataFile = new File("./src/test/resources/output_actual_data.csv");
		FileOutputStream fosActual = new FileOutputStream(outputActualDataFile); 
		
		for (ShoppingCart sc : shoppingCarts) {
			fosActual.write(sc.toString().getBytes());
		}
		
		fosActual.flush();
		fosActual.close();
		
		File expectedDataFile = new File("./src/test/resources/output_data.csv");
		
		List<String> actualLines = convertFileIntoListOfStrings(outputActualDataFile);
		List<String> expectedLines = convertFileIntoListOfStrings(expectedDataFile);
		
		Assert.assertEquals(expectedLines.size(),actualLines.size());
		int i=0;
		for (String expected : expectedLines) {
			Assert.assertEquals(expected,actualLines.get(i));
			i++;
		}
		
	}

	
	List<String> convertFileIntoListOfStrings(File file) throws Exception {
		LineNumberReader lnr = new LineNumberReader(new FileReader(file));
		String line = null;
		List<String> lines = new ArrayList<String>();
		line = lnr.readLine();
		while(line!=null) {
			lines.add(line.trim());
			line=lnr.readLine();
		}
		lnr.close();
		return lines;
			
	}
}
