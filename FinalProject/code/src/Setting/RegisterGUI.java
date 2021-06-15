package Setting;

import java.io.*;
import java.rmi.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.lang.Thread;

public class RegisterGUI extends JFrame {

    private JLabel userName = new JLabel("Enter userName: ");
    private JLabel password = new JLabel("Enter password: ");
    private JTextField userText = new JTextField(20);
    private JTextField passText = new JTextField(20);
    private JButton Login = new JButton("Register");

    public int isRegisterFin = 0;

    public String getName() {
        return userText.getText();
    }

    public String getPass() {
        return passText.getText();
    }

    public RegisterGUI() {
        super("Register");

        // create a new panel with GridBagLayout manager
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(userName, constraints);

        constraints.gridx = 1;
        panel.add(userText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(password, constraints);

        constraints.gridx = 1;
        panel.add(passText, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(Login, constraints);

        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isRegisterFin = 1;
            }
        });

        add(panel);
        pack();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screenSize.getWidth() / 5, (int) screenSize.getHeight() / 4);
        this.setVisible(false);
    }

    /*public static void main(String[] args) {
        BeginGUI b = new BeginGUI(); // t
        RegisterGUI g = new RegisterGUI(); // f

        while (true) {
            if (b.typeNum == 2) {
                b.setVisible(false);
                g.setVisible(true);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (g.isRegisterFin == 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        g.setVisible(false);
        g.isRegisterFin = 0;
        b.typeNum = 0;
        b.setVisible(true);
        
    }*/

}




        