package gamesate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.net.*;

public class Testing {

	public static void display(Iterator<Vertex> it) {
		while (it.hasNext()) {
			Vertex p = it.next();
			System.out.println(p.getId() + " : " + p + " ");
		}
	}

	public static String bytesToHex(byte[] b) {
		char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}

	public static int hashCode(int myInteger) {
		StringBuilder builder = new StringBuilder();
		builder.append(myInteger);
		return builder.toString().hashCode();
	}

	public static void testVertices() {
		Vertex a = new Vertex(1, 0, 0, 1, true);
		Vertex b = new Vertex(2, 1, 0, 1, true);
		// Vertex c = new Vertex(3, 2, 0, 3, true);
		// Vertex d = new Vertex(4, 3, 0, 5, true);
		// test vertex attribues
		System.out.println("Vertex a = a: " + a.equals(a));
		System.out.println("Vertex a = b: " + a.equals(b));
		System.out.println("Vertex a(x) : " + a.getX());
		System.out.println("Vertex a(y) : " + a.getY());
		System.out.println("Vertex a(weight) : " + a.getWeight());
		System.out.println("Vertex a(walkable) : " + a.getWalkable());
		System.out.println("Vertex a(id): " + a.getId());
		System.out.println("Vertex b(id): " + b.getId());

		// edges
		System.out.println("vertex a edges: " + a.getEdges());
		a.addEdge(b);
		a.addEdge(a);
		System.out.println("vertex a edges: " + a.getEdges().size());

	}

	public static Graph testGraph() {
		Graph myGraph = new Graph();
		System.out
				.println("****************now testing the handmade graph****************");

		// make vertices
		Vertex a = new Vertex(1, 0, 0, 1, true);
		Vertex b = new Vertex(2, 1, 0, 1, true);
		Vertex c = new Vertex(3, 2, 0, 1, true);
		Vertex d = new Vertex(4, 3, 0, 1, true);

		a.addEdge(b);
		b.addEdge(a);
		c.addEdge(d);
		d.addEdge(a);
		b.addEdge(d);
		b.addEdge(c);

		// test adding
		myGraph.addVertex(a);
		myGraph.addVertex(b);
		myGraph.addVertex(c);
		myGraph.addVertex(d);
		// System.out.println("Graph has vertex a: " + myGraph.hasVertex(a));
		// System.out.println("Graph has vertex b: " + myGraph.hasVertex(b));

		// System.out.println("Map : " + myGraph.toString());

		System.out.println("Map values to follow");
		System.out.println("Map = " + myGraph.getGameMap());

		for (Vertex key : myGraph.getGameMap().keySet()) {
			System.out.println(key);
		}
		for (Entry<Vertex, List<Vertex>> entry : myGraph.getGameMap().entrySet()) {

			Vertex key = entry.getKey();
			List<Vertex> value = entry.getValue();

			System.out.print("key: " + key + " | ");
			System.out.println("value: " + value);
		}

		Vertex not = new Vertex(-1, 0, 0, 0, true);
		// Iterator<Integer> it = myGraph.gameMap.iterator();
		System.out.println(myGraph.hasVertex(a));
		System.out.println(myGraph.hasVertex(b));
		System.out.println(myGraph.hasVertex(not));

		System.out.println("Hasedge: " + myGraph.hasEdge(a, b));
		System.out.println("Map = " + myGraph.getGameMap());
		System.out.println("GetNeightbors: " + myGraph.neighbors(b));
		System.out.println("GetVertices: " + myGraph.getTotalVertices());

		return myGraph;
	}

