import java.awt.Color;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class Map extends Application {
	// to create objects
	private Player aPlayer;
	private ArrayList<Enemy> EnemyList = new ArrayList();
	private ArrayList<Shot> ShotList = new ArrayList();
	private ArrayList<Wall> WallList = new ArrayList();
	
	// to start Screen
	private int gameWidth = 800;
	private int gameHeight = 800;
	private Pane root = new Pane();
	private Text gameOverLabel;
	
	// to update the positions
	private char EnemyMovingDirection = 'l'; // can be l,r,d
	private int EnemyMoveToSide = 10; 
	private char EnemyMoveBefore = 'l';
	private int PlayerMove = 10;
	private int safety = 50;
	
    private Parent createContent() {
    	// gameOverLabel code from https://github.com/ajsaavedra/SpaceInvadersFX/blob/master/src/com/tonyjs/spaceinvadersfx/SpaceInvadersFX.java
    	gameOverLabel = new Text("GAME OVER");
        //gameOverLabel.setFill(Color.WHITE);
        //gameOverLabel.setFont(Font.font("Monaco", FontWeight.EXTRA_BOLD, 20));
        gameOverLabel.setX(350);
        gameOverLabel.setY(400);
        
        //set up game
        Line bottomline = new Line(0, 700, 800, 700);
        root.getChildren().add(bottomline);
        root.setPrefSize(gameWidth, gameHeight);
        spawnPlayer();
        spawnEnemies(); //later here different game difficulties could be started

        //spawnWalls(); // implement this later

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
            	try {
            	pause(50);
            	updateEnemyPos();
            	updateShotPos();
            	checkOverlaps();
            	checkEnemyHeight();
            	checkShotHeight();
            	if(aPlayer.getHealth() <= 0) {
            		root.getChildren().add(gameOverLabel);
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
    //objects acting methods
    public void shoot(Player a1)  {
    	spawnShot(a1);
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
    //running game methods
    public void updateEnemyPos() {
    	//Enemies:
    	//check where Enemies need to move
  
    	try {
    	if (EnemyMovingDirection == 'r') {
    		if (this.getMostRightEnemy().getX() < gameWidth - EnemyMoveToSide - safety)
    			for (Enemy i : EnemyList){
    				i.moveRight(EnemyMoveToSide);
    			}else {
    				EnemyMoveBefore = EnemyMovingDirection;
    				EnemyMovingDirection = 'd';
    			}
    	}
    	if (EnemyMovingDirection == 'l') {
    		if (this.getMostLeftEnemy().getX() > EnemyMoveToSide + safety)
    			for (Enemy i : EnemyList){
    				i.moveLeft(EnemyMoveToSide);
    			}else {
    				EnemyMoveBefore = EnemyMovingDirection;
    				EnemyMovingDirection = 'd';
    			}
    	}
    	if (EnemyMovingDirection == 'd') {
    		if (EnemyMoveBefore == 'r') {
    			//move down and left next time
    			for (Enemy i : EnemyList){
    				i.moveDown(EnemyMoveToSide);
    			}
    			EnemyMoveBefore = EnemyMovingDirection;
    			EnemyMovingDirection = 'l';
    		}else {
    			for (Enemy i : EnemyList){
    				i.moveDown(EnemyMoveToSide);
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
    
    public void checkOverlaps() {
    	for (Shot i : ShotList) {
    		for (Enemy j : EnemyList) {
    			if(i.intersects(j.getLayoutBounds())) {
    				//remove Enemy j and Shot i, if Enemy j is hit by Shot i
    				root.getChildren().remove(i);
    				root.getChildren().remove(j);
    				EnemyList.remove(j);
    				ShotList.remove(i);
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
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        	// copied error handling from https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
            System.err.format("IOException: %s%n", e);
        }
    }
}