import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Part4 {

    private int TTL = 0;


    public Part4() {
        parseTTL();
    }

    /**
     * parse TTL from lookup
     */
    private void parseTTL() {
        try (BufferedReader FR = new BufferedReader(new FileReader("TTL.txt"))) {
            String line;
            while ((line = FR.readLine()) != null) {
                if (line.contains("ttl =")) {
                    TTL = Integer.parseInt(line.substring(line.indexOf("=") + 2));
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void writeToTable() {
        try (FileWriter write2Table = new FileWriter("part4.csv", true)) {
            write2Table.write(String.format("%d, ", TTL));
        } catch (IOException e) {
            System.err.println("Error outputting data to csv " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Part4 dns = new Part4();
        dns.writeToTable();
    }
}