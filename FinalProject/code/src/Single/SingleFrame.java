package Single;
import Basic.Frame;

public class SingleFrame extends Frame {

	public SingleFrame(int x, int y) {
		super(x, y);
	}
	
	public void addPanel(SingleInitPanel panel) {
		this.setVisible(false);
		container.removeAll();
		container.add(panel);
		this.setVisible(true);
		this.setBounds(this.x, this.y, WIDTH, HEIGHT);
		
	}
	
	public void addPanel(SingleSelectPanel panel) {
		this.setVisible(false);
		container.removeAll();
		container.add(panel);
		this.setVisible(true);
		this.setBounds(this.x, this.y, WIDTH, HEIGHT);
	}
	
	public void addPanel(SingleGamingPanel panel) {
		this.setVisible(false);
		container.removeAll();
		container.add(panel);
		this.setVisible(true);
		this.setBounds(this.x, this.y, WIDTH, HEIGHT);
	}

	public void addPanel(SingleWaitPanel panel) {
		this.setVisible(false);
		container.removeAll();
		container.add(panel);
		this.setVisible(true);
		this.setBounds(this.x, this.y, WIDTH, HEIGHT);
	}
	
}
