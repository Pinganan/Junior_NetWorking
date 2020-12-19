#*******************************************************************
#  Network Programming - Unit 10 Socket Programming in Python         
#  Program Name: 2-UDPServer.py                                      			
#  The program creates a UDP socket and waits for request.            		
#  2020.08.25                                                   									
#*******************************************************************
import socket

PORT = 8888

def main():
	# Create a UDP Server socket
	dgramSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

	# Bind 	on any incoming interface with PORT, '' is any interface
	dgramSocket.bind(('', PORT))

	count = 0
	while 1:	
		# Receive client message, buffer size = 1024
		client_msg, (rip, rport) = dgramSocket.recvfrom(1024)
		count += 1
		msg = "Receive " + str(count) + "th messgae: " + client_msg.decode('utf-8') + ",from IP: " + str(rip) + " port: " + str(rport)
		print(msg)
	
	# Close the UDP socket
	dgramSocket.close()
# end of main

if __name__ == '__main__':
	main()
