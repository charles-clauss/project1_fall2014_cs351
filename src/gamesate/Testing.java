package gamesate;

import java.util.*;
import java.io.*;
import java.net.*;

public class Testing {
		
	public static void display(Iterator<Vertex> it){
		while (it.hasNext()){
			Vertex p = it.next();
			System.out.println(p.getId()+ " : " + p + " " );
		}
	}

	public static void main (String[] args) throws MalformedURLException, IOException{
		Graph myGraph = new Graph();
		// make vertices
		Vertex a = new Vertex(1, 0, 0, 1, true);
		Vertex b = new Vertex(2, 1, 0, 1, true);
//		Vertex c = new Vertex(3, 2, 0, 3, true);
//		Vertex d = new Vertex(4, 3, 0, 5, true);
		// test vertex attribues
		System.out.println("Vertex a = a: " + a.equals(a));
		System.out.println("Vertex a = b: " + a.equals(b));
		System.out.println("Vertex a(x) : " + a.getX());
		System.out.println("Vertex a(y) : " + a.getY());
		System.out.println("Vertex a(weight) : " + a.getWeight());
		System.out.println("Vertex a(walkable) : " + a.getWalkable());
		System.out.println("Vertex a(id): " + a.getId());
		
		//test adding
		myGraph.addVertex(a);
		System.out.println("Graph has vertex a: " + myGraph.hasVertex(a));
		System.out.println("Graph has vertex b: " + myGraph.hasVertex(b));
		//edges
		System.out.println("vertex a edges: " + a.getEdges());
		a.addEdge(b);
		a.addEdge(a);
		System.out.println("vertex a edges: " + a.getEdges());
		System.out.println("Map : " + myGraph.toString());

		
	}
}
