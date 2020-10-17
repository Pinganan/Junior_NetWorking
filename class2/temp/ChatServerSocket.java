
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class ChatServerSocket extends Thread {
    private ServerSocket skt;
    private Socket client;
    private BufferedReader theInputStream;
    private PrintStream theOutputStream;
    private String message;
    private ChatOneToOne chatBox;

    public ChatServerSocket(int port) {
        try {
            skt = new ServerSocket(port);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "server1", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setMessageObserver(ChatOneToOne box) {
        chatBox = box;
    }

    public String getMessage() {
        return message;
    }

    public void run() {
        try {
            message = "server2";
            chatBox.update();

            client = skt.accept();
            message = "server3" + client.getInetAddress() + "server3\n";
            chatBox.update();

            theInputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            theOutputStream = new PrintStream(client.getOutputStream());

            while ((message = theInputStream.readLine()) != null) {
                message = "re: " + message + "\n";
                chatBox.update();
            }

            if (message == null) {
                skt.close();
                message = "server4\n";
                chatBox.update();
                chatBox.setGUIState(true);
            }
        } catch (IOException e) {
            message = e.toString();
            chatBox.setGUIState(true);
        }
    }

    public void dataOutput(String data) {
        theOutputStream.println(data);
    }
}