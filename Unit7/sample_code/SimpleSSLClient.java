//*******************************************************************
//*  Network Programming - Unit 7 Simple SSL Client and Server      *
//*  Program Name: SimpleSSLClient                                  *
//*  The program connects to server and send/receive message.       *
//*  The program gets the server IP from args[0].                   *
//*  2016.02.02                                                     *
//*******************************************************************
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

class SimpleSSLClient
{
	public static void main(String args[])
	{
		SSLSocketFactory	sslsocketfactory = null;
		SSLSocket			client = null;
		InputStream 	in = null;
		OutputStream 	out = null;
		int				port = 9999;
		byte []			buf = new byte[100];

		if(args.length == 0)
		{
		    System.out.println("Usage: java SimpleSSLClient server_ip");
		}
		else
		{
			try
			{
			    // Creates a stream socket and connects it to the specified port number 
			    // at the specified IP address.
				sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
				client = (SSLSocket) sslsocketfactory.createSocket(args[0], port);
				// Use default cipher suite
				client.setEnabledCipherSuites(client.getSupportedCipherSuites());

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
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}