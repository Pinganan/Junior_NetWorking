
// Student ID : D0782956
// Name : PingTsuAn

import java.net.*;
import java.awt.*;
import java.io.*;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

public class Route extends Thread {
    private GUI gui = null;
    private String TheardName;
    private boolean clientSever;
    private Socket socket = null;
    private InputStream in = null;
    private OutputStream out = null;
    private int no = 0;
    byte[] buf = new byte[100];

    private int parameter = 10;
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

    public Route(String TheardName, SSLSocket socket, boolean clientSever, GUI gui) {
        this.TheardName = TheardName;
        this.socket = socket;
        this.clientSever = clientSever;
        this.gui = gui;
    }

    public Route(String TheardName, SSLSocket socket, boolean clientSever, int total, int number, int parameter) {
        this.TheardName = TheardName;
        this.socket = socket;
        this.clientSever = clientSever;
        this.parameter = parameter;
        this.no = number;
        int x = (int) screenSize.getWidth() / 4;
        int rowAmount = (int) ((total + 3) / 4);
        int width = (int) (x * 3 / rowAmount);
        int high = (int) screenSize.getHeight() / 4;

        this.gui = new GUI("c " + number, x + ((number - 1) % rowAmount) * width, (number - 1) / rowAmount * high, high,
                width);

    }

    public static int byteArrayToInt(byte[] b) {
        return b[3] & 255 | (b[2] & 255) << 8 | (b[1] & 255) << 16 | (b[0] & 255) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[] { (byte) ((a >> 24) & 255), (byte) ((a >> 16) & 255), (byte) ((a >> 8) & 255),
                (byte) (a & 255) };
    }

    public void run() {
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();

            if (clientSever) {
                gui.setResponse(TheardName + no + " send : " + parameter + "\n");
                out.write(intToByteArray(parameter));
            }

            while (true) {
                int len = in.read(buf);
                int num = byteArrayToInt(buf);
                if (num == 0 || len == -1) {
                    if (num == 0) {
                        gui.setResponse(TheardName + no + " receive : " + num + "\n");
                    } else {
                        gui.setResponse("adversary disconnect\n");
                    }
                    break;
                } else {
                    gui.setResponse(TheardName + no + " receive : " + num + "\n");
                    num--;
                    out.write(intToByteArray(num));
                    gui.setResponse(TheardName + no + " send : " + num + "\n");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
