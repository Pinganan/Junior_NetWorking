
// Student ID : D0782956
// Name : PingTsuAn

import java.net.*;
import java.io.*;
import java.awt.*;

public class server {
    public static void main(String args[]) {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        GUI gui = new GUI("server", 0, 0, (int) screenSize.getHeight(), (int) screenSize.getWidth() / 4);
        ServerSocket serverSocket = null;
        Socket sc = null;
        int port = 6666;
        try {
            serverSocket = new ServerSocket(port);
            try {
                while (true) {
                    sc = serverSocket.accept();
                    if (sc != null) {
                        Route t = new Route("server", sc, false, gui);
                        t.start();
                        sc = null;
                    }
                }
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                sc.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
