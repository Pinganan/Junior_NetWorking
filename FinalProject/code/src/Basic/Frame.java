package Basic;
import java.awt.*;
import javax.swing.*;

import Single.SingleSelectPanel;

public class Frame extends JFrame {
	
	public final static int WIDTH = 900 + 13;
	public final static int HEIGHT = 350 + 35;
	
	protected int x;
	protected int y;
	protected Container container;
	
	// x, y is computer location
	public Frame(int x, int y) {
		this.x = x;
		this.y = y;
		container = getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(false);
		this.setBounds(x, y, WIDTH, HEIGHT);
	}
	
	public void addPanel(EndPanel panel) {
		this.setVisible(false);
		container.removeAll();
		container.add(panel);
		this.setVisible(true);
		this.setBounds(this.x, this.y, WIDTH, HEIGHT);
	}
	
}
