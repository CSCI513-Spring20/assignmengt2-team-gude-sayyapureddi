package source;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;



//  contains the coordinates for pirate ship
//        each pirate island launches one pirate ship
public class PirateShip implements Observer {
    private Point currentLocation;
    int[][] ocean;

    //    constructor
    PirateShip(Point Location) {
        ocean = OceanMap.getMap();
        int flag = 1;
        int x = Location.x;
        int y = Location.y;
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
    public Point getPirateLocation() {
        return currentLocation;
    }

    // setter for pirate location
    private void setPirateLocation(Point newLocation) {
        ocean[currentLocation.x][currentLocation.y] = 0;
        currentLocation = newLocation;
        ocean[currentLocation.x][currentLocation.y] = 3;
    }


    @Override
//    when the ship moves the pirate ship receives the location of the ship
//    its then used checked with the best possible next step (Greedy) from the possible for the pirate ship
    public void update(Observable o, Object arg) {

        // using manhattan distance for the distance between the pirate ship and columbus
        Point shipLocation = (Point) arg;
        Point nextMove = currentLocation;

        int distance = 999;
        int flag = 1;
        if (currentLocation.x > 0 ) {
//            checking left
            int temp = Math.abs(currentLocation.x - shipLocation.x - 1) + Math.abs(currentLocation.y - shipLocation.y);
            if(temp!=0){if (temp < distance && ocean[currentLocation.x - 1][currentLocation.y] !=2
                    && ocean[currentLocation.x - 1][currentLocation.y] !=3 && ocean[currentLocation.x - 1][currentLocation.y] !=4) {
                distance = temp;
                nextMove = new Point(currentLocation.x - 1, currentLocation.y);

            }
            }
            else{
                flag = 0;
            }
        }
        if (currentLocation.x < 9 && flag ==1) {
//            checking right
            int temp = Math.abs(currentLocation.x - shipLocation.x + 1) + Math.abs(currentLocation.y - shipLocation.y);
            if(temp!=0)
            {if (temp < distance && ocean[currentLocation.x + 1][currentLocation.y] !=2
                    && ocean[currentLocation.x + 1][currentLocation.y] !=3 && ocean[currentLocation.x + 1][currentLocation.y] !=4) {

                distance = temp;
                nextMove = new Point(currentLocation.x + 1, currentLocation.y);

            }}
            else{
                flag= 0;
            }
        }
        if (currentLocation.y < 9 && flag==1) {
//            checking down
            int temp = Math.abs(currentLocation.x - shipLocation.x) + Math.abs(currentLocation.y - shipLocation.y + 1);
            if(temp!=0)
            {if (temp < distance && ocean[currentLocation.x][currentLocation.y + 1] !=2 && ocean[currentLocation.x][currentLocation.y + 1] !=3
                    && ocean[currentLocation.x][currentLocation.y + 1] !=4 ) {
                distance = temp;
                nextMove = new Point(currentLocation.x, currentLocation.y + 1);

            }}
            else{
                flag= 0;
            }
        }
        if (currentLocation.y > 0) {
//            checking up

            int temp = Math.abs(currentLocation.x - shipLocation.x) + Math.abs(currentLocation.y - shipLocation.y - 1);
            if(temp!=0)
            {if (temp < distance && ocean[currentLocation.x][currentLocation.y - 1] !=2 && ocean[currentLocation.x][currentLocation.y - 1] !=3
                    && ocean[currentLocation.x][currentLocation.y - 1] !=4 ) {
                distance = temp;
                nextMove = new Point(currentLocation.x, currentLocation.y - 1);

            }}
            else{
                flag=0;
            }
        }
        if(flag==1)
        setPirateLocation(nextMove);

    }
}
