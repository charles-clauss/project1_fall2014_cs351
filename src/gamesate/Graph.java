package gamesate;
import java.util.*;




public class Graph implements GameGraphInterface<Vertex> {

	private Map<Integer, List<Vertex>> graphDict;
//	public Iterator<Integer> it = graphDict.
	protected int numVertices;
	
	Graph(){
		
	}
	public void addVertex(Vertex vertex) {
		// add each 'key' using the linkedHashMap
		if (graphDict == null){
			graphDict = new HashMap<Integer, List<Vertex>>();
		}
		else {
			graphDict.put(vertex.getId(), vertex.getEdges());
		}
		
	}

//	public void addEdge(Vertex vertex1, Vertex vertex2) {
//		// gets the list at the id of vertex on, adds vertex two to that list
//		graphDict.get(vertex1.getId()).add(vertex2);
//	}

	
	public boolean hasVertex(Vertex vertex) {
		boolean present;
		present = graphDict.containsKey(vertex.getId());
		return present;
	}

	
	public boolean hasEdge(Vertex vertex1, Vertex vertex2) {
		boolean present;
		// if vertex 2 is in vertex 1's edgelist
		present = graphDict.get(vertex1.getId()).contains(vertex2);
		return present;
	}

	
	public ArrayList<Vertex> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Vertex> neighbors(Vertex vertex) {
		return graphDict.get(vertex.getId());
	}
	
	public List<Vertex> getEdges(Vertex vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
