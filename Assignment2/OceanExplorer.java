package source;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class OceanExplorer extends Application {

    int[][] islandMap;
    Pane root;
    final int dimensions = 10;
    final int scale = 50;
    final int islandCount = 10;
    final int pirateShipsCount = 2;
    final int pirateIslandsCount = 2;
    Image shipImage,islandImage,pirateShipImage,pirateIslandImage;
    ImageView shipImageView,islandImageView;
    ImageView[] pirateShipImageView,pirateIslandImageView;
    OceanMap oceanMap;
    Scene scene;
    Ship ship;
    PirateShip[] pirateShips;
    PirateIsland[] pirateIslands;
    Button button;




    @Override
    public void start(Stage oceanStage) throws Exception {

        // create ocean grid
        oceanMap = new OceanMap(dimensions, islandCount);
        islandMap = OceanMap.getMap();
        // layout
        root = new AnchorPane();
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

        drawMap();
        root.getChildren().add(button);



        ship = new Ship();
        // add observers
        for (int i = 0; i < pirateShipsCount; i++) {
            ship.addObserver(pirateShips[i]);
        }

        loadShipImage();


        scene = new Scene(root, 500, 550);

        oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
        oceanStage.setScene(scene);
        oceanStage.show();
        startSailing();
    }

    //    draw map
    private void drawMap() {
        int[][] oceanGrid = islandMap;

        for (int x = 0; x < dimensions; x++) {
            for (int y = 0; y < dimensions; y++) {
                Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
                rect.setStroke(Color.BLACK);
//                add ocean
                rect.setFill(Color.PALETURQUOISE);
                root.getChildren().add(rect);
/*               add islands
                if (oceanGrid[x][y] == 2)
                    loadIslandImage(x, y);*/

            }
        }
       // loadPirateIsland();
        loadPirateShipImage();

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

   
    //  event handler method
    private void startSailing() {
        scene.setOnKeyPressed(ke -> {
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
            for (int i = 0; i < pirateShipsCount; i++) {
                pirateShipImageView[i].setX(pirateShips[i].getPirateLocation().x * scale);
                pirateShipImageView[i].setY(pirateShips[i].getPirateLocation().y * scale);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
