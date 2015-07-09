import yahoofinance.*;
import java.math.BigDecimal;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
	    try {
	        Stock stock = YahooFinance.get("INTC");
	        BigDecimal price = stock.getQuote().getPrice();
	        BigDecimal change = stock.getQuote().getChangeInPercent();
	        BigDecimal peg = stock.getStats().getPeg();
	        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

	        stock.print();
        } catch (IOException e) {
            System.out.println("IOException...");
        }
		System.out.println("Hello World!");
	}
}
