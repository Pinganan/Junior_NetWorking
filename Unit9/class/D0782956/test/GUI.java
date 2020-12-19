  
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

    public GUI(String str, int x, int y, int highSize, int widthSize) {
        responese.setEditable(false);
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        JFrame window = new JFrame(str);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(widthSize, highSize);
        window.setLocation(x, y);
        Container body = window.getContentPane();
        body.add(roll, BorderLayout.CENTER);
        window.setVisible(true);
    }
}