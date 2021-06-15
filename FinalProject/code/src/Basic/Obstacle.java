package Basic;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Obstacle {
	// y axis is constant
	public final static int INIT_Y = 300;
	// create possibility
    public final static int POSSIBILITY = 45;
    public int x, y;
    public BufferedImage image;
    
    // self create, distance is made by self
    public Obstacle(int x, int rand) {
        try {
            image = ImageIO.read(new File("image/box.png"));
        } catch (IOException e) {
            System.out.println("Error at read image");
            e.printStackTrace();
        }
        int distance = BackGround.SPEED * Role.ROLE_MAX_JUMP_HEIGHT / Role.JUMP_SPEED;
        this.x = x + 2 * distance + new Random().nextInt(rand);
        y = INIT_Y;
    }
    // other create, receive x and setup
    public Obstacle(int x) {
        try {
            image = ImageIO.read(new File("image/box.png"));
        } catch (IOException e) {
            System.out.println("Error at read image");
            e.printStackTrace();
        }
        this.x = x;
        y = INIT_Y;
    }
    // roll with BG speed
    public void ObstacleRoll() {
        x -= BackGround.SPEED;
    }
    // image rectangle
    public Rectangle getBound() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
}
