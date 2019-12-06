import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Player extends Sprite {
	
	private int health;
	//defaultX = 400;
	//defaultY = 750;
	Player(int x, int y, int w, int h, int kind) {
		// test constructor
		super(x, y, w, h, "player", kind);
		this.setLook();
	}
	Player(int kind) {
		// creates a player with initial health 3 of a given kind
		// kind will determine the look of the player
		super(400, 750, 30, 30, "player" , kind);
		this.setLook();
		this.setHealth(3);
	}
	Player() {
		// creates the standard player
		super(400, 750, 30, 30, "player", 0);
		this.setLook();
		this.setHealth(3);
	}
	
	public void setLook() {
		// gives the player object its look by setting an image, depending on the kind of the player
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
    	// uses superclasses moving methods to stay in boudaries and makes the player move its default moving length of 15 to the left
    	super.moveLeft(15);
    }

    public void moveRight() {
    	// uses superclasses moving methods to stay in boudaries and makes the player move its default moving length of 15 to the right
    	super.moveRight(15);
    }
}