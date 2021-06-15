package Basic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Single.SingleRoute;

public class EndPanel extends Panel {
	protected BufferedImage endImage;
	
	public EndPanel(boolean isWinner) {
		try {
        	if(isWinner) {
        		endImage = ImageIO.read(new File("image/Win.png"));
        	}
        	else {
        		endImage = ImageIO.read(new File("image/Lose.png"));
        	}
        } catch (IOException e) {
            System.out.println("Error at read image");
		}
		tool.drawImage(endImage, 0, 0, this);
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
	
}
