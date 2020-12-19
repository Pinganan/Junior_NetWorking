
  
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private JTextArea responese = new JTextArea();
    private JScrollPane roll = new JScrollPane(responese);
    public boolean state = true;

    public String getResponse() {
        return new String(responese.getText());
    }
    public void setResponse(String str) {
        responese.append(str);
    }

    public GUI(String s) {
        responese.setEditable(false);
        JFrame window = new JFrame(s);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(200, 200);
        window.setLocation(100, 100);
        Container body = window.getContentPane();
        body.add(roll, BorderLayout.CENTER);
        window.setVisible(true);
    }
}