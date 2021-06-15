package Basic;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {

	protected BufferedImage image;
	protected Graphics2D tool;
	
	public Panel() {
		image = new BufferedImage(Frame.WIDTH, Frame.HEIGHT, BufferedImage.TYPE_INT_BGR);
		tool = image.createGraphics();
		tool.setColor(Color.WHITE);
		tool.setFont(new Font("Comic Sans MS", Font.PLAIN, 42));
	}

}

