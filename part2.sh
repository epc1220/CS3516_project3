# list of websites
file="sites.txt"

printf "Domain\tADNS\t#ADNS\t\tWeb\t\t#Web\tMail\t#Mail\n" > part2.csv

# run through all sites
while IFS= read -r line; do
    # get ADNS output
    nslookup -type=NS "$line" > getADNS.txt
    # get web output
    nslookup "www.$line" > getWeb.txt
    # get mail output
    nslookup -type=MX "$line" > getMail.txt

    # run parser
    #java -cp out/production/Project3 Part2 "$line"
    java Part2 "$line"

done < "$file"