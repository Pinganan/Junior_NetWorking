
import javax.swing.*;
import java.net.*;
import java.io.*;

public class UDPClient1 {
	public static void main(String args[]) throws Exception {
		InetAddress addr = InetAddress.getByName(JOptionPane.showInputDialog("give IP address", "localhost"));
		int amount = Integer.parseInt(JOptionPane.showInputDialog("give positive amount for client", "12"));
		int parameter = Integer.parseInt(JOptionPane.showInputDialog("give large positive integer for decrease", "5"));
		int time = Integer.parseInt(JOptionPane.showInputDialog("limited time", "10"));
		
		for(int no=1 ; no<=amount ; no++) {
			DatagramSocket socket = new DatagramSocket();
			socket.setSoTimeout(time);
			Route r = new Route(socket, addr, amount, no, parameter);
			r.start();
		}
	}
}
