package source;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class OceanExplorer extends Application {

	int[][] islandMap;
	Pane root;
	final int dimensions = 10;
	final int scale = 50;
	final int islandCount = 5;
	final int pirateShipsCount = 2;
	final int pirateIslandsCount = 2;
	Image shipImage, islandImage, pirateShipImage, pirateIslandImage;
	ImageView shipImageView, islandImageView;
	ImageView[] pirateShipImageView, pirateIslandImageView;
	OceanMap oceanMap;
	Scene scene;
	Ship ship;
	PirateShip[] pirateShips;
	PirateIsland[] pirateIslands;
	public static Label label;
	public static Text text, text2;
	Stage oceanStage;
	public static boolean close;
	Button button;

	@Override
	public void start(Stage oceanStage) throws Exception {
		this.oceanStage = oceanStage;
		// create ocean grid
		oceanMap = new OceanMap(dimensions, islandCount);
		islandMap = OceanMap.getMap();
		// layout

		root = new AnchorPane();
		// label created
		label = new Label();
		label.setText("Enjoy The Game");
		label.setPrefWidth(500);
		label.setPrefHeight(50);
		label.setLayoutX(0);
		label.setLayoutY(550);
		label.setTextAlignment(TextAlignment.CENTER);

		// pirate ship initializations
		pirateShips = new PirateShip[pirateShipsCount];
		pirateIslands = new PirateIsland[pirateIslandsCount];

		for (int i = 0; i < pirateIslandsCount; i++) {
			pirateIslands[i] = new PirateIsland();
		}

		for (int i = 0; i < pirateShipsCount; i++) {
			pirateShips[i] = new PirateShip(pirateIslands[i].getPirateIslandLocation());

		}
		pirateShipImageView = new ImageView[pirateShipsCount];
		pirateIslandImageView = new ImageView[pirateIslandsCount];
		button = new Button("Reset");
		button.setPrefSize(500, 50);
		button.setLayoutX(0);
		button.setLayoutY(500);
		button.setOnAction(event -> {
			try {
				close = false;
				start(oceanStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		ship = new Ship();
		// add observers
		for (int i = 0; i < pirateShipsCount; i++) {
			ship.addObserver(pirateShips[i]);
		}
		// loadShipImage();

		drawMap();
		root.getChildren().add(button);
		root.getChildren().add(label);

		scene = new Scene(root, 500, 600);

		oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		oceanStage.setScene(scene);
		oceanStage.show();
		// message pop after ship is caught
		text = new Text();
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 28));
		text.setX(10);
		text.setY(300);
		text.setFill(Color.BROWN);
		root.getChildren().add(text);
		// alert text for reset
		text2 = new Text();
		text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		text2.setX(20);
		text2.setY(350);
		text2.setFill(Color.BLACK);
		root.getChildren().add(text2);
		startSailing();
	}

	// draw map
	private void drawMap() {
		int[][] oceanGrid = islandMap;

		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
				rect.setStroke(Color.BLACK);
				// add ocean
				rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
				// add islands
				if (oceanGrid[x][y] == 2)
					loadIslandImage(x, y);

			}
		}
		//calling various images on to scene
		loadShipImage();
		loadPirateShipImage();
		loadPirateIsland();

	}

	// load ship image
	private void loadShipImage() {
		shipImage = new Image("source/ship.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);

		shipImageView.setX(ship.getShipLocation().x * scale);
		shipImageView.setY(ship.getShipLocation().y * scale);

		root.getChildren().add(shipImageView);

	}
	// load pirate ship image

	private void loadPirateShipImage() {
		pirateShipImage = new Image("source/pirateShip.png", 50, 50, true, true);
		int t = 0;
		while (t < pirateShipsCount) {

			pirateShipImageView[t] = new ImageView(pirateShipImage);

			pirateShipImageView[t].setX(pirateShips[t].getPirateLocation().x * scale);
			pirateShipImageView[t].setY(pirateShips[t].getPirateLocation().y * scale);
			root.getChildren().add(pirateShipImageView[t]);
			t++;
		}

	}

//  load pirate island
	private void loadPirateIsland() {
		pirateIslandImage = new Image("source/pirateIsland.png", 50, 50, true, true);
		int t = 0;
		while (t < pirateIslandsCount) {

			pirateIslandImageView[t] = new ImageView(pirateIslandImage);
			pirateIslandImageView[t].setX(pirateIslands[t].getPirateIslandLocation().x * scale);
			pirateIslandImageView[t].setY(pirateIslands[t].getPirateIslandLocation().y * scale);
			root.getChildren().add(pirateIslandImageView[t]);
			t++;
		}

	}

	// load islands to the map
	private void loadIslandImage(int x, int y) {
		islandImage = new Image("source/island.jpg", 50, 50, true, true);
		islandImageView = new ImageView(islandImage);
		islandImageView.setX((double) x * scale);
		islandImageView.setY((double) y * scale);
		root.getChildren().add(islandImageView);
	}

	// event handler method
	// if we need to close the application after game over we can uncomment the code
	// in the below if case
	private void startSailing() {
		scene.setOnKeyPressed(ke -> {
			if (close) {
//		                try {
//		                    sleep(2000);
//		                } catch (InterruptedException e) {
//		                    e.printStackTrace();
//		                }
//		                exit();

			} else {
				switch (ke.getCode()) {
				case RIGHT:// if we click right arrow
					ship.goEast();
					break;
				case LEFT:// if we click left arrow
					ship.goWest();
					break;
				case UP:// if we click Top arrow
					ship.goNorth();
					break;// if we click Bottom arrow
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				}
			}
			//new location update for ship after event handle
			shipImageView.setX(ship.getShipLocation().x * scale);
			shipImageView.setY(ship.getShipLocation().y * scale);
			//new location update for pirate ships after every ships movement
			for (int i = 0; i < pirateShipsCount; i++) {
				pirateShipImageView[i].setX(pirateShips[i].getPirateLocation().x * scale);
				pirateShipImageView[i].setY(pirateShips[i].getPirateLocation().y * scale);
			}
		});
	}
//main method
	public static void main(String[] args) {
		launch(args);
	}
}
