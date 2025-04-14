file="LDNSs.txt"
printf "Checking LDNSs:\n" > LDNS_out.txt
# run through all sites
while IFS= read -r line; do
    # get ADNS output
    nslookup www.wpi.edu "$line" >> LDNS_out.txt

done < "$file"