package cs351lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import cs351lab1.Picture;

public class Astar {
  public static int[][] map = new int[30][28];
  public static int startx;
  public static int starty;
  public static int finishx;
  public static int finishy;
  public static Picture pic = new Picture("/Users/cclauss/Documents/workspace/cs351lab1/src/cs351lab1/mapimage.png");

  public Astar() {
  }

  public void readMap() {
    File file = new File("/Users/cclauss/Documents/workspace/cs351lab1/src/cs351lab1/MapWeights.txt");
    FileReader filereader;
    try {
      filereader = new FileReader(file);
    } catch (FileNotFoundException e) {
      System.out.println("Error opening file MapWeights");
      return;
    }
    BufferedReader reader = new BufferedReader(filereader);
    String line;
      try {
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
      } catch (IOException e) {
        System.out.println("Error during parsing.");
      }
  }

  public String shortestPath() {
    Tuple3 start = new Tuple3(startx, starty, map[starty][startx]);
    Tuple3 visit;
    Comparator<Tuple3> compare = new TupleSort();
    PriorityQueue<Tuple3> pq = new PriorityQueue<Tuple3>(25, compare);
    if(pq.comparator() == null) { return "Natural order."; }
    int visitx;
    int visity;
    int visitcost;
    int westx;
    int eastx;
    int southy;
    int northy;
    pq.add(start);
    ArrayList<Tuple3> visited = new ArrayList<Tuple3>();
    while((visit = pq.poll()) != null) {
      visited.add(visit);
      visitx = visit.getX();
      visity = visit.getY();
      visitcost = visit.getFullCost();
      //System.out.println(visity);
      //System.out.println(visitx);
      //System.out.println(visitcost);
      if(visitx == finishx && visity == finishy) {
        return constructPath(visit);
      }
      westx = visitx - 1;
      eastx = visitx + 1;
      southy = visity + 1;
      northy = visity - 1;
      if(westx >= 0) {
        Tuple3 westNeighbor = new Tuple3(westx, visity, visit.getPathCost() + map[visity][westx], visit);
        if(!visited.contains(westNeighbor)) {
          pq.add(westNeighbor);
          //System.out.println("Searched west.");
          //System.out.println(westNeighbor.getFullCost());
        }
      }
      if(eastx < map[0].length) {
        Tuple3 eastNeighbor = new Tuple3(eastx, visity, visit.getPathCost() + map[visity][eastx], visit);
        if(!visited.contains(eastNeighbor)) {
          pq.add(eastNeighbor);
          //System.out.println("Searched east.");
          //System.out.println(eastNeighbor.getFullCost());
        }
      }
      if(southy < map.length) {
        Tuple3 southNeighbor = new Tuple3(visitx, southy, visit.getPathCost() + map[southy][visitx], visit);
        if(!visited.contains(southNeighbor)) {
          pq.add(southNeighbor);
          //System.out.println("Searched south.");
          //System.out.println(southNeighbor.getFullCost());
        }
      }
      if(northy >= 0) {
        Tuple3 northNeighbor = new Tuple3(visitx, northy, visit.getPathCost() + map[northy][visitx], visit);
        if(!visited.contains(northNeighbor)) {
          pq.add(northNeighbor);
          //System.out.println("Searched north.");
          //System.out.println(northNeighbor.getFullCost());
        }
      }
    }
    return "Failure.";
  }

  public String constructPath(Tuple3 finish) {
    Tuple3 previous = finish;
    Tuple3 current = finish.getOrigin();
    String path = "";
    while(current != null) {
      for(int i = 0; i < 16; i++) {
        for(int j = 0; j < 16; j++) {
          pic.setRGB(16*previous.getX() + i, 16*previous.getY() + j, 255, 0, 0);
        }
      }
      path += pathHelper(current, previous);
      previous = current;
      current = current.getOrigin();
    }
    pic.saveImage();
    return finish.getFullCost() + "\n" + (new StringBuffer(path).reverse().toString());
  }

  public String pathHelper(Tuple3 a, Tuple3 b) {
    if((a.getX() - b.getX()) == 1) { return "L"; }
    if((b.getX() - a.getX()) == 1) { return "R"; }
    if((a.getY() - b.getY()) == 1) { return "U"; }
    if((b.getY() - a.getY()) == 1) { return "D"; }
    return "X";
  }

  public static int cost(int x, int y, int accumulate) {
    int heuristic = Math.abs(finishx - x) + Math.abs(finishy - y);
    return accumulate + heuristic;
  }

  public static void main(String[] args) {
    Astar star = new Astar();
    star.readMap();
    String status = star.shortestPath();
    System.out.println(status);
  }
}

class Tuple3 {
  protected final int x;
  protected final int y;
  protected int pathCost;
  protected int fullCost;
  protected Tuple3 origin = null;
  public Tuple3(int x, int y, int pathCost) {
    this.x = x;
    this.y = y;
    this.pathCost = pathCost;
    this.fullCost = Astar.cost(x, y, pathCost);
  }
  public Tuple3(int x, int y, int pathCost, Tuple3 origin) {
    this.x = x;
    this.y = y;
    this.pathCost = pathCost;
    this.fullCost = Astar.cost(x, y, pathCost);
    this.origin = origin;
  }
  public int getX() { return this.x; }
  public int getY() { return this.y; }
  public int getPathCost() { return this.pathCost; }
  public int getFullCost() { return this.fullCost; }
  public Tuple3 getOrigin() { return this.origin; }
  public boolean equals(Object o) {
    if(o instanceof Tuple3) {
      if(((Tuple3)o).getX() == this.x &&
         ((Tuple3)o).getY() == this.y) {
        return true;
      }
    }
    return false;
  }
}

class TupleSort implements Comparator<Tuple3> {
  public int compare(Tuple3 a, Tuple3 b) {
    return a.getFullCost() - b.getFullCost();
  }
}
