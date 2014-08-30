package gamesate;

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

  List<V> getEdges(V vertex);

  /**
   * Returns the line of vertices of the graph.
   */
  ArrayList<V> vertices();

  /**
   * Returns the line adjacency of vertex.
   */
  List<V> neighbors(V vertex);
}
