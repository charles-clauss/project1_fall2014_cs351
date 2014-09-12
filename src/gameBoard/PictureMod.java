package gameBoard;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

public class PictureMod implements ComponentListener
{

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

  public void writeText(String string, Picture pic, Coordinate c){
   
   
    
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
    System.out.println("testing pic class");

    String path = new String("/Users/carlyhendrickson/Dropbox/cs/351/project_01/clientPack/AntWorld.png");
    Picture pic = new Picture(path);
    

    // test blue values
    //int red = pic.getRed(0, 0);
    //int blue = pic.getBlue(0, 0);
    //int green = pic.getGreen(0, 0);
    

   // System.out.println("blue = " + blue);
    //System.out.println("red = " + red);
    //System.out.println("green = " + green);
    List<Integer> rgb = new ArrayList<Integer>(getPixelVal(pic, 0, 0));
    for (Integer i : rgb){
      System.out.println(i);
    }
    System.out.println(getPixelVal(pic, 0, 10));

    
    System.out.println("Testing Astar");
    // AStar astar = new AStar();

     Coordinate start = new Coordinate(300,250);
     //Coordinate start = new Coordinate(580,170);

     Coordinate goal = new Coordinate(580, 160);
     

     
     List<Coordinate> myPath = new ArrayList<Coordinate>();
     // printStuff(goal);
     myPath = AStar.findPath(start, goal);

     int cumWeight = 0;
     for (Coordinate c : myPath)
     {
       cumWeight+=c.getWeight();
       System.out.println("Path is: " + c.getX() + "," + c.getY() + "Distance"
           + c.getDistanceToGoal());
       Color black = new Color(0, 0, 0);
       Coordinate.getPic().setColor(c.getX(), c.getY(), black);
       
     }
     System.out.println("Weight of the path = " + cumWeight);



  }

}
