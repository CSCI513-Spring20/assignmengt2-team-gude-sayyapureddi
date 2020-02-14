import java.awt.Point;

public class Ship {
	int xCell;
	int yCell;

	public Ship(int x, int y) {

		xCell = x;
		yCell = y;
	}

   //event handle for right
	public void goEast() {
		if (xCell != 9) {
			xCell++;
		}
	}

   //event handle for left
	public void goWest() {
		if (xCell != 0) {
			xCell--;
		}
	}

	// event handle for top
	public void goNorth() {
		if (yCell != 0) {
			yCell--;
		}
	}

	// event handle for bottom
	public void goSouth() {
		if (yCell != 9) {
			yCell++;
		}
	}

	// getShipLocation returns the position of the ship after event(or) action made
	public Point getShipLocation() {
		return new Point(xCell, yCell);
	}

}
