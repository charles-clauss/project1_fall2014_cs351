package astar;
import java.lang.Math;

/**
 * @author agonzales
 * class that defines both the manhatten distance and 
 * euclidian distance as potential heuristics for the 
 * a* search. Can add new methods here as applicable
 *
 *
 *
 * 
 */

public class heuristic {

	/**
	 * manDistance is the distance optimized for grids.
	 * @param currentX X coord of current node
	 * @param currentY Y coord of current node
	 * @param targetX X coord of target node
	 * @param targetY Y coord of target node
	 *
	 */
	public double manDistance(int currentX, int currentY, int targetX, int targetY ){
		double distance = Math.abs(targetX - currentX) + Math.abs(targetY - currentY);
		return distance;
	}
	/**
	 * euclidDistance is the regular euclidian distance.
	 * @param currentX X coord of current node
	 * @param currentY Y coord of current node
	 * @param targetX X coord of target node
	 * @param targetY Y coord of target node
	 *
	 */
	
	public double euclidDistance(int currentX, int currentY, int targetX, int targetY ){
		double distance = Math.sqrt(
				Math.pow(currentX - targetX, 2) +
				Math.pow(currentY - targetY, 2));
		return distance;
	}

}
