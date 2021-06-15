package Single;
import Basic.Award;
import Basic.BackGround;
import Basic.GamingPanel;
import Basic.Obstacle;
import Basic.Panel;
import Basic.readArduino;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class SingleGamingPanel extends GamingPanel {
	
	int score = 0, targetPoint = 200, No=0;		// don't care
	public SinglePlayer role;
	DataOutputStream out = null;
	// panel for sending self data out, and refreshing self
	public SingleGamingPanel(DataOutputStream out, readArduino read, int roleNum) {
		super();
		this.out = out;
		role = new SinglePlayer(roleNum, read);
		SingleRoute r = new SingleRoute(this);
		r.start();
	}
	
	public void paintImage() {
		role.playerMove();
		backgroundImage.rollGamingBackground();
		tool.drawImage(backgroundImage.image, 0, 0, this);
		
		for (int i = 0; i < obstacle.size(); i++) {
			Obstacle o = obstacle.get(i);
            o.ObstacleRoll();
            if (o.x < -o.image.getWidth()) {
            	obstacle.remove(i);
            }
            else {
            	tool.drawImage(o.image, o.x, o.y, this);
            }
			role.isCollision(o.getBound());
        }
		for (int j = 0; j < award.size(); j++) {
			Award a = award.get(j);
            a.AwardRoll();
            
            if(role.isPlus(a.getBound())) {
            	score += 10;
            	award.remove(j);
            }
            else if (a.x < -a.image.getWidth()) {
            	award.remove(j);
            }
            else {
            	tool.drawImage(a.image, a.x, a.y, this);
            }
        }
		tool.drawImage(role.image, role.x, role.y, this);
		tool.drawString(Integer.toString(score), 775, 43);
		
	}

	@Override
	public void paint(Graphics g) {
		this.paintImage();
		score ++;
		if(score >= 99999) {
			
		}
		else if(score >= 90000) {
			SingleRoute.PERIOD = score / 1000;
		}
		else if(score >= 10000) {
			BackGround.SPEED = new Random().nextInt(15) + 10;
		}
		else if(score >= targetPoint) {
			BackGround.SPEED += 1;
			targetPoint += targetPoint;
		}
		g.drawImage(image, 0, 0, this);
	}
	
}
