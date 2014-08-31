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

	public List<Vertex> findPath(Vertex start, Vertex goal){
		int gScore = 0;
		int fScore = gScore + heuristicCost(start, goal);
		openList.add(start);

		while (openList.size() != 0){
			if (current == goal){
				return constructPath(cameFromList, goal);
			}

		}
		
		return finishedPath;
	}

	public List<Vertex> constructPath(List<Vertex> cameFrom, Vertex goal){
		List<Vertex> pathList = new ArrayList<Vertex>();

		return pathList;

	}


} // end class AStar


/*
 *
closedset := the empty set    // The set of nodes already evaluated.
openset := {start}    // The set of tentative nodes to be evaluated, initially containing the start node
came_from := the empty map    // The map of navigated nodes.

g_score[start] := 0    // Cost from start along best known path.
// Estimated total cost from start to goal through y.
f_score[start] := g_score[start] + heuristic_cost_estimate(start, goal)

	while openset is not empty
		current := the node in openset having the lowest f_score[] value
		if current = goal
		return reconstruct_path(came_from, goal)

		remove current from openset
		add current to closedset
		for each neighbor in neighbor_nodes(current)
			if neighbor in closedset
				continue
			tentative_g_score := g_score[current] + dist_between(current,neighbor)

			if neighbor not in openset or tentative_g_score < g_score[neighbor] 
				came_from[neighbor] := current
				g_score[neighbor] := tentative_g_score
				f_score[neighbor] := g_score[neighbor] + heuristic_cost_estimate(neighbor, goal)
				if neighbor not in openset
					add neighbor to openset

		return failure

function reconstruct_path(came_from, current_node)
	if current_node in came_from
		p := reconstruct_path(came_from, came_from[current_node])
		return (p + current_node)
	else
		return current_node

*/
