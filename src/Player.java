import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {
	int health = 100;
	int xPos = 50;
	int yPos = 10;
	
    public void moveX(KeyEvent m, int xPos, int yPos) {
    	if(m.getKeyCode() == KeyEvent.VK_RIGHT) {
    		xPos = xPos + 1; }
    	if(m.getKeyCode() == KeyEvent.VK_LEFT) {
    		xPos = xPos - 1; }
    }
    
    public void updateHealth(int health) {
    	boolean deathCheck = false;
    	boolean hitDetect = false;
    	if (hitDetect = true) {
    		health = health - 10; }
    	if (health == 0) {
    		deathCheck = true; }
    }
    
    public void playerShoot(KeyEvent s, int xPos) {
    	if(s.getKeyCode() == KeyEvent.VK_SPACE) {
    		//shoot();
    	}
    }
}
