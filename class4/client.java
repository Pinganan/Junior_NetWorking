
import java.net.*;
import javax.swing.*;
import java.io.*;

public class client {
    public static void main(String[] args) {
        Socket client = null;
        int port = 6666;
        try {
            String ipAddress = JOptionPane.showInputDialog("give IP address", "localhost");
            int amount = Integer.parseInt(JOptionPane.showInputDialog("give positive amount for client", "10"));
            int total = amount;
            int parameter = Integer
                    .parseInt(JOptionPane.showInputDialog("give large positive integer for decrease", "10"));
            while (amount != 0) {
                client = new Socket(ipAddress, port);
                Route t = new Route("client", client, true, total, amount, parameter);
                t.start();
                client = null;
                amount--;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
