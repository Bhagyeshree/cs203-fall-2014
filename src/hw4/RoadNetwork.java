package hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class RoadNetwork {

    java.util.ArrayList<Road> roads =  new java.util.ArrayList<Road>() ;
    HashMap<Long, Point> allPoints = new HashMap<Long, Point>();
    private Scanner scanner;
    
    HashMap<Long, Road> tableOfRoads = null;
    HashMap<Long, Point> tableOfPoints = null;
    		
    
    /*
     * This method returns all possible cell network combinations
     *
     */
    ArrayList<CellNetwork> getAllCellConfigurations(int N, double radius) {

        /*
         * This method will return an array of cellular networks
         * Each network consist of cell towers
         */
        ArrayList<CellNetwork> cellNetworks = new ArrayList<CellNetwork>();

        /*
         * obtain points from allPoints hash table
         */
        ArrayList<Point> points = new ArrayList<Point>(allPoints.values());
        
        /*
         * Use the combinations class to find unique combination of points
         */
        Set<Set<Point>> pointSets = new Combinations<Point>().getCombinationsFor(points, N);
        
        
        for (Set<Point> s : pointSets) {
            CellNetwork cn = new CellNetwork();
            for (Point p : s) {
                CellTower tower = new CellTower(p, radius);
                cn.add(tower);
            }
            cellNetworks.add(cn);
        }
        
        return cellNetworks;
    }

    private void readTextFormat(File file) {
        Road road = new Road();
        java.util.ArrayList<Point> tempPoints = 
                new java.util.ArrayList<Point>();
        try {
            scanner = new Scanner( file );
            while ( scanner.hasNext() ) {
                String line = scanner.nextLine();
                Point p = new Point( line );
                Boundaries.update( p );
                tempPoints.add( p );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        road.build( tempPoints );
        roads.add(road);
    }

    public RoadNetwork(File file) {
        readTextFormat(file);
    }


    private void readOSMFormat(File file) {
       OSM.extractBoundaries(file);
       tableOfPoints = OSM.extractNodes(file);
       tableOfRoads = OSM.extractWays(file, tableOfPoints);
    }

    public RoadNetwork(File file, boolean isOSM) {
        if (isOSM) {
            readOSMFormat(file); 
        } else {
            readTextFormat(file);
        }
    }

    public void draw() {
        for (Road road : roads ) {
            road.draw();
        }
    }

    public void dump() {
        for (Road road : roads ) {
            road.dump();
        }
    }

	public Road getLongestRoad() {
		int max = -Integer.MAX_VALUE;
		Road maxRoad = null;
		
		for (Road road : tableOfRoads.values() ) {
			int count = road.getAllPoints().size();
			if (count > max) {
				count = max;
				maxRoad = road;
			}
		}
		
		return maxRoad;
	}
}
