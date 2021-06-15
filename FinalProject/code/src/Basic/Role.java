package Basic;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Role {
	public final static int ROLE_INIT_Y = 250;
	public final static int JUMP_SPEED = 14;
	public final static int ROLE_MAX_JUMP_HEIGHT = 200;
	
	public int x, y, timer;
	public boolean isJump, isFin, isAward;	// isAward is not use
	public BufferedImage image;
	public BufferedImage[] player = new BufferedImage[4];
	protected int jumpValue = -JUMP_SPEED;
	
	public Role(int Number) {
		x = 0;
		y = 350-100;
		timer = 0;
		isJump = false;
		this.isFin = false;
		this.isAward = false;
		System.out.println(Number);
		try {
			if(Number == 0) {
				player[0] = ImageIO.read(new File("image/cat_orange_1.png"));
				player[1] = ImageIO.read(new File("image/cat_orange_2.png"));
				player[2] = ImageIO.read(new File("image/cat_orange_3.png"));
				player[3] = ImageIO.read(new File("image/cat_orange_2.png"));
			} else if(Number == 1) {
				player[0] = ImageIO.read(new File("image/cat_gray_1.png"));
				player[1] = ImageIO.read(new File("image/cat_gray_2.png"));
				player[2] = ImageIO.read(new File("image/cat_gray_3.png"));
				player[3] = ImageIO.read(new File("image/cat_gray_2.png"));
			} else if(Number == 2) {
				player[0] = ImageIO.read(new File("image/cat_black_1.png"));
				player[1] = ImageIO.read(new File("image/cat_black_2.png"));
				player[2] = ImageIO.read(new File("image/cat_black_3.png"));
				player[3] = ImageIO.read(new File("image/cat_black_2.png"));
			}
		} catch (IOException e) {
			System.out.println("role reads error");
		}
	}
	// judge award trigger
	public boolean isPlus(Rectangle r) {
        return new Rectangle(x, y, image.getWidth(), image.getHeight()).intersects(r);
    }
	// judge obstacle trigger
	public void isCollision(Rectangle r) {
        this.isFin =  new Rectangle(x, y, image.getWidth(), image.getHeight()).intersects(r) | this.isFin;
    }
	
}
