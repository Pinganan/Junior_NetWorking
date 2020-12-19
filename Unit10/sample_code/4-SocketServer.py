#*******************************************************************
#  Network Programming - Unit 10 Socket Programming in Python         
#  Program Name: 4-SocketServer.py                                      			
#  The program creates a TCP Server and waits for request.            		
#  2020.08.29                                                  									
#*******************************************************************
import socketserver

PORT = 6666

class SrvHandler(socketserver.BaseRequestHandler):
	def handle(self):
		msg = "Connected from : " + str(self.client_address)
		print(msg)

		# Receive client message, buffer size = 1024
		data = self.request.recv(1024)

		msg = "Receive messgae: " + data.decode('utf-8')
		print(msg)
			
		# Send message to client
		server_reply = "Server Reply!!"
		self.request.send(server_reply.encode('utf-8'))

def main():
	myaddr = ('', PORT)
	myserver = socketserver.TCPServer(myaddr, SrvHandler)
	print("Waiting for request .....")
	myserver.serve_forever()
# end of main

if __name__ == '__main__':
	main()
