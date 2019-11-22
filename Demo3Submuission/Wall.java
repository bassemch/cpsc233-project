import javafx.scene.image.Image;

public class Wall extends Sprite{
	private int health = 7;
	
	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height, "wall");
	}
	public Wall(int x, int y) {
		//using default values, could also be implemented with kinds
		super(x, y, 30, 20, "wall");
	}
	public void setLook() {
		
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
