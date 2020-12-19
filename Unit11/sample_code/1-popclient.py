import sys
import getpass
import poplib

POP3_SERVER_PORT = '110'

def query_email(pop_server, username, password):
	mailbox = poplib.POP3(pop_server, POP3_SERVER_PORT)
	try:
		mailbox.user(username)
		mailbox.pass_(password)
	except poplib.error_proto:
		print('Authentication failed')
		exit(1)
	num_messages = len(mailbox.list()[1]) 
	print("Total emails: {0}".format(num_messages)) 
	print("Getting last message")
	for msg in mailbox.retr(num_messages)[1]:
		print(msg) 
	mailbox.quit()
# end of query_email
	
def main():
	popserver = input("POP3 Server: ")
	username = input("Enter your email user ID: ")
	password = getpass.getpass(prompt="Enter your email password: ")
	query_email(popserver, username, password)
# end of main

if __name__ == '__main__':
	main()