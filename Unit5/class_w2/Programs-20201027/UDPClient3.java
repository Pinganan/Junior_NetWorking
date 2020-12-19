//*******************************************************************
//*  Network Programming - Unit 5 User Datagram Protocol            *
//*  Program Name: UDPClient3                                       *
//*  This program is a client based on SAWSocket.                   *
//*  2017.09.05                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;

public class UDPClient3
{
	public static void main(String args[]) throws Exception
	{
		int		port = 8888;
		byte []	buf = new byte[SAWSocket.BufferSize];
		
		if(args.length < 1)
		{
			System.out.println("Usage: java UDPClient1 ip_address");
		}
		else
		{
			InetAddress addr = InetAddress.getByName(args[0]);

			SAWSocket 	client = new SAWSocket(addr, port);
			client.connect();
			System.out.println("Connected!!");
			
			for(int i = 0 ; i < 100 ; i++)
			{
				String msg = new String("Test!! (" + i + ")");
				buf = msg.getBytes(); 
				client.send(buf, msg.length());
				System.out.println("Send : " + msg);
			}
			
			client.close();
		}
	}
}
