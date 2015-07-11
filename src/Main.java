import yahoofinance.*;
import yahoofinance.graphs.*;
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
    public static final String historyInfoFile = "tmp/output";
    public static final String graphInfoFile = "tmp/graphInfo";

    private static final String CURRENCY = "Currency : ";
    private static final String DIVIDEND = "Dividend : ";
    private static final String HISTORY = "History : ";
    private static final String NAME = "Name : ";
    private static final String QUOTE = "Quote : ";
    private static final String STATISTICS = "Statistics : ";
    private static final String STOCK_EXCHANGE = "Stock exchange : ";
    private static final String SYMBOL = "Symbol : ";

    private static final Calendar DEFAULT_FROM = Calendar.getInstance();

    public static void writeInfos() {
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(symbolsFilename));
	        String line = null;
	        while ( (line = br.readLine())  != null) {
	            GNUplotGraph graph = new GNUplotGraph(line, 0, 0, 10, Interval.DAILY);
	            graph.writeGNUplotMacro();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException...");
        } catch (IOException e) {
            System.out.println("IOException...");
        }
    }

	public static void main(String[] args) {
	    writeInfos();
	}
}
