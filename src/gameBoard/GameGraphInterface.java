package gameBoard;

import java.util.*;

public interface GameGraphInterface<V>
{

  /**
   * Adds vertex to the graph.
   */
  void addVertex(V vertex);

  /**
   * Adds the edge (vertex1, vertex2) to the graph.
   */
  // void addEdge(V vertex1, V vertex2);

  /**
   * Return true if graph contains vertex.
   */
  boolean hasVertex(V vertex);

  /**
   * Return true if graph contains the edge (vertex1, vertex2).
   */
  boolean hasEdge(V vertex1, V vertex2);

    /**
   * Returns the line of vertices of the graph.
   */
  ArrayList<V> vertices();

  /**
   * Returns the adjacent vertices of vertex.
   */
  List<V> neighbors(V vertex);
  
  /**
   * 
   * @return number of vertices contained in this graph
   */
  int getTotalVertices();
}
