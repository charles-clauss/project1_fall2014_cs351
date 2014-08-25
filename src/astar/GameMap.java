package astar;

import java.util.Comparator;


/*
 * Map will implement something as a weighted graph structure 
 * using an adjacency-list representation.
 * 
 * for full map, 2500x5000, need to use an int (+-2^32 possible)
 * to hold / count, shorts won't cut it. may have memory issues? 
 *
 */
class GameMap {
	protected final int x;
	protected final int y;
	protected int pathCost;
	protected int fullCost;
	protected GameMap origin = null;

	/**
	 * Constructs a MapData object with no origin, and is only used for the
	 * start of the path that will be computed by Astar.
	 * 
	 * @param x
	 *            - x coordinate
	 * @param y
	 *            - y coordinate
	 * @param pathCost
	 *            - initial cost of the path (typically 0)
	 */
	public GameMap(int x, int y, int pathCost) {
		this.x = x;
		this.y = y;
		this.pathCost = pathCost;
		this.fullCost = AStar.cost(x, y, pathCost);
	}

	/**
	 * Constructs a MapData object with a reference to the object that birthed
	 * it, as well as position and cost data.
	 * 
	 * @param x
	 *            - x coordinate
	 * @param y
	 *            - y coordiante
	 * @param pathCost
	 *            - cost to reach this node
	 * @param origin
	 *            - the node that this was searched from, which is guaranteed to
	 *            be part of the best path since the heuristic that is used is
	 *            monotonic.
	 */
	public GameMap(int x, int y, int pathCost, GameMap origin) {
		this.x = x;
		this.y = y;
		this.pathCost = pathCost;
		this.fullCost = AStar.cost(x, y, pathCost);
		this.origin = origin;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getPathCost() {
		return this.pathCost;
	}

	/**
	 * Indicates the estimated cost from the start to the finish if this node is
	 * included in the path.
	 * 
	 * @return Returns f(node), where [f(node) = g(node) + h(node)] g being the
	 *         cost to reach this node, and h being the heuristic that estimates
	 *         the remaining cost to the finish.
	 */
	public int getFullCost() {
		return this.fullCost;
	}

	public GameMap getOrigin() {
		return this.origin;
	}

	public boolean equals(Object o) {
		if (o instanceof GameMap) {
			if (((GameMap) o).getX() == this.x && ((GameMap) o).getY() == this.y) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Provides the parameter of interest to be used for comparison in the
	 * priority queue that is used to implement the Astar algorithm.
	 * 
	 * @see Astar
	 */
	class SortMapData implements Comparator<GameMap> {
		public int compare(GameMap a, GameMap b) {
			return a.getFullCost() - b.getFullCost();
		}
	}
}
