package astar;

/*
 * Node will be the generic Node that is passed to us?
 * Perhaps?
 *
 *
 */
public class Node<N> {
	// counter for number of nodes
	private static int numNodes = 0;
	//weight of the node
	protected <N> weight;
	// can move to this node
	protected Boolean canMoveTo;
	// all nodes by default are not targets
	protected Boolean isTarget = false;
	
	Node createNode(char c){
		if (c == "!"){this.isTarget = true;}
		if (c == "X"){this.canMoveTo = false;}
		if (c == 0){ this.cost = 1;}
		if (c == 1){ this.cost = 2;}
		if (c == 2){ this.cost = 4;}
		// start note has cost 0
		if (c == ">"){this.cost = 0;}
		return this;
	}
}
