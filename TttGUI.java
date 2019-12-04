package ticTacToeAssignment;




import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;



public class TttGUI extends BorderPane {
	
	
	
	public TttGUI() {
		TttGrid cellBoard = new TttGrid();
		Group group = new Group();
		group.getChildren().add(cellBoard);
		
		HBox titlePane = getTitlePane();
		this.setCenter(group);
		this.setTop(titlePane);
		setAlignment(group,Pos.CENTER);
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
	
	
	private class TttGrid extends GridPane  {
		private TttCell[][] cells = new TttCell[3][3];
		private Button[][] buttons = new Button[3][3];
		private Image xImage = new Image("x.png");
		private Image yImage = new Image("y.png");
		private ImageView ivX = null;
		private ImageView ivY = null;
	
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
					cells[i][j] = new TttCell();
					add(buttons[i][j],j,i);
				}
			}
		}
		
		
		
		
		private EventHandler<MouseEvent> click = mouseEvent -> {
			Button button = (Button)mouseEvent.getSource();
			if(isSelected(button)) {
				//button.setText(""+letter);
				FadeTransition ft = new FadeTransition();
			      ft.setNode(button);
			      ft.setDuration(new Duration(2000));
			      ft.setFromValue(1.0);
			      ft.setToValue(0.0);
			      ft.setCycleCount(6);
			      ft.setAutoReverse(true);
			      ft.play();
				if(letter == 'X') {
					ivX = new ImageView();
					ivX.setImage(xImage);
					ivX.setFitHeight(110);
					ivX.setFitWidth(110);
					button.setGraphic(ivX);
					button.setStyle("-fx-border-color: #ffc400;"
							+"-fx-border-width: 2px;"
							+"-fx-background-color: #d9dba4;");
					letter = 'O';
				}
				else {
					ivY = new ImageView();
					ivY.setImage(yImage);
					ivY.setFitHeight(110);
					ivY.setFitWidth(110);
					button.setGraphic(ivY);
					button.setStyle("-fx-border-color: #5900ff;"
							+"-fx-border-width: 2px;"
							+"-fx-background-color: #d1c1e0;");
					letter = 'X';
				}
			}
			else
				button.setStyle("-fx-background-color: #ff424c;");
			
			
		}; 
		
		private boolean isSelected(Button button) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(button.equals(buttons[i][j]) && cells[i][j].getIsEmpty()) {
						cells[i][j].setCharacter(letter);
						cells[i][j].setIsEmpty(false);
						System.out.println("(" + i + ", " + j + ")");
						checkRows(cells[i][j],i,j);
						return true;
					}
					
				}
			}
			return false;
		}
		
		private boolean checkRows(TttCell cell,int xPos, int yPos) {
			int counter = 0;
			System.out.println(cell);
			
			for(int j = 0; j < 3; j++) {
				if(cell.equals(cells[xPos][j]) && counter != 3) {
					counter++;
					
				}
				else
					counter = 0;
					
			}
			
			if(counter == 3) {
				System.out.println(cell.getCharacter() + " has won.");
				return true;
			}
			return false;
				
			
		}
	}
	
}
