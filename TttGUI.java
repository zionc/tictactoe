package ticTacToeAssignment;




import java.util.ArrayList;
import java.util.Optional;

import javafx.animation.Animation;
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



public class TttGUI extends BorderPane {
	
	
	
	
	
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
	
	public VBox winnerPane(String input) {
		Button restartButton = new Button("Restart");
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
		return winnerPane;
		
		
		
	}
	private EventHandler<MouseEvent> restart = mouseEvent->{
		TttGrid cellBoard = new TttGrid();
		Group cellGroup = new Group();
		cellGroup.getChildren().add(cellBoard);
		
		
		
		setCenter(cellGroup);
		setBottom(null);
		setAlignment(cellGroup,Pos.TOP_CENTER);
		
		
	};
	
	
	
	
	
	
	
	
	
	private class TttGrid extends GridPane  {
		private TttCell[][] cells = new TttCell[3][3];
		private Button[][] buttons = new Button[3][3];
		private Image xImage;
		private Image oImage;
		private ImageView ivX = null;
		private ImageView ivY = null;
		private boolean isFinished = false;
		private int numMoves = 0;
		private ArrayList<TttCell> correctCells = new ArrayList<TttCell>();
		private char letter = 'X';
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
					ivY = new ImageView();
					ivY.setImage(oImage);
					ivY.setFitHeight(120);
					ivY.setFitWidth(120);
					button.setGraphic(ivY);
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
		
		private void setEffectGroup(ParallelTransition pt,ArrayList<TttCell> correctCells) {
			
			for(int i = 0; i < 3; i++) {
				for(int y = 0; y <3; y++) {
					System.out.println("(" + i + ", " + y + ")");
					if(!cells[i][y].equals(correctCells.get(i))) {
						 
						
						  FadeTransition individualEffects = new FadeTransition();
						 
						  individualEffects.setDuration(new Duration(800));
						  individualEffects.setFromValue(1.0);
						  individualEffects.setToValue(0.0);
						  individualEffects.setCycleCount(Animation.INDEFINITE);
						  individualEffects.setAutoReverse(true);
						  individualEffects.setNode(buttons[i][y]);
						  pt.getChildren().add(individualEffects);
					}
				}
			} 
			
		
		}
		
		private EventHandler<MouseEvent> enter = mouseEvent -> {
			Button button = (Button)mouseEvent.getSource();
			
			button.setEffect(new InnerShadow(60, Color.web("#fbff00")));
			
			
		}; 
		
		private EventHandler<MouseEvent> exit = mouseEvent -> {
			Button button = (Button)mouseEvent.getSource();
			
			button.setEffect(null);
			
			
		}; 
		
		private boolean isSelected(Button button) {
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(button.equals(buttons[i][j]) && cells[i][j].getIsEmpty()) {
						cells[i][j].setCharacter(letter);
						cells[i][j].setIsEmpty(false);
						checkAll(cells[i][j],i,j);
						return true;
					}
					else if(button.equals(buttons[i][j]) && !cells[i][j].getIsEmpty()) {
						TttGUI.this.setEffect(new GaussianBlur());
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Oh no!");
						alert.setHeaderText("Invalid Move");
						alert.setContentText("Cannot click on a button with a letter! Click Ok to continue");
						//alert.showAndWait();
						Optional<ButtonType> result = alert.showAndWait();
						 if (result.isPresent() && result.get() == ButtonType.OK) {
							 System.out.println(alert.getButtonTypes());
						     TttGUI.this.setEffect(null);
						 }
						
						
						
					}
				}
			}
			return false;
		}
		
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
