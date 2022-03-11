package main_package;

import net.sourceforge.jFuzzyLogic.FIS;
import java.io.File;
import java.net.URISyntaxException;

public class Profit {
	private final FIS fis;
	
	private long market_size;
	private double product_count;
	private double employee_count;
	
	public Profit(double employee_count, double product_count, long market_size) throws URISyntaxException {
		this.market_size = market_size;
		this.product_count = product_count;
		this.employee_count = employee_count;
		
		File file = new File(getClass().getResource("Model.fcl").toURI());
		fis = FIS.load(file.getPath());
		
		fis.setVariable("market_size", market_size);
		fis.setVariable("product_count", product_count);
		fis.setVariable("employee_count", employee_count);
		fis.evaluate();
	}
	
	public FIS getModel() {
		return fis;
	}
	
	public double getProfit() {
		return fis.getVariable("profit").getValue();
	}
	
	@Override
	public String toString() {
		return "\nMarket Size:" + market_size + "\nProduct Count:" + product_count + "\nEmployee Count:" + employee_count + "\n\nProfit:" + fis.getVariable("profit");
	}
}
