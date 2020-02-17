package source;

import static source.OceanMap.dimensions;

import java.awt.*;
import java.util.Random;

//  contains the coordinates for pirate ship
//        each pirate island launches one pirate ship
public class PirateShip  {
    private static Point currentLocation;
    static int[][] ocean;
    static Random rand = new Random();

    //    constructor
    PirateShip() {
        ocean = OceanMap.getMap();
        int flag = 1;
        int x;
        int y;
        do {
            //      add the pirate ship if its in ocean

            x = new Random().nextInt(dimensions);
            y = new Random().nextInt(dimensions);

        } while (x!=0 && y!=0&&ocean[x][y] != 0);
        if(x<9 && ocean[x+1][y]==0){
            ocean[++x][y] = 3;
            flag = 0;
        }
        if(flag==1 && x>0 && ocean[x-1][y]==0){
            ocean[--x][y] = 3;
            flag = 0;
        }
        if(flag==1 && y>0 && ocean[x][y-1]==0){
            ocean[x][--y] = 3;
            flag = 0;
        }
        if(flag==1 && y<9 && ocean[x][y+1]==0 ){
            ocean[x][++y] = 3;
            flag = 0;
        }
        currentLocation = new Point(x,y);


    }

    //  returns the location of the pirate ship
    public static Point getPirateLocation(){
		
		return currentLocation;
	}
 
}
