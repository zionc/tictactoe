package ticTacToeAssignment;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TttMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("TicTacToe");
		
		
		
		TttGUI gui = new TttGUI();
		
		//main.setId("pane");
		Scene scene = new Scene(gui,1400,900);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	public static void main(String[]args) {
		launch(args);
	}
}
