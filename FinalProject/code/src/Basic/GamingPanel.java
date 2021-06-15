package Basic;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import Single.SingleFrame;
import Single.SinglePlayer;
import Single.SingleRoute;

public abstract class GamingPanel extends Panel {

public static int DENOMINATOR = 500;
	
	// component
	protected BackGround backgroundImage;
	public ArrayList<Obstacle> obstacle = new ArrayList<Obstacle>();
	public ArrayList<Award> award = new ArrayList<Award>();
	
	public GamingPanel() {
		super();
		backgroundImage = new BackGround();
	}
	
	public abstract void paintImage();

	@Override
	public abstract void paint(Graphics g);
	
}
