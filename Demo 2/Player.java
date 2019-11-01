package SpaceInvadersGame;

import javafx.scene.paint.Color;

public class Player extends Sprite {

	Player(int x, int y, int w, int h, String type, Color color) {
		super(x, y, w, h, type, color);
	
	}
	
	Player(Player toCopy){
		super(toCopy.xpos,toCopy.ypos, toCopy.width, toCopy.height, toCopy.type, toCopy.color);
	}
}
