package Connect;

import Basic.Role;
import Single.SingleRoute;

public class ConnectPlayer extends Role {

	public ConnectPlayer(int Number) {
		super(Number);
	}
	
	public void playerMove() {
		timer = (timer + SingleRoute.PERIOD) % (player.length * 1000);
    	image = player[timer/1000];
    	
    	if(isJump) {
    		if(y <= ROLE_INIT_Y - ROLE_MAX_JUMP_HEIGHT) {
    			jumpValue = JUMP_SPEED;
    		}
    		y += jumpValue;
    		if(y >= ROLE_INIT_Y) {
    			isJump = false;
    			jumpValue = -JUMP_SPEED;
    		}
    	}
	}

}
