package SpaceInvadersGame;



import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class spaceinvadersgame extends Application {
	
	private Sprite player = new Sprite(300, 750, 30, 30, "player", Color.BLUE);
    private Pane root = new Pane();
    private int gameHeight = 800;
    private int gameWidth = 800;
    private Text gameOverLabel;
 
    
    
    
    private Parent createContent() {
    	// gameOveLabel code from https://github.com/ajsaavedra/SpaceInvadersFX/blob/master/src/com/tonyjs/spaceinvadersfx/SpaceInvadersFX.java
    	gameOverLabel = new Text("GAME OVER");
        gameOverLabel.setFill(Color.WHITE);
        gameOverLabel.setFont(Font.font("Monaco", FontWeight.EXTRA_BOLD, 20));
        gameOverLabel.setX(350);
        gameOverLabel.setY(400);
        root.setPrefSize(gameWidth, gameHeight);

        root.getChildren().add(player);

        AnimationTimer timer = new AnimationTimer() {
 
            public void handle(long now) {
            	enemyMovement();
            	updateGame();
            	if(player.dead == true) {
            	root.getChildren().add(gameOverLabel);
            	
            	}
            	 }       	 
            
        };

        timer.start();
        SpawnEnemies();

        return root;
    }
// code for method SpawnEnemies used from https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/invaders/SpaceInvadersApp.java
    private void SpawnEnemies() {
        for (int i = 0; i < 10; i++) {
            Sprite s = new Sprite(50 + i*55, 150, 25, 25, "enemy", Color.RED);
            root.getChildren().add(s);
        }
    }
// code for method sprites() from https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/invaders/SpaceInvadersApp.java
    private List<Sprite> sprites() {
        return root.getChildren().stream().map(n -> (Sprite)n).collect(Collectors.toList());
    }
   
   private void enemyMovement() {
	   for (Sprite enemy : sprites()) {
	   if(enemy.type == "enemy" && enemy.xpos < gameWidth - 50 && enemy.ypos < gameHeight - 600) {
   			enemy.moveRightslow();
   		}
   		else if(enemy.type == "enemy" && enemy.ypos < gameHeight - 600) {
   			enemy.moveDownslow();
   		}
   		else if(enemy.type == "enemy" && enemy.xpos > gameWidth - 750 && enemy.ypos < gameHeight - 500) {
   			enemy.moveLeftslow();
   		}
   		else if(enemy.type == "enemy" && enemy.ypos < gameHeight - 500) {
   			enemy.moveDownslow();
   		}
   		else if(enemy.type == "enemy" && enemy.xpos < gameWidth - 50 && enemy.ypos < gameHeight - 400) {
   			enemy.moveRightslow();
   		}
   		else if(enemy.type == "enemy" && enemy.ypos < gameHeight - 400) {
   			enemy.moveDownslow();
   		}
   		else if(enemy.type == "enemy" && enemy.xpos > gameWidth - 750 && enemy.ypos < gameHeight - 300) {
   			enemy.moveLeftslow();
   		}
   		else if(enemy.type == "enemy" && enemy.ypos < gameHeight - 300) {
   			enemy.moveDownslow();

   		}
   		else if(enemy.type == "enemy" && enemy.xpos < gameWidth - 50 && enemy.ypos < gameHeight - 200) {
   			enemy.moveRightslow();
   		}
   		else if(enemy.type == "enemy" && enemy.ypos < gameHeight - 200) {
   			enemy.moveDownslow();

   		}
   		else if(enemy.type == "enemy" && enemy.xpos > gameWidth - 750 && enemy.ypos < gameHeight - 100) {
   			enemy.moveLeftslow();
   		}
   		else if(enemy.type == "enemy" && enemy.ypos < gameHeight - 100) {
   			enemy.moveDownslow();

   		}
   		else if(enemy.type == "enemy" && enemy.xpos < gameWidth - 50 && enemy.ypos < gameHeight - 50) {
   			enemy.moveRightslow();
   		}
   		else if(enemy.type == "enemy" && enemy.ypos < gameHeight - 50) {
   			enemy.moveDownslow();
   			player.dead = true;
   		}}}
//code of method from https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/invaders/SpaceInvadersApp.java
    private void updateGame() {

        sprites().forEach(s -> {
            switch (s.type) {

               
                case "playerbullet":
                    s.moveUp();

                    sprites().stream().filter(e -> e.type.equals("enemy")).forEach(enemy -> {
                        if (s.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                            enemy.dead = true;
                            s.dead = true;
                        }
                    });

                    break;

                
            }
        });

        root.getChildren().removeIf(n -> {
            Sprite s = (Sprite) n;
            return s.dead;
        });
        
        }
        
 
       
        
      
       
    
// method shoot from https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/invaders/SpaceInvadersApp.java
    private void shoot(Sprite who) {
        Sprite s = new Sprite((int) who.getTranslateX() + 20, (int) who.getTranslateY(), 5, 20, who.type + "bullet", Color.WHITE);

        root.getChildren().add(s);
    }

    @Override
    // code for switch method used from https://github.com/AlmasB/FXTutorials/blob/master/src/com/almasb/invaders/SpaceInvadersApp.java
    public void start(Stage stage) throws Exception {
        
    	Scene scene = new Scene(createContent());
    	stage.setTitle("Block Invaders");
    	scene.setFill(Color.BLACK);
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    player.moveLeft();
                    break;
                case D:
                    player.moveRight();
                    break;
                case SPACE:
                    shoot(player);
                    break;
            }
        });
      
        stage.setScene(scene);
        stage.show();
    }

   

    public static void main(String[] args) {
        launch(args);
    }
}
