	import javafx.scene.layout.StackPane;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;
	import javafx.application.Application;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;

	public class Startscreen extends Application implements EventHandler<ActionEvent>{
		
		Button Level1 = new Button("Start Level 1");
		Button Level2 = new Button("Start Level 2");
		private VBox abox = new VBox();
		private StackPane root = new StackPane();
		
		
		private Parent createContent() {
			abox.getChildren().add(Level1);
			abox.getChildren().add(Level2);
			Level1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
				public void handle(ActionEvent event) {
					Head.startGame(1);
				}
			});
			Level2.setOnAction(new EventHandler<ActionEvent>() {
				@Override
			public void handle(ActionEvent event) {
				Head.startGame(2);
			}
		});
			abox.setTranslateX(400);
			abox.setTranslateY(400);
			root.setPrefSize(800, 800); // use getter method or something to have this match game width and game height
			root.getChildren().add(abox);
			return root;
		}
		
		
		@Override
		public void start(Stage stage) throws Exception {
			Scene scene = new Scene(createContent());
			stage.setScene(scene);
	        stage.show();
			
		}


		
		@Override
		public void handle(ActionEvent event) {
			
			
			
		}
		public static void main(String[] args) {
	        launch(args);
	    }
		
	}
