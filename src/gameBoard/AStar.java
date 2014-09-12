package gameBoard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import event.GameEvent;
import event.Observer;

/**
 * @author agonzales Implements a version of the A* algorithm using a priority
 *         queue. Uses euclidian distance 
 *         as a heuristic. 
 *         Generates a 'coordinate' object with path information.
 */
public class AStar extends Observer
{

  @Override
  public void update(GameEvent ge)
  {
    // TODO Auto-generated method stub

  }

  /**
   * Returns the manhatten distance from a coordinate to another coordinate
   * @param current a coordinate
   * @param goal another coordinate
   * @return the distance between the two
   */
  public static int manDistance(Coordinate current, Coordinate goal)
  {
    int heuristic = Math.abs(goal.getX() - current.getX())
        + Math.abs(goal.getY() - current.getY());
    return heuristic;
  }

  /**
   * Euclidian distance between nodes.
   * @param current
   * @param goal
   * @return ceiling of the float distance. 
   */
  public static int euclidDistance(Coordinate current, Coordinate goal)
  {
    double distance = Math.sqrt(
        Math.pow(current.getX() - goal.getX(), 2) +
            Math.pow(current.getY() - goal.getY(), 2));
    return (int) Math.ceil(distance);
  }

  public static int estimateDistance(Coordinate goal,
      Coordinate neighbor, int currentDistance)
  {
    int estimate = manDistance(neighbor, goal) + currentDistance;
    return estimate;
  }

  public static void drawpath(Coordinate c){
    Color red = new Color(200, 0, 0);
    Coordinate.getPic().setColor(c.getX(), c.getY(), red);
  }
  
  public static boolean checkClosed(List<Coordinate> list, Coordinate c){
    for (Coordinate a : list){
      if( c.areEqual(a) ){
        return true;
      }
      
    }
    return false;
    
  }
  /**
   * Implementation of A* for pathfinding. Doesn't use a closedlist, 
   * just tossing things on the priority queue (openList) and letting it handle 
   * what's on top. I may recheck the comparator at some point.
   * @param start start of your path
   * @param goal target node
   * @return a list of the coordinates that each ant must traverse - this gives them
   * a better thing that can handle game events. 
   */
  public static List<Coordinate> findPath(Coordinate start, Coordinate goal)
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
    // System.out.println("Astar: pq peek = " + openList.peek());
    current.setDistanceToGoal(euclidDistance(current, goal));
    int examined = 0;
    while (openList.peek() != null)
    {
      current = openList.poll();
      closedList.add(current);
      cameFromList.add(current);

      if (current.areEqual(goal) == true)
      {
        // System.out.println(constructPath(goal));
        System.out.println("examined "+  examined + " nodes");
        return cameFromList;
      }

      List<Coordinate> neighbors = current.getNeighbors();

      for (Coordinate n : neighbors)
      {
        /*if (n.isMovable()==false){
          closedList.add(n);
          System.out.print("added " + n + " to closedlist, size = " );
          System.out.println(closedList.size());
          continue;
        }*/
        
        
        //if (!closedList.contains(n))
        if (checkClosed(closedList, n) == false)
        {
          n.setDistanceToGoal(n.getWeight() + euclidDistance(n, goal));
          //n.setDistanceToGoal(euclidDistance(n, goal));
          //n.setDistanceToGoal(n.getWeight());

          openList.add(n);
          
        }
        drawpath(n);
        //System.out.print("added " + n + " "+ n.getX() + "," +n.getY()+ " to openlist, size = " );
       // System.out.println(openList.size());
        examined++;
      } // end for
      //System.out.println(openList.peek().getDistanceToGoal());
      neighbors = null;
    } // end while
    // failure
    return nullList;
  } // end findpath

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
