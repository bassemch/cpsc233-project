import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Head extends Application{
	private static boolean screenOn = false;
	private static boolean mapOn = false;
	private static boolean scoresOn = false;
	
	// for game
	private static Pane root = new Pane();
	private static Map game;
	private static Startscreen screen;
	//
	private int gameWidth = 800;
	private int gameHeight = 800;
	private ArrayList<Integer> Scores = new ArrayList();

	private Parent createContent() {
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
		// should be invoked when button in Startscreen is pressed
		// should lead to delete StartScreen and create Map
		Head.screenOn = false;
		Head.mapOn = true;
		screen = null;
		game = new Map(root, level);
	}
	public static void endGame() {
		mapOn = false;
		//game = null;
		startScreen();
	}
	public static void startScores() {
		// should be invoked when button in Startscreen is pressed
		// should lead to delete StartScreen and create Highsores
		Head.screenOn = false;
		Head.scoresOn = true;
		createPane();
	}
	public static void startScreen() {
		Head.mapOn = false;
		Head.scoresOn = false;
		Head.screenOn = true;
		screen = new Startscreen();
		screen.run(root);
	}
	
	
	
	public static void createPane() {
		// checks which class will be used and calls it
		if (screenOn == true) {
			//start Startscreen and delete others
			System.out.println("Worked");
			
		}else if (mapOn == true){
			//start Map and delete others
			
		}else {
			//start Highscores and delete others
			
		}
	}
}
