# use to find IP
nslookup web.mit.edu
# use to find ADNS servers
nslookup -type=NS mit.edu
# use to find mail servers
nslookup -type=MX mit.edu
# use to reverse-lookup IP
nslookup -type=ptr 184.27.2.27