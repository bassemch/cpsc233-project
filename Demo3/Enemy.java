import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Enemy extends Sprite {
	
	public Enemy(int x, int y, int w, int h, int kind) {
		super(x, y, w, h, "enemy", kind);
		this.setLook();
	}
	public Enemy(int x, int y, int w, int h) {
		super(x, y, w, h, "enemy", new Random().nextInt(2));
		this.setLook();
	}

	public void setLook() {
		switch(this.getKind()) {
		case(0): this.setFill(Color.DARKGREEN); //this.setImage("");
			break;
		case(1): this.setFill(Color.LAWNGREEN);
			break;
		}
    }
}
