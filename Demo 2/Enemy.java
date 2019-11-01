package SpaceInvadersGame;

import javafx.scene.paint.Color;

public class Enemy extends Sprite {
	Enemy(int x, int y, int w, int h, String type, Color color) {
		super(x, y, w, h, type, color);
	
	}
	
	Enemy(Enemy toCopy){
		super(toCopy.xpos,toCopy.ypos, toCopy.width, toCopy.height, toCopy.type, toCopy.color);
	}
}
