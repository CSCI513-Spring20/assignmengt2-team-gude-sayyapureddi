package source;

import java.awt.*;
import java.util.Random;

import static source.OceanMap.dimensions;

public class PirateIsland {
    private Point currentLocation;
    int[][] ocean;
// stores the location of pirateislands
    PirateIsland(){
        ocean = OceanMap.getMap();
        int x;
        int y;
        do {
            //      add the pirate ship if its in ocean

            x = new Random().nextInt(dimensions);
            y = new Random().nextInt(dimensions);

        } while (x!=0 && y!=0&&ocean[x][y] != 0);
        currentLocation = new Point(x, y);
        ocean[x][y] = 4;
    }
// gets the location of the pirate island
    public Point getPirateIslandLocation(){
        return currentLocation;
    }


}
