LDNSs="LDNSs.txt"
sites="sitesP4.txt"

printf "Checking LDNSs:\n" > LDNS_out.txt

echo "" > part4.csv
while IFS= read -r LDNS; do
  printf "%s,\t" "$LDNS" >> part4.csv
done < "$LDNSs"
printf "\n" >> part4.csv

# run through all sites
while IFS= read -r site; do
  printf "%s," "$site" >> part4.csv
  while IFS= read -r LDNS; do
    # get server name
    nslookup -debug -type=ptr "$site $LDNS" > TTL.txt
    java -cp out/production/Project3 Part4
  done < "$LDNSs"
  printf "\n" >> part4.csv
done < "$sites"