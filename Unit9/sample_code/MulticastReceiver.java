//*******************************************************************
//*  Network Programming - Unit 9 Multicast                        *
//*  Program Name: MulticastReceiver                                *
//*  The program receives messages from a multicast group.          *
//*  2014.05.09                                                     *
//*******************************************************************
import java.net.*;

class MulticastReceiver
{
	public static void main(String args[])
		throws UnknownHostException, SocketException, java.io.IOException
	{
		int				port = 8765;
		String			addr = "224.0.0.1";
		InetAddress		group = InetAddress.getByName(addr);
		
		// Create a multicast socket and bind it to a specific port
		MulticastSocket	mc_socket = new MulticastSocket(port);
		// Set to a non-zero timeout, a call to receive() for this DatagramSocket 
		// will block for only this amount of time
		mc_socket.setSoTimeout(1000); // only wait 1 second
		// Join a multicast group
		mc_socket.joinGroup(group);
		System.out.println("Join multicast group " + addr + " ......");
		
		// Prepare buffer for receiving messages
		byte []			data = new byte[1000];
		DatagramPacket	packet = new DatagramPacket(data, data.length);
		
		for(int i = 0 ; i < 100 ; i++)
		{
			try
			{
				// Receive
				System.out.println("Wait for receiving ...");
				mc_socket.receive(packet);
				String			inMsg = new String(packet.getData(), 0, packet.getLength());
				System.out.println("Receive: " + inMsg);
			}
			catch(SocketTimeoutException e)
			{
				System.out.println("Receive timeout .....");
			}
		}
		System.out.println("Leave multicast group " + addr + " ......");
		mc_socket.leaveGroup(group);
		mc_socket.close();
	}
}