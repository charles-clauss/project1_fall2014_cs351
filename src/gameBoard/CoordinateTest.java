package gameBoard;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CoordinateTest
{

  public void printStuff(Coordinate a){
    System.out.println("Testing Attributes of coordinate: " + a);
    System.out.println("GetRGB: " + a.getRGB());
    System.out.println("GetXY: " + a.getX() + "," + a.getY());
    System.out.println("GetWeight " + a.getWeight());
    System.out.println("GetParent  " + a.getParent());

  }
  @Test
  public void test()
  {
    String path = new String("/Users/carlyhendrickson/Dropbox/cs/351/project_01/clientPack/AntWorld.png");
    Picture pic = new Picture(path);
    
    Coordinate justInts = new Coordinate(0, 0);
    Coordinate fromPicture = new Coordinate(0,0,pic);
    
    Coordinate birthed = new Coordinate(0,0,pic,fromPicture);
    
    
    printStuff(justInts);
    printStuff(fromPicture);
    printStuff(birthed);
    
    List<Coordinate> coordlist = new ArrayList<Coordinate>();
    for (int x = 1000; x < 2000; x++){
      for( int y=1000; y < 2000; y++){
        coordlist.add(new Coordinate(x,y,pic));
      }
    }
    for (Coordinate c : coordlist){
      printStuff(c);
    }
    
  }
  

}
