#*******************************************************************
#  Network Programming - Unit 10 Socket Programming in Python         
#  Program Name: 1-TCPClient.py                                      			
#  The program creates a socket and send message to server.            		
#  2020.08.25                                                   									
#*******************************************************************
import sys
import socket

PORT = 6666

def main():
	if(len(sys.argv) < 2):
		print("Usage: python3 1-TCPClient.py ServerIP\n")
		exit(1)

	# Get server IP
	serverIP = socket.gethostbyname(sys.argv[1])
	
	# Create a TCP client socket
	cSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

	# Connect to server
	cSocket.connect((serverIP, PORT))
	
	# Send message to server
	msg = "Client hello!!"
	cSocket.send(msg.encode('utf-8'))
	
	# Receive server reply, buffer size = 1024
	server_reply = cSocket.recv(1024)
	print(server_reply.decode('utf-8'))
	
	# Close the TCP socket
	cSocket.close()

# end of main


if __name__ == '__main__':
	main()
