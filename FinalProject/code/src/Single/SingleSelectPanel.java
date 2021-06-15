package Single;
import Basic.Panel;
import Basic.readArduino;

import java.awt.Graphics;

public class SingleSelectPanel extends Panel {

		SingleBackGround backgroundImage;
		public boolean isSelected = false;
		readArduino read;
		public int selectedNumber;
		
		public SingleSelectPanel(readArduino read) {
			super();
			selectedNumber = 0;
			backgroundImage = new SingleBackGround();
			this.read = read;
			SingleRoute r = new SingleRoute(this);
			r.start();
		}
		
		public void paintImage() {
			selectedNumber = backgroundImage.switchRole(read.isSoundTrigger, selectedNumber);
			if(selectedNumber >= SingleBackGround.PLAYER_NUMBER) {
				selectedNumber -= SingleBackGround.PLAYER_NUMBER;
				isSelected = true;
			}
			tool.drawImage(backgroundImage.image, 0, 0, this);
			read.isSoundTrigger = false;
		}
		
		@Override
	    public void paint(Graphics g) {
			this.paintImage();
	        g.drawImage(image, 0, 0, this);
	    }
}
