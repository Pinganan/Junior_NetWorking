
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class ChatClientSocket extends Thread {
    private Socket skt;
    private InetAddress host;
    private int port;

    private BufferedReader theInputStream;
    private PrintStream theOutputStream;
    private String message;
    private ChatOneToOne chatBox;

    public ChatClientSocket(final String ip, final int port) {
        try {

            host = InetAddress.getByName(ip);
            this.port = port;
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "client1", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setMessageObserver(final ChatOneToOne box) {
        chatBox = box;
    }

    public String getMessage() {
        return message;
    }

    public void run() {
        try {
            message = "client2";
            chatBox.update();

            skt = new Socket(host, port);
            message = "client3";
            chatBox.update();

            theInputStream = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            theOutputStream = new PrintStream(skt.getOutputStream());

            while ((message = theInputStream.readLine()) != null) {
                message = "re: " + message + "\n";
                chatBox.update();
            }

            if (message == null) {
                skt.close();
                message = "client4\n";
                chatBox.update();
                chatBox.setGUIState(true);
            }
        } catch (final IOException e) {
            message = e.toString();
            chatBox.update();
        }
    }

    public void dataOutput(final String data) {
        theOutputStream.println(data);
    }
}