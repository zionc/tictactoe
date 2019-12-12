package ticTacToeAssignment;

import java.util.ArrayList;
import java.util.Optional;


import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.util.Duration;

/**
 * Class TttGUI is of type BorderPane and represents the top most 
 * layout that contains a TttGrid, title and info box when the TttGrid game is over.
 * @author zionchilagan
 *
 */

public class TttGUI extends BorderPane {
	
	/**
	 * Constructs a no-arg TttGUI object that
	 * contains a TttGrid and sets it to the center of the GUI and
	 * a header which is set to the top of the GUI.
	 */
	public TttGUI() {
		
		TttGrid cellBoard = new TttGrid();
		Group cellGroup = new Group();
		cellGroup.getChildren().add(cellBoard);
		
		
		HBox titlePane = getTitlePane();
		this.setCenter(cellGroup);
		this.setTop(titlePane);
		setAlignment(cellGroup,Pos.TOP_CENTER);
		
		this.setPadding(new Insets(10,10,10,10));
		this.setStyle("-fx-background-color: #dae2f0;");
		
	}
	
	/**
	 * Method to create a header for the GUI
	 * @return - Layout that represents the title for the GUI
	 */
	public HBox getTitlePane() {
		
		Label titleLabel = new Label();
		titleLabel.setText("Tic-Tac-Toe!");
		titleLabel.setStyle("-fx-font: 70 arial;");
		titleLabel.setTextFill(Color.web("#9b25f5"));
		
		HBox titleBox = new HBox();
		titleBox.getChildren().add(titleLabel);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.setStyle("-fx-background-color: #98d9e3;");
		titleBox.setPrefHeight(USE_COMPUTED_SIZE + 150);
		
		
		return titleBox;
	}
	
	/**
	 * Method that returns a layout pane that displays
	 * who won the Tic-Tac-Toe game, effects are also 
	 * applied.
	 * @param input - Winner of the game, represented by the letter
	 * @return
	 */
	public VBox winnerPane(String input) {
		Button restartButton = new Button("Restart");
		restartButton.setPrefSize(80, 40);
		restartButton.setStyle("-fx-font: 17px Tahoma;");
		restartButton.setTextFill(Color.web("#ff0000"));
		VBox winnerPane = new VBox();
		winnerPane.setAlignment(Pos.TOP_CENTER);
		winnerPane.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" 
			+ "-fx-border-width: 5;"
			+ "-fx-border-insets: 5;"
			+ "-fx-border-radius: 5;"
			+ "-fx-border-color: #98d9e3;");
		restartButton.setOnMouseClicked(restart);
		
