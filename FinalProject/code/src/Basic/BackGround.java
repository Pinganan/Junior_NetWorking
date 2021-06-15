package Basic;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;

public class BackGround {
	// player image amount
	public final static int PLAYER_NUMBER = 3;
	public static int SPEED = 15;
	public BufferedImage image;
	Graphics2D tool;
	
	protected int x1, x2, timer;
    protected BufferedImage[] startImage = new BufferedImage[10];
    protected BufferedImage[] selectRoleImage = new BufferedImage[3];
    protected BufferedImage[] selectRoleImageTimer = new BufferedImage[15];
    protected BufferedImage[] WaitImageTimer = new BufferedImage[3];
    protected BufferedImage[] gamingImage = new BufferedImage[2];
    protected BufferedImage scoreImage;

    public BackGround() {
    	image = new BufferedImage(Frame.WIDTH, Frame.HEIGHT, BufferedImage.TYPE_INT_BGR);
		tool = image.createGraphics();
		// take all image for BG
        try {
        	startImage[0] = ImageIO.read(new File("image/start1.png"));
        	startImage[1] = ImageIO.read(new File("image/start2.png"));
        	startImage[2] = ImageIO.read(new File("image/start3.png"));
        	startImage[3] = ImageIO.read(new File("image/start4.png"));
        	startImage[4] = ImageIO.read(new File("image/start5.png"));
        	startImage[5] = ImageIO.read(new File("image/start6.png"));
        	startImage[6] = ImageIO.read(new File("image/start7.png"));
        	startImage[7] = ImageIO.read(new File("image/start8.png"));
        	startImage[8] = ImageIO.read(new File("image/start9.png"));
        	startImage[9] = ImageIO.read(new File("image/start10.png"));
        	selectRoleImage[0] = ImageIO.read(new File("image/whichcat_orange.png"));
        	selectRoleImage[1] = ImageIO.read(new File("image/whichcat_gray.png"));
        	selectRoleImage[2] = ImageIO.read(new File("image/whichcat_black.png"));
        	
        	selectRoleImageTimer[0] = ImageIO.read(new File("image/whichcat_orange_5.png"));
        	selectRoleImageTimer[1] = ImageIO.read(new File("image/whichcat_orange_4.png"));
        	selectRoleImageTimer[2] = ImageIO.read(new File("image/whichcat_orange_3.png"));
        	selectRoleImageTimer[3] = ImageIO.read(new File("image/whichcat_orange_2.png"));
        	selectRoleImageTimer[4] = ImageIO.read(new File("image/whichcat_orange_1.png"));
        	
        	selectRoleImageTimer[5] = ImageIO.read(new File("image/whichcat_gray_5.png"));
        	selectRoleImageTimer[6] = ImageIO.read(new File("image/whichcat_gray_4.png"));
        	selectRoleImageTimer[7] = ImageIO.read(new File("image/whichcat_gray_3.png"));
        	selectRoleImageTimer[8] = ImageIO.read(new File("image/whichcat_gray_2.png"));
        	selectRoleImageTimer[9] = ImageIO.read(new File("image/whichcat_gray_1.png"));
        	
        	selectRoleImageTimer[10] = ImageIO.read(new File("image/whichcat_black_5.png"));
        	selectRoleImageTimer[11] = ImageIO.read(new File("image/whichcat_black_4.png"));
        	selectRoleImageTimer[12] = ImageIO.read(new File("image/whichcat_black_3.png"));
        	selectRoleImageTimer[13] = ImageIO.read(new File("image/whichcat_black_2.png"));
        	selectRoleImageTimer[14] = ImageIO.read(new File("image/whichcat_black_1.png"));
        	
        	WaitImageTimer[0] = ImageIO.read(new File("image/waiting1.png"));
        	WaitImageTimer[1] = ImageIO.read(new File("image/waiting2.png"));
        	WaitImageTimer[2] = ImageIO.read(new File("image/waiting3.png"));
        	
        	gamingImage[0] = ImageIO.read(new File("image/background.png"));
        	gamingImage[1] = ImageIO.read(new File("image/background.png"));
        	scoreImage = ImageIO.read(new File("image/score.png"));
        } catch (IOException e) {
            System.out.println("Error at read image");
        }
        x1 = 0;
        x2 = Frame.WIDTH;
        timer = 0;
    }
    // roll BG
    public void rollGamingBackground() {
        x1 -= SPEED;
        x2 -= SPEED;

        if(x1 < -Frame.WIDTH) {
            x1 = Frame.WIDTH;
        }
        else if(x2 < -Frame.WIDTH) {
            x2 = Frame.WIDTH;
        }
        tool.drawImage(scoreImage, 0, 0, null);
        tool.drawImage(gamingImage[0], x1, 50, null);
        tool.drawImage(gamingImage[1], x2, 50, null);
    }
    
}