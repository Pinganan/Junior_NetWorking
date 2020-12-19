
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
    private int posterNum;
    private JScrollPane roll = null;

    public BoardGUI(String name, ArrayList<Topic> topics) {
        super("Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("hello, " + name + ". welcome ugly GUI"), constraints);

        constraints.gridx = 2;
        panel.add(createTopic, constraints);
        constraints.gridx = 1;
        panel.add(deleteTopic, constraints);

        JPanel topicPanel = new JPanel();
        topicPanel.setLayout(new GridLayout(topics.size(), 2));
        int i;
        for (i = 0; i < topics.size(); i++) {

            JButton button = new JButton(
                    topics.get(i).getTitle() + "\t" + topics.get(i).getEditor() + " " + topics.get(i).getDate());
            button.setPreferredSize(new Dimension(500, 50));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                }
            });
            topicPanel.add(button);
        }
        roll = new JScrollPane(topicPanel);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(roll, constraints);

        add(panel);
        pack();
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screenSize.getWidth() / 5, (int) screenSize.getHeight() / 4);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // f.getContentPane().add(scrollPane);
        ArrayList<Topic> t = new ArrayList<Topic>();
        Topic c = new Topic("1", "2", "3");
        t.add(c);
        t.add(c);
        t.add(c);
        t.add(c);
        t.add(c);
        t.add(c);
        new BoardGUI("name", t);
        System.out.println(t.get(0).getTitle());
    }
}
