	import javafx.scene.layout.Pane;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.scene.control.Button;

	public class Startscreen implements EventHandler<ActionEvent>{
		
		Button Level1 = new Button("Start Level 1");
		Button Level2 = new Button("Start Level 2");
		Button Level3 = new Button("Start Level 3");
		private Pane root;
		
		
		public void run(Pane root){
			this.root = root;
			Level1.setTranslateX(350);
			Level1.setTranslateY(300);
			Level2.setTranslateX(350);
			Level2.setTranslateY(400);
			Level3.setTranslateX(350);
			Level3.setTranslateY(500);
			root.getChildren().add(Level1);
			root.getChildren().add(Level2);
			root.getChildren().add(Level3);
			Level1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
				public void handle(ActionEvent event) {
					root.getChildren().clear();
					Head.startGame(1);
				}
			});
			Level2.setOnAction(new EventHandler<ActionEvent>() {
					@Override
				public void handle(ActionEvent event) {
					root.getChildren().clear();
					Head.startGame(2);
			}
		});
			Level3.setOnAction(new EventHandler<ActionEvent>() {
				@Override
			public void handle(ActionEvent event) {
				root.getChildren().clear();
				Head.startGame(3);
		}
	});
		}
		public void removeAll() {
			root.getChildren().remove(Level1);
			root.getChildren().remove(Level2);
		}


		
		@Override
		public void handle(ActionEvent event) {
			
			
			
		}
		
	}
