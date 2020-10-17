import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Client extends Thread {
    private Socket server;
    private InetAddress address;
    private int port;
    private BufferedReader inputStream;
    private PrintStream outputStream;
    private String message;
    private model box;

    public Client(String ip, int port) {
        try {

            address = InetAddress.getByName(ip);
            this.port = port;
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "client1", JOptionPane.ERROR_MESSAGE);
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
            message = "client";
            box.update();
            server = new Socket(address, port);
            message = "client";
            box.update();
            inputStream = new BufferedReader(new InputStreamReader(server.getInputStream()));
            outputStream = new PrintStream(server.getOutputStream());

            while ((message = inputStream.readLine()) != null) {
                message = "re: " + message + "\n";
                box.update();
            }
            if (message == null) {
                server.close();
                message = "client\n";
                box.update();
                box.setGUIState(true);
            }
        } catch (final IOException e) {
            message = e.toString();
            box.update();
        }
    }

    public void dataOutput(final String data) {
        outputStream.println(data);
    }
}
