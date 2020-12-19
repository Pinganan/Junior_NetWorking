import java.net.*;  
import java.io.*;
import java.awt.*;

class Server {
    public static void main(String args[]) throws UnknownHostException, SocketException, java.io.IOException {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        GUI g = new GUI("Server", 0, 0, (int) screenSize.getHeight() / 2, (int) screenSize.getWidth() / 2);

        System.out.println("guvs");

        int port = 8765;
        String addr = "225.255.255.255";
        InetAddress group = InetAddress.getByName(addr);

        MulticastSocket mc_socket = new MulticastSocket(port);
        mc_socket.joinGroup(group);
        System.out.println("Join multicast group " + addr + " ......");
        g.setResponse("Join multicast group " + addr + " ...... \n");

        // send loop
        int count = 0;
        while (true) {
            String outMsg = "Multicast message (" + count + ")";
            byte[] data = outMsg.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, group, port);
            System.out.println("Send: " + outMsg);
            g.setResponse("Send: " + outMsg + "\n");
            mc_socket.send(packet);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            count = (count + 1) % 10;
        }
    }
}