package SpaceInvadersGame;

import javafx.scene.paint.Color;

public class Projectile extends Sprite {

	Projectile(int x, int y, int w, int h, String type, Color color) {
		super(x, y, w, h, type, color);
	
	}
	
	Projectile(Projectile toCopy){
		super(toCopy.xpos,toCopy.ypos, toCopy.width, toCopy.height, toCopy.type, toCopy.color);
	}
}
