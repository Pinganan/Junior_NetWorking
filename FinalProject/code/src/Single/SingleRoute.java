package Single;

import java.io.IOException;
import java.util.Random;

import Basic.Award;
import Basic.Obstacle;

public class SingleRoute extends Thread {
	
	public static int PERIOD = 40;

    SingleInitPanel iPanel = null;
    SingleSelectPanel sPanel = null;
    SingleWaitPanel wPanel = null;
    SingleGamingPanel gPanel = null;

    public SingleRoute(SingleInitPanel iPanel) {
        this.iPanel = iPanel;
    }

    public SingleRoute(SingleSelectPanel sPanel) {
        this.sPanel = sPanel;
    }
    
    public SingleRoute(SingleWaitPanel wPanel) {
        this.wPanel = wPanel;
    }
    
    public SingleRoute(SingleGamingPanel gPanel) {
        this.gPanel = gPanel;
    }
    

    @Override
    public void run() {
    	if(gPanel != null) {
    		while (!gPanel.role.isFin) {
//    		while (true) {
    			int value = 0;
    			// create award or obstacle
    			int r = new Random().nextInt(SingleGamingPanel.DENOMINATOR);
    			if(r < Award.POSSIBILITY) {
    				if(!gPanel.award.isEmpty()) {
    					gPanel.award.add(new Award(gPanel.award.get(gPanel.award.size()-1).x, 1500));
    					value += 200000;
    					value += gPanel.award.get(gPanel.award.size()-1).x;
    				}
    				else {
    					gPanel.award.add(new Award(SingleFrame.WIDTH));
    					value += 200000;
    					value += gPanel.award.get(gPanel.award.size()-1).x;
    				}
    			}
    			else if(r < Obstacle.POSSIBILITY) {
    				if(!gPanel.obstacle.isEmpty()) {
    					gPanel.obstacle.add(new Obstacle(gPanel.obstacle.get(gPanel.obstacle.size()-1).x, 500));
    					value += 100000;
    					value += gPanel.obstacle.get(gPanel.obstacle.size()-1).x;
    				}
    				else {
    					gPanel.obstacle.add(new Obstacle(SingleFrame.WIDTH));
    					value += 100000;
    					value += gPanel.obstacle.get(gPanel.obstacle.size()-1).x;
    				}
    			}
    			if(gPanel.role.isJump) {
    				value += 1000000;
    			}
				// send state & Type & x
				System.out.println(value);
    			try {
					gPanel.out.writeInt(value);
					value = 0;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
    			gPanel.repaint();
    	        try {
    	        	Thread.sleep(PERIOD);
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }
    		}
    		// send end
			try {
				gPanel.out.writeInt(2000000);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
    	}
    	else if(wPanel != null) {
    		while (true) {
    			wPanel.updateUI();
   				// wPanel.repaint();
    	        try {
    	        	Thread.sleep(PERIOD);
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }
    		}
    	}
    	else if(sPanel != null) {
    		while (true) {
    			sPanel.updateUI();
    			sPanel.repaint();
    	        try {
    	        	Thread.sleep(PERIOD);
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }
    		}
    	}
    	else if(iPanel != null) {
    		while (true) {
    			iPanel.updateUI();
//    			iPanel.repaint();
    	        try {
    	        	Thread.sleep(PERIOD);
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }
    		}
    	}
    }
}