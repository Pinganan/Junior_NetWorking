//*******************************************************************
//*  Network Programming - Unit 2 Simple Client and Server          *
//*  Program Name: SimpleClient1                                     *
//*  The program connects to server and send/receive message.       *
//*  The program gets the server IP from args[0].                   *
//*  2017.08.04                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;

class SimpleClient1
{
	public static void main(String args[])
	{
		Socket			client = null;
		InputStream 	in = null;
		OutputStream 	out = null;
		int				port = 6666;
		byte []			buf = new byte[100];

		if(args.length == 0)
		{
		    System.out.println("Usage: java SimpleClient1 server_ip");
		}
		else
		{
			try
			{
			    // Creates a stream socket and connects it to the specified port number 
			    // at the specified IP address.
				client = new Socket(args[0], port);

					// Send message to server
					out = client.getOutputStream();
					String data = "Client hello!!";
					out.write(data.getBytes());

					// Read message from server
					in = client.getInputStream();
					in.read(buf);
					System.out.println("Receive message: " + new String(buf));

					out.close();
					in.close();

				client.close();
			}
			catch(UnknownHostException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}