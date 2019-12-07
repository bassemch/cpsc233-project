import javafx.scene.image.Image;

public class Shot extends Sprite {
	
	private Image image = new Image("/Shot.png");
	// defaultWidth = 5;
	// defaultHeight = 10;
	public Shot(int x, int y, int width, int heigth) {
		super(x, y, width, heigth, "shot");
		this.setLook();
	}
	public Shot(int x, int y) {
		super(x, y, 5, 10, "shot");
		this.setLook();
	}
	
	public void setLook() {
		// gives the object its look, by filling in an image
		this.setImage(image);
		//this.setFill(Color.BROWN);
	}
}
