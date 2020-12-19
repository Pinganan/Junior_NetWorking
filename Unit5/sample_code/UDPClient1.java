//*******************************************************************
//*  Network Programming - Unit 5 User Datagram Protocol            *
//*  Program Name: UDPClient1                                       *
//*  This program sends one UDP message to server and wait server   *
//*  reply.                                                         *
//*  2020.08.15                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;

public class UDPClient1
{
	public static void main(String args[]) throws Exception
	{
		int		port = 8888;
		byte []	sbuf = new byte[1000];
		byte []	rbuf = new byte[1000];
		
		if(args.length < 1)
		{
			System.out.println("Usage: java UDPClient1 ip_address");
		}
		else
		{
			InetAddress addr = InetAddress.getByName(args[0]);

			// Construct a datagram socket and bind it to any available port
			DatagramSocket socket = new DatagramSocket();
			
			String msg = "Client hello!!";
			// Encode this String into a sequence of bytes and store to buf.
			sbuf = msg.getBytes();
			// Construct a datagram packet for sending packets of length length to 
			// the specified port number on the specified host.
			DatagramPacket packet = new DatagramPacket(sbuf, sbuf.length, addr, port);
			// Send message
			socket.send(packet);
			
			// Construct a DatagramPacket for receiving packet
			DatagramPacket recPacket = new DatagramPacket(rbuf, rbuf.length);
			try
			{
				// Receive a datagram packet from this socket
				socket.receive(recPacket);
				// Process message
				InetAddress senderAddr = recPacket.getAddress();
				int	senderPort = recPacket.getPort();
				int recLength = recPacket.getLength();
				String rdata = new String(recPacket.getData());
				rdata = rdata.substring(0, recLength);
				String reply = new String("Receive message '" + rdata + 
									"' from address : "	+ senderAddr + 
									", port : " + senderPort);
				System.out.println(reply);
			}
			catch(Exception e)
			{
				System.out.println("Receive time out!!");
				e.printStackTrace();
			}
				
			socket.close();
		}
	}
}
