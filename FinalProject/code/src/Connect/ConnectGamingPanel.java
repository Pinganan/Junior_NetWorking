package Connect;

import java.awt.Graphics;
import java.net.*;
import java.io.*;
import java.util.Random;

import Basic.Award;
import Basic.GamingPanel;
import Basic.Obstacle;
import Single.SingleFrame;

public class ConnectGamingPanel extends GamingPanel {
	
	public ConnectPlayer role = null;
	DataInputStream in = null;
	// Panel for reading number & refreshing other interface
	public ConnectGamingPanel(DataInputStream in, int roleNum) {
		role = new ConnectPlayer(roleNum);
		this.in = in;
		ConnectRoute r = new ConnectRoute(this);
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
        }
		for (int j = 0; j < award.size(); j++) {
			Award a = award.get(j);
            a.AwardRoll();
            if (a.x < -a.image.getWidth()) {
            	award.remove(j);
			}
			else {
				tool.drawImage(a.image, a.x, a.y, this);
			}
		}
		tool.drawImage(role.image, role.x, role.y, this);
	}

	@Override
	public void paint(Graphics g) {
		this.paintImage();
		g.drawImage(image, 0, 0, this);
	}
	
}
