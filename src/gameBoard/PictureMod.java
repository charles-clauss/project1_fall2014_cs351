package gameBoard;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

public class PictureMod implements ComponentListener
{
  private static final Picture MODPIC =
      new Picture(
          "/Users/carlyhendrickson/Dropbox/cs/351/project_01/clientPack/AntWorld.png");
  PictureMod()
  {

  }

  /**
   * @param pic
   * @param x
   * @param y
   * @return
   */
  public static List<Integer> getPixelVal(Picture pic, int x, int y)
  {
    int red = pic.getRed(x, y);
    int green = pic.getGreen(x, y);
    int blue = pic.getBlue(x, y);
    List<Integer> pixels = new ArrayList<Integer>();
    pixels.add(red);
    pixels.add(green);
    pixels.add(blue);

    return pixels;
  }

  /**
   * will iterate over a range in the picture to get values
   * 
   * @param start
   * @param stop
   * @return
   */
  public List<Integer> iteratePixels(int startx, int stopx, int starty,
      int stopy)
  {
    List<Integer> al = new ArrayList<Integer>();
    return al;
  }
  static public Picture getPic(){
    return PictureMod.MODPIC;
  }

  public void writeText(String string, Picture pic, Coordinate c)
  {

  }

  public void componentResized(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  public void componentMoved(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  public void componentShown(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  public void componentHidden(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  public static void main(String[] args)
  {
    ///System.out.println("testing pic class");
 /*   String path = new String(
        "/Users/carlyhendrickson/Dropbox/cs/351/project_01/clientPack/AntWorld.png");
    Picture pic = new Picture(path);
    */
  /*
    List<Integer> rgb = new ArrayList<Integer>(getPixelVal(pic, 0, 0));
    for (Integer i : rgb)
    {
      System.out.println(i);
    }
    System.out.println(getPixelVal(pic, 0, 10));
*/
    System.out.println("Testing Astar");
    // AStar astar = new AStar();

     Coordinate start = new Coordinate(300, 250);
    // Coordinate start = new Coordinate(580,170);

    //Coordinate goal = new Coordinate(580, 160);
     Coordinate goal = new Coordinate(905,517);
    
    List<Coordinate> myPath = new ArrayList<Coordinate>();
    // printStuff(goal);
    myPath = AStar.findPath(start, goal);

    int cumWeight = 0;
    for (Coordinate c : myPath)
    {
      cumWeight += c.getCostSoFar();
      System.out.println("Path is: " + c.getX() + "," + c.getY() + "Distance"
          + c.getDistanceToGoal());
      Color black = new Color(0, 0, 0);
      PictureMod.getPic().setColor(c.getX(), c.getY(), black);
      //PictureMod.MODPIC.getGraphics().drawString(".", c.getX(), c.getY());


    }
    System.out.println("Weight of the path = " + cumWeight);

   // PictureMod.MODPIC.getGraphics().drawString("Test", 100, 100);
    
  }
  

}
