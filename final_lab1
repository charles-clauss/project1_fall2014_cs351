package cs351lab1;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.net.URL;
import java.net.MalformedURLException;

/**
* The Astar class is responsible for opening and reading the map,
* finding the shortest path in a best first search manner, and
* outputting the path to the terminal along with its cost.
*/
public class Astar {
  public static int[][] map = new int[30][28];
  public static int startx;
  public static int starty;
  public static int finishx;
  public static int finishy;

  public Astar() {
  }

  /**
  * Parses the provided text file into an array that stores the
  * cost of travelling onto each position.
  * @throws MalformedURLException In the case that the mapweight file no longer exists
  * @throws IOException In the case that an error occurs reading lines from the file or
  * there is an error reading the data contained at the URL with a BufferedReader
  */
  public void readMap() throws MalformedURLException, IOException {
    //I made this a URL mostly as practice, but also so that there wouldn't be
    //file system issues when I turned in my code.
    URL mapfile = new URL("https://www.cs.unm.edu/~toriadam/mapweight.txt");
    Reader fileReader = new InputStreamReader(mapfile.openStream());
    BufferedReader reader = new BufferedReader(fileReader);
    String line;
    int row = 0;
    int column = 0;
    int temp = 0;
    while((line = reader.readLine()) != null) {
      for(char c : line.toCharArray()) {
        switch(c) {
          case 'X':
            temp = 100;
            break;
          case '0':
            temp = 1;
            break;
          case '1':
            temp = 2;
            break;
          case '2':
            temp = 4;
            break;
          case '>':
            temp = 0;
            startx = column;
            starty = row;
            break;
          case '!':
            temp = 0;
            finishx = column;
            finishy = row;
            break;
          default:
            temp = -1;
            break;
        }
        map[row][column] = temp;
        column++;
      }
      column = 0;
      row++;
    }
  }

  /**
  * Implements the Astar best first search algorithm to find the
  * shortest path from the start to the goal.
  * @return A string containing the cost and the list of moves
  * to perform to reach the goal as cheaply as possible.
  */
  public String shortestPath() {
    MapData start = new MapData(startx, starty, map[starty][startx]);
    MapData visit;
    Comparator<MapData> compare = new SortMapData();
    PriorityQueue<MapData> pq = new PriorityQueue<MapData>(25, compare);
    int visitx;
    int visity;
    int westx;
    int eastx;
    int southy;
    int northy;
    pq.add(start);
    ArrayList<MapData> visited = new ArrayList<MapData>();
    while((visit = pq.poll()) != null) {
      visited.add(visit);
      visitx = visit.getX();
      visity = visit.getY();
      if(visitx == finishx && visity == finishy) {
        return constructPath(visit);
      }
      westx = visitx - 1;
      eastx = visitx + 1;
      southy = visity + 1;
      northy = visity - 1;
      if(westx >= 0) {
        MapData westNeighbor = new MapData(westx, visity, visit.getPathCost() + map[visity][westx], visit);
        if(!visited.contains(westNeighbor)) {
          pq.add(westNeighbor);
        }
      }
      if(eastx < map[0].length) {
        MapData eastNeighbor = new MapData(eastx, visity, visit.getPathCost() + map[visity][eastx], visit);
        if(!visited.contains(eastNeighbor)) {
          pq.add(eastNeighbor);
        }
      }
      if(southy < map.length) {
        MapData southNeighbor = new MapData(visitx, southy, visit.getPathCost() + map[southy][visitx], visit);
        if(!visited.contains(southNeighbor)) {
          pq.add(southNeighbor);
        }
      }
      if(northy >= 0) {
        MapData northNeighbor = new MapData(visitx, northy, visit.getPathCost() + map[northy][visitx], visit);
        if(!visited.contains(northNeighbor)) {
          pq.add(northNeighbor);
        }
      }
    }
    return "Failure.";
  }

