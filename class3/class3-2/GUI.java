
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private JLabel ipAddress = new JLabel("Enter ipAddress: ");
    private JLabel userName = new JLabel("Enter userName: ");
    private JLabel password = new JLabel("Enter password: ");
    private JTextField ipText = new JTextField(20);
    private String ip = "";
    private JTextField userText = new JTextField(20);
    private String user = "";
    private JTextField passText = new JTextField(20);
    private String pass = "";
    private JButton loginButton = new JButton("Login");
    private JTextArea responese = new JTextArea();
    private JScrollPane roll = new JScrollPane(responese);
    private JComboBox actionBox = new JComboBox();
    private JTextField parameterText = new JTextField(10);
    public boolean state = true;
    public int actID = 1;

    public int getParameter() {
        return Integer.parseInt(parameterText.getText());
    }

    public String getIp() {
        return new String(ip);
    }

    public String getUserName() {
        return new String(user);
    }

    public String getPass() {
        return new String(pass);
    }

    public String getResponse() {
        return new String(responese.getText());
    }

    public void setResponse(String str) {
        responese.append(str);
    }

    public JFrame TakeShowGUI() {
        responese.setEditable(false);
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        JFrame window = new JFrame("Reply information");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(700, 900);
        window.setLocation((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 10);
        Container body = window.getContentPane();
        body.add(roll, BorderLayout.CENTER);
        window.setVisible(true);
        return window;
    }

    public GUI() {
        super("Setting Structure");

        // create a new panel with GridBagLayout manager
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(ipAddress, constraints);

        constraints.gridx = 1;
        panel.add(ipText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(userName, constraints);

        constraints.gridx = 1;
        panel.add(userText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(password, constraints);

        constraints.gridx = 1;
        panel.add(passText, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(parameterText, constraints);

        constraints.gridx = 0;
        panel.add(actionBox, constraints);

        actionBox.addItem("How many mails");
        actionBox.addItem("Delete ?th mail");
        actionBox.addItem("Get all mail info");
        actionBox.addItem("Get ?th mail content");
        actionBox.addItem("Quit");

        actionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = (String) actionBox.getSelectedItem();// get the selected item

                switch (str) {
                    case "How many mails":
                        actID = 1;
                        break;
                    case "Delete ?th mail":
                        actID = 2;
                        break;
                    case "Get all mail info":
                        actID = 3;
                        break;
                    case "Get ?th mail content":
                        actID = 4;
                        break;
                    default:
                        actID = 0;
                        break;
                }
            }
        });

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                state = false;
                ip = ipText.getText();
                user = userText.getText();
                pass = passText.getText();
            }
        });

        add(panel);
        pack();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screenSize.getWidth() / 5, (int) screenSize.getHeight() / 4);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        GUI g = new GUI();
        g.TakeShowGUI();

    }

}
