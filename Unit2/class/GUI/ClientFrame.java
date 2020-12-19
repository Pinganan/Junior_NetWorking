
//  StudentID   D0782956
//  Name        PingTsuAn

import javax.swing.*;
import java.awt.event.*;

public class ClientFrame extends JFrame {

    private JLabel ipLabel, numLabel;
    private JTextField ipText, numText;
    private JButton sumButton;
    private String ip;
    private int number;
    public boolean state = true;

    public String getIP() {
        return new String(this.ip);
    }

    public int getNumber() {
        return number;
    }

    public ClientFrame() {
        super();
        this.setSize(300, 400);
        this.getContentPane().setLayout(null);
        this.add(this.getIpTextField(), null);
        this.add(this.getNumberTextField(), null);
        this.add(this.getSummitButton(), null);
        this.add(this.getNumberLabel(), null);
        this.add(this.getIpLabel(), null);
        this.setTitle("Client GUI");
        this.setVisible(true);
    }

    private JTextField getIpTextField() {
        if (ipText == null) {
            ipText = new JTextField("127.0.0.1");
            ipText.setBounds(120, 50, 100, 30);
        }
        return ipText;
    }

    private JTextField getNumberTextField() {
        if (numText == null) {
            numText = new JTextField("1234");
            numText.setBounds(120, 100, 100, 30);
        }
        return numText;
    }

    private JLabel getIpLabel() {
        if (ipLabel == null) {
            ipLabel = new JLabel();
            ipLabel.setBounds(40, 50, 100, 30);
            ipLabel.setText("IP address");
            ipLabel.setToolTipText("JLabel");
        }
        return ipLabel;
    }

    private JLabel getNumberLabel() {
        if (numLabel == null) {
            numLabel = new JLabel();
            numLabel.setBounds(40, 100, 100, 30);
            numLabel.setText("Number");
            numLabel.setToolTipText("JLabel");
        }
        return numLabel;
    }

    private JButton getSummitButton() {
        if (sumButton == null) {
            sumButton = new JButton();
            sumButton.setBounds(100, 150, 100, 30);
            sumButton.setText("Summit");
            sumButton.setToolTipText("OK");

            sumButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ip = ipText.getText();
                    number = Integer.parseInt(numText.getText());
                    state = false;
                }
            });
        }
        return sumButton;
    }
}
