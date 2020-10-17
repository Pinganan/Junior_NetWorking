
//*******************************************************************
//*  Network Programming - Unit 2 Simple Client and Server          *
//*  Program Name: SimpleClient2                                     *
//*  The program connects to server and send/receive message.       *
//*  The program gets the server IP from args[0].                   *
//*  2017.08.04                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;

class SimpleClient2 {
	public static void main(String args[]) {
		Socket client = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		int port = 6666;

		if (args.length == 0) {
			System.out.println("Usage: java SimpleClient2 server_ip");
		} else {
			try {
				// Creates a stream socket and connects it to the specified port number
				// at the specified IP address.
				client = new Socket(args[0], port);

				// Send message to server
				out = new DataOutputStream(client.getOutputStream());
				String data = "Client hello!!";
				out.writeUTF(data);

				// Read message from server
				in = new DataInputStream(client.getInputStream());
				String buf = new String(in.readUTF());
				System.out.println("Receive message: " + buf);

				out.close();
				in.close();

				client.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}