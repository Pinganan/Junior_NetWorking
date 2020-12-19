import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;

public class BRThread extends Thread {
    Socket sc = null;
    ArrayList<Socket> scarr = new ArrayList<>();
    ServerSocket serverSocket = null;
    int port = 6666;

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            try {
                while (true) {
                    System.out.println("Waiting for request ...\n");
                    sc = serverSocket.accept();
                    if (sc != null) {
                        System.out.println("BR accept");
                        scarr.add(sc);
                    }
                    sc = null;
                }
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}