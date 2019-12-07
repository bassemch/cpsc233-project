import java.util.Random;
import javafx.scene.image.Image;

public class Enemy extends Sprite {
	
	private Image image0 = new Image("/BigEnemy.png");
	private Image image1 = new Image("/SmallEnemy.png");
	
	public Enemy(int x, int y, int w, int h, int kind) {
		// test constructor
		super(x, y, w, h, "enemy", kind);
		this.setLook();
	}
	public Enemy(int x, int y) {
		// creates an enemy of a random kind
		super(x, y, 30, 10, "enemy", new Random().nextInt(2));
		this.setSize();
		this.setLook();
	}
	public Enemy(int x, int y, int kind) {
		// creates an enemy of a given kind
		super(x, y, 30, 10, "enemy", kind);
		this.setSize();
		this.setLook();
	}
	public void setLook() {
		// sets the look of the enemy by giving it an image, depending on its kind
		switch(this.getKind()) {
		case(0): 
			this.setImage(image0);
			break;
		case(1):
			this.setImage(image1);
			break;
		}
    }
	public void setSize() {
		// sets the size of the enemy, depending on its kind
		switch(this.getKind()) {
		case(0):
			super.setWidth(40.0);
			super.setHeight(40.0);
			break;
		case(1):
			this.setWidth(15.0);
			this.setHeight(15.0);
			break;
		}
	}
}
