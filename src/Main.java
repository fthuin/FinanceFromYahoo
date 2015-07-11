import yahoofinance.*;
import yahoofinance.histquotes.*;
import java.math.BigDecimal;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
//import java.lang.reflect.Field;

public class Main {
    public static final String symbolsFilename = "src/symbols.txt";
    public static final String outputFile = "src/output";

    private static final String CURRENCY = "Currency : ";
    private static final String DIVIDEND = "Dividend : ";
    private static final String HISTORY = "History : ";
    private static final String NAME = "Name : ";
    private static final String QUOTE = "Quote : ";
    private static final String STATISTICS = "Statistics : ";
    private static final String STOCK_EXCHANGE = "Stock exchange : ";
    private static final String SYMBOL = "Symbol : ";

    private static final Calendar DEFAULT_FROM = Calendar.getInstance();

	public static void main(String[] args) {
	    List<Stock> stocks = new ArrayList<Stock>();
	    List<String> symbols = new ArrayList<String>();

	    try {
	        BufferedReader br = new BufferedReader(new FileReader(symbolsFilename));
	        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
	        String line = null;
	        while ( (line = br.readLine())  != null) {
	            Stock stock = YahooFinance.get(line);
	            bw.write(stock.toString(), 0, stock.toString().length());
	            bw.newLine();
	            bw.write(CURRENCY, 0, CURRENCY.length());
	            bw.write(stock.getCurrency(), 0, stock.getCurrency().length());
	            bw.newLine();
	            bw.write(HISTORY, 0, HISTORY.length());
	            bw.newLine();
	            
	            DEFAULT_FROM.add(Calendar.YEAR, -3);
                for (HistoricalQuote hq : stock.getHistory(DEFAULT_FROM, Calendar.getInstance(), Interval.DAILY)) {
	                bw.write(hq.toString(), 0, hq.toString().length());
	                bw.newLine();
                }
                bw.newLine();
	            //stock.print();
            }
            br.close();
            bw.close();
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
