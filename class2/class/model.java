import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class model extends Frame {
    public static void main(String[] args) {
        model m = new model();
    }

    private Button clientButton, serverButton;
    private TextArea textArea;
    private TextField textAddress, textPort, textType;
    private Client client;
    private Server server;
    private boolean isServer;

    public model() {
        clientButton = new Button("Client");
        serverButton = new Button("Server");
        textArea = new TextArea("", 10, 50, TextArea.SCROLLBARS_BOTH);
        textAddress = new TextField("127.0.0.1");
        textPort = new TextField("6666");
        textType = new TextField(50);

        textType.addKeyListener(new TFListener());
        textArea.setEditable(false);

        setLayout(new FlowLayout());
        add(textAddress);
        add(textPort);
        add(clientButton);
        add(serverButton);
        add(textArea);
        add(textType);
        setSize(400, 300);
        setTitle("client / server GUI structure");

        clientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setClient();
            }
        });

        serverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setServer();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        show();
    }

    public void setGUIState(boolean state) {
        textAddress.setEnabled(state);
        textPort.setEnabled(state);
        serverButton.setEnabled(state);
        clientButton.setEnabled(state);
    }

    public void update() {
        if (isServer)
            textArea.append(server.getMessage());
        else
            textArea.append(client.getMessage());
    }

    private void setClient() {
        int port = Integer.parseInt(textPort.getText());
        String ip = textAddress.getText();

        client = new Client(ip, port);
        client.setMessageObserver(this);
        client.start();
    }

    private void setServer() {
        int port = Integer.parseInt(textPort.getText());

        server = new Server(port);
        server.setMessageObserver(this);
        server.start();

        isServer = true;
        textAddress.setText("client connecting");
        setGUIState(false);
    }

    private class TFListener implements KeyListener {
        public void keyPressed(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                textArea.append(textType + "\n");

                if (isServer) {
                    server.dataOutput(textType.getText());
                } else {
                    client.dataOutput(textType.getText());
                }
                textType.setText("");
            }
        }
    }

}