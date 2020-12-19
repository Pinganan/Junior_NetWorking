
import java.net.*;
import java.io.*;

public class Client {

    public static void main(String args[]) throws Exception {
        int port = 8888;
        byte[] buf = new byte[SAWSocket.BufferSize];
        InetAddress addr = InetAddress.getByName("127.0.0.1");

        UDPSocket client = new UDPSocket(addr, port);
        client.clientConnect();
        System.out.println("Connected!!");

        for (int i = 0; i < 100; i++) {
            String msg = new String("Test!! (" + i + ")");
            buf = msg.getBytes();
            client.socketSend(buf, msg.length());
            System.out.println("Send : " + msg);
            System.out.println();
        }
        client.close();
    }

}