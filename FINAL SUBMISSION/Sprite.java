import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

abstract public class Sprite extends Rectangle{

	private String type;
	private int kind;
	private int safety = 25;
	// safety is used to keep objects within boundaries of the pane and stay the safety in pixels away form the actual borders
	
	public Sprite(int x, int y, int w, int h, String type, int kind) {
		super(x, y, w, h);
		this.setType(type);
		this.setKind(kind);
	}
	public Sprite(int x, int y, int w, int h, String type) {
		// additional constructor for sprites that don't have a kind (Wall, Shot)
		super(x, y, w, h);
		this.setType(type);
	}
	
	abstract public void setLook();
		// every has to implement this method using Sprite.setImage() to set its Image depending on the kind
	
	public void setImage(Image image) {
		// passed object "image" passes the actual loaded image
		this.setFill(new ImagePattern(image));
	}
    public void moveLeft(int amount) {
    	// moves the object #(amount) pixels to the left and makes sure it stays in boundaries of the game
    	if (this.getX() - amount - safety > 0) {
    		this.setX((int) this.getX() - amount);
    	}
    }

    public void moveRight(int amount) {
    	// moves the object #(amount) pixels to the right and makes sure it stays in boundaries of the game
    	if (this.getX() + amount + safety < 800) { 
    		this.setX((int) this.getX() + amount);
    	}
    }    
    public void moveUp(int amount) {
    	// moves the object #(amount) pixels up and makes sure it stays in boundaries of the game
    	if (this.getY() - safety - amount > 0) { 
    		this.setY((int) this.getY() - amount);
    	}
    }
    public void moveDown(int amount) {
    	// moves the object #(amount) pixels down and makes sure it stays in boundaries of the game
    	if (this.getY() + safety + amount < 800) { 
    		this.setY((int) this.getY() + amount);
    	}
    }
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		// types will be Enemy, Player, Shot, PowerUp, Wall
		this.type = type;
	}
}
