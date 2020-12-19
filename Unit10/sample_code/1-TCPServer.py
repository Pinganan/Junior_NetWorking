#*******************************************************************
#  Network Programming - Unit 10 Socket Programming in Python         
#  Program Name: 1-TCPServer.py                                      			
#  The program creates a socket and waits for request.            		
#  2020.08.25                                                   									
#*******************************************************************
import socket

PORT = 6666

def main():
	# Create a TCP Server socket
	srvSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

	# Bind 	on any incoming interface with PORT, '' is any interface
	srvSocket.bind(('', PORT))
	# Listen incomming connection, connection number = 5
	srvSocket.listen(5)
	print("Listing at :", srvSocket.getsockname())
	# Accept the incomming connection
	srvsc, (rip, rport) = srvSocket.accept()
	
	# Receive client message, buffer size = 1024
	client_msg = srvsc.recv(1024)
	msg = "Receive messgae: " + client_msg.decode('utf-8') + ",from IP: " + str(rip) + " port: " + str(rport)
	print(msg)
	
	# Send message to client
	server_reply = "Server Reply!!"
	srvsc.send(server_reply.encode('utf-8'))
	
	# Close the TCP socket
	srvSocket.close()
# end of main

if __name__ == '__main__':
	main()
