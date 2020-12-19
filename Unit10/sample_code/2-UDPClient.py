import sys
#*******************************************************************
#  Network Programming - Unit 10 Socket Programming in Python         
#  Program Name: 2-UDPClient.py                                      			
#  The program creates a socket and send message to server.            		
#  2020.08.25                                                   									
#*******************************************************************
import socket

PORT = 8888

def main():
	if(len(sys.argv) < 2):
		print("Usage: python3 1-TCPClient.py ServerIP\n")
		exit(1)

	# Get server IP
	serverIP = socket.gethostbyname(sys.argv[1])
	
	# Create a UDP client socket
	dgramSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

	for i in range(100):
		msg = "The " + str(i )+ "th message from client"
		dgramSocket.sendto(msg.encode('utf-8'), (serverIP, PORT))
		print("Send message: " + msg)
	
	# Close the TCP socket
	dgramSocket.close()

# end of main


if __name__ == '__main__':
	main()
