import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Server extends Thread {

    private ServerSocket socket;
    private Socket client;
    private BufferedReader inputStream;
    private PrintStream outputStream;
    private String message;
    private model box;

    public Server(int port) {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "server1", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setMessageObserver(model box) {
        this.box = box;
    }

    public String getMessage() {
        return new String(message);
    }

    public void run() {
        try {
            message = "server";
            box.update();
            client = socket.accept();
            message = "server" + client.getInetAddress() + "server\n";
            box.update();

            inputStream = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputStream = new PrintStream(client.getOutputStream());

            while ((message = inputStream.readLine()) != null) {
                message = "re: " + message + "\n";
                box.update();
            }

            if (message == null) {
                socket.close();
                message = "server close";
                box.update();
                box.setGUIState(true);
            }
        } catch (IOException e) {
            message = e.toString();
            box.setGUIState(true);
        }
    }

    public void dataOutput(String data) {
        outputStream.println(data);
    }
}
