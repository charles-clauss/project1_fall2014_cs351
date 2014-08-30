package gamesate;

import java.util.*;
/**
 * it's possible that this could be implemetned using a TreeMap
 * as it can have a subMap method that could be very useful for 
 * returning a subset of the map for each ant.
 * @author agonzales
 *
 */
public class Graph implements GameGraphInterface<Vertex>
{

  protected static Map<Integer, List<Vertex>> gameMap;
  // public Iterator<Integer> it = graphDict.
  protected int numVertices;

  
  Graph()
  {
	  gameMap = new HashMap<Integer, List<Vertex>>();
  }

  public void addVertex(Vertex vertex)
  {
    gameMap.put(vertex.getId(), vertex.getEdges());

  }

  // public void addEdge(Vertex vertex1, Vertex vertex2) {
  // // gets the list at the id of vertex on, adds vertex two to that list
  // graphDict.get(vertex1.getId()).add(vertex2);
  // }

  public boolean hasVertex(Vertex vertex)
  {
    boolean present;
    present = gameMap.containsKey(vertex.getId());
    return present;
  }

  public boolean hasEdge(Vertex vertex1, Vertex vertex2)
  {
    boolean present;
    // if vertex 2 is in vertex 1's edgelist
    present = gameMap.get(vertex1.getId()).contains(vertex2);
    return present;
  }

  public ArrayList<Vertex> vertices()
  {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Vertex> neighbors(Vertex vertex)
  {
    return gameMap.get(vertex.getId());
  }

  public List<Vertex> getEdges(Vertex vertex)
  {
    // TODO Auto-generated method stub
    return null;
  }

}