  /**
  * A method that uses each MapData's origin field to construct
  * the path that was taken from the finish back to the start.
  * @return The list of moves to reach the finish from the start.
  */
  public String constructPath(MapData finish) {
    MapData previous = finish;
    MapData current = finish.getOrigin();
    String path = "";
    while(current != null) {
      /* This code remains as a snippet to indicate what was done to create
         the generated image.
      for(int i = 0; i < 16; i++) {
        for(int j = 0; j < 16; j++) {
          pic.setRGB(16*previous.getX() + i, 16*previous.getY() + j, 255, 0, 0);
        }
      }*/
      path += pathHelper(current, previous);
      previous = current;
      current = current.getOrigin();
    }
    //pic.saveImage();
    return finish.getFullCost() + "\n" + (new StringBuffer(path).reverse().toString());
  }

  /**
  * A helper for constructPath that compares the x and y values
  * of two nodes to determine whether a move of left, right,
  * up, or down was made.
  * @return A single character string of L, R, D, or U.
  * @see constructPath
  */
  public String pathHelper(MapData a, MapData b) {
    if((a.getX() - b.getX()) == 1) { return "L"; }
    if((b.getX() - a.getX()) == 1) { return "R"; }
    if((a.getY() - b.getY()) == 1) { return "U"; }
    if((b.getY() - a.getY()) == 1) { return "D"; }
    return "X";
  }

  /**
  * A function that calculates a heuristic for each node, in this
  * case the manhattan distance of the node from the finish
  * is used.
  * @return The estimated total distance of a node from the finish,
  * based on the cost to reach that node plus the heuristic estimate.
  */
  public static int cost(int x, int y, int accumulate) {
    int heuristic = Math.abs(finishx - x) + Math.abs(finishy - y);
    return accumulate + heuristic;
  }

  public static void main(String[] args) {
    Astar star = new Astar();
    try {
      star.readMap();
    } catch(IOException e) {
      System.out.println("Error reading map file.");
    }
    String status = star.shortestPath();
    System.out.println(status);
  }
}

/**
* Stores information about each point in the map.
*/
class MapData {
  protected final int x;
  protected final int y;
  protected int pathCost;
  protected int fullCost;
  protected MapData origin = null;
  /**
  * Constructs a MapData object with no origin,
  * and is only used for the start of the path that
  * will be computed by Astar.
  * @param x - x coordinate
  * @param y - y coordinate
  * @param pathCost - initial cost of the path (typically 0)
  */
  public MapData(int x, int y, int pathCost) {
    this.x = x;
    this.y = y;
    this.pathCost = pathCost;
    this.fullCost = Astar.cost(x, y, pathCost);
  }
  /**
  * Constructs a MapData object with a reference to the
  * object that birthed it, as well as position and cost
  * data.
  * @param x - x coordinate
  * @param y - y coordiante
  * @param pathCost - cost to reach this node
  * @param origin - the node that this was searched from, which is
  * guaranteed to be part of the best path since the heuristic
  * that is used is monotonic.
  */
  public MapData(int x, int y, int pathCost, MapData origin) {
    this.x = x;
    this.y = y;
    this.pathCost = pathCost;
    this.fullCost = Astar.cost(x, y, pathCost);
    this.origin = origin;
  }
  public int getX() { return this.x; }
  public int getY() { return this.y; }
  public int getPathCost() { return this.pathCost; }
  /**
  * Indicates the estimated cost from the start to the finish
  * if this node is included in the path.
  * @return Returns f(node), where [f(node) = g(node) + h(node)]
  * g being the cost to reach this node, and h being the
  * heuristic that estimates the remaining cost to the finish.
  */
  public int getFullCost() { return this.fullCost; }
  public MapData getOrigin() { return this.origin; }
  public boolean equals(Object o) {
    if(o instanceof MapData) {
      if(((MapData)o).getX() == this.x &&
         ((MapData)o).getY() == this.y) {
        return true;
      }
    }
    return false;
  }
}

/**
* Provides the parameter of interest to be used
* for comparison in the priority queue that is
* used to implement the Astar algorithm.
* @see Astar
*/
class SortMapData implements Comparator<MapData> {
  public int compare(MapData a, MapData b) {
    return a.getFullCost() - b.getFullCost();
  }
}
