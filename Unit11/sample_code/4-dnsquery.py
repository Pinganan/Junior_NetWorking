import dns.resolver

def main(): 
	loookup_continue = True 
	while loookup_continue:
		name = input('Enter the DNS name to resolve: ') 
		record_type = input('Enter the query type [A/MX/CNAME]: ')
		try:
			#answers = dns.resolver.query(name, record_type)
			answers = dns.resolver.resolve(name, record_type) 
			if record_type == 'A':
				print('Got answer IP address: %s' %[x.to_text() for x in answers])
			elif record_type == 'CNAME':
				print('Got answer Aliases: %s' %[x.to_text() for x in answers])
			elif record_type == 'MX':
               			for rdata in answers:
                  			print('Got answers for Mail server records:')
                  			print('Mailserver', rdata.exchange.to_text(), 'has preference', rdata.preference)
			else: 
				print('Record type: %s is not implemented' %record_type)
		except dns.resolver.NoAnswer:
			print('The DNS response does not contain an answer to the question: ' + name + ' IN ' + record_type)
		except dns.rdatatype.UnknownRdatatype:
			print('DNS resource record type is unknown.');
		lookup_more = input("Do you want to lookup more records? [y/n]: " )
		if lookup_more.lower() == 'n':
			loookup_continue = False
#end of main
if __name__ == '__main__':
	main()