import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NewSeparateur {
    public static void main(String[] args) {
        try {
        BufferedReader br = new BufferedReader(new FileReader("output"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("output2"));
        String line = null;
        while ( (line = br.readLine() ) != null) {
            String[] parts = line.split(" ");
            bw.write(parts[0], 0, parts[0].length());
            bw.write(" ", 0, 1);
            String finalValue = parts[3].replace("(", "");
            finalValue = finalValue.replace(")", "");
            bw.write(finalValue, 0, finalValue.length());
            bw.newLine();
        }
        br.close();
        bw.close();
        } catch (IOException e) {
            System.out.println("IOException...");
        }
    }
}
