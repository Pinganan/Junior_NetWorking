//*******************************************************************
//*  Network Programming - Unit 6 User Datagram Protocol            *
//*  Program Name: UDPClient1                                       *
//*  This program sends 100 UDP messages to server.                 *
//*  2016.02.04                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;

public class UDPClient2
{
	public static void main(String args[]) throws Exception
	{
		int		port = 8888;
		byte []	buf = new byte[1000];
		
		if(args.length < 1)
		{
			System.out.println("Usage: java UDPClient2 ip_address");
		}
		else
		{
			InetAddress addr = InetAddress.getByName(args[0]);
			for(int count = 0 ; count < 100 ; count++)
			{
				String msg = new String("Send the " + count + "th message.");
				// Encodes this String into a sequence of bytes and stores to buf.
				buf = msg.getBytes();
				// Constructs a datagram packet for sending packets of length length to 
				// the specified port number on the specified host.
				DatagramPacket packet = new DatagramPacket(buf, buf.length, addr, port);
				// Constructs a datagram socket and binds it to any available port on 
				// the local host machine.
				DatagramSocket socket = new DatagramSocket();
				socket.send(packet);
				socket.close();

		    	try
		    	{
		    		Thread.sleep(100);
		    	}
		    	catch (InterruptedException e) { }
			}
		}
	}
}
