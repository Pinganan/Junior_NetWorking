import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.lang.Thread;

public class ALLTopic extends JFrame {

    private JTextField re = new JTextField(20);
    private String res;
    private JButton b = new JButton("reply");
    private JButton can = new JButton("leave");
    private JTextArea responese = new JTextArea();
    private JScrollPane roll = new JScrollPane(responese);
    public boolean state = true;
    public boolean resState = false;

    public String getResponse() {
        return new String(responese.getText());
    }
    public void setResponse(String str) {
        responese.append(str);
    }

    public ALLTopic() {
        super("Reply");
        responese.setEditable(false);
        this.setSize(700, 300);
        
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        roll.setPreferredSize(new Dimension(600, 400));
        roll.setSize(1000, 1000);
        panel.add(roll, constraints);

        constraints.gridy = 1;
        panel.add(re, constraints);
        constraints.gridy = 2;
        panel.add(b, constraints);
        constraints.gridy = 3;
        panel.add(can, constraints);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                res = re.getText();
                resState = true;
            }
        });

        can.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                state = false;
            }
        });

        add(panel);
        pack();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screenSize.getWidth() / 5, (int) screenSize.getHeight() / 4);
        this.setVisible(true);
    }

    public String getRes() {
        return res;
    }

    public static void main(String[] args) {
        new ALLTopic();
    }
}