	public static Graph makeBigGraph(int x, int y) {
		// Random rand = new Random();
		System.out.println("****************now testing the graph****************");
		Graph graph = new Graph();
		Vertex[][] vertArray = new Vertex[x][y];
		int id = 0;
		for (int i = 0; i < vertArray.length; i++) {
			for (int j = 0; j < vertArray[i].length; j++) {
				id++;

				System.out.println("Coordinates: " + i + "," + j);
				System.out.println("ID =         " + id);
				// = i * j + Math.abs(rand.nextInt());
				// System.out.println( );
				vertArray[i][j] = new Vertex(id, i, j, true);
				List<Vertex> empty = new ArrayList<Vertex>();
				graph.addVertex(vertArray[i][j], empty);
			}
			id++;
		}
		/*
		 * System.out.println(vertArray[x-x][y-y].getId());
		 * System.out.println(vertArray[x-x][y-y].getEdges());
		 * System.out.println(vertArray[x-x][y-y].getX());
		 * 
		 * System.out.println(vertArray[x-1][y-1].getId());
		 * System.out.println(vertArray[x-1][y-1].getEdges());
		 * System.out.println(vertArray[x-1][y-1].getX());
		 */
		int col = 0;
		for (int row = 0; row < vertArray.length; row++) {
			System.out.println("Initializing row " + row + " of the vertices");
			while (col < vertArray[row].length) {

				// assign north -
				if (row == 0) {
					vertArray[row][col].north = null;
					System.out.println("Null!" + vertArray[row][col].north);

				} else {
					vertArray[row][col].north = vertArray[row - 1][col];
					System.out.println("NotNull!" + vertArray[row][col].north);
				}

				// assign south
				if (row == (vertArray.length - 1)) {
					vertArray[row][col].south = null;
					System.out.println("Null!" + vertArray[row][col].north);

				} else {
					vertArray[row][col].south = vertArray[row + 1][col].south;
					System.out.println("NotNull!" + vertArray[row][col].north);

				}

				// assign east
				if (col == (vertArray[row].length - 1)) {
					vertArray[row][col].east = null;
					System.out.println("Null!" + vertArray[row][col].north);
					

				} else {
					vertArray[row][col].east = vertArray[row][col + 1];
					System.out.println("NotNull!" + vertArray[row][col].north);

				}
				// assign west
				if (col == 0) {
					vertArray[row][col].east = null;
					System.out.println("Null!" + vertArray[row][col].north);
					System.out.println("NotNull!" + vertArray[row][col].north);


				} else {
					vertArray[row][col].east = vertArray[row][col - 1];
				}

				// test print
				// System.out.println(vertArray[row][col].getId());
				// if (vertArray[row][col].south == null){
				// System.out.println("null");
				// }
				// else {

				// System.out.println(vertArray[row][col].south.getId());
				// }

				List<Vertex> tmpEdges = new ArrayList<Vertex>();
				tmpEdges.add(vertArray[row][col].north);
				tmpEdges.add(vertArray[row][col].east);
				tmpEdges.add(vertArray[row][col].south);
				tmpEdges.add(vertArray[row][col].west);
				vertArray[row][col].addEdges(tmpEdges);
				// graph.addVertex();
				// tmpEdges.clear();

				graph.addVertex(vertArray[row][col]);

				col++;

			}
			col = 0;
			System.out.println(vertArray[row][col].getEdges());
			System.out.println(vertArray[row][col].north);
			System.out.println(vertArray[row][col].east);
			System.out.println(vertArray[row][col].south);
			System.out.println(vertArray[row][col].west);

		}

		return graph;

	}
	
	public static Graph makeBigGraph2(int x, int y) {
		
		System.out.println("****************now testing the graph****************");
		Graph graph = new Graph();
		Vertex[][] vertArray = new Vertex[x][y];
		int id = 0;
		for (int i = 0; i < vertArray.length; i++) {
			for (int j = 0; j < vertArray[i].length; j++) {
				id++;

				System.out.println("Coordinates: " + i + "," + j);
				System.out.println("ID =         " + id);
				// = i * j + Math.abs(rand.nextInt());
				// System.out.println( );
				vertArray[i][j] = new Vertex(id, i, j, true);
				List<Vertex> empty = new ArrayList<Vertex>();
				graph.addVertex(vertArray[i][j], empty);
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
					System.out.println("North Null!" + vertArray[row][col].north);

				} else {
					graph.addEdge(vertArray[row][col], vertArray[row - 1][col]);
					System.out.println("North NotNull!");
				}

				// assign south
				if (row == (vertArray.length - 1)) {
					vertArray[row][col].south = null;
					System.out.println("South Null!" + vertArray[row][col].north);

				} else {
					graph.addEdge(vertArray[row][col], vertArray[row + 1][col]);
					System.out.println("South NotNull!" + vertArray[row][col].north);

				}

				// assign east
				if (col == (vertArray[row].length - 1)) {
					vertArray[row][col].east = null;
					System.out.println("East Null!" + vertArray[row][col].north);
					

				} else {
					graph.addEdge(vertArray[row][col], vertArray[row][col + 1]);
					System.out.println("East NotNull!" + vertArray[row][col].north);

				}
				// assign west
				if (col == 0) {
					vertArray[row][col].east = null;
					System.out.println("East Null!" + vertArray[row][col].north);
				} else {
					graph.addEdge(vertArray[row][col], vertArray[row][col - 1]);
					System.out.println("East NotNull!" + vertArray[row][col].north);
				}
				System.out.println(graph.getTotalVertices());

				//////////// do not touch this ////////
				col++;
				////////// do not touch this ////////
			}
			col = 0;
			System.out.println("Neighbor test " + graph.neighbors(vertArray[row][col]));

		}
		vertArray=null;

		return graph;

	}

	public static void main(String[] args) throws MalformedURLException,
			IOException, NoSuchAlgorithmException {
		testVertices();
		Graph testGraph = testGraph();
		// makeEdges(testGraph);
		Graph newGraph = makeBigGraph2(2, 2);
		System.out.println(newGraph.getTotalVertices());
		System.out.println(newGraph.toString());
		
		Graph anotherGraph = new Graph(10,10);
		
		

	}
}
