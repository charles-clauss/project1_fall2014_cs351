package astar;

public class Node {
	protected int cost;
	protected Boolean canMoveTo;
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
