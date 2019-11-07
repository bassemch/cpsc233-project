import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {
	
	private int safety = 25;
	private int health;
	//defaultX = 400;
	//defaultY = 750;
	Player(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	Player() {
		super(400, 750, 30, 30);
		this.setHealth(3);
	}
	
	Player(Player toCopy){
		super(toCopy.getX(),toCopy.getY(), toCopy.getWidth(), toCopy.getHeight());
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

    public void moveLeft(int amount) {
    	if (this.getX() - amount - safety > 0) {
    		this.setX((int) this.getX() - amount);
    	}
    }

    public void moveRight(int amount) {
    	if (this.getX() + amount + safety < 800) { 
    		this.setX((int) this.getX() + amount);
    	}
    }    
    public void moveLeft() {
    	if (this.getX()- 15 - safety > 0) {
    		this.setX((int) this.getX() - 15);
    	}
    }

    public void moveRight() {
    	if (this.getX() + 15 + safety < 800) { 
    		this.setX((int) this.getX() + 15);
    	}
    }
//    void moveUp(int amount) {
//    	if (this.getY() - amount > 0) { 
//    		this.setLocation((int) this.getX(), (int) this.getY() - amount);
//    	}
//    }
//    void moveDown(int amount) {
//    	if (this.getY() + amount < 800) { 
//    		this.setLocation((int) this.getX(), (int) this.getY() + amount);
//    	}
//    }
}