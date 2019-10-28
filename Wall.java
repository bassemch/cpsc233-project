import java.awt.Rectangle;

public class Wall extends Rectangle {
	private int health = 7;
	
	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void decreaseHealth(int amount) {
		this.health -= amount;
	}
}
