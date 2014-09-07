package gameBoard;

import java.util.ArrayList;
import java.util.List;

public class PictureMod
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

  }

}
