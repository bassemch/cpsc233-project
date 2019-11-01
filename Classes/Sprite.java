package SpaceInvadersGame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sprite extends Rectangle {
	boolean dead = false;
    final String type;
    int xpos;
    int ypos;
    int width;
    int height;
    Color color;
    // code used and inspired from https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/invaders/SpaceInvadersApp.java
    
    
    
    Sprite(int x, int y, int w, int h, String type, Color color) {
        super(w, h, color);
        xpos = x;
        ypos = y;
        width = w;
        height = h;
        this.type = type;
        this.color = color;
        setTranslateX(x);
        setTranslateY(y);
    }
    Sprite(Sprite toCopy){
    	super(toCopy.width, toCopy.height, toCopy.color);
    	xpos = toCopy.xpos;
    	ypos = toCopy.ypos;
    	width = toCopy.width;
    	height = toCopy.height;
    	this.type = toCopy.type;
    	this.color = toCopy.color;
    	setTranslateX(toCopy.xpos);
    	setTranslateY(toCopy.ypos);
    	
    }

    void moveLeft() {
        setTranslateX(getTranslateX() - 10);
    }

    void moveRight() {
        setTranslateX(getTranslateX() + 10);
    }

    void moveUp() {
        setTranslateY(getTranslateY() - 10);
    }

    void moveDown() {
        setTranslateY(getTranslateY() + 10);
    }
    void moveRightslow() {
    	setTranslateX(getTranslateX() + 3);
    	xpos = xpos + 3;
    }
    void moveDownslow() {
    	setTranslateY(getTranslateY() + 3);
    	ypos = ypos + 3;

    }
    void moveLeftslow() {
    	setTranslateX(getTranslateX() - 3);
    	xpos = xpos - 3;
    }
    void moveUpslow() {
    	setTranslateY(getTranslateY() - 3);
    }
   
    
   
}

