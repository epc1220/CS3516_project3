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
        ADNS = web = mail = "";
        numADNS = numWeb = numMail = 0;
        parseADNS();
        parseWeb();
        parseMail();
    }


    /**
     * parses the ADNS file to get number of ADNS servers and the name of the server
     */
    private void parseADNS() {
        try (BufferedReader FR = new BufferedReader(new FileReader("getADNS.txt"))) {
            String line;
            while ((line = FR.readLine()) != null) {
                if (line.startsWith(domain)) {
                    if (ADNS.isEmpty()) {
                        // splits the line into subdomains: eg. [ns2, dns, ucla, edu]
                        String[] subdomains = line.substring(line.indexOf("=") + 2,line.length()-1).split("\\.");
                        // get last 2 subdomains
                        ADNS = subdomains[subdomains.length-2] + "." + subdomains[subdomains.length-1];
                    }
                    numADNS++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Parses the web file to get number of web servers and the name of the server
     */
    private void parseWeb() {
        try (BufferedReader FR = new BufferedReader(new FileReader("getWeb.txt"))) {
            String line;
            while ((line = FR.readLine()) != null) {
                if (line.startsWith("Name:") && web.isEmpty()) {
                    String[] subdomains = line.substring(line.indexOf("\t") + 1).split("\\.");
                    web = subdomains[subdomains.length-2] + "." + subdomains[subdomains.length-1];
                }
                else if (line.startsWith("Address:") && line.contains(".") && !web.isEmpty()) {
                    numWeb++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Parses the mail file to get number of mail servers and the name of the server
     */
    private void parseMail() {
        try (BufferedReader FR = new BufferedReader(new FileReader("getMail.txt"))) {
            String line;
            while ((line = FR.readLine()) != null) {
                if (line.startsWith(domain)) {
                    if (mail.isEmpty()) {
                        // splits the line into subdomains: eg. [ns2, dns, ucla, edu]
                        String[] subdomains = line.substring(line.indexOf("=") + 2,line.length()-1).split("\\.");
                        // get last 2 subdomains
                        mail = subdomains[subdomains.length-2] + "." + subdomains[subdomains.length-1];
                    }
                    numMail++;
                }
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