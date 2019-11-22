import java.io.FileInputStream;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Enemy extends Sprite {
	
	Image image0 = new Image("/BigEnemy.png");
	Image image1 = new Image("/SmallEnemy.png");
	
	public Enemy(int x, int y, int w, int h, int kind) {
		super(x, y, w, h, "enemy", kind);
		this.setLook();
	}
	public Enemy(int x, int y, int w, int h) {
		super(x, y, w, h, "enemy", new Random().nextInt(2));
		this.setLook();
	}
	public Enemy(int x, int y, int kind) {
		super(x, y, 30, 10, "enemy", kind);
		this.setSize();
		this.setLook();
	}
	public void setLook() {
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
