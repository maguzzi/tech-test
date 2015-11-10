package com.marcoaguzzi.techtest;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.marcoaguzzi.techtest.model.ShoppingCart;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

public class EndToEndTest {

	private File inputDataFile;
	private List<ShoppingCart> shoppingCarts;
	
	@Before
	public void setUp() throws Exception {
		inputDataFile = new File("./src/test/resources/input_data.csv");
		LineNumberReader lnr = new LineNumberReader(new FileReader(inputDataFile));
		
		shoppingCarts = new ArrayList<ShoppingCart>();
		ShoppingCart sc = null;
		String line = lnr.readLine();		
		while(line != null) {
			if (line.startsWith("#")) {
				sc = new ShoppingCart();
				shoppingCarts.add(sc);
			} else {
				ShoppingCartEntry entry = convertLineToShoppingCart(line);
				sc.addEntry(entry);
			}
			line = lnr.readLine();
		}
		
		lnr.close();
	}

	private ShoppingCartEntry convertLineToShoppingCart(String line) {
		String[] fields = line.split(";");
		ShoppingCartEntry sce = new ShoppingCartEntry();
		sce.setQuantity(new BigDecimal(fields[0]));
		sce.setImported(Boolean.parseBoolean(fields[1]));
		sce.setFood(Boolean.parseBoolean(fields[2]));
		sce.setDescription(fields[3]);
		sce.setPrice(new BigDecimal(fields[4]));
		return sce;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		int i=1;
		for (ShoppingCart sc : shoppingCarts) {
			System.out.println("Shopping Cart "+i);
			for (ShoppingCartEntry sce : sc.getEntries()) {
				System.out.print(sce.getQuantity()+";");
				System.out.print(sce.getImported()+";");
				System.out.print(sce.getFood()+";");
				System.out.print(sce.getDescription()+";");
				System.out.println(sce.getPrice());
			}
			i++;
		}
	}

}
