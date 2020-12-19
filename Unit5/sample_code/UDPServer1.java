//*******************************************************************
//*  Network Programming - Unit 5 User Datagram Protocol            *
//*  Program Name: UDPServer1                                       *
//*  This program receives a UDP message and reply a message to     *
//*  client.                                                        *
//*  2020.08.15                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;

public class UDPServer1
{
	public static void main(String args[]) throws Exception
	{
		int		port = 8888;
		byte []	sbuf = new byte[1000];
		byte []	rbuf = new byte[1000];
		
		// Construct a datagram socket and bind it to the specified port 
		DatagramSocket socket = new DatagramSocket(port);

		System.out.println("Waiting on port : " + port);

		// Construct a DatagramPacket for receiving packets
		DatagramPacket recPacket = new DatagramPacket(rbuf, rbuf.length);
		// Receive a datagram packet from this socket
		socket.receive(recPacket);
		// Process message
		InetAddress senderAddr = recPacket.getAddress();
		int	senderPort = recPacket.getPort();
		int	recLength = recPacket.getLength();
		String rdata = new String(recPacket.getData());
		rdata = rdata.substring(0, recLength);
		String msg = new String("Receive message '" + rdata + 
									"' from address : "	+ senderAddr + 
									", port : " + senderPort);
		System.out.println(msg);
		
		// Prepare reply message
		String  reply = "Server reply";
		// Encode this String into a sequence of bytes and store to buf.
		sbuf = reply.getBytes();
		// Construct a DatagramPacket for reply message
		DatagramPacket replyPacket = new DatagramPacket(sbuf, sbuf.length, senderAddr, senderPort);
		// Send message
		socket.send(replyPacket);
		
		socket.close();
	}
}
