package ticTacToeAssignment;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main method sets the parent node to TttGUI for the scene
 * @author zchil
 *
 */
public class TttMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("TicTacToe");
		
		
		
		TttGUI gui = new TttGUI();
		
		Scene scene = new Scene(gui,1400,900);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	public static void main(String[]args) {
		launch(args);
	}
}
