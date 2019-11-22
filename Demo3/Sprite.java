import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

abstract public class Sprite extends Rectangle{

	private String type;
	private int kind;
	private int safety = 25;
	
	public Sprite(int x, int y, int w, int h, String type, int kind) {
		super(x, y, w, h);
		this.setType(type);
		this.setKind(kind);
	}
	public Sprite(int x, int y, int w, int h, String type) {
		super(x, y, w, h);
		this.setType(type);
	}
	
	abstract public void setLook();
	
	public void setImage(Image file) {
		this.setFill(new ImagePattern(file));
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
    public void moveUp(int amount) {
    	if (this.getY() - safety - amount > 0) { 
    		this.setY((int) this.getY() - amount);
    	}
    }
    public void moveDown(int amount) {
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
		this.type = type;
	}
}
