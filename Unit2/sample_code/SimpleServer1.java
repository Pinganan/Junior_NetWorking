//*******************************************************************
//*  Network Programming - Unit 2 Simple Client and Server          *
//*  Program Name: SimpleServer1                                     *
//*  The program creates a socket and waits for request.            *
//*  2017.08.04                                                     *
//*******************************************************************
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class SimpleServer1
{
	public static void main(String args[])
	{
		ServerSocket	srverSocket = null;
		Socket			sc = null;
		InputStream		in = null;
		OutputStream	out = null;
		int				port = 6666;
		byte []			buf = new byte[100];
		
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
				    in = sc.getInputStream();
				    // Reads some number of bytes from the input stream and stores them into buf.
				    in.read(buf);
				    System.out.println("Receive message: " + new String(buf));
				    
				    // Send reply message to client
				    // Returns an output stream for socket sc.
				    out = sc.getOutputStream();
				    String data = "Server reply!!";
				    // Writes bytes from the specified byte array to this output stream.
				    out.write(data.getBytes());
				    // getBytes(): Encodes this String into a sequence of bytes using the 
				    // platform's default charset, storing the result into a new byte array.
				    
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
