
// Student ID : D0782956
// Name : PingTsuAn

import java.net.*;
import java.io.*;
import java.awt.*;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class server {
    
    public static void main(String args[]) {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        GUI gui = new GUI("server", 0, 0, (int) screenSize.getHeight(), (int) screenSize.getWidth() / 4);
        int port = 6666;
        SSLServerSocketFactory	sslserversocketfactory = null;
		SSLServerSocket 		sslserversocket = null;
        SSLSocket				sc = null;
        
        try {
            sslserversocketfactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sslserversocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(port);

            try {
                while (true) {
                    sc = (SSLSocket) sslserversocket.accept();
                    // Use default cipher suite
                    sc.setEnabledCipherSuites(sc.getSupportedCipherSuites());

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
