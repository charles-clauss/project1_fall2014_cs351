package gameBoard;

import java.util.*;

/**
 * it's possible that this could be implemetned using a TreeMap as it can have a
 * subMap method that could be very useful for returning a subset of the map for
 * each ant. Will test performance and come back.
 * 
 * Stores a graph in a HashMap with a key, value pair of Vertex, List<Vertex>
 * aka - adjacency list. 
 * Gives O(1) lookup time for vertices.
 * 
 * @author agonzales
 * 
 */
/**
 * @author agonzales
 *
 */
public class Graph implements GameGraphInterface<Vertex> {

	private static Map<Vertex, List<Vertex>> gameMap;
	// public Iterator<Integer> it = graphDict.
	private int numVertices;

	/**
	 * Default Constructor. May remove. 
	 */
	Graph() {
		setGameMap(new HashMap<Vertex, List<Vertex>>());
	}

	/**
	 * 
	 * @param hashMap initializes.
	 */
	private void setGameMap(HashMap<Vertex, List<Vertex>> hashMap) {
		// TODO Auto-generated method stub
		gameMap = hashMap;
	}

	/**
	 * Adds a vertex to the graph using the vertex's inner edgeList. 
	 * May remove. 
	 * @deprecated - should not do this? 
	 * @param vertex - vertex to be added to the graph
	 * 
	 */
	public void addVertex(Vertex vertex) {
		getGameMap().put(vertex, vertex.getEdges());
		this.numVertices++;

	}

	/**
	 * adds a vertex to the graph using the <Vertex, List<Vertex>> style.
	 * should be safe this way?
	 * @param vertex vertex to be added 
	 * @param edges - list of vertices to which vertex is connected
	 */
	public void addVertex(Vertex vertex, List<Vertex> edges) {
		getGameMap().put(vertex, edges);
		// if (vertex.getEdges() == null ){
		// vertex.addEdges(edges);
		// }
		this.numVertices++;
	}

	/**
	 * Adds an edge between two vertices, unidirectional
	 * @param vertex vertex to which edge is added
	 * @param e vertex to be added
	 */
	public void addEdge(Vertex vertex, Vertex e) {
		getGameMap().get(vertex).add(e);

	}

	/**
	 * Looks up graph for vertex Vertex
	 */
	public boolean hasVertex(Vertex vertex) {
		boolean present;
		present = getGameMap().containsKey(vertex);
		return present;
	}

	/**
	 * Returns true if the vertex1 and vertex2 are connected
	 */
	public boolean hasEdge(Vertex vertex1, Vertex vertex2) {
		boolean present;
		present = getGameMap().get(vertex1).contains(vertex2);
		return present;
	}

	/**
	 * returns a list of all the vertices
	 * may not implement this unless needed
	 */
	public ArrayList<Vertex> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the List of edges for vertex
	 * @param vertex - vertex to be queried
	 * @return list of edges
	 */
	public List<Vertex> neighbors(Vertex vertex) {
		return getGameMap().get(vertex);
	}

	/**
	 * Gets the vertices from the vertex counter.
	 * @return number of vertices in the graph
	 */
	public int getTotalVertices() {
		return numVertices;
	}

	/**
	 * Gets a copy of the map.
	 * @return the gameMap
	 */
	public static Map<Vertex, List<Vertex>> getGameMap() {
		return gameMap;
	}

	public List<Integer> getPixelVal(Picture pic, int x, int y)
  {
    int red = pic.getRed(x, y);
    int green = pic.getGreen(x, y);
    int blue = pic.getBlue(x, y);
    List<Integer> pixels = new ArrayList<Integer>();
    pixels.add(red);
    pixels.add(green);
    pixels.add(blue);

    return pixels;
  }
	/**
	 * Constructor for Graph that builds a graph with x rows and y columns.
	 * The graph is built as a grid for a game, with each (row, column) pair 
	 * being instantiated and added to the graph map. Will change slightly
	 * to handle the weight assignment for each vertex. 
	 * @param x number of rows
	 * @param y number of columns
	 */
	Graph( Picture pic) {
// todo
	  // each vertex needs to be created with pixel values
	  // pic read in to graph
	  // 
	  int x = pic.getImageWidth();
	  int y = pic.getImageHeight();
	  
		Vertex[][] vertArray = new Vertex[x][y];
		int id = 0;
		for (int i = 0; i < vertArray.length; i++) {
			for (int j = 0; j < vertArray[i].length; j++) {
				id++;

				System.out.println("Coordinates: " + i + "," + j);
				System.out.println("ID =         " + id);
				vertArray[i][j] = new Vertex(id, i, j, true);
				
				vertArray[i][j].updateWeight(getPixelVal(pic, i,j));
				System.out.println("Weight: " + getPixelVal(pic,i,j));
				List<Vertex> empty = new ArrayList<Vertex>();
				this.addVertex(vertArray[i][j], empty);
			}
			id++;
		}

		int col = 0;
		for (int row = 0; row < vertArray.length; row++) {
			System.out.println("Initializing row " + row + " of the vertices");
			while (col < vertArray[row].length) {

				// assign north -
				if (row == 0) {
					vertArray[row][col].north = null;
					//System.out.println("North Null!"
						//	+ vertArray[row][col].north);

				} else {
					this.addEdge(vertArray[row][col], vertArray[row - 1][col]);
					//System.out.println("North NotNull!");
				}

				// assign south
				if (row == (vertArray.length - 1)) {
					vertArray[row][col].south = null;
					//System.out.println("South Null!"
						//	+ vertArray[row][col].north);

				} else {
					this.addEdge(vertArray[row][col], vertArray[row + 1][col]);
					//System.out.println("South NotNull!"
						//	+ vertArray[row][col].north);

				}

				// assign east
				if (col == (vertArray[row].length - 1)) {
					vertArray[row][col].east = null;
					//System.out
						//	.println("East Null!" + vertArray[row][col].north);

				} else {
					this.addEdge(vertArray[row][col], vertArray[row][col + 1]);
					//System.out.println("East NotNull!"
						//	+ vertArray[row][col].north);

				}
				// assign west
				if (col == 0) {
					vertArray[row][col].east = null;
					//System.out
						//	.println("East Null!" + vertArray[row][col].north);
				} else {
					this.addEdge(vertArray[row][col], vertArray[row][col - 1]);
					//System.out.println("East NotNull!"
						//	+ vertArray[row][col].north);
				}
				//System.out.println(this.getVertices());

				// ////////// do not touch this ////////
				col++;
				// //////// do not touch this ////////
			}
			col = 0;
			//System.out.println("Neighbor test "
				//	+ this.neighbors(vertArray[row][col]));

		}
		vertArray = null;

	} // end big constructor
	
	/**
	 * Builds a graph using a picture object. 
	 * uses helper functions to fill in the right info.
	 * @param pic
	 */
	
	  
	  Vertex getVertex(int id){
	    for (Vertex v : this.getGameMap().keySet()){
	      if (v.getId() == id) {
	        return v;
	      }
	        else {
	          return null;
	        }
	      
	    }
      return null;
	    
	  }
	

	
}
