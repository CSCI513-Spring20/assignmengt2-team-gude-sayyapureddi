
import java.awt.Point;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
	final int dimension = 10; // We are creating 10X10 maps
	final int scale = 50; // Scale everything by 50.
	Image shipImage,pirateshipImage;
	ImageView shipImageView,pirateshipImageView;
	OceanMap oceanMap;// for ocean class
	Pane root;
	Scene scene;
	Ship ship;// for ship class
	Point startPosition;

	public static void main(String[] args) {
		launch(args);
		// TODO Auto-generated method stub

	}

	@Override
	public void start(Stage oceanStage) throws Exception {
		 OceanMap oceanMap = new OceanMap();

		root = new AnchorPane();
		drawMap();

		startPosition = OceanMap.getShipLocation();
		ship = new Ship(startPosition.x, startPosition.y);// sends position of the ship to ship class from ocean map
															// class
		loadShipImage();// load the ship image
		loadpirateShipImage();// load the pirate ship image

		scene = new Scene(root, 500, 500);
		oceanStage.setTitle("Christopher columbus Sails The Blue Ocean");
		oceanStage.setScene(scene);
		oceanStage.show();// display the grid and ship
		startSailing();

	}

	// startSailing() this is an event handle method this handle various movement of
	// the ship
	private void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				switch (ke.getCode()) {
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				}
				shipImageView.setX(ship.getShipLocation().x * scale);
				shipImageView.setY(ship.getShipLocation().y * scale);
			}
		});

	}

    // draw map will draw the grid of ocean with each rectangle blue color 
	public void drawMap() {
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
				rect.setStroke(Color.BLACK); // We want the black outline
				rect.setFill(Color.PALETURQUOISE); // I like this color better than BLUE
				root.getChildren().add(rect); // Add to the node tree in the pane

			}
		}
	}

	// loads the target ship image to grid with random positions
	private void loadShipImage() {
		// Load target image
		Image shipImage = new Image("\\ship.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(OceanMap.getShipLocation().x * scale);
		shipImageView.setY(OceanMap.getShipLocation().y * scale);
		root.getChildren().add(shipImageView);
	}
	private void loadpirateShipImage() {
		// Load target image
		Image pirateshipImage = new Image("\\pirateShip.png", 50, 50, true, true);
		pirateshipImageView = new ImageView(pirateshipImage);
		pirateshipImageView.setX(OceanMap.getpirateShipLocation().x * scale);
		pirateshipImageView.setY(OceanMap.getpirateShipLocation().y * scale);
		root.getChildren().add(pirateshipImageView);
	}

}
