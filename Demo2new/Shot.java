import javafx.scene.shape.Rectangle;

public class Shot extends Rectangle {
	
	// defaultWidth = 5;
	// defaultHeight = 10;
	public Shot(int x, int y, int width, int heigth) {
		super(x, y, width, heigth);
	}
	public Shot(int x, int y) {
		super(x, y, 5, 10);
	}
    public void moveUp(int amount) {
    	if (this.getY() - amount > 0) { 
    		this.setY((int) this.getY() - amount);
    	}
    }
}
