import java.awt.Point;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OceanMap extends OceanExplorer {
	static Random rand = new Random();
	static int [][] grid ;
	static int a = rand.nextInt(10); // Generating a random number from 0 -10
	static int b = rand.nextInt(10); // Generating a random number from 0 -10

	public OceanMap()
 {
		grid = new int[11][11];
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {

				grid[x][y]=0;
			}
		}
	}
	

	// gets position of ship by an random generator
	public static Point getShipLocation() {
		boolean Ship = true;
		int a = rand.nextInt(10);
		int b = rand.nextInt(10); 
		while(Ship){
	        if(grid[a][b] == 0){
	        	Ship = false;
	        	grid[a][b] = 1;
	        }
	     }
		return new Point(a, b);
	}
	public static Point getpirateShipLocation() {
		boolean Ship = true;
		int a = rand.nextInt(10);
		int b = rand.nextInt(10); 
		while(Ship){
	        if(grid[a][b] == 0&&grid[a][b]!=1){
	        	Ship = false;
	        	grid[a][b] = 2;
	        }
	     }
		return new Point(a, b);
	}
}
