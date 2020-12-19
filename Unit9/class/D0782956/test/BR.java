import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;

class BR {
    public static void main(String args[]) throws UnknownHostException, SocketException, java.io.IOException {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        GUI g = new GUI("BR", (int) screenSize.getWidth() / 2, 0, (int) screenSize.getHeight() / 2, (int) screenSize.getWidth() / 2);

        int port = 8765;
        String addr = "225.255.255.255";
        InetAddress group = InetAddress.getByName(addr);

        MulticastSocket mc_socket = new MulticastSocket(port);
        mc_socket.setSoTimeout(1000);
        mc_socket.joinGroup(group);
        System.out.println("Join multicast group " + addr + " ......");
        g.setResponse("Join multicast group " + addr + " ...... \n");

        byte[] data = new byte[1000];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        // tcp
        BRThread th = new BRThread();
        th.start();

        while (true) {
            try {
                System.out.println("Wait for receiving ...");
                g.setResponse("Wait for receiving ...\n");
                mc_socket.receive(packet);
                String inMsg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Receive: " + inMsg);
                g.setResponse("Receive: " + inMsg + "\n");
                SendMessage(inMsg, th.scarr);
            } catch (SocketTimeoutException e) {
                System.out.println("Receive timeout .....");
                g.setResponse("Receive timeout .....\n");
            }
        }
    }

    public static void SendMessage(String str, ArrayList<Socket> scarr) {
        System.out.println("BRsend is trigger");
        for (Socket sc : scarr) {
            try {
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
                out.writeUTF(str);
            } catch (IOException e) {
                System.err.println(e);
            } 
        }
    }
}