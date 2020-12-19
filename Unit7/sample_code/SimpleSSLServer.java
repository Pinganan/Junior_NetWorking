//*******************************************************************
//*  Network Programming - Unit 7 Simple SSL Client and Server      *
//*  Program Name: SimpleSSLServer                                  *
//*  The program creates a secure socket and waits for request.     *
//*  2016.02.02                                                     *
//*******************************************************************
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class SimpleSSLServer
{
	public static void main(String args[])
	{
		SSLServerSocketFactory	sslserversocketfactory = null;
		SSLServerSocket 		sslserversocket = null;
		SSLSocket				sc = null;
		InputStream		in = null;
		OutputStream	out = null;
		int				port = 9999;
		byte []			buf = new byte[100];
		
		try
		{
		    // Creates a secure server socket, bound to the specified port.
			sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(port);
			
			System.out.println("Waiting for request ...");
			try
			{
			    // Listens for a connection to be made to this socket and accepts it.
				sc = (SSLSocket) sslserversocket.accept();
				// Use default cipher suite
				sc.setEnabledCipherSuites(sc.getSupportedCipherSuites());
				
					// Read message from client
					// Returns an input stream for socket sc.
				    in = sc.getInputStream();
				    // Reads some number of bytes from the input stream and stores them into buf.
				    in.read(buf);
				    System.out.println("Receive message: " + new String(buf));
				    
				    // Send reply message to client
				    // Returns an output stream for socket sc.
				    out = sc.getOutputStream();
				    String data = "SSL Server reply!!";
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
				sslserversocket.close();
			}
		}
		catch(IOException e)
		{
			System.err.println(e);
		}
	}
}
