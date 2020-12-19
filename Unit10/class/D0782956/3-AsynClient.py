#*******************************************************************
#  Network Programming - Unit 10 Socket Programming in Python         
#  Program Name: 3-AsynClient.py                                      			
#  The program creates a socket and send message to server.            		
#  2020.08.28                                                   									
#*******************************************************************
import sys
import socket
import time

def main():
	if(len(sys.argv) < 3):
		print("Usage: python3 1-TCPClient.py ServerIP port\n")
		exit(1)

	# Get server IP
	serverIP = socket.gethostbyname(sys.argv[1])
	port = int(sys.argv[2])
	
	# Create a TCP client socket
	cSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

	# Connect to server
	cSocket.connect((serverIP, port))
	i=0
	if port == 8880 :
		while i < 10 :
			# Send message to server
			msg = str(i) + " Client hello!!"
			cSocket.send(msg.encode('utf-8'))
			# Receive server reply, buffer size = 1024
			server_reply = cSocket.recv(1024)
			print(server_reply.decode('utf-8'))
			time.sleep(1)
			i = (i+1)%10
	else :
		while True :
			msg = "request"
			cSocket.send(msg.encode('utf-8'))
			server_reply = cSocket.recv(1024)
			print(server_reply.decode('utf-8'))
			time.sleep(2)

# end of main

if __name__ == '__main__':
	main()
