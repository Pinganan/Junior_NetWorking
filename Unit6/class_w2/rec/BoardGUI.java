
import java.io.*;
import java.rmi.*;
import java.util.ArrayList;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.lang.Thread;

public class BoardGUI extends JFrame {

    private JButton createTopic = new JButton("Create");
    private JButton deleteTopic = new JButton("Delete");
    private JButton showAllTopic = new JButton("Show ALL");
    private JButton showTopic = new JButton("show detail");
    public int posterNum=0;
    private JTextField topicF = new JTextField(20);
    private JTextField deleteF = new JTextField(20);
    private String ddddd;
    private String ttttt;

    public String getDeleted() {
        return new String(ddddd);
    }

    public String getTopic() {
        return new String(ttttt);
    }

    public BoardGUI(String name, ArrayList<Topic> t) {
        super("Specification");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("hello, " + name + ". welcome ugly GUI"), constraints);

        constraints.gridy = 1;
        panel.add(createTopic, constraints);
        constraints.gridy = 2;
        panel.add(deleteF, constraints);
        constraints.gridx = 1;
        panel.add(deleteTopic, constraints);
        constraints.gridy = 3;
        panel.add(showTopic, constraints);
        constraints.gridx = 0;
        panel.add(topicF, constraints);
        

        for(int i=0 ; i<t.size() ; i++) {
            constraints.gridy = i+4;
            panel.add(new JLabel(t.get(i).getTitle() + "\t" + t.get(i).getEditor()), constraints);
        }

        showTopic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                posterNum = 3;
                ttttt = topicF.getText();
            }
        });

        createTopic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                posterNum = 1;
            }
        });

        deleteTopic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                posterNum = 2;
                ddddd = deleteF.getText();
            }
        });

        add(panel);
        pack();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screenSize.getWidth() / 5, (int) screenSize.getHeight() / 4);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        
    }
}
