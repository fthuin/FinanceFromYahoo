import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class GNUplotGraph {

    private Stock stock;
    private Calendar DEFAULT_FROM = Calendar.getInstance();
    private Calendar DEFAULT_TO = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String dataFile;

    public GNUplotGraph(String symbol, int year, int month, int day, Interval interval) {
        DEFAULT_FROM.add(Calendar.YEAR, 0-year);
        DEFAULT_FROM.add(Calendar.MONTH, 0-month);
        DEFAULT_FROM.add(Calendar.DAY_OF_YEAR, 0-day);
        try {
            this.stock = YahooFinance.get(symbol);
            dataFile = "tmp/"+symbol+".dat";
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
            for (HistoricalQuote hq : this.stock.getHistory(DEFAULT_FROM, DEFAULT_TO, interval)) {
                String dateStr = getDateStr(hq.getDate().getTime());
                bw.write(dateStr, 0, dateStr.length());
                bw.write(" ", 0, 1);
                bw.write(hq.getAdjClose().toString(), 0, hq.getAdjClose().toString().length());
                bw.newLine();

            }
            bw.close();
        } catch (IOException e) {
            System.out.println("GNUplotGraph Constructor - IOException");
        }
    }
    
    private String getDateStr(Date date) {
        return dateFormat.format(date);
    }
    
    public void writeGNUplotMacro() {
        String name = stock.getName();
        String symbol = stock.getSymbol();
        String dateFrom = getDateStr(DEFAULT_FROM.getTime());
        String dateTo = getDateStr(DEFAULT_TO.getTime());
        long dateSeparation = ((DEFAULT_TO.getTimeInMillis() - DEFAULT_FROM.getTimeInMillis()) / 1000) / 5;
        String intro = "#!/usr/bin/gnuplot\n" +
            "#\n" +
            "#Plot of (" + symbol + ") " + name +"\n" +
            "#From " + dateFrom + " to " + dateTo + "\n" +
            "set output \"gnuplot_"+ symbol +".ps\"\n" +
            "set terminal postscript portrait\n" +
            "set xlabel \"Date\"\n"+
            "set ylabel \"Valeur\"\n"+
            "set xdata time\n"+
            "set timefmt '%Y-%m-%d'\n"+
            "set format x \"%Y-%m-%d\"\n"+
            "# Activate the grid\n" +
            "set grid\n"+
            "set xtics rotate -45\n"+
            "set xtics format \"%d/%m/%Y\"\n"+
            "set xtics \""+ dateFrom +"\", "+ dateSeparation +", \"" + dateTo + "\"\n"+
            "plot '"+symbol+".dat' using 1:2:(0.00001) smooth unique title \""+ name + " ("+ symbol+ ")\"";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tmp/"+symbol+".gnu"));
            bw.write(intro, 0, intro.length());
            bw.close();
        } catch (IOException e) {
            System.out.println("GNUplotGraph writeGNUplotMacro - IOException ");
        }
    }
}
