#!/bin/bash 
cd /Users/cuichen/Downloads/d2rq-0.8.1
./generate-mapping -u $1 -p $2 -o first.ttl jdbc:mysql:///$3
./dump-rdf -o aa.nt first.ttl
./generate-mapping -u $4 -p $5 -o second.ttl jdbc:mysql:///$6
./dump-rdf -o bb.nt second.ttl
cd 
#./generate-mapping -u $3 -p $4 -o second.ttl jdbc:mysql:///