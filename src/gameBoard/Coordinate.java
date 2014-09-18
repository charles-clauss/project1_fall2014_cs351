package gameBoard;

import java.util.ArrayList;
import java.util.List;

import antworld.data.AntAction;
import antworld.data.Direction;

/**
 * Coordinate is the class that helps pathfinding and many other game events.
 *  it creates nodes based on the game picture and can hold coordinate information
 *  and other information as needed. 
 *  @author AaronGonzales
 */
public class Coordinate
{
  private int x;
  private int y;
  private List<Integer> rgb;
  private static final Picture PIC =
      new Picture(
          "clientPack/AntWorld.png");
  private int weight;
  private Coordinate parent;
  private int distanceSoFar;
  private int costWithMe;
  private int distanceToGoal;
  private boolean isFood;
  private boolean isMovable = true;
  private boolean isEnemy;
  private boolean isFriendly;
  private boolean isNest = false;

  /**
   * Main constructor that stores the rgb values from the pic Mostly to be used
   * for the starting and goal or just a generic 'coordinate'
   * 
   * @param a
   *          x coordinate
   * @param b
   *          y coordinate
   */
  public Coordinate(int a, int b)
  {
    this.x = a;
    this.y = b;
    // Coordinate.setPic(game);
    // PIC = game;
    this.rgb = new ArrayList<Integer>();
    rgb.add(PIC.getRed(x, y));
    rgb.add(PIC.getGreen(x, y));
    rgb.add(PIC.getBlue(x, y));
    if (rgb.get(2) == 255){
      this.isMovable = false;
    }
    setWeight();

  }

  /**
   * @param a
   *          x coordinate
   * @param b
   *          y coordinate
   * @param parent
   *          node from which this was birthed
   * @param costsofar
   *          movement cost from the start of the path not including this node
   * @param costWithMe
   *          movement cost from the start of the path including this node
   */
  Coordinate(int a, int b, Coordinate parent)
  {
    this.x = a;
    this.y = b;
    // Coordinate.setPic(game);
    // PIC = game;
    this.rgb = new ArrayList<Integer>();
    this.rgb.add(PIC.getRed(x, y));
    this.rgb.add(PIC.getGreen(x, y));
    this.rgb.add(PIC.getBlue(x, y));
    setWeight();
    this.parent = parent;
    this.distanceSoFar = parent.getCostSoFar() + this.weight;
    this.costWithMe = this.distanceSoFar + this.weight;
    if (this.rgb.get(2) == 255){
      this.isMovable = false;
    }

  }

  /**
   * @return x coordinate
   */
  public int getX()
  {
    return this.x;

  }

  /**
   * @return y coordinate
   */
  public int getY()
  {
    return y;
  }

  /**
   * @return rgb value list
   */
  public List<Integer> getRGB()
  {
    return this.rgb;
  }

  /**
   * sets the weight of this coordinate using 'magic numbers' TODO update the
   * values as the are learned
   */
  public void setWeight()
  {
    // water
    if (this.rgb.get(2) == 255)
    {
      this.weight = 10000;
      this.isMovable = false;
    }

    else if (this.rgb.get(1) == 55)
    {
      this.weight = 1;
    }
    else if (this.rgb.get(1) > 55)
    {
      this.weight = 2;
    }
    else if (this.rgb.get(0) == 240){
      this.isMovable = true;
      this.weight = 0; 
      this.isNest = true;
    }

    // need to implement the rest of these color schemes as we know what they
    // are
  }

  public boolean getNest(){
    return this.isNest;
  }
  /**
   * @return this node's movement weight
   */
  public int getWeight()
  {
    return this.weight;
  }

  /**
   * @return get's this nodes parent, useful for returning the path
   */
  public Coordinate getParent()
  {
    return this.parent;
  }

  /**
   * Tests if a node is equal to another node based on x/y coordinates
   * 
   * @param a
   *          - node to test
   * @return true if equal, false if not
   */
  public boolean areEqual(Coordinate a)
  {
    if (a.getX() == this.getX() && a.getY() == this.getY())
    {
      return true;
    }
    else
    {
      return false;
    }

  }

  /**
   * Get's the neighboring (N,NE, E, SE, S, SW, W, NW) neighbors of the current node
   * 
   * @return list of this coordinate's neighbors
   */
  public List<Coordinate> getNeighbors()
  {
    List<Coordinate> neighbors = new ArrayList<Coordinate>();

    Coordinate north = new Coordinate(this.x - 1, this.y, this);
    if (north.isMovable){
    neighbors.add(north);
    }
    
    Coordinate northEast = new Coordinate(this.x-1, this.y+1, this);
    if (northEast.isMovable){
    neighbors.add(northEast);}

    Coordinate east = new Coordinate(this.x, this.y + 1,
        this);
    if (east.isMovable){
    neighbors.add(east);
    }
    
    Coordinate southEast = new Coordinate(this.x+1, this.y+1, this);
    if (southEast.isMovable){
    neighbors.add(southEast);}
    

    Coordinate south = new Coordinate(this.x + 1, this.y, this);
    if (south.isMovable){
    neighbors.add(south);}
    
    Coordinate southWest = new Coordinate(this.x + 1, this.y-1, this);
    if (southWest.isMovable){
    neighbors.add(southWest);}
    

    Coordinate west = new Coordinate(this.x, this.y - 1, this);
    if (west.isMovable){
    neighbors.add(west);
    }
    
    Coordinate northWest = new Coordinate(this.x-1, this.y - 1, this);
    if (northWest.isMovable){
    neighbors.add(northWest);}

    
    return neighbors;
  }

  /**
   * @return cost of the path so far
   */
  int getCostSoFar()
  {
    return this.distanceSoFar;
  }

  /**
   * @return cost of path with me included
   */
  int getCostWithMe()
  {
    return this.costWithMe;
  }

  public int getDistanceToGoal()
  {
    return distanceToGoal;
  }

  public void setDistanceToGoal(int distanceToGoal)
  {
    this.distanceToGoal = distanceToGoal;
  }
  
  //two adjacent coordinates
  public static Direction getDirection(Coordinate a, Coordinate b)
  {
    int deltax = b.x - a.x;
    int deltay = b.y - a.y;
    Direction myDir = null;
    for(Direction d : Direction.values())
    {
      if(deltax == d.deltaX() && deltay == d.deltaY())
      {
        myDir = d;
      }
    }
    return myDir;
  }

  static public Picture getPic(){
    return Coordinate.PIC;
  }

  public boolean isFood()
  {
    return isFood;
  }

  public void setFood(boolean isFood)
  {
    this.isFood = isFood;
  }

  public boolean isMovable()
  {
    return isMovable;
  }

  public void setMovable(boolean isMovable)
  {
    this.isMovable = isMovable;
  }

  public boolean isEnemy()
  {
    return isEnemy;
  }

  public void setEnemy(boolean isEnemy)
  {
    this.isEnemy = isEnemy;
  }

  public boolean isFriendly()
  {
    return isFriendly;
  }

  public void setFriendly(boolean isFriendly)
  {
    this.isFriendly = isFriendly;
  }

} // end class

