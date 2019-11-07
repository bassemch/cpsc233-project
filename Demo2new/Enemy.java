import java.awt.Image;

import javafx.scene.shape.Rectangle;

public class Enemy extends Rectangle {
	
	private Image look;
	private int kind;
	
	public Enemy(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	public Enemy(Enemy toCopy){
		super(toCopy.getX(),toCopy.getY(), toCopy.getWidth(), toCopy.getHeight());
	}
	// look and size specify the kind of an enemy which gives different cases
//	public Enemy(int newX, int newY, int kind) {
//
//		// cases with images for different enemies
//		switch(kind) {
//		case 1: super(newX, newY, 20, 20);
//				this.look = new Image("");
//			break;
//		case 2: super(newX, newY, 20, 10);
//				this.look = new Image("");
//			break;
//		}
//	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
    public void moveLeft(int amount) {
    	if (this.getX()-amount > 0) {
    		this.setX((int) this.getX() - amount);
    	}
    }

    public void moveRight(int amount) {
    	if (this.getX() + amount < 800) { 
    		this.setX((int) this.getX() + amount);
    	}
    }    
    public void moveUp(int amount) {
    	if (this.getY() - amount > 0) { 
    		this.setY((int) this.getY() - amount);
    	}
    }
    public void moveDown(int amount) {
    	if (this.getY() + amount < 800) { 
    		this.setY((int) this.getY() + amount);
    	}
    }
    public void moveLeft() {
    	if (this.getX()-5 > 0) {
    		this.setX((int) this.getX() - 5);
    	}
    }

    public void moveRight() {
    	if (this.getX() + 5 < 800) { 
    		this.setX((int) this.getX() + 5);
    	}
    }
	
	
	
	
}
