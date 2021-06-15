package Single;
import Basic.Panel;
import Basic.BackGround;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class SingleInitPanel extends Panel {
	
	SingleBackGround backgroundImage;
	
	public SingleInitPanel() {
		super();
		backgroundImage = new SingleBackGround();
		SingleRoute r = new SingleRoute(this);
		r.start();
	}
	
	public void paintImage() {
		backgroundImage.switchInitBackground();
		tool.drawImage(backgroundImage.image, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		this.paintImage();
		g.drawImage(image, 0, 0, this);
	}
}