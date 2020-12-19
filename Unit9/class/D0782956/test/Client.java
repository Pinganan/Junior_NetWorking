import java.net.*;
import java.io.*;
import java.awt.*;

class Client {
    public static void main(String args[]) throws UnknownHostException, SocketException, java.io.IOException {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        GUI g = new GUI("Client", (int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2, (int) screenSize.getHeight() / 2, (int) screenSize.getWidth() / 2);

        int port = 8765;
        String addr = "226.255.255.255";
        InetAddress group = InetAddress.getByName(addr);

        MulticastSocket mc_socket = new MulticastSocket(port);
        mc_socket.setSoTimeout(1000);
        mc_socket.joinGroup(group);
        System.out.println("Join multicast group " + addr + " ......");
        g.setResponse("Join multicast group " + addr + " ...... \n");

        byte[] data = new byte[1000];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        while(true) {
            try {
                System.out.println("Wait for receiving ...");
                g.setResponse("Wait for receiving ... \n");
                mc_socket.receive(packet);
                String inMsg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Receive: " + inMsg);
                g.setResponse("Receive: " + inMsg + "\n");
            } catch (SocketTimeoutException e) {
                System.out.println("Receive timeout .....");
                g.setResponse("Receive timeout .....\n");
            }
        }
    }
}