#*******************************************************************
#  Network Programming - Unit 10 Socket Programming in Python         
#  Program Name: 3-AsynServer.py                                      			
#  The program creates a socket and send message to server.            		
#  2020.08.28                                                   									
#*******************************************************************
import sys
import socket
import select
import queue

PORT = 6666

def main():
	if(len(sys.argv) < 2):
		print("Usage python3 3-AsynServer.py port1 port2 ...")
		exit(1)

	inputs = []
	srv_list = []
	outputs = []
		
	for i in range(1, len(sys.argv)):
		port = int(sys.argv[i])
		# Creat a TCP socket
		server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		server.bind(('', port))
		# Set socket non blocking
		server.setblocking(False)
		server.listen(5)
		# Add to list
		inputs.append(server)
		srv_list.append(server)
		print("Listening on port " + str(port))

	print("Waiting connection .....")
	
	while True:
		readable, writable, exceptional = select.select(inputs, outputs, inputs)
		for s in readable:
			if s in srv_list:
				# Accept the incomming connection
				connection, (rip, rport) = s.accept()
				# Set the connection non blocking
				connection.setblocking(False)
				# Add connection to inputs (listen message on the connection)
				inputs.append(connection)
				msg = "Accept connection from " + str(rip) + ", port : " + str(rport)
				print(msg)
			else:
				try:
					data = s.recv(1024)
					if data:
						raddr = s.getpeername()
						laddr = s.getsockname()
						msg = "Receive messgae: " + data.decode('utf-8') + " on :" + str(laddr) + " from : " + str(raddr)
						print(msg)
				
						# Send message to client
						server_reply = "Server Reply!!"
						s.send(server_reply.encode('utf-8'))
						
						# Close connection
						print("Close connection from: ", raddr)
						inputs.remove(s)
						s.close()
				except ConnectionResetError:
					print("Connection reset by peer")
					pass

		for s in exceptional:
			print("Close : ", s)
			inputs.remove(s)
			s.close()
        
# end of main()

if __name__ == '__main__':
	main()