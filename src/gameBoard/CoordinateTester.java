package gameBoard;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * JUnit test class for the coordinates/pathfinding.
 * 
 * @author agonzales
 */
public class CoordinateTester
{

  /**
   * Prints various attributes of the coordinate class
   * 
   * @param a
   *          - a coordinate
   */
  public void printStuff(Coordinate a)
  {
    System.out.println("Testing Attributes of coordinate: " + a);
    System.out.println("GetRGB: " + a.getRGB());
    System.out.println("GetX,Y: " + a.getX() + "," + a.getY());
    System.out.println("GetWeight " + a.getWeight());
    System.out.println("GetParent  " + a.getParent());

  }

  @Test
  public void makeCoordinates()
  {
    // String path = new
    // String("/Users/carlyhendrickson/Dropbox/cs/351/project_01/clientPack/AntWorld.png");
    // Picture pic = new Picture(path);

    System.out.println("*****TESTING MAKECOORDINATES******");
    Coordinate justInts = new Coordinate(0, 0);
    Coordinate fromPicture = new Coordinate(0, 0);
    System.out.println("AreEqual: " + justInts.areEqual(fromPicture));
    Coordinate birthed = new Coordinate(10, 10, fromPicture);
    printStuff(justInts);
    printStuff(fromPicture);
    printStuff(birthed);
    List<Coordinate> coordlist = new ArrayList<Coordinate>();
    for (int x = 1000; x < 1010; x++)
    {
      for (int y = 2370; y < 2400; y++)
      {
        coordlist.add(new Coordinate(x, y));
      }
    }
    for (Coordinate c : coordlist)
    {
      printStuff(c);
    }
  }

  // @Test
  public void testPathfinder()
  {
    System.out.println("Testing Astar");
    Coordinate start = new Coordinate(1000, 2400);
    Coordinate goal = new Coordinate(1010, 2370);
    List<Coordinate> myPath = new ArrayList<Coordinate>();
    // printStuff(goal);
    myPath = AStar.findPath(start, goal);
    int cumWeight = 0;
    for (Coordinate c : myPath)
    {
      cumWeight += c.getWeight();
      System.out.println("Path is: " + c.getX() + "," + c.getY() + "Distance"
          + c.getDistanceToGoal());
      Color black = new Color(0, 0, 0);
      Coordinate.getPic().setColor(c.getX(), c.getY(), black);
    }
    System.out.println("Weight of the path = " + cumWeight);

  }

}
