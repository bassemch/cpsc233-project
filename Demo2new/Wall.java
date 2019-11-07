import javafx.scene.shape.Rectangle;

public class Wall extends Rectangle{
	private int health = 7;
	
	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	public Wall(int x, int y) {
		//using default values, could also be implemented with kinds
		super(x, y, 30, 20);
	}
	
	public void decreaseHealth(int amount) {
		this.setHealth(this.getHealth() - amount);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
