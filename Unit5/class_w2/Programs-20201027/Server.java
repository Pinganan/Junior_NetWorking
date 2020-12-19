
import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) throws Exception {
        int port = 8888;
        byte[] buf = new byte[1024];
        int length;

        UDPSocket server = new UDPSocket(port);
        server.serverAccept();
        System.out.println("Accepted!!");
    }

}