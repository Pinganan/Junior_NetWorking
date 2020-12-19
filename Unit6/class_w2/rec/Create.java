
import java.io.*;
import java.rmi.*;
import java.util.ArrayList;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.lang.Thread;

public class Create extends JFrame {
    private String title;
    private String content;
    private JTextField titleT = new JTextField(20);
    private JLabel titlel = new JLabel("input title");
    private JTextField contentT = new JTextField(100);
    private JLabel contentl = new JLabel("input content");
    private JButton check = new JButton("send");
    public boolean checkFlag = false;

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Create() {
        super("Create");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(titlel, constraints);
        constraints.gridy = 1;
        panel.add(titleT, constraints);

        constraints.gridy = 2;
        panel.add(contentl, constraints);

        constraints.gridy = 3;
        panel.add(contentT, constraints);

        constraints.gridy = 4;
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                title = titleT.getText();
                content = contentT.getText();
                checkFlag = true;
            }
        });

        panel.add(check, constraints);

        add(panel);
        pack();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screenSize.getWidth() / 5, (int) screenSize.getHeight() / 4);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Create();
    }
}
