//*******************************************************************
//*  Network Programming - Unit 8 Non-blocking Socket               *
//*  Program Name: MultiPortServer                                  *
//*  The program creates a server that registers on multiple ports. *
//*     Usage: java MultiPortServer port1 [port2 port3 ...]       *
//*  2016.02.04                                                     *
//*******************************************************************
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class MultiPortServer
{
	public static void main(String args[]) throws Exception 
	{
		Selector 	selector = Selector.open();   	// Create a selector

    	if (args.length <= 0)
    	{
			System.err.println("Usage: java MultiPortServer port1 [port2 port3 ...]");
			System.exit(1);
		}
		
		// Create non-blocking sockets on each port 
		// and register each socket channel with selector
		for (int i = 0; i < args.length ; i++)
		{
			int port = Integer.parseInt(args[i]);	// we don't handle the format error

			// Create a server socket channel and bind it to the specified port
			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false); // Config the channel to non-blocking mode
			ServerSocket ss = ssc.socket();
			ss.bind(new InetSocketAddress(port));

			// Register the socket channel with selector
			// and listen on the SOCKET-ACCEPT event
			SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);			
			
			System.out.println("Listening on " + port + " port...");
		}
		
		// The main loop waiting for events
		while(true)
		{
		    // The following method blocks until 
		    // at least one of the registered events occurs.
			int num = selector.select();
			
			// Returns a Set of the SelectionKey objects 
			// for which events have occurred
			Set selectedKeys = selector.selectedKeys();
			// Organize the elements in set into iterator
			Iterator element = selectedKeys.iterator(); 
			
			while(element.hasNext()) // Handle each event in the set
			{
				// Returns the next element in the iteration.
				SelectionKey key = (SelectionKey)element.next();
				
					if(key.isAcceptable()) // Accept the new connection
					{
						// Return the ServerSocketChannel
						ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
						SocketChannel sc = ssc.accept();
						sc.configureBlocking(false); // Config the channel to non-blocking mode
						
						// Register the new connection with the selector
						// and listen on the SOCKET-READ event
						SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
						
						System.out.println("Got connection from " + sc);
					}
					else if(key.isReadable())
					{
						// Return the SocketChannel
						SocketChannel sc = (SocketChannel)key.channel();
					    try
					    {
							// Receive message
							ByteBuffer 	b = ByteBuffer.allocate(1000); 
							int			len = sc.read(b);	// read message from sc
							if(len > 0)
							{
								System.out.println("Receive message : "	+ new String(b.array(), 0, len) +
				    			" from " + sc);

								// Send message to client
								String data = "Server hello!! (" + sc + ")";
								ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());
								sc.write(buffer);
							}
						}
						catch(IOException e)
						{
							System.out.println("Connection reset by peer :" + sc);
							sc.close();
						}
					}
				
				// Remove the element from the iteration
				element.remove();
			} // end of handling each event
		} // end of main loop
	}
}
