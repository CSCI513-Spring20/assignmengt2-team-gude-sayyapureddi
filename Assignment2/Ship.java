package source;

import static source.OceanMap.dimensions;

import java.awt.*;
import java.util.*;
import java.util.Random;

// contains the coordinates of the ship
public class Ship extends Observable {

	private Point currentLocation;
	static Random rand = new Random();
	int[][] ocean;
	int a = new Random().nextInt(dimensions);
	int b = new Random().nextInt(dimensions);

	Ship() {
		currentLocation = new Point(a, b);
		ocean = OceanMap.getMap();
		ocean[a][b] = 1;

	}

	// returns location of the ship
	public Point getShipLocation() {
		return currentLocation;
	}

	// setter for ship location
	private void setShipLocation(Point newLocation) {
		ocean[currentLocation.x][currentLocation.y] = 0;
		currentLocation = newLocation;
		ocean[currentLocation.x][currentLocation.y] = 1;
	}

	// when right button is clicked
//    checks if its not on the edge
//    if true then sends message to the pirate ship observer
	public void goEast() {
		if (currentLocation.x < 9 && (ocean[currentLocation.x + 1][currentLocation.y] == 0)) {
			setShipLocation(new Point(currentLocation.x + 1, currentLocation.y));
			setChanged();
			notifyObservers(currentLocation);

		}
	}
//    when left button is clicked

	public void goWest() {
		if (currentLocation.x > 0 && (ocean[currentLocation.x - 1][currentLocation.y] == 0)) {
			setShipLocation(new Point(currentLocation.x - 1, currentLocation.y));
			setChanged();
			notifyObservers(currentLocation);
		}
	}

	// when top button is clicked
	public void goNorth() {
		if (currentLocation.y > 0 && (ocean[currentLocation.x][currentLocation.y - 1] == 0)) {
			setShipLocation(new Point(currentLocation.x, currentLocation.y - 1));
			setChanged();
			notifyObservers(currentLocation);
		}
	}

	// when down button is clicked
	public void goSouth() {
		if (currentLocation.y < 9 && (ocean[currentLocation.x][currentLocation.y + 1] == 0)) {
			setShipLocation(new Point(currentLocation.x, currentLocation.y + 1));
			setChanged();
			notifyObservers(currentLocation);
		}
	}

}
