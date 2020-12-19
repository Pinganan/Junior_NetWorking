//*******************************************************************
//*  Network Programming - Unit 5 User Datagram Protocol            *
//*  Program Name: UDPServer2                                       *
//*  This program receives 100 UDP messages 						*
//*  2020.08.15                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;

public class UDPServer2
{
	public static void main(String args[]) throws Exception
	{
		int		port = 8888;
		byte []	buf = new byte[1000];
		
		System.out.println("Waiting on port : " + port);
		for(int count = 0 ; count < 100 ; count++)
		{
			// Constructs a DatagramPacket for receiving packets
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			// Constructs a datagram socket and binds it to the specified port 
			DatagramSocket socket = new DatagramSocket(port);
			// Receives a datagram packet from this socket
			socket.receive(packet);
			int recLength = packet.getLength();
			String msg = new String("Receive message '" + new String(buf, 0, recLength) + 
									"' from address : "	+ packet.getAddress() + 
									", port : " + packet.getPort());
		    System.out.println(count + "th message : " + msg);
		    socket.close();
		}
	}
}
