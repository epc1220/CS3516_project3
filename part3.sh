file="LDNSs.txt"
printf "Checking LDNSs:\n" > LDNS_out.txt

printf "Server,\tIP,\tRTT\n" > part3.csv
# run through all sites
while IFS= read -r line; do

    # get server name
    nslookup -type=ptr "$line" > LDNS_lookup.txt
    # get RTT
    dig @"$line" www.wpi.edu +norecurse > LDNS_dig.txt
    # run parser
    java -cp out/production/Project3 Part3 "$line"

done < "$file"