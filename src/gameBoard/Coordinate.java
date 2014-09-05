package gameBoard;

import java.util.ArrayList;
import java.util.Comparator;
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
  private static Picture pic; 
  private int weight;
  private Coordinate parent;
  private int pathCost;
  /**
   * Main constructor that stores the rgb values from the pic
   * @param a x
   * @param b y
   * @param game picture object
   */
  Coordinate(int a, int b, Picture game)
  {
    this.x = a;
    this.y = b;
    Coordinate.setPic(game);
    
    this.rgb = new ArrayList<Integer>();
    rgb.add(pic.getRed(x,y));
    rgb.add(pic.getGreen(x, y));
    rgb.add(pic.getBlue(x,y));    
    setWeight();
    
  }
  
  /**
   * Constructor that helps return the path on the map
   * 
   * @param a x
   * @param b y
   * @param game picture
   * @param Parent coordinate that birthed this
   */
  Coordinate(int a, int b, Picture game, Coordinate parent)
  {
    this.x = a;
    this.y = b;
    Coordinate.setPic(game);
    
    this.rgb = new ArrayList<Integer>();
    rgb.add(pic.getRed(x,y));
    rgb.add(pic.getGreen(x, y));
    rgb.add(pic.getBlue(x,y));    
    setWeight();
    this.parent = parent;
    
  }
  /**
   * Constructor that just holds the coordinates; can be used to pass the coordinates 
   * to an ant in a list?
   * @param x
   */
  Coordinate(int x, int y){
    this.x=x;
    this.y=y;
  }

  public int getX(){
    return this.x;
    
  }
  public int getY(){
    return y;
  }
  
  public List<Integer> getRGB(){
    return this.rgb;
  }

  public static Picture getPic()
  {
    return pic;
  }

  public static void setPic(Picture pic)
  {
    Coordinate.pic = pic;
  }
/**
 * sets the weight of this coordinate using 
 * 'magic numbers'
 */
  public void setWeight(){
    // water
    if (this.rgb.get(2) == 255){
      this.weight = 1000;
    }
    
    else if (this.rgb.get(1) > 0) {
      this.weight = 1;
    }
    
    // need to implement the rest of these color schemes as we know what they are
  }

public int getWeight()
{
  return weight;
}

public Coordinate getParent(){
  return this.parent;
}

} // end class
  
/**
 * Provides the parameter of interest to be used
 * for comparison in the priority queue that is
 * used to implement the Astar algorithm.
 * @see final_lab1
 */
 class SortCoordinate implements Comparator<Coordinate> {
   public int compare(Coordinate a, Coordinate b) {
     return a.getWeight() - b.getWeight();
   }
 }

