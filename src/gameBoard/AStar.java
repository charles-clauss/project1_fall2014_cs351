package gameBoard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import event.GameEvent;
import event.Observer;

/**
 * @author agonzales Implements a version of the A* algorithm using a priority
 *         queue. Will change as we get program specs on how the graph is
 *         represented.
 */
public class AStar extends Observer
{

  @Override
  public void update(GameEvent ge)
  {
    // TODO Auto-generated method stub

  }

  public static int manDistance(Coordinate current, Coordinate goal)
  {
    int heuristic = Math.abs(goal.getX() - current.getX())
        + Math.abs(goal.getY() - current.getY());
    return heuristic;
  }

  public static int estimateDistance(Coordinate goal,
      Coordinate neighbor, int currentDistance)
  {
    int estimate = manDistance(neighbor, goal) + currentDistance;
    return estimate;
  }

  public List<Coordinate> findPath(Coordinate start, Coordinate goal)
  {
    Coordinate current = start;
    Comparator<Coordinate> compare = new SortCoordinate();
    List<Coordinate> nullList = new ArrayList<Coordinate>();
    List<Coordinate> closedList = new ArrayList<Coordinate>();
    List<Coordinate> cameFromList = new ArrayList<Coordinate>();
    // List<Coordinate> finishedPath = new ArrayList<Coordinate>();
    Queue<Coordinate> openList = new PriorityQueue<Coordinate>(25, compare);
    nullList = null;
    openList.add(current);
//    System.out.println("Astar: pq peek = " + openList.peek());
    current.setDistanceToGoal(manDistance(current, goal));

    while (openList.peek() != null)
    {
      current = openList.poll();
      closedList.add(current);
      cameFromList.add(current);

      if (current.areEqual(goal) == true)
      {
        // System.out.println(constructPath(goal));
        return cameFromList;
      }

      List<Coordinate> neighbors = current.getNeighbors();

      for (Coordinate n : neighbors)
      {
        n.setDistanceToGoal(n.getWeight() + manDistance(n, goal));
        if (!closedList.contains(n))
        {
          openList.add(n);
        }
      } // end for
      System.out.println(openList.peek().getDistanceToGoal());

    } // end while
    // failure
    return nullList;
  } // end findpath

  /**
   * A method that uses each MapData's origin field to construct the path that
   * was taken from the finish back to the start.
   * 
   * @return The list of moves to reach the finish from the start.
   */
  public String constructPath(Coordinate finish)
  {
    Coordinate previous = finish;
    Coordinate current = finish.getParent();
    String path = "";
    while (current != null)
    {
      /*
       * This code remains as a snippet to indicate what was done to create the
       * generated image. for(int i = 0; i < 16; i++) { for(int j = 0; j < 16;
       * j++) { pic.setRGB(16*previous.getX() + i, 16*previous.getY() + j, 255,
       * 0, 0); } }
       */
      path += pathHelper(current, previous);
      previous = current;
      current = current.getParent();
    }
    // pic.saveImage();
    return finish.getCostSoFar() + "\n"
        + (new StringBuffer(path).reverse().toString());
  }

  /**
   * A helper for constructPath that compares the x and y values of two nodes to
   * determine whether a move of left, right, up, or down was made.
   * 
   * @return A single character string of L, R, D, or U.
   * @see constructPath
   */
  public String pathHelper(Coordinate a, Coordinate b)
  {
    if ((a.getX() - b.getX()) == 1)
    {
      return "L";
    }
    if ((b.getX() - a.getX()) == 1)
    {
      return "R";
    }
    if ((a.getY() - b.getY()) == 1)
    {
      return "U";
    }
    if ((b.getY() - a.getY()) == 1)
    {
      return "D";
    }
    return "X";
  }

} // end class AStar
