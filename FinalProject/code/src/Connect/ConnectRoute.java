package Connect;

import java.io.IOException;

import Basic.Award;
import Basic.Obstacle;

public class ConnectRoute extends Thread {
	
	public static int PERIOD = 40;
	ConnectGamingPanel panel = null;
	
	public ConnectRoute(ConnectGamingPanel panel) {
		this.panel = panel;
	}
	
	public void run() {
		while(!panel.role.isFin) {
			panel.repaint();
			//					1  + 		  1 + 	   00000
			// receive playerState + ObjectType + Distance_x
			int value = 0;
			try {
				value = panel.in.readInt();
				if(value != 0) {
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// get player state
			switch(value/1000000) {
				// none
				case 0 :
					break;
				// jump
				case 1 :
					panel.role.isJump = true;
					break;
				// die
				case 2 :
					panel.role.isFin = true;
					break;
			}
			// get object type & set distance
			switch((value/100000)%10) {
				// none
				case 0 :
					break;
				// obstacle
				case 1:
					panel.obstacle.add(new Obstacle(value%100000));
					break;
				// award
				case 2 :
					panel.award.add(new Award(value%100000));
					break;
			}
			try {
	        	Thread.sleep(PERIOD);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
	}

}
