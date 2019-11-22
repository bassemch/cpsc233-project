import java.util.ArrayList;

public class Head {
	private boolean screenOn = false;
	private boolean mapOn = false;
	private boolean scoresOn = false;
	
	private Startscreen screen;
	private Highscores scoreBoard;
	private Map level;
	
	private ArrayList<Integer> Scores = new ArrayList();
	
	public Head() {
		screenOn = true;
		
		level = new Map(this); // only for now
		//createPane();
		// when a button is pressed we want to update which class is on and call createPane() again
	}
	
	public void startGame() {
		// should be invoked when button in Startscreen is pressed
		// should lead to delete StartScreen and create Map
		this.screenOn = false;
		this.mapOn = true;
		createPane();
	}
	public void startScores() {
		// should be invoked when button in Startscreen is pressed
		// should lead to delete StartScreen and create Highsores
		this.screenOn = false;
		this.scoresOn = true;
		createPane();
	}
	public void startScreen() {
		this.mapOn = false;
		this.scoresOn = false;
		this.screenOn = true;
		createPane();
	}
	public void createPane() {
		// checks which class will be used and calls it
		if (screenOn == true) {
			//start Startscreen and delete others
			screen = new Startscreen(this);
			
		}else if (mapOn == true){
			//start Map and delete others
			level = new Map(this);
		}else {
			//start Highscores and delete others
			scoreBoard = new Highscores(this, Scores);
		}
	}
}
