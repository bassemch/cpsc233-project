import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
 
public class Map {
	// to create objects
	private Player aPlayer;
	private ArrayList<Enemy> EnemyList = new ArrayList();
	private ArrayList<Shot> ShotList = new ArrayList();
	private ArrayList<PowerUp> PowerList = new ArrayList();
	
	// to start Screen
	private Pane root = new Pane();
	private AnimationTimer timer;
	
	private Text gameOverLabel;
	private Text winLabel;
	
	// to update the positions
	private char EnemyMovingDirection = 'l'; // can be l,r,d
	private int EnemyMove = 10; 
	private char EnemyMoveBefore = 'l';
	private int safety = 50;
	private int gameWidth = 800;
	private int gameHeight = 800;
	
	//for powerUps
	private boolean tripleshot;
	private int maxShotNumber = 11;
	
	// for different levels
	private int levelnumber = 1;
	
	public Map(Pane root, int level) {
		// constructor sets the level and starts the run method to start the game
		this.root = root;
		this.setLevel(level);
		this.run();
	}
	
	//private Parent createContent()
	public void run() {
    	gameOverLabel = new Text("GAME OVER");
        gameOverLabel.setX(350);
        gameOverLabel.setY(100);
        gameOverLabel.setScaleX(5);
        gameOverLabel.setScaleY(5); 
        
        winLabel = new Text("YOU WIN");
        winLabel.setX(350);
        winLabel.setY(100);
        winLabel.setScaleX(5);
        winLabel.setScaleY(5);        	
        
        //set up game
        Line bottomline = new Line(0, 700, 800, 700);
        root.getChildren().add(bottomline);
        spawnPlayer();
        
        switch(levelnumber) {
        case(1): startLevel1();
        	break;
        case(2): startLevel2();
        	break;
        case(3): startLevel3();
        	break;
        }

        timer = new AnimationTimer() {
            public void handle(long now) {
            	try {
            		// constantly moves all sprites (except player) and 
            		// decreases health of player when neccessary
            		// constantly calls methods to check whether objects have to be removed
            	pause(50);
            	updateEnemyPos();
            	updateShotPos();
            	updatePowerUpPos();
            	
            	checkOverlaps();
            	
            	checkEnemyHeight();
            	checkShotHeight();
            	checkPowerUpHeight();
            	
            	if(aPlayer.getHealth() <= 0) {
            		
            		root.getChildren().clear();
            		root.getChildren().add(gameOverLabel);
            		exitGame();
            	}
            	if(EnemyList.size() == 0 && aPlayer.getHealth() > 0) {
            		root.getChildren().clear();
            		root.getChildren().add(winLabel);
            		exitGame();
            	}
            	}catch (Exception e) {
            		
            	}
            }
        };
        
        timer.start();
    }
    public void exitGame() {
    	timer.stop();
    	timer = null;
    	Head.endGame();
    }
    
