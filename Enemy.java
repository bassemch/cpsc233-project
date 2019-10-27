import java.awt.Image;
import java.awt.Rectangle;

public class Enemy extends Rectangle {
	
//	private int x;
//	private int y;
//	private int heigth;
//	private int width;
	
	// look and size specify the kind of an enemy which gives different cases
	private Image look;
	
	public Enemy(int newX, int newY, int width, int heigth) {
		super(newX, newY, width, heigth);
	}
	
	public Enemy(int newX, int newY, int kind) {

		// cases with images for different enemies
//		switch(kind) {
//		case 1: super(newX, newY, 20, 20);
//				this.look = new Image("");
//			break;
//		case 2: super(newX, newY, 20, 10);
//				this.look = new Image("");
//			break;
//		}
	}
	
	
	
	
}