		if(input.equals("X") || input.equals("O")) {
			Label winnerLabel = new Label();
			winnerLabel.setText(input + " has won!");
			winnerLabel.setStyle("-fx-font: 50 arial;");
			winnerLabel.setTextFill(Color.web("#9b25f5"));
			winnerPane.getChildren().add(winnerLabel);
		
			
		}
		else {
			Label drawLabel = new Label();
			drawLabel.setText("Draw!");
			drawLabel.setStyle("-fx-font: 50 arial;");
			drawLabel.setTextFill(Color.web("#9b25f5"));
			winnerPane.getChildren().add(drawLabel);
		}
		winnerPane.getChildren().add(restartButton);
		FadeTransition transition = new FadeTransition(Duration.seconds(2), winnerPane);
		transition.setFromValue(0.0);
		transition.setToValue(1.0);
		transition.play();
		return winnerPane;
		
		
		
	}
	
	/**
	 * Handles an event where the restart button is clicked, by 
	 * creating a new TttGrid object and setting it to the 
	 * center of the BorderPane
	 */
	private EventHandler<MouseEvent> restart = mouseEvent->{
		TttGrid cellBoard = new TttGrid();
		Group cellGroup = new Group();
		cellGroup.getChildren().add(cellBoard);
		
		
		
		setCenter(cellGroup);
		setBottom(null);
		setAlignment(cellGroup,Pos.TOP_CENTER);
		
		
	};
	
	
	
	
	
	
	
	/**
	 * TttGrid encapsulates a game of Tic-Tac-Toe where two players are assigned
	 * a letter, namely X and O, in which each user will try to match their letters
	 * adjacently in a 3x3 grid either horizontally,vertically or diagonally. 
	 * TttGrid is of type GridPane and contains a 3x3 grid of buttons which are mapped
	 * to TttCell objects.
	 * @author zionchilagan
	 *
	 */
	
	private class TttGrid extends GridPane  {
		/** 2D array of TttCell objects */
		private TttCell[][] cells = new TttCell[3][3];
		/** 2D array of Buton objects */
		private Button[][] buttons = new Button[3][3];
		/** Image used to display letter X */
		private Image xImage;
		/** Image used to display letter O */
		private Image oImage;
		/** ImageView to paint image X */
		private ImageView ivX = null;
		/** Image View to paint image O */
		private ImageView ivO = null;
		/** Value to determine whether or not the game has finished */
		private boolean isFinished = false;
		/** Value to determine the number of moves that transpired */
		private int numMoves = 0;
		/** List of 3 Cells that are adjacently placed, mainly used for effects */
		private ArrayList<TttCell> correctCells = new ArrayList<TttCell>();
		/** Letter to be placed, initial letter is set to X */
		private char letter = 'X';
		
		/**
		 * Constructs no-arg TttGrid by initializing each X and O
		 * images and adding buttons to the GridPane as well as adding
		 * TttCell objects to the 2D array of TttCell objects
		 */
		public TttGrid() {
			
		try {
			xImage = new Image("x.png");
		} catch(NullPointerException e) {
				e.printStackTrace();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		try {
			oImage = new Image("o.png");
		}  catch(NullPointerException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		
			this.setPrefSize(600, 600);
			this.setHgap(1);
			this.setVgap(1);
			initializeGrid();
			
			
			
			setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" 
					+ "-fx-border-width: 5;"
					+ "-fx-border-insets: 5;"
					+ "-fx-border-radius: 5;"
					+ "-fx-border-color: #98d9e3;");
			//this.setGridLinesVisible(true);
			
		}
		
		/**
		 * Method to initialize GridPane by assigning a button
		 * to a particular location in the GridPane and 2D array and
		 * for each Button, a TttCell object is creating in the same location
		 * in a different 2D array.
		 */
		private void initializeGrid() {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					buttons[i][j] = new Button();
					buttons[i][j].setStyle("-fx-border-color: #050505;"
							+"-fx-border-width: 2px;");
					buttons[i][j].setPrefSize(200, 200);
					buttons[i][j].setOnMouseClicked(click);
					buttons[i][j].setOnMouseEntered(enter);
					buttons[i][j].setOnMouseExited(exit);
					cells[i][j] = new TttCell();
					add(buttons[i][j],j,i);
				}
			}
		}
		
		
		
		/**
		 * Handles the event when user clicks on a button in the grid
		 * by setting the image of a button based on the current letter.
		 * This method also checks if a user is clicking on a button
		 * that is already taken and creates an alert box to notify user.
		 * If the game has finished either by a user having 3 adjacently placed
		 * letters in the grid or if the maximum possible number of moves have been 
		 * met, TttGui is notified and displays a text that explained who won or
		 * if the game resulted in a tie.
		 */
		private EventHandler<MouseEvent> click = mouseEvent -> {
			Button button = (Button)mouseEvent.getSource();
			if(isSelected(button)) {
				numMoves++;
				
				if(letter == 'X' && xImage!= null) {
					ivX = new ImageView();
					ivX.setImage(xImage);
					ivX.setFitHeight(110);
					ivX.setFitWidth(110);
					button.setGraphic(ivX);
					button.setStyle("-fx-border-color: #ffc400;"
							+"-fx-border-width: 2px;"
							+"-fx-background-color: #d9dba4;"); 
					if(!isFinished)
						letter = 'O';
				}
				else if(letter == 'O' && oImage != null) {
					ivO = new ImageView();
					ivO.setImage(oImage);
					ivO.setFitHeight(120);
					ivO.setFitWidth(120);
					button.setGraphic(ivO);
					button.setStyle("-fx-border-color: #5900ff;"
							+"-fx-border-width: 2px;"
							+"-fx-background-color: #d1c1e0;");
					if(!isFinished)
						letter = 'X';
				}
			}
			if(numMoves >= 9 && !isFinished) {
				setBottom(winnerPane("Draw"));
				for(Button[] rows: buttons) {
					for(Button b: rows) {
						b.setOnMouseClicked(null);
					}
				} 
			}
			if(isFinished) {
				setBottom(winnerPane("" +letter));
				for(Button[] rows: buttons) {
					for(Button b: rows) {
						b.setOnMouseClicked(null);
					}
				} 
				
				
				ParallelTransition effectAll = new ParallelTransition();
			    setEffectGroup(effectAll,correctCells);
			    effectAll.play();
				
			}
			
			
			
			
		}; 
		
		/**
		 * Helper method to set a FadeTransition for all the cells 
		 * that are not equal to the list of correct cells.
		 * 
		 * @param pt - ParallelTransition which plays a multiple transitions at the same time
		 * @param correctCells - TttCell objects placed in 3 adjacent spots
		 */
		private void setEffectGroup(ParallelTransition pt,ArrayList<TttCell> correctCells) {
			
			for(int i = 0; i < 3; i++) {
				for(int y = 0; y <3; y++) {
					System.out.println("(" + i + ", " + y + ")");
					if(!cells[i][y].equals(correctCells.get(i))) {
						 
						
						  FadeTransition individualEffects = new FadeTransition();
						 
						  individualEffects.setDuration(new Duration(900));
						  individualEffects.setFromValue(1.0);
						  individualEffects.setToValue(0.0);
						  individualEffects.setCycleCount(4);
						  individualEffects.setAutoReverse(true);
						  individualEffects.setNode(buttons[i][y]);
						  pt.getChildren().add(individualEffects);
					}
				}
			} 
			
		
		}
		
		/**
		 * Handles event where user hovers mouse pointer over a button
		 * by creating an inner shadow
		 */
		private EventHandler<MouseEvent> enter = mouseEvent -> {
			Button button = (Button)mouseEvent.getSource();
			
			button.setEffect(new InnerShadow(60, Color.web("#fbff00")));
			
			
		}; 
		
		/**
		 * Handles event where user moves mouse point away from
		 * button by removing any effect
		 */
		private EventHandler<MouseEvent> exit = mouseEvent -> {
			Button button = (Button)mouseEvent.getSource();
			
			button.setEffect(null);
			
			
		}; 
		
		/**
		 * Method to check is a user clicked on a button already taken, if 
		 * the cell is taken, an alert box appears
		 * @param button -  Button that is being clicked
		 * @return - True if the button being clicked was empty, false if it wasnt
		 */
		private boolean isSelected(Button button) {
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(button.equals(buttons[i][j]) && cells[i][j].getIsEmpty()) {
						cells[i][j].setCharacter(letter);
						//cells[i][j].setIsEmpty(false);
						checkAll(cells[i][j],i,j);
						return true;
					}
					else if(button.equals(buttons[i][j]) && !cells[i][j].getIsEmpty()) {
						TttGUI.this.setEffect(new GaussianBlur());
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Oh no!");
						alert.setHeaderText("Invalid Move");
						alert.setContentText("Cannot click on a button with a letter! Click Ok to continue");
						Optional<ButtonType> result = alert.showAndWait();
						 if ((result.isPresent() && result.get() == ButtonType.OK) || (!result.isPresent())) {
							
						     TttGUI.this.setEffect(null);
						 }
						 
						 
						
						
						
					}
				}
			}
			return false;
		}
		
		/**
		 * Helper method to check if a cell is placed in 3
		 * adjacent spots either horizontally, vertically or diagonally
		 * @param cell - TttCell object to determine the winner
		 * @param x - Row location in the 2D array
		 * @param y - Column location in the 2D array
		 * @return - True if a cell a row, column or diagonal has matching
		 * TttCell objects
		 */
		private boolean checkAll(TttCell cell,int x, int y) {
			if(checkRows(cell,x)) {
				isFinished = true;
				return true;
			}
			else if(checkColumns(cell,y)) {
				isFinished = true;
				return true;
			}
			else if(checkLeftDiagonal(cell)) {
				isFinished = true;
				return true;
			} 
			return false;
		}
		
		/**
		 * Helper method to check if 3 equivalent cells
		 * are positioned horizontally
		 * @param cell - Cell to check
		 * @param xPos - Row of Cell
		 * @return - True if a row of cells are equal to each other
		 */
		private boolean checkRows(TttCell cell,int xPos) {
			int counter = 0;
			
			System.out.println(cell);
			for(int j = 0; j < 3; j++) {
				if(cell.equals(cells[xPos][j]) && counter != 3) {
					counter++;
					correctCells.add(cell);
				}
				else {
					counter = 0;	
				}	
			}
			
			if(counter == 3) {
				return true;
			}
			else {
				correctCells.clear();
				return false;
			}
		}
		
		/**
		 * Helper method to check if 3 equivalent cells
		 * are positioned vertically
		 * @param cell - Cell to check
		 * @param yPos - Column of Cell
		 * @return - True if a column of cells are equal to each other
		 */
		private boolean checkColumns(TttCell cell,int yPos) {
			int counter = 0;
			System.out.println(cell);
			for(int j = 0; j < 3; j++) {
				if(cell.equals(cells[j][yPos]) && counter != 3) {
					counter++;
					correctCells.add(cell);
				}
				else 
					counter = 0;
			}
			if(counter == 3) {
				
				return true;
			}
			correctCells.clear();
			return false;
		}
		
		/**
		 * Helper method to check if 3 equivalent cells
		 * are positioned diagonally from top left to bottom right, if
		 * that doesn't exist, check diagonal from top right to bottom left
		 * @param cell - cell to check
		 * @return - true if a diagonal of cells are equal to each other
		 */
		private boolean checkLeftDiagonal(TttCell cell) {
			int leftCounter = 0;
			System.out.println(cell);
			for(int j = 0; j < 3; j++) {
				if(cell.equals(cells[j][j]) && leftCounter != 3) {
					leftCounter++;
					correctCells.add(cell);
				}
				else 
					leftCounter = 0;	
			}
			
			if(leftCounter == 3) {
				return true;
			}
			else {
				correctCells.clear();
				return checkRightDiagonal(cell);
			}
			
		
		}
		
		/**
		 * Helper method to check diagonal from top right to bottom left
		 * for equivalent cells
		 * @param cell - Cell to check 
		 * @return true if the diagonal from top right to bottom left
		 * contains 3 equivalent cells
		 */
		private boolean checkRightDiagonal(TttCell cell) {
			int rightIndex = 2;
			int rightCounter = 0;
			for(int i = 0; i < 3; i++) {
				if(cell.equals(cells[i][rightIndex]) && rightCounter != 3) {
					rightCounter++;
					correctCells.add(cell);
				}
				else
					rightCounter = 0;
				rightIndex= (rightIndex-1) % 3;
			}
			if(rightCounter == 3) {
				return true;
			}
			else {
				correctCells.clear();
				return false;
			}
		}
		
		
	}
	
}
