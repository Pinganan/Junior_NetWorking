//*******************************************************************
//*  Network Programming - Unit 2 Simple Client and Server          *
//*  Program Name: SimpleServer2                                    *
//*  The program creates a socket and waits for request.            *
//*  2017.08.04                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;

public class SimpleServer2
{
	public static void main(String args[])
	{
		ServerSocket	srverSocket = null;
		Socket			sc = null;
		DataInputStream		in = null;
		DataOutputStream	out = null;
		int				port = 6666;
		
		try
		{
		    // Creates a server socket, bound to the specified port.
			srverSocket = new ServerSocket(port);
			
			System.out.println("Waiting for request ...");
			try
			{
			    // Listens for a connection to be made to this socket and accepts it.
				sc = srverSocket.accept();
					// Read message from client
					// Returns an input stream for socket sc.
				    in = new DataInputStream(sc.getInputStream());
				    String buf = new String(in.readUTF());
				    System.out.println("Receive message: " + buf);
				    
				    // Send reply message to client
				    // Returns an output stream for socket sc.
				    out = new DataOutputStream(sc.getOutputStream());
				    String data = "Server reply!!";
				    out.writeUTF(data);
				    
				    // Closes in/out stream and releases any system resources associated with this stream.
				    in.close();
				    out.close();
				//Closes this socket
				sc.close();
			}
			catch(IOException e)
			{
				System.err.println(e);
			}
			finally
			{
				srverSocket.close();
			}
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
	}
}
