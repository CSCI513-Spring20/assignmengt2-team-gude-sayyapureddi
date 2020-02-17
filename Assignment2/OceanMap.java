package source;

import java.util.Random;

// grid containing the points of the
public class OceanMap {

    private static int islandCount;
    public static int dimensions;
    private static int[][] cell;


    OceanMap(int dimensions, int islandCount) {
        OceanMap.dimensions = dimensions;
        OceanMap.islandCount = islandCount;

        cell = new int[dimensions][dimensions];
        addIslands();

    }


    public static int[][] getMap() {
        return cell;
    }

    private static void addIslands() {
        int t = islandCount;
        while (t > 0) {
//            1 is for islands
            int x = new Random().nextInt(dimensions);
            int y = new Random().nextInt(dimensions);
            if (!(x < 2 && y < 2)&&cell[x][y]==0) {
                cell[x][y] = 2;
                t--;
            }
        }
    }

}