    //methods that are activated for object's actions
    public void shoot(Player a1)  {
    	// calls to spawn another shot if there are not too many shots on the pane already
    	if (ShotList.size() < maxShotNumber) {
    		spawnShot(a1);
    	}
    }
    public void activatePowerUp(int kind) {
    	// activates the effect of a PowerUp given on its kind
    	// PowerUps are active for a given time, meaning after a given time maxshotnumber and tripleshot are reseted by using deactivatePowerUp()
    	switch(kind) {
    	case(0): 
    		tripleshot = true;
    		this.maxShotNumber = this.maxShotNumber * 3;
    		deactivatePowerUp(kind);
    		break;
    	case(1): 
    		EnemyMove = EnemyMove * 2;
    		deactivatePowerUp(kind);
    		break;
    	}
    }
    public void deactivatePowerUp(int kind) {
    	// deactivates the effect of a PowerUp depending on its kind after 5 seconds
    	// can only be invoked from the method activatePowerUp()
    	Timer timer1 = new Timer();
		timer1.schedule(new TimerTask() {
			@Override
			public void run() {
				switch(kind) {
				case(0):
					tripleshot = false;
					maxShotNumber = maxShotNumber / 3;
					break;
				case(1):
					EnemyMove = EnemyMove / 2;
				}
			}
		}, 5000);
    }
    // different levels
    public void startLevel1() {
    	// spawns a row of enemies of the big kind
        for (int i = 0; i < 10; i++) {
            Enemy s = new Enemy(50 + i*55, 100, levelnumber-1);
            EnemyList.add(s);
            root.getChildren().add(s);
        }
    }
    public void startLevel2() {
    	// spawns a row of enemies of the small kind
        for (int i = 0; i < 10; i++) {
            Enemy s = new Enemy(50 + i*55, 100, levelnumber-1);
            EnemyList.add(s);
            root.getChildren().add(s);
        }
    }
    public void startLevel3() {
    	// spawns a row of enemies of random kinds and sets double movement speed for enemies
    	this.EnemyMove = 20;
        for (int i = 0; i < 10; i++) {
            Enemy s = new Enemy(50 + i*55, 100);
            EnemyList.add(s);
            root.getChildren().add(s);
        }
    }
    // running game methods
    public void updateEnemyPos() {
    	// in charge of the movement of the enemies
    	// makes enemies move left/right constantly and shifts them one row down once one touches the boundaries	
  
    	try {
    	if (EnemyMovingDirection == 'r') {
        	// if enemies moved right before they have to either move right again or move down, if one reached the right border
    		if (this.getMostRightEnemy().getX() < gameWidth - EnemyMove - safety)
    			for (Enemy i : EnemyList){
    				i.moveRight(EnemyMove);
    			}else {
    				EnemyMoveBefore = EnemyMovingDirection;
    				EnemyMovingDirection = 'd';
    			}
    	}
    	if (EnemyMovingDirection == 'l') {
    		// if enemies moved left before they have to either move left again or move down, if one reached the left border
    		if (this.getMostLeftEnemy().getX() > EnemyMove + safety)
    			for (Enemy i : EnemyList){
    				i.moveLeft(EnemyMove);
    			}else {
    				EnemyMoveBefore = EnemyMovingDirection;
    				EnemyMovingDirection = 'd';
    			}
    	}
    	if (EnemyMovingDirection == 'd') {
    		// if enemies moved down before they have to either move right, if they moved left before or move left, if they moved right before 
    		if (EnemyMoveBefore == 'r') {
    			for (Enemy i : EnemyList){
    				i.moveDown(EnemyMove);
    			}
    			EnemyMoveBefore = EnemyMovingDirection;
    			EnemyMovingDirection = 'l';
    		}else {
    			for (Enemy i : EnemyList){
    				i.moveDown(EnemyMove);
    			}
    			EnemyMoveBefore = EnemyMovingDirection;
    			EnemyMovingDirection = 'r';
    			}
    	}	
    	}catch (Exception e) {
    		
    	}
    }
    public void updateShotPos() {
    	// constantly moves all Shots upwards
    	try {
    		for (Shot i : ShotList) {
    			i.moveUp(10);
    		}
    	}catch(Exception e) {
    		//System.out.println("No shots available");
    	}
    }
    public void updatePowerUpPos() {
    	// constantly moves all shots downwards
    	try {
    		for (PowerUp i : PowerList) {
    			i.moveDown(15);
    		}
    	} catch(Exception e) {
    		
    	}
    }
    public void checkOverlaps() {
    	// constantly checks if there are any Shots and Enemies overlapping and removes them if so
    	for (Shot i : ShotList) {
    		for (Enemy j : EnemyList) {
    			if(i.intersects(j.getLayoutBounds())) {
    				//remove Enemy j and Shot i, if Enemy j is hit by Shot i
    				root.getChildren().remove(i);
    				root.getChildren().remove(j);
    				EnemyList.remove(j);
    				ShotList.remove(i);
    				spawnPowerUp();
    			}
    		}
    	}
    	for (PowerUp i : PowerList) {
			if (aPlayer.intersects(i.getLayoutBounds())) {
				activatePowerUp(i.getKind());
				root.getChildren().remove(i);
				PowerList.remove(i);
			}
		}
    } 
    //supporting methods
    public void spawnPlayer() {
    	aPlayer = new Player();
    	root.getChildren().add(aPlayer);
    }
    public void spawnShot(Player aPlayer) {
    	// spawns a Shot a the player's position 
    	// spawns three Shots if the powerup tripleshot is activated
    	Shot s = new Shot((int) (aPlayer.getX() + aPlayer.getWidth()/2), (int) aPlayer.getY());
        ShotList.add(s);
        root.getChildren().add(s);
        
        // if clause is only activated during powerUp
        if (tripleshot == true) {
        	s = new Shot((int) (aPlayer.getX() + aPlayer.getWidth()/2 + 10), (int) aPlayer.getY());
            ShotList.add(s);
            root.getChildren().add(s);
            s = new Shot((int) (aPlayer.getX() + aPlayer.getWidth()/2 - 10), (int) aPlayer.getY());
            ShotList.add(s);
            root.getChildren().add(s);
        }
    }
    public void spawnPowerUp() {
    	// activated when enemy is shot
    	// spawns a new powerUp
    	PowerUp p = new PowerUp();
    	PowerList.add(p);
    	root.getChildren().add(p);
    }
    public Enemy getMostRightEnemy() {
    	// returns the most right enemy
    	Enemy e = EnemyList.get(0);
    	for (Enemy i : EnemyList) {
    		if (e.getX() < i.getX()) {
    			e = i;
    		}
    	}
    	return e;
    }
    public Enemy getMostLeftEnemy() {
    	// returns the most left enemy
    	Enemy e = EnemyList.get(0);
    	for (Enemy i : EnemyList) {
    		if (e.getX() > i.getX()) {
    			e = i;
    		}
    	}
    	return e;
    }
    public void checkEnemyHeight() {
    	// removes enemies if they move too low and decreases health
    	for (Enemy i : EnemyList) {
    		if (i.getY() > 700) {
    			root.getChildren().remove(i);
    			EnemyList.remove(i);
    			aPlayer.decreaseHealth(1);
    		}
    	}
    }
    public void checkShotHeight() {
    	// removes Shots before they fly out of the pane
    	for (Shot i : ShotList) {
    		if (i.getY() < 50) {
    			root.getChildren().remove(i);
    			ShotList.remove(i);
    		}
    	}    	
    }
    public void checkPowerUpHeight() {
    	// removes Shots before they fly out of the pane
    	for (PowerUp i : PowerList) {
    		if (i.getY() > 750){
    			root.getChildren().remove(i);
    			PowerList.remove(i);
    		}
    	}
    }
    public void setLevel(int levelnumber) {
		this.levelnumber = levelnumber;
	}
    public Player getPlayer() {
    	return aPlayer;
    }
    public static void pause(int ms) {
    	// Pausing method to slow the game speed
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        	// copied error handling from https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
            System.err.format("IOException: %s%n", e);
        }
    }
}