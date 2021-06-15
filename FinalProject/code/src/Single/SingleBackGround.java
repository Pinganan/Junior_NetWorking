package Single;
import Basic.BackGround;

public class SingleBackGround extends BackGround {
	// for selectPanel
	public int switchRole(boolean trigger, int selectedNum) {
    	if(trigger) {
    		timer = 0;
    		selectedNum = (selectedNum + 1) % PLAYER_NUMBER;
    	}
    	else {
    		timer += SingleRoute.PERIOD;
    	}
    	int n = timer / 1000;
    	image = selectRoleImageTimer[selectedNum * (selectRoleImageTimer.length / PLAYER_NUMBER) + n];
    	if(n == 4) {
    		selectedNum += PLAYER_NUMBER;
    	}
    	return selectedNum;
    }
    // for initPanel
    public void switchInitBackground() {
    	timer = (timer + SingleRoute.PERIOD) % (startImage.length * 150);
    	image = startImage[timer/150];
    }
    // for waitPanel
    public void switchWaitBackground() {
    	timer = (timer + SingleRoute.PERIOD) % (WaitImageTimer.length * 600);
    	image = WaitImageTimer[timer/600];
    }

}
