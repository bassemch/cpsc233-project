import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.Random;
import javafx.scene.shape.Rectangle;

public class PowerUp extends Sprite{	
	
	//powerUps can be either good or bad
	public PowerUp() {
		super(new Random().nextInt(700) + 50, new Random().nextInt(500) + 100, 15, 15, "shot", new Random().nextInt(2));
		this.setLook();
	}
	
	public void setLook() {
		switch(this.getKind()) {
		case(0): //case Tripleshot
			//this.look = 
			this.setFill(Color.BLUE);
			break;
		case(1): //case Enemies move faster
			this.setFill(Color.DARKRED);
			break;
		}
	}
}
