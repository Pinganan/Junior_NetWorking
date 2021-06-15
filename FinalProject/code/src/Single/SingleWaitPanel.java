package Single;
import Basic.Panel;
import Basic.BackGround;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class SingleWaitPanel extends Panel {
	
	SingleBackGround backgroundImage;
	
	public SingleWaitPanel() {
		super();
		backgroundImage = new SingleBackGround();
		SingleRoute r = new SingleRoute(this);
		r.start();
	}
	
	public void paintImage() {
		backgroundImage.switchWaitBackground();
		tool.drawImage(backgroundImage.image, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		this.paintImage();
		System.out.println("???");
		g.drawImage(image, 0, 0, this);
	}
}