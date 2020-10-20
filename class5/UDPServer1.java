
import java.net.*;
import java.io.*;

public class UDPServer1 {
	public static void main(String args[]) throws Exception {
		int port = 8888;
		
		DatagramSocket socket = new DatagramSocket(port);
		Route r = new Route(socket);
		r.start();
	}
}
