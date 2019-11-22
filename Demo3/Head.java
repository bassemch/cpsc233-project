import java.util.ArrayList;

public class Head {
	private static boolean screenOn = false;
	private static boolean mapOn = false;
	private static boolean scoresOn = false;
	
	
	private ArrayList<Integer> Scores = new ArrayList();
	private static String [] args1;
//	public Head() {
//		screenOn = true;
//		
//		//level = new Map(this); // only for now
//		//createPane();
//		// when a button is pressed we want to update which class is on and call createPane() again
//	}
	public static void main(String[] args) {
		args1 = args;
		Startscreen.main(args1);
	}
	public static void startGame(int level) {
		// should be invoked when button in Startscreen is pressed
		// should lead to delete StartScreen and create Map
		Head.screenOn = false;
		Head.mapOn = true;
		//Map.setLevel(level);
		createPane();
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
		createPane();
	}
	public static void createPane() {
		// checks which class will be used and calls it
		if (screenOn == true) {
			//start Startscreen and delete others
			Startscreen.main(args1);
			
		}else if (mapOn == true){
			//start Map and delete others
			Map.main(args1);
		}else {
			//start Highscores and delete others
			//ScoreBoard.main(args1);
		}
	}
}
