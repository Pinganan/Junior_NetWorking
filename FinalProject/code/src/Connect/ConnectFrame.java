package Connect;
import Basic.Frame;

public class ConnectFrame extends Frame {

	public ConnectFrame(int x, int y) {
		super(x, y);
	}

	public void addPanel(ConnectGamingPanel panel) {
		this.setVisible(false);
		container.removeAll();
		container.add(panel);
		this.setVisible(true);
		this.setBounds(this.x, this.y, WIDTH, HEIGHT);
	}
	
}
