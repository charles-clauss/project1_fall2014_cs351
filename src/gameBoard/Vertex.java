package gameBoard;


import java.util.*;

/**
 * @author aaron gonzales The Vertex class provides a Vertex object that
 *         contains relevant information for our game board. The Vertices will
 *         be put into an adjacency list in the Graph class.
 * 
 */
public class Vertex
{
  private int id;
  private int x;
  private int y;
  private int weight;
  protected boolean walkable;
  protected int fullCost;
  // pointers to the vertices connecting it, may remove these at some point
  protected Vertex origin = null;
  protected Vertex north = null;
  protected Vertex east = null;
  protected Vertex south = null;
  protected Vertex west = null;
  // list of edges for this node
  List<Vertex> edgeList = new ArrayList<Vertex>();
  private boolean nest = false;

  /**
   * Constructor that gives a pointer for astar
   */
  public Vertex(){
	  
  }
  /**
   * Constructor that takes the id, x and y, and walkable
   */
  Vertex(int Id, int x, int y, boolean walkable)
  {
    this.id = Id;
    this.x = x;
    this.y = y;
    this.weight = 0;
    this.walkable = true;
  }

  /**
   * Constructor that adds the weight to the node may make this the default?
   */
  Vertex(int Id, int X, int Y, int Weight, boolean Walkable)
  {
    this.id = Id;
    this.x = X;
    this.y = Y;
    this.weight = Weight;
    this.walkable = Walkable;
  }

  /**
   * Constructor that provides the edges for this node in a List<Vertex>
   */
  Vertex(int Id, int X, int Y, int Weight, boolean Walkable, List<Vertex> edges)
  {
    this.id = Id;
    this.x = X;
    this.y = Y;
    this.weight = Weight;
    this.walkable = Walkable;
    this.edgeList = edges;
  }

  // getter and setters
  public int getId()
  {
    return this.id;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public int getWeight()
  {
    return this.weight;
  }

  /**
   * will be essential to read game state? may re implement another way, perhaps
   * with a few more Booleans, e.g. isFood, isEnemy, etc.
   */
  public void updateWeight(List<Integer> rgb)
  {
    //rgb values - switch? 
    // blue, water
    if (rgb.get(2) == 255){
      this.walkable = false;
      // right now this is a magic number
      this.weight = 1000;
    } 
    //green
    if (rgb.get(1) >= 50 ){
      this.weight = 10;
      this.walkable = true;
    }
    
    if (rgb.get(2) == 0){
      this.weight = 0;
      this.walkable = true;
      this.nest = true;
    }
  }

  public boolean getWalkable()
  {
    return this.walkable;
  }

  public void setWalkable(boolean w)
  {
    this.walkable = w;
  }

  public List<Vertex> getEdges()
  {
    return this.edgeList;
  }

  /**
   * Adds an edge to the object's list
   */
  public void addEdge(Vertex v)
  {
    this.edgeList.add(v);
  }

  /**
   * @param edges
   *          - adds a list of edges to this object's list
   */
  public void addEdges(List<Vertex> edges)
  {
    for (Vertex e : edges)
    {
      this.edgeList.add(e);
    }
  }

  /**
   * Indicates the estimated cost from the start to the finish if this node is
   * included in the path.
   * 
   * @return Returns f(node), where [f(node) = g(node) + h(node)] g being the
   *         cost to reach this node, and h being the heuristic that estimates
   *         the remaining cost to the finish.
   */
  public int getFullCost()
  {
    return this.fullCost;
  }

  public Vertex getOrigin()
  {
    return this.origin;
  }

  @Override
  public boolean equals(Object o)
  {
    if (o instanceof Vertex)
    {
      if (((Vertex) o).getX() == this.x &&
          ((Vertex) o).getY() == this.y &&
          ((Vertex) o).getWeight() == this.weight)
      {
        return true;
      }
    }
    return false;
  }
  
  /**
  * Provides the parameter of interest to be used
  * for comparison in the priority queue that is
  * used to implement the Astar algorithm.
  * @see final_lab1
  */
  class SortVertex implements Comparator<Vertex> {
    public int compare(Vertex a, Vertex b) {
      return a.getWeight() - b.getWeight();
    }
  }

} // end class

