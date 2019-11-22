import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class Map extends Application {
	// give parent game
	private Head game;
	
	// to create objects
	private Player aPlayer;
	private ArrayList<Enemy> EnemyList = new ArrayList();
	private ArrayList<Shot> ShotList = new ArrayList();
	private ArrayList<Wall> WallList = new ArrayList();
	private ArrayList<PowerUp> PowerList = new ArrayList();
	
	// to start Screen
	private int gameWidth = 800;
	private int gameHeight = 800;
	private Pane root = new Pane();
	
	private Text gameOverLabel;
	private Text winLabel;
	
	// to update the positions
	private char EnemyMovingDirection = 'l'; // can be l,r,d
	private int EnemyMove = 10; 
	private char EnemyMoveBefore = 'l';
	private int safety = 50;
	
	//for powerUps
	private boolean tripleshot;
	private int maxShotNumber = 11;
	
	// for different levels
	private static int levelnumber = 1;
	

	private Parent createContent() {
    	gameOverLabel = new Text("GAME OVER");
        gameOverLabel.setX(350);
        gameOverLabel.setY(400);
        gameOverLabel.setScaleX(5);
        gameOverLabel.setScaleY(5); 
        
        winLabel = new Text("YOU WIN");
        winLabel.setX(350);
        winLabel.setY(400);
        winLabel.setScaleX(5);
        winLabel.setScaleY(5);        	
        
        //set up game
        Line bottomline = new Line(0, 700, 800, 700);
        root.getChildren().add(bottomline);
        root.setPrefSize(gameWidth, gameHeight);
        spawnPlayer();
         //later here different game difficulties can be started
        
        switch(levelnumber) {
        case(1): startLevel1();
        	break;
        case(2): startLevel2();
        	break;
        }

        //spawnWalls(); // implement this later

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
            	try {
            	pause(50);
            	updateEnemyPos();
            	updateShotPos();
            	updatePowerUpPos();
            	
            	checkOverlaps();
            	
            	checkEnemyHeight();
            	checkShotHeight();
            	checkPowerUpHeight();
            	
            	if(aPlayer.getHealth() <= 0) {
            		root.getChildren().add(gameOverLabel);
            		//pause(2000);
            		//Head.startScreen();
            		//game.scores.add();
            	}
            	if(EnemyList.size() == 0) {
            		root.getChildren().add(winLabel);
            		//pause(2000);
            		//Head.startScreen();
            		//game.scores.add()
            		// also add score
            	}
            	}catch (Exception e) {
            		
            	}
            }
        };

        timer.start();
        return root;
    }
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
    	Scene scene = new Scene(createContent());
    	stage.setTitle("Space Invaders");
    	//scene.setFill(Color.BLACK);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    aPlayer.moveLeft();
                    break;
                case RIGHT:
                    aPlayer.moveRight();
                    break;
                case SPACE:
                    shoot(aPlayer);
                    break;
            }
        });
      
        stage.setScene(scene);
        stage.show();
    }
    
    //methods that are activated for object's actions
    public void shoot(Player a1)  {
    	if (ShotList.size() < maxShotNumber) {
    		spawnShot(a1);
    	}
    }
    public void activatePowerUp(int kind) {
    	switch(kind) {
    	case(0): //activate for a given time, meaning after a given time reset maxshotnumber and tripleshot by using deactivatePowerUp()
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
        for (int i = 0; i < 10; i++) {
            Enemy s = new Enemy(50 + i*55, 100, levelnumber-1);
            EnemyList.add(s);
            root.getChildren().add(s);
        }
    }
    public void startLevel2() {
        for (int i = 0; i < 10; i++) {
            Enemy s = new Enemy(50 + i*55, 100, levelnumber-1);
            EnemyList.add(s);
            root.getChildren().add(s);
        }
    }
    // running game methods
    public void updateEnemyPos() {
    	// if enemies moved right before they have to either move right again or move down, if one reached the right border
    	// if enemies moved left before they have to either move left again or move down, if one reached the left border
    	// if enemies moved down before they have to either move right, if they moved left before or move left, if they moved right before   	
  
    	try {
    	if (EnemyMovingDirection == 'r') {
    		if (this.getMostRightEnemy().getX() < gameWidth - EnemyMove - safety)
    			for (Enemy i : EnemyList){
    				i.moveRight(EnemyMove);
    			}else {
    				EnemyMoveBefore = EnemyMovingDirection;
    				EnemyMovingDirection = 'd';
    			}
    	}
    	if (EnemyMovingDirection == 'l') {
    		if (this.getMostLeftEnemy().getX() > EnemyMove + safety)
    			for (Enemy i : EnemyList){
    				i.moveLeft(EnemyMove);
    			}else {
    				EnemyMoveBefore = EnemyMovingDirection;
    				EnemyMovingDirection = 'd';
    			}
    	}
    	if (EnemyMovingDirection == 'd') {
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
    	try {
    		for (Shot i : ShotList) {
    			i.moveUp(10);
    		}
    	}catch(Exception e) {
    		//System.out.println("No shots available");
    	}
    }
    public void updatePowerUpPos() {
    	try {
    		for (PowerUp i : PowerList) {
    			i.moveDown(15);
    		}
    	} catch(Exception e) {
    		
    	}
    }
    
    public void checkOverlaps() {
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
    		for (Wall j : WallList) {
    			if (i.intersects(j.getLayoutBounds())) {
    				j.decreaseHealth(1);
    				if (j.getHealth() <= 0) {
    					root.getChildren().remove(j);
    					WallList.remove(j);
    				}
    				root.getChildren().remove(i);
    				ShotList.remove(i);
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
    public void spawnEnemies() {
        for (int i = 0; i < 10; i++) {
            Enemy s = new Enemy(50 + i*55, 100, 25, 25);
            EnemyList.add(s);
            root.getChildren().add(s);
        }
    }
    public void spawnShot(Player aPlayer) {
    	Shot s = new Shot((int) (aPlayer.getX() + aPlayer.getWidth()/2), (int) aPlayer.getY());
        ShotList.add(s);
        root.getChildren().add(s);
        
        // if clause is only activated during powerUp0
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
    	PowerUp p = new PowerUp();
    	PowerList.add(p);
    	root.getChildren().add(p);
    }
    public void spawnWalls() {
    	//spawn some walls related to the level
    }
    public Enemy getMostRightEnemy() {
    	
    	Enemy e = EnemyList.get(0);
    	for (Enemy i : EnemyList) {
    		if (e.getX() < i.getX()) {
    			e = i;
    		}
    	}
    	return e;
    }
    public Enemy getMostLeftEnemy() {
    	Enemy e = EnemyList.get(0);
    	for (Enemy i : EnemyList) {
    		if (e.getX() > i.getX()) {
    			e = i;
    		}
    	}
    	return e;
    }
    public void checkEnemyHeight() {
    	for (Enemy i : EnemyList) {
    		if (i.getY() > 700) {
    			root.getChildren().remove(i);
    			EnemyList.remove(i);
    			aPlayer.decreaseHealth(1);
    		}
    	}
    }
    public void checkShotHeight() {
    	for (Shot i : ShotList) {
    		if (i.getY() < 50) {
    			root.getChildren().remove(i);
    			ShotList.remove(i);
    		}
    	}    	
    }
    public void checkPowerUpHeight() {
    	for (PowerUp i : PowerList) {
    		if (i.getY() > 750){
    			root.getChildren().remove(i);
    			PowerList.remove(i);
    		}
    	}
    }
    public static void setLevel(int levelnumber) {
		Map.levelnumber = levelnumber;
	}
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        	// copied error handling from https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
            System.err.format("IOException: %s%n", e);
        }
    }
}