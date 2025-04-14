# list of website
sites=("wpi.edu" "ucla.edu")

# go to out directory of java project
#cd Parser/out/production/Parser

# run through all sites
for str in "${sites[@]}";
do
    # get ADNS output
    nslookup -type=NS "$str" > getADNS.txt
    # get web output
    nslookup "www.$str" > getWeb.txt
    # get mail output
    nslookup -type=MX "$str" > getMail.txt

    # run parser
    #java "Part2" $str

    #rm Parser/getADNS.txt
    #rm Parser/getWeb.txt
    #rm Parser/getMail.txt
done