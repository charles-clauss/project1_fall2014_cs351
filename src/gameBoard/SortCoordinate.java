package gameBoard;

import java.util.Comparator;
/**
* Provides the parameter of interest to be used
* for comparison in the priority queue that is
* used to implement the Astar algorithm.
* @see final_lab1
*/
public class SortCoordinate implements Comparator<Coordinate> {
  public int compare(Coordinate a, Coordinate b) {
    return a.getWeight() - b.getWeight();
  }
}
