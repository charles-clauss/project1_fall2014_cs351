/**
 * 
 */
package astar;

import gameBoard.*;

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
    Coordinate current;
    Comparator<Coordinate> compare = new SortCoordinate();
    List<Coordinate> nullList = new ArrayList<Coordinate>();

    List<Coordinate> closedList = new ArrayList<Coordinate>();
    List<Coordinate> cameFromList = new ArrayList<Coordinate>();
    // List<Coordinate> finishedPath = new ArrayList<Coordinate>();
    Queue<Coordinate> openList = new PriorityQueue<Coordinate>(25, compare);

    int gScore = 0;
    int fScore = gScore;
    
    nullList = null;
    openList.add(start);
    System.out.println("Astar: pq peek = " + openList.peek());
    
    cameFromList.add(start);

    while (openList.peek() != null)
    {
      current = openList.poll();
      System.out.println("Astar: current = " + current + "X,Y :"
          + " " + current.getX() + " " + current.getY());
      closedList.add(current);

      if (current.areEqual(goal) == true)
      {
        return cameFromList;
      }

      for (Coordinate neighbor : current.getNeighbors())
      {
        // System.out.println("Neighbors X,Y: " + neighbor.getX() + "," +
        // neighbor.getY() +", weight = " + neighbor.getWeight() );
        if (closedList.contains(neighbor))
        {
          continue;
        }
        
        int tempDistance = gScore + estimateDistance(neighbor, goal, gScore);
        
        if (openList.contains(neighbor)== false && tempDistance < )

      } // end for

    } // end while
    // failure
    return nullList;
  } // end findpath

  /**
   * public List<Coordinate> constructPath(List<Coordinate> cameFrom, Coordinate
   * current){ List<Coordinate> pathList = new ArrayList<Coordinate>(); if
   * (cameFrom.contains(current)){ finishedPath.add(constructPath(cameFrom,
   * cameFrom(current))); } else { return current; } return pathList; }
   */
} // end class AStar
