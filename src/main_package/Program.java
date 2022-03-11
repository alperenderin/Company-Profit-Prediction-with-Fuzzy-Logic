package main_package;

import java.net.URISyntaxException;
import java.util.Scanner;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Rule;

public class Program {

	public static void main(String[] args) {
		boolean ring = true;
		do {
			Scanner in = new Scanner(System.in);
			
			System.out.println("--- Profit Counter ---");
			System.out.print("Market Size -unit is billion- (30-800):");
			long market_size = in.nextLong();
			System.out.print("Employee Count (1-60.000):");
			double employee_count = in.nextDouble();
			System.out.print("Product Count (1-125.000):");
			double product_count = in.nextDouble();
			
			if(!(30 < market_size && market_size <= 800)) {
				System.out.print("Please enter the number between 30 to 800bil ₺)\n\n");
				ring = true;
				continue;
			}
			else if(!(0 < employee_count && employee_count <= 60000)) {
				System.out.print("Please enter the number between 1 to 60.000\n\n");
				ring = true;
				continue;
			}
			else if(!(10 < product_count && product_count <= 125000)) {
				System.out.print("Please enter the number between 1 to 125.000)\n\n");
				ring = true;
				continue;
			}
			
			try {
				Profit profit = new Profit(employee_count, product_count, market_size);
				System.out.print("\nProfit: " + profit.getProfit() + " million \n\n");
				
				FIS fis = profit.getModel();
				for(Rule rule : fis.getFunctionBlock("Model").getFuzzyRuleBlock("rule_block_1").getRules()){
					if(rule.getDegreeOfSupport() > 0) {
						System.out.println(rule);
					}
				}
				
				JFuzzyChart.get().chart(profit.getModel());
				JFuzzyChart.get().chart(fis.getVariable("profit").getDefuzzifier(), "Profit", true);
			}
			catch(URISyntaxException e) {
				System.out.println(e.getMessage());
			}
		}while(ring);
	}

}
