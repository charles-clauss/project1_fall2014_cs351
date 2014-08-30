package gamesate;

import java.util.*;

/**
 * it's possible that this could be implemetned using a TreeMap as it can have a
 * subMap method that could be very useful for returning a subset of the map for
 * each ant.
 * 
 * @author agonzales
 * 
 */
public class Graph implements GameGraphInterface<Vertex> {

	protected static Map<Vertex, List<Vertex>> gameMap;
	// public Iterator<Integer> it = graphDict.
	private int numVertices;

	// protected static Map<Integer, Vertex> vertexList;

	Graph() {
		gameMap = new HashMap<Vertex, List<Vertex>>();
	}

	/**
	 * adds a vertex to the graph map. takes the Vertex's inner edge list and
	 * adds it as <Vertex, List> to the inner map.
	 */
	public void addVertex(Vertex vertex) {
		gameMap.put(vertex, vertex.getEdges());
		this.numVertices++;

	}
	

	public void addVertex(Vertex vertex, List<Vertex> edges ) {
		gameMap.put(vertex, edges);
		//if (vertex.getEdges() == null ){
			//vertex.addEdges(edges);
		//}
		this.numVertices++;
	}
	public void addEdge(Vertex vertex, Vertex e) {
		gameMap.get(vertex).add(e);
		
	}

	public boolean hasVertex(Vertex vertex) {
		boolean present;
		present = gameMap.containsKey(vertex);
		return present;
	}

	public boolean hasEdge(Vertex vertex1, Vertex vertex2) {
		boolean present;
		
		present = gameMap.get(vertex1).contains(vertex2);
		return present;
	}

	public ArrayList<Vertex> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Vertex> neighbors(Vertex vertex) {
		return gameMap.get(vertex);
	}
	
	public int getVertices(){
		return numVertices;
	}

}
