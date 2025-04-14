import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Part2 {

    private final String domain;
    private String ADNS, web, mail;
    private int numADNS, numWeb, numMail;


    public Part2(String domain) {
        this.domain = domain;
        parseADNS();
        parseWeb();
        parseMail();
    }


    private void parseADNS() {
        try (BufferedReader FR = new BufferedReader(new FileReader("getADNS.txt"))) {
            String line;
            while ((line = FR.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void parseWeb() {
        try (BufferedReader FR = new BufferedReader(new FileReader("getWeb.txt"))) {
            String line;
            while ((line = FR.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void parseMail() {
        try (BufferedReader FR = new BufferedReader(new FileReader("getMail.txt"))) {
            String line;
            while ((line = FR.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    private void writeToTable() {
        try (FileWriter write2Table = new FileWriter("part2.csv", true)) {
            write2Table.write(String.format("%s, %s, %d, %s, %d, %s, %d\n", domain, ADNS, numADNS, web, numWeb, mail, numMail));
        } catch (IOException e) {
            System.err.println("Error outputting data to csv " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Part2 dns = new Part2(args.length == 1 ? args[0] : "wpi.edu");
        dns.writeToTable();
    }
}