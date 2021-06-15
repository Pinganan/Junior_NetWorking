package Single;
import Basic.Role;
import Basic.readArduino;

public class SinglePlayer extends Role {
	
	readArduino read = null;

	public SinglePlayer(int Number, readArduino read) {
		super(Number);
		this.read = read;
	}
	
	public void playerMove() {
		timer = (timer + SingleRoute.PERIOD) % (player.length * 1000);
    	image = player[timer/1000];
    	// jump when sound is trigger
    	if(read.isSoundTrigger) {
    		isJump = true;
    	}
    	if(isJump) {
    		if(y <= ROLE_INIT_Y - ROLE_MAX_JUMP_HEIGHT) {
    			jumpValue = JUMP_SPEED;
    		}
    		y += jumpValue;
    		if(y >= ROLE_INIT_Y) {
    			isJump = false;
    			read.isSoundTrigger = false;
    			jumpValue = -JUMP_SPEED;
    		}
    	}
	}

}
