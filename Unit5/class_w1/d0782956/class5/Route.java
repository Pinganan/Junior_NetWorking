
import java.net.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Parameter;

public class Route extends Thread {
    private int dstPort;
    private InetAddress dstAddress;
    private DatagramPacket recPacket = null;
    private DatagramPacket sendPacket = null;
    private DatagramSocket socket = null;
    private GUI gui = null;
    private int clientSever;
    private int no = 0;
    int p;
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

    public Route(DatagramSocket socket) {
        this.socket = socket;
        gui = new GUI("server", 0, 0, (int) screenSize.getHeight(), (int) screenSize.getWidth() / 4);
        this.clientSever = 1;
    }

    public Route(DatagramSocket socket, InetAddress dstAddress, int total, int no, int parameter) {
        this.dstPort = 8888;
        this.p = parameter;
        this.socket = socket;
        this.sendPacket = new DatagramPacket(intToByteArray(parameter), intToByteArray(parameter).length, dstAddress,
                dstPort);
        this.clientSever = 0;
        this.no = no;
        int x = (int) screenSize.getWidth() / 4;
        int rowAmount = (int) ((total + 3) / 4);
        int width = (int) (x * 3 / rowAmount);
        int high = (int) screenSize.getHeight() / 4;
        this.gui = new GUI("c " + no, x + ((no - 1) % rowAmount) * width, (no - 1) / rowAmount * high, high, width);

    }

    public static int byteArrayToInt(byte[] b) {
        return b[3] & 255 | (b[2] & 255) << 8 | (b[1] & 255) << 16 | (b[0] & 255) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[] { (byte) ((a >> 24) & 255), (byte) ((a >> 16) & 255), (byte) ((a >> 8) & 255),
                (byte) (a & 255) };
    }

    public void run() {
        int num = p;
        byte[] buf = new byte[1000];
        if (clientSever == 0) {
            try {
                gui.setResponse("Send " + byteArrayToInt(sendPacket.getData()) + "\n");
                socket.send(sendPacket);
            } catch (Exception e) {
                System.out.println("Receive time out!!");
                e.printStackTrace();
            }
            do {
                try {
                    recPacket = new DatagramPacket(buf, buf.length);
                    socket.receive(recPacket);
                    dstAddress = recPacket.getAddress();
                    dstPort = recPacket.getPort();
                    num = byteArrayToInt(recPacket.getData());
                    gui.setResponse("Receive " + num + " from " + dstAddress + " via " + dstPort + "\n");
                    if (num == 0) {
                        break;
                    }
                    sendPacket = new DatagramPacket(intToByteArray(num), intToByteArray(num).length, dstAddress,
                            dstPort);
                    gui.setResponse("Send " + byteArrayToInt(sendPacket.getData()) + "\n");
                    socket.send(sendPacket);
                } catch (Exception exception) {
                    gui.setResponse("Error sent number " + num + " again------------------------------------------------------------------------------------------------\n");
                }
            } while (true);
        } else {
            try {
                while (true) {
                    Thread.sleep(10);
                    recPacket = new DatagramPacket(buf, buf.length);
                    socket.receive(recPacket);
                    dstAddress = recPacket.getAddress();
                    dstPort = recPacket.getPort();
                    num = byteArrayToInt(recPacket.getData());
                    gui.setResponse("Receive " + num + " from " + dstAddress + " via " + dstPort + "\n");
                    num -= clientSever;
                    sendPacket = new DatagramPacket(intToByteArray(num), intToByteArray(num).length, dstAddress,
                            dstPort);
                    socket.send(sendPacket);
                    gui.setResponse("Send " + num + "\n");
                }
            } catch (Exception e) {
                System.out.println("Receive time out!!");
                e.printStackTrace();
            }
        }
    }
}
