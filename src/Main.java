import yahoofinance.*;
import java.math.BigDecimal;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Main {
    public static final String symbolsFilename = "src/symbols.txt";

	public static void main(String[] args) {
	    List<Stock> stocks = new ArrayList<Stock>();
	    List<String> symbols = new ArrayList<String>();

	    try {
	        BufferedReader br = new BufferedReader(new FileReader(symbolsFilename));
	        String line = null;
	        while ( (line = br.readLine())  != null) {
	            Stock stock = YahooFinance.get(line);
	            System.out.println(stock.getName());
	            //stock.print();
            }
	        /*
	        BigDecimal price = stock.getQuote().getPrice();
	        BigDecimal change = stock.getQuote().getChangeInPercent();
	        BigDecimal peg = stock.getStats().getPeg();
	        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
            */
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException...");
        } catch (IOException e) {
            System.out.println("IOException...");
        }
	}
}
