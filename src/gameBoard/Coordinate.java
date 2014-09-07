package gameBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic methods for tuples, mostly used as a data structure for generating
 * and updating the vertices in the game graph. based on implementation of our
 * textbook's generic tuple approach, unsure why this isn't working
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
  private int costSoFar = 0;

  // private int pathCost;
  /**
   * Main constructor that stores the rgb values from the pic
   * 
   * @param a
   *          x
   * @param b
   *          y
   * @param game
   *          picture object
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
   * Constructor that helps return the path on the map
   * 
   * @param a
   *          x
   * @param b
   *          y
   * @param game
   *          picture
   * @param Parent
   *          coordinate that birthed this
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

  }

  /**
   * Constructor that just holds the coordinates; can be used to pass the
   * coordinates to an ant in a list?
   * 
   * @param x
   */
  /*Coordinate(int x, int y)
  {
    this.x = x;
    this.y = y;

  }
*/
  public int getX()
  {
    return this.x;

  }

  public int getY()
  {
    return y;
  }

  public List<Integer> getRGB()
  {
    return this.rgb;
  }

  public static Picture getPic()
  {
    return PIC;
  }

  /**
   * sets the weight of this coordinate using 'magic numbers'
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

  public int getWeight()
  {
    return weight;
  }

  public Coordinate getParent()
  {
    return this.parent;
  }
  
  public boolean areEqual (Coordinate a){
    if (a.getX() == this.getX() && a.getY() == this.getY()){
      return true;
    }
    else {
      return false;
    }
    
  }

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
  
  int getCostSoFar(){
    return this.costSoFar;
  }
  
  void setCostSoFar(int c){
    this.costSoFar = c;
  }

} // end class

