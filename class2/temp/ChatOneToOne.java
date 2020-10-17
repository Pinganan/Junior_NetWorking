
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChatOneToOne extends Frame {
    private Button clientBtn, serverBtn;
    private TextArea textArea;
    private TextField tfAddress, tfPort, tfType;
    private ChatClientSocket clientSkt;
    private ChatServerSocket serverSkt;
    private boolean isServer;

    public static void main(String args[]) {
        ChatOneToOne frm = new ChatOneToOne();
    }

    public ChatOneToOne() {

        clientBtn = new Button("Client");
        serverBtn = new Button("Server");
        textArea = new TextArea("", 10, 50, TextArea.SCROLLBARS_BOTH);
        tfAddress = new TextField("localhost");
        tfPort = new TextField("port");
        tfType = new TextField(50);

        tfType.addKeyListener(new TFListener());
        textArea.setEditable(false);

        setLayout(new FlowLayout());
        add(tfAddress);
        add(tfPort);
        add(clientBtn);
        add(serverBtn);
        add(textArea);
        add(tfType);
        setSize(400, 300);
        setTitle("One to One Chat");

        clientBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setToClient();
            }
        });

        serverBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setToServer();
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
        tfAddress.setEnabled(state);
        tfPort.setEnabled(state);
        serverBtn.setEnabled(state);
        clientBtn.setEnabled(state);
    }

    public void update() {
        if (isServer)
            textArea.append(serverSkt.getMessage());
        else
            textArea.append(clientSkt.getMessage());
    }

    private void setToClient() {
        int port = Integer.parseInt(tfPort.getText());
        clientSkt = new ChatClientSocket(tfAddress.getText(), port);
        clientSkt.setMessageObserver(this);
        clientSkt.start();
    }

    private void setToServer() {
        int port = Integer.parseInt(tfPort.getText());
        serverSkt = new ChatServerSocket(port);
        serverSkt.setMessageObserver(this);

        serverSkt.start();
        isServer = true;
        tfAddress.setText("one");
        setGUIState(false);
    }

    private class TFListener implements KeyListener {
        public void keyPressed(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                textArea.append("me: " + tfType.getText() + "\n");
                if (isServer)
                    serverSkt.dataOutput(tfType.getText());
                else
                    clientSkt.dataOutput(tfType.getText());
                tfType.setText("");
            }
        }
    }
}