/**
 * 
 */
package astar;

import gameBoard.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import event.GameEvent;
import event.Observer;

/**
 * @author agonzales Implements a version of the A* algorithm using a priority
 *         queue. Will change as we get program specs on how the graph is
 *         represented.
 */
public class AStar extends Observer {
	private Graph searchMap;
	private Vertex current;
	private Vertex next;
	private Queue<Vertex> openList = new PriorityQueue<Vertex>();
	private List<Vertex> closedList = new ArrayList<Vertex>();
	private List<Vertex> cameFromList = new ArrayList<Vertex>();
	private List<Vertex> finishedPath = new ArrayList<Vertex>();

	public List<Vertex> getFinishedPath() {
		return finishedPath;
	}

	
	public AStar(Graph graph) {
		this.searchMap = graph;
	}

	@Override
	public void update(GameEvent ge) {
		// TODO Auto-generated method stub

	}

	/**
  * A function that calculates a heuristic for each node, in this
  * case the manhattan distance of the node from the finish
  * is used.
  * @return The estimated total distance of a node from the finish,
  * based on the cost to reach that node plus the heuristic estimate.
  */
  public static int distance(Vertex current, Vertex goal) {
    int heuristic = Math.abs(goal.getX() - current.getX()) + Math.abs(goal.getY() - current.getY());
    return heuristic;
  }

  public static int estimateDistance(Vertex current, Vertex goal, Vertex neighbor, int currentDistance){
    int estimate = distance(neighbor, goal) + currentDistance;
    return estimate;
  }
  
	public List<Vertex> findPath(Vertex start, Vertex goal){
		int gScore = 0;
		int fScore = gScore + heuristicCost(start, goal);
		int accumulatedScore;
		openList.add(start);
		List<Vertex> nullList = new ArrayList<Vertex>();
		nullList = null;

		while (openList.poll() != null){
			current = openList.poll();
			if (current == goal){
				return constructPath(cameFromList, goal);
			}

			openList.remove(current);
			closedList.add(current);

			for (Vertex neighbor : current.getEdges()){
				if (closedList.contains(neighbor)){
					continue;
				}
				else {
					accumulatedScore = distance(current, goal) + distance(current, neighbor);
				}
				if (openList.contains(neighbor) == false || accumulatedScore < distance(neighbor,goal)){
					if (!openList.contains(neighbor)) {
						openList.add(neighbor);
					}
					cameFromList.add(current);
					current = neighbor;

				}

			}
		}
		return nullList;
	}

	public List<Vertex> constructPath(List<Vertex> cameFrom, Vertex current){
		List<Vertex> pathList = new ArrayList<Vertex>();
		if (cameFrom.contains(current)){
			finishedPath.add(constructPath(cameFrom, cameFrom(current)));
			}
			else {
				return current;
			}
			return pathList;
		}
} // end class AStar
