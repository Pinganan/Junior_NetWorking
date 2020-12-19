
import java.io.*;
import java.rmi.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.lang.Thread;

public class BeginGUI extends JFrame {

    private JLabel userName = new JLabel("Enter userName: ");
    private JLabel password = new JLabel("Enter password: ");
    private JTextField userText = new JTextField("D0782956");
    private JTextField passText = new JTextField("123456");
    private JButton Login = new JButton("Login");
    private JButton Register = new JButton("Register");

    // 0->self, 1->login, 2->register
    public int typeNum = 0;

    public String getName() {
        return this.userText.getText();
    } 

    public String getPass() {
        return this.passText.getText();
    }

    public BeginGUI() {
        super("login/register");

        // create a new panel with GridBagLayout manager
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(userName, constraints);

        constraints.gridx = 1;
        panel.add(userText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(password, constraints);

        constraints.gridx = 1;
        panel.add(passText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(Register, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(Login, constraints);

        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeNum = 1;
            }
        });

        Register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                typeNum = 2;
            }
        });

        add(panel);
        pack();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screenSize.getWidth() / 5, (int) screenSize.getHeight() / 4);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        BeginGUI g = new BeginGUI();

    }

}
