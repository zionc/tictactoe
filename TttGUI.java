package ticTacToeAssignment;




import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		
		
		//centerLayout.getChildren().add(winnerPane(' '));
		
		
		//VBox winnerPane = winnerPane(' ');
		//HBox centerPane = new HBox();
		//centerPane.getChildren().add(winnerPane);
		//centerPane.getChildren().add(cellBoard);
		
		HBox titlePane = getTitlePane();
		this.setCenter(cellGroup);
		//this.setBottom(null);
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
	
	public VBox winnerPane(char letter) {
		Button restartButton = new Button("Restart");
		restartButton.setOnMouseClicked(restart);
		if(letter!=' ') {
			Label winnerLabel = new Label();
			winnerLabel.setText("" + letter + " has won!");
			winnerLabel.setStyle("-fx-font: 50 arial;");
			winnerLabel.setTextFill(Color.web("#9b25f5"));
		
			VBox winnerPane = new VBox();
			winnerPane.getChildren().add(winnerLabel);
			winnerPane.setAlignment(Pos.TOP_CENTER);
			winnerPane.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" 
				+ "-fx-border-width: 5;"
				+ "-fx-border-insets: 5;"
				+ "-fx-border-radius: 5;"
				+ "-fx-border-color: #98d9e3;");
		
			winnerPane.getChildren().add(restartButton);
			return winnerPane;
		}
		else {
			VBox winnerPane = new VBox();
			winnerPane.getChildren().add(restartButton);
			winnerPane.setAlignment(Pos.TOP_CENTER);
			winnerPane.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" 
				+ "-fx-border-width: 5;"
				+ "-fx-border-insets: 5;"
				+ "-fx-border-radius: 5;"
				+ "-fx-border-color: #98d9e3;");
			return winnerPane;
			
		}
		
		
	}
	private EventHandler<MouseEvent> restart = mouseEvent->{
		TttGrid cellBoard = new TttGrid();
		Group cellGroup = new Group();
		cellGroup.getChildren().add(cellBoard);
		
		
		
		setCenter(cellGroup);
		setBottom(null);
		setAlignment(cellGroup,Pos.TOP_CENTER);
		
		//this.setPadding(new Insets(10,10,10,10));
	};
	
	
	
	
	
	
	
	
	
	private class TttGrid extends GridPane  {
		private TttCell[][] cells = new TttCell[3][3];
		private Button[][] buttons = new Button[3][3];
		private Image xImage = new Image("x.png");
		private Image oImage = new Image("o.png");
		private ImageView ivX = null;
		private ImageView ivY = null;
		private boolean isFinished = false;
		private ArrayList<TttCell> correctCells = new ArrayList<TttCell>();
		private char letter = 'X';
		public TttGrid() {
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
				//button.setText(""+letter);
			
				if(letter == 'X') {
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
				else {
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
			if(isFinished) {
				setBottom(winnerPane(letter));
				for(Button[] rows: buttons) {
					for(Button b: rows) {
						b.setOnMouseClicked(null);
					}
				} 
				
				
				ParallelTransition effectAll = new ParallelTransition();
				
			   // System.out.println("SIZE: " + correctButtons.size());
			    /*for(int i = 0; i < correctButtons.size(); i++) {
			    	correctButtons.get(i).setStyle("-fx-background-color: #ff424c;");
			    }
			    for(int i = 0; i < 3; i++) {
			    	System.out.println(buttons[0][i].equals(correctButtons.get(i)));
			    } */
			   
			    setEffectGroup(effectAll,correctCells);
			    //for(int i = 0; i < correctButtons.size(); i++) {
			    //	correctButtons.get(i).setStyle("-fx-background-color: #ff424c;");
			    //}
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
						System.out.println("(" + i + ", " + j + ")");
						checkAll(cells[i][j],i,j);
						
						
						return true;
					}
					
					
				}
			}
			return false;
		}
		
		private boolean checkAll(TttCell cell,int x, int y) {
			if(checkRows(cell,x,y)) {
				isFinished = true;
				return true;
			}
			else if(checkColumns(cell,x,y)) {
				isFinished = true;
				return true;
			}
			else if(checkDiagonal(cell)) {
				isFinished = true;
				return true;
			} 
			return false;
		}
		
		private boolean checkRows(TttCell cell,int xPos, int yPos) {
			int counter = 0;
			String s = "";
			System.out.println(cell);
			for(int j = 0; j < 3; j++) {
				if(cell.equals(cells[xPos][j]) && counter != 3) {
					counter++;
					s+="(" + xPos + ", " + j + "), ";
					
					correctCells.add(cell);
					
				}
				else {
					counter = 0;
					s = "";
				}
					
			}
			
			if(counter == 3) {
				System.out.println(cell.getCharacter() + " has won.");
				
				
				System.out.println(s);
				return true;
			}
			else {
				correctCells.clear();
				return false;
			}
		}
		
		private boolean checkColumns(TttCell cell,int xPos, int yPos) {
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
				System.out.println(cell.getCharacter() + " has won.");
				return true;
			}
			correctCells.clear();
			return false;
		}
		private boolean checkDiagonal(TttCell cell) {
			int leftCounter = 0;
			int rightCounter = 0;
			
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
				System.out.println(cell.getCharacter() + " has won.");
				return true;
			}
			else {
				correctCells.clear();
			}
			
			return false;
		}
		
		private char getLetter() {
			return letter;
		}
		
	}
	
}
