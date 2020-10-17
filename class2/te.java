
//*******************************************************************
//*  D0746384  李筱文                                               *
//*******************************************************************
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.Thread;

public class Server_GUI {
    public static void main(String args[]) {
        ServerSocket srverSocket = null;
        Socket sc = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        int port = 6666;

        try {
            Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            // Create Frame
            JFrame jf = new JFrame("Server");
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jf.setSize(250, 250);
            jf.setLocation((int) screenSize.getWidth() / 2 - 250, (int) screenSize.getHeight() / 2 - 125);
            JTextArea ta1 = new JTextArea("");
            Container c = jf.getContentPane();
            ta1.setEditable(false);
            JScrollPane jsp = new JScrollPane(ta1);
            c.add(jsp, BorderLayout.CENTER);
            jf.setVisible(true);
            // Creates a server socket, bound to the specified port.
            srverSocket = new ServerSocket(port);
            ta1.append("Waiting for request ...\n");

            try {
                // Listens for a connection to be made to this socket and accepts it.
                sc = srverSocket.accept();
                // Read message from client
                // Returns an input stream for socket sc.
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
                while (true) {
                    // Reads some number of bytes from the input stream and stores them into buf.
                    String nstr = in.readUTF();
                    if (nstr.equals("exit")) {
                        break;
                    }
                    ta1.append("Receive message: " + nstr + "\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    if (nstr.equals("0")) {
                        String str = "exit";
                        out.writeUTF(str);
                        break;
                    }
                    // Send reply message to client
                    // Returns an output stream for socket sc.
                    String onstr = Integer.toString(Integer.parseInt(nstr) - 1);
                    // Writes bytes from the specified byte array to this output stream.
                    out.writeUTF(onstr);
                    // getBytes(): Encodes this String into a sequence of bytes using the
                    // platform's default charset, storing the result into a new byte array.
                }
                // Closes in/out stream and releases any system resources associated with this
                // stream.
                in.close();
                out.close();
                // Closes this socket
                sc.close();
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                srverSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
