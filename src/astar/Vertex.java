package astar;

/*
 * Node will be the generic Node that is passed to us?
 * Perhaps?
 *
 *
 */
public class Vertex {
	// counter for number of nodes
	private static int totalVertex = 0;
	//weight of the node
	protected int weight;
	// all nodes by default are not targets
	protected boolean isMovable = false;
	protected boolean isWater = false;
	protected boolean isTarget = false;
	protected boolean isFood = false;
	protected boolean isEnemy = false;
	protected boolean isFriendly = false;
	// x and y coordinates
	protected final int x;
	protected final int y;

	// 'pointers' to connected nodes
	Vertex north; 
	Vertex east;
	Vertex south;
	Vertex west;


	/**
	 * default const
	 */
	Vertex (){
		this.weight = 1;
		this.isMovable = true;
		this.isTarget = false;
		this.isFood = false;
		this.isEnemy = false;
		this.isFriendly = false;
		this.isMovable = true;
		// x and y uninitialized
		totalVertex++;
		this.x = -1;
		this.y = -1;
	}

	Vertex(int edgeWeight, boolean canMove, boolean Target, 
			boolean food, boolean enemy, boolean friendly, boolean movable,
			int xPos, int yPos){
		if (totalVertex == 0){
			// set root, upper left??
			this.north = null;
			this.west = null;
		}
		this.weight = edgeWeight;
		this.isMovable = canMove;
		this.isTarget = Target;
		this.isFood = food;
		this.isEnemy = enemy;
		this.isFriendly = friendly;
		this.isMovable = movable;

		this.x = xPos;
		this.y = yPos;
		// x and y uninitialized
		totalVertex++;
	}

	// getters
	int getTotalVertex(){
		return totalVertex;
	}

	Vertex(int edgeWeight, boolean canMove, boolean Target, 
			boolean food, boolean enemy, boolean friendly, boolean movable,
			int xPos, int yPos, int color){
		this.weight = edgeWeight;
		this.isMovable = canMove;
		this.isTarget = Target;
		this.isFood = food;
		this.isEnemy = enemy;
		this.isFriendly = friendly;
		this.isMovable = movable;

		this.x = xPos;
		this.y = yPos;
		// x and y uninitialized
		totalVertex++;
	}

	/*
	private void setParams(String color){
		switch (color){
			case 'blue': 
				isWater = true;
				break;

			case 'black':
				isNest = true;
				break;
			case 'green':
				weight = 1;
				break;
			case 'deepGreen':
				weight = 2;
				break;
			case 'lightGreen':
				weight = 3;
				break;
			case 'otherGreen':
				weight = 4;
				break;
			case 'lightestGreen':
				weight = 5;
				break;
		} //end switch



	} // end setParams method
	*/

}//end class

