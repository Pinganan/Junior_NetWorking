import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
public class BS {
    public static void main(String args[]) throws UnknownHostException, SocketException, java.io.IOException {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        GUI g = new GUI("BS", 0, (int) screenSize.getHeight() / 2, (int) screenSize.getHeight() / 2, (int) screenSize.getWidth() / 2);

        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 6666);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int port = 8765;
        String addr = "226.255.255.255";
        InetAddress group = InetAddress.getByName(addr);
        MulticastSocket mc_socket = new MulticastSocket(port);
        mc_socket.joinGroup(group);
        System.out.println("Join multicast group " + addr + " ......");
        g.setResponse("Join multicast group " + addr + " ...... \n");

        while (true) {
            try {
                DataInputStream in = new DataInputStream(client.getInputStream());
                while (true) {
                    String str = in.readUTF();
                    System.out.println("Receive message: " + str + "\n");
                    g.setResponse("Receive message: " + str + " ...... \n");

                    byte[] data = str.getBytes();
                    DatagramPacket packet = new DatagramPacket(data, data.length, group, port);
                    System.out.println("Send: " + str);
                    g.setResponse("Join multicast group " + addr + " ...... \n");
                    g.setResponse("Join multicast group " + addr + " ...... \n");
                    mc_socket.send(packet);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                    }
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }
}
