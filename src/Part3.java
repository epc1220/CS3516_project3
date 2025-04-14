import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Part3 {

    private final String IP;
    private String name;
    private int RTT = 0;


    public Part3(String IP) {
        this.IP = IP;
        parseName();
        parseLDNS();
    }


    /**
     * parse LDNS_lookup to get the name of the server
     */
    private void parseName() {
        try (BufferedReader FR = new BufferedReader(new FileReader("LDNS_lookup.txt"))) {
            String line;
            while ((line = FR.readLine()) != null) {
                if (line.contains("name =")) {
                    // splits the line into subdomains: eg. [ns2, dns, ucla, edu]
                    String[] subdomains = line.substring(line.indexOf("=") + 2,line.length()-1).split("\\.");
                    // get last 2 subdomains
                    name = subdomains[subdomains.length-2] + "." + subdomains[subdomains.length-1];
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * parses the LDNS_dig to find the RTT
     */
    private void parseLDNS() {
        try (BufferedReader FR = new BufferedReader(new FileReader("LDNS_dig.txt"))) {
            String line;
            while ((line = FR.readLine()) != null) {
                if (line.startsWith(";; Query time:")) {
                    // splits the line into subdomains: eg. [ns2, dns, ucla, edu]
                    String[] subdomains = line.substring(line.indexOf(":") + 2,line.length()-1).split(" ");
                    // get last 2 subdomains
                    RTT = Integer.parseInt(subdomains[0]);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    private void writeToTable() {
        try (FileWriter write2Table = new FileWriter("part3.csv", true)) {
            write2Table.write(String.format("%s, %s, %d\n", name, IP, RTT));
        } catch (IOException e) {
            System.err.println("Error outputting data to csv " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Part3 dns = new Part3(args.length == 1 ? args[0] : "8.8.8.8");
        dns.writeToTable();
    }
}