import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player extends Sprite {
	
	private int health;
	//defaultX = 400;
	//defaultY = 750;
	Player(int x, int y, int w, int h, int kind) {
		super(x, y, w, h, "player", kind);
		this.setLook();
	}
	Player(int kind) {
		super(400, 750, 30, 30, "player" , kind);
		this.setLook();
		this.setHealth(3);
	}
	Player() {
		super(400, 750, 30, 30, "player", 0);
		this.setLook();
		this.setHealth(3);
	}
	
	public void setLook() {
		switch(this.getKind()) {
		case(0): 
			this.setImage(new Image("/PlayerV1.png"));
			break;
		}
	}
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public void decreaseHealth(int amount) {
		this.health -= amount;
	}
  
    public void moveLeft() {
    	super.moveLeft(15);
    }

    public void moveRight() {
    	super.moveRight(15);
    }
}