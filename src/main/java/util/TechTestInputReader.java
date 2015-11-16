package util;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.marcoaguzzi.techtest.model.ShoppingCart;
import com.marcoaguzzi.techtest.model.ShoppingCartEntry;

public class TechTestInputReader {
	public static List<ShoppingCart> readInputFile(File file) throws Exception {
		List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
		LineNumberReader lnr = new LineNumberReader(new FileReader(file));

		shoppingCarts = new ArrayList<ShoppingCart>();
		ShoppingCart sc = null;
		String line = lnr.readLine();
		while (line != null) {
			if (line.startsWith("#")) {
				
				String[] splitted = line.split(":");
				sc = new ShoppingCart(Locale.forLanguageTag(splitted[2]));
				sc.setId(Integer.parseInt(splitted[1]));
				shoppingCarts.add(sc);
			} else {
				ShoppingCartEntry entry = convertLineToShoppingCart(line);
				entry.setShoppingCart(sc);
				sc.addEntry(entry);
			}
			line = lnr.readLine();
		}

		lnr.close();
		
		
		
		return shoppingCarts;
	}

	private static ShoppingCartEntry convertLineToShoppingCart(String line) throws Exception {
		if (line.contains(";")) {
			String[] fields = line.split(";");
			ShoppingCartEntry sce = new ShoppingCartEntry();
			BigDecimal quantity = new BigDecimal(fields[0]);
			quantity.setScale(2,RoundingMode.HALF_UP);
			sce.setQuantity(quantity);
			sce.setImported(Boolean.parseBoolean(fields[1]));
			sce.setCategory(fields[2]);
			sce.setDescription(fields[3]);
			BigDecimal price = new BigDecimal(fields[4]);
			price.setScale(2,RoundingMode.HALF_UP);
			sce.setPrice(price);
			sce.setPriceWithTaxes(price);
			return sce;
		} else {
			throw new Exception(line);
		}
	}
}
