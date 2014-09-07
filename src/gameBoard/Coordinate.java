package gameBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordinate is the class that helps pathfinding.
 * it creades nodes based on the game picture and all
 * 
 * @author AaronGonzales
 *
 */
public class Coordinate
{
  private int x;
  private int y;
  private List<Integer> rgb;
  private static final Picture PIC = 
      new Picture("/Users/carlyhendrickson/Dropbox/cs/351/project_01/clientPack/AntWorld.png");
  private int weight;
  private Coordinate parent;
  private int costSoFar;
  private int costWithMe;

  
  /**
   * Main constructor that stores the rgb values from the pic
   * Mostly to be used for the starting and goal  
   * @param a x coordinate
   * @param b y coordinate
   *
   *
   */
  Coordinate(int a, int b)
  {
    this.x = a;
    this.y = b;
    // Coordinate.setPic(game);
    //PIC = game;
    this.rgb = new ArrayList<Integer>();
    rgb.add(PIC.getRed(x, y));
    rgb.add(PIC.getGreen(x, y));
    rgb.add(PIC.getBlue(x, y));
    setWeight();

  }

/**
 * 
 * @param a x coordinate
 * @param b y coordinate
 * @param parent node from which this was birthed
 * @param costsofar movement cost from the start of the path not including this node
 * @param costWithMe movement cost from the start of the path including this node
 */
  Coordinate(int a, int b, Coordinate parent)
  {
    this.x = a;
    this.y = b;
    // Coordinate.setPic(game);
    //PIC = game;
    this.rgb = new ArrayList<Integer>();
    rgb.add(PIC.getRed(x, y));
    rgb.add(PIC.getGreen(x, y));
    rgb.add(PIC.getBlue(x, y));
    setWeight();
    this.parent = parent;
    this.costSoFar = parent.getCostSoFar();
    this.costWithMe = this.costSoFar + this.weight;

  }

/**
 * 
 * @return x coordinate
 */
  public int getX()
  {
    return this.x;

  }
/**
 * 
 * @return y coordinate
 */
  public int getY()
  {
    return y;
  }

  /**
   * 
   * @return rgb value list
   */
  public List<Integer> getRGB()
  {
    return this.rgb;
  }

  /**
   * sets the weight of this coordinate using 'magic numbers'
   * TODO update the values as the are learned
   */
  public void setWeight()
  {
    // water
    if (this.rgb.get(2) == 255)
    {
      this.weight = 1000;
    }

    else if (this.rgb.get(1) > 0)
    {
      this.weight = 1;
    }

    // need to implement the rest of these color schemes as we know what they
    // are
  }
/**
 * 
 * @return this node's movement weight
 */
  public int getWeight()
  {
    return weight;
  }

  /**
   * 
   * @return get's this nodes parent, useful for returning the path
   */
  public Coordinate getParent()
  {
    return this.parent;
  }
  
  /**
   * Tests if a node is equal to another node based on x/y coordinates
   * @param a - node to test
   * @return true if equal, false if not
   */
  public boolean areEqual (Coordinate a){
    if (a.getX() == this.getX() && a.getY() == this.getY()){
      return true;
    }
    else {
      return false;
    }
    
  }

  /**
   * Get's the neighboring (N,E,S,W) neighbors of the current node
   * @return list of this coordinate's neighbors
   */
  public List<Coordinate> getNeighbors()
  {
    List<Coordinate> neighbors = new ArrayList<Coordinate>();
    
      Coordinate north = new Coordinate(this.x - 1, this.y, this);
      neighbors.add(north);

    

   
      Coordinate east = new Coordinate(this.x, this.y + 1,
          this);
      neighbors.add(east);

    

    Coordinate south = new Coordinate(this.x + 1, this.y, this);
    neighbors.add(south);

    
      Coordinate west = new Coordinate(this.x, this.y - 1, this);
      neighbors.add(west);

    
    return neighbors;
  }
  
  /**
   * 
   * @return cost of the path so far
   */
  int getCostSoFar(){
    return this.costSoFar;
  }
  
  /**
   * 
   * @return cost of path with me included
   */
  int getCostWithMe(){
    return this.costWithMe;
  }
  

} // end class

