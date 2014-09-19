package gameBoard;

import java.util.*;

/**
 * it's possible that this could be implemetned using a TreeMap as it can have a
 * subMap method that could be very useful for returning a subset of the map for
 * each ant. Will test performance and come back. Stores a graph in a HashMap
 * with a key, value pair of Vertex, List<Vertex> aka - adjacency list. Gives
 * O(1) lookup time for vertices.
 * 
 * @author agonzales
 */
public class Graph
{

  private Map<Coordinate, List<Coordinate>> myMap;
  private int numVertices;

  /**
   * Default Constructor. May remove.
   */
  Graph()
  {

    this.myMap = new HashMap<Coordinate, List<Coordinate>>();

  }

  /**
   * Default Constructor. May remove.
   */
  Graph(Coordinate key, List<Coordinate> value)
  {

    this.myMap = new HashMap<Coordinate, List<Coordinate>>();
    this.myMap.put(key, value);
  }

  /**
   * adds a vertex to the graph using the <Vertex, List<Vertex>> style. should
   * be safe this way?
   * 
   * @param vertex
   *          vertex to be added
   * @param edges
   *          - list of vertices to which vertex is connected
   */
  public void addVertex(Coordinate key, List<Coordinate> value)
  {
    getGameMap().put(key, value);
    // if (vertex.getEdges() == null ){
    // vertex.addEdges(edges);
    // }
    this.numVertices++;
  }

  /**
   * Adds an edge between two vertices, unidirectional
   * 
   * @param vertex
   *          vertex to which edge is added
   * @param e
   *          vertex to be added
   */
  public void addEdge(Coordinate vertex, Coordinate e)
  {
    getGameMap().get(vertex).add(e);

  }

  /**
   * Looks up graph for vertex Vertex
   */
  public boolean hasVertex(Coordinate vertex)
  {
    boolean present;
    present = getGameMap().containsKey(vertex);
    return present;
  }

  /**
   * Returns true if the vertex1 and vertex2 are connected
   */
  public boolean hasEdge(Coordinate vertex1, Coordinate vertex2)
  {
    boolean present;
    present = getGameMap().get(vertex1).contains(vertex2);
    return present;
  }

  /**
   * returns a list of all the vertices may not implement this unless needed
   */
  public ArrayList<Coordinate> vertices()
  {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Returns the List of edges for vertex
   * 
   * @param vertex
   *          - vertex to be queried
   * @return list of edges
   */
  public List<Coordinate> neighbors(Coordinate vertex)
  {
    return getGameMap().get(vertex);
  }

  /**
   * Gets the vertices from the vertex counter.
   * 
   * @return number of vertices in the graph
   */
  public int getTotalVertices()
  {
    return numVertices;
  }

  /**
   * Gets a copy of the map.
   * 
   * @return the map
   */
  public Map<Coordinate, List<Coordinate>> getGameMap()
  {
    return this.myMap;
  }

} // end class Graph
