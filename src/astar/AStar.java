/**
 * 
 */
package astar;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import event.Observer;

/**
 * @author agonzales Implements a version of the A* algorithm using a priority
 *         queue. Will change as we get program specs on how the graph is
 *         represented.
 */
public class AStar extends Observer {

	public AStar() {
	}
	/**
	 * Implements the Astar best first search algorithm to find the shortest
	 * path from the start to the goal.
	 * 
	 * @return A string containing the cost and the list of moves to perform to
	 *         reach the goal as cheaply as possible.
	 */
	public String shortestPath() {
		Map start = new Map(startx, starty, map[starty][startx]);
		Map visit;
		Comparator<Map> compare = new SortMapData();
		PriorityQueue<Map> pq = new PriorityQueue<Map>(25, compare);
		int visitx;
		int visity;
		int westx;
		int eastx;
		int southy;
		int northy;
		pq.add(start);
		ArrayList<Map> visited = new ArrayList<Map>();
		while ((visit = pq.poll()) != null) {
			visited.add(visit);
			visitx = visit.getX();
			visity = visit.getY();
			if (visitx == finishx && visity == finishy) {
				return constructPath(visit);
			}
			westx = visitx - 1;
			eastx = visitx + 1;
			southy = visity + 1;
			northy = visity - 1;
			if (westx >= 0) {
				Map westNeighbor = new Map(westx, visity, visit.getPathCost()
						+ map[visity][westx], visit);
				if (!visited.contains(westNeighbor)) {
					pq.add(westNeighbor);
				}
			}
			if (eastx < map[0].length) {
				Map eastNeighbor = new Map(eastx, visity, visit.getPathCost()
						+ map[visity][eastx], visit);
				if (!visited.contains(eastNeighbor)) {
					pq.add(eastNeighbor);
				}
			}
			if (southy < map.length) {
				Map southNeighbor = new Map(visitx, southy, visit.getPathCost()
						+ map[southy][visitx], visit);
				if (!visited.contains(southNeighbor)) {
					pq.add(southNeighbor);
				}
			}
			if (northy >= 0) {
				Map northNeighbor = new Map(visitx, northy, visit.getPathCost()
						+ map[northy][visitx], visit);
				if (!visited.contains(northNeighbor)) {
					pq.add(northNeighbor);
				}
			}
		}
		return "Failure.";
	}

	/**
	 * A method that uses each MapData's origin field to construct the path that
	 * was taken from the finish back to the start.
	 * 
	 * @return The list of moves to reach the finish from the start.
	 */
	public String constructPath(Map finish) {
		Map previous = finish;
		Map current = finish.getOrigin();
		String path = "";
		while (current != null) {
			/*
			 * This code remains as a snippet to indicate what was done to
			 * create the generated image. for(int i = 0; i < 16; i++) { for(int
			 * j = 0; j < 16; j++) { pic.setRGB(16*previous.getX() + i,
			 * 16*previous.getY() + j, 255, 0, 0); } }
			 */
			path += pathHelper(current, previous);
			previous = current;
			current = current.getOrigin();
		}
		// pic.saveImage();
		return finish.getFullCost() + "\n"
				+ (new StringBuffer(path).reverse().toString());
	}

	/**
	 * A helper for constructPath that compares the x and y values of two nodes
	 * to determine whether a move of left, right, up, or down was made.
	 * 
	 * @return A single character string of L, R, D, or U.
	 * @see constructPath
	 */
	public String pathHelper(Map a, Map b) {
		if ((a.getX() - b.getX()) == 1) {
			return "L";
		}
		if ((b.getX() - a.getX()) == 1) {
			return "R";
		}
		if ((a.getY() - b.getY()) == 1) {
			return "U";
		}
		if ((b.getY() - a.getY()) == 1) {
			return "D";
		}
		return "X";
	}

  public void update() {
  }
}
