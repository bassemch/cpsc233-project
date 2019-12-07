import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Head extends Application{
	private static boolean screenOn = false;
	private static boolean mapOn = false;
	
	// for game
	private static Pane root = new Pane();
	private static Map game;
	private static Startscreen screen;
	//
	private int gameWidth = 800;
	private int gameHeight = 800;

	private Parent createContent() {
		// sets up the pane and starts the game
		root.setPrefSize(gameWidth, gameHeight);
		screenOn = true;
		startScreen();
		
		return root;
	}
	
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
    	// starts scene and moves player when a is game enabled
    	Scene scene = new Scene(createContent());
    	stage.setTitle("Space Invaders");
    	//scene.setFill(Color.BLACK);
        scene.setOnKeyPressed(e -> {
        	if (mapOn == true) {
            switch (e.getCode()) {
                case LEFT:
                    game.getPlayer().moveLeft();
                    break;
                case RIGHT:
                    game.getPlayer().moveRight();
                    break;
                case SPACE:
                    game.shoot(game.getPlayer());
                    break;
            }
        	}
        });
      
        stage.setScene(scene);
        stage.show();
    }
	public static void startGame(int level) {
		// invoked when button in Startscreen to start the game is pressed
		// leads to deletion of StartScreen and creation of Map (starts the game)
		Head.screenOn = false;
		Head.mapOn = true;
		screen = null;
		game = new Map(root, level);
	}
	public static void endGame() {
		// ends the game and calls to start the Startscreen
		mapOn = false;
		//game = null;
		startScreen();
	}
	public static void startScreen() {
		// starts the Startscreen
		Head.mapOn = false;
		Head.screenOn = true;
		screen = new Startscreen();
		screen.run(root);
	}
}
