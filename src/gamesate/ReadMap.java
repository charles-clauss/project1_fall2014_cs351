package gamesate;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ReadMap
{

  public static int[][] map = new int[30][28];
  public static int startx;
  public static int starty;
  public static int finishx;
  public static int finishy;

  ReadMap()
  {
  };

  /**
   * @throws IOException
   * @throws MalformedURLException
   */
  public void readMapPic() throws IOException
  {
    BufferedImage img = ImageIO.read(new File("../clientPack/Antworld.png")); // Throws
                                                                              // IOException
    int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0,
        img.getWidth());

    // also available through the BufferedImage's Raster, in multiple
    // formats.
    // Raster r = img.getData();
    // int[] pixels = r.getPixels(0,0,r.getWidth(), r.getHeight(),
    // (int[])null);

  }

  public void readMapText() throws MalformedURLException, IOException
  {
    // I made this a URL mostly as practice, but also so that there wouldn't
    // be
    // file system issues when I turned in my code.
    URL mapfile = new URL("https://www.cs.unm.edu/~toriadam/mapweight.txt");
    Reader fileReader = new InputStreamReader(mapfile.openStream());
    BufferedReader reader = new BufferedReader(fileReader);
    String line;
    int row = 0;
    int column = 0;
    int temp = 0;
    while ((line = reader.readLine()) != null)
    {
      for (char c : line.toCharArray())
      {
        switch (c)
        {
          case 'X':
            temp = 100;
            break;
          case '0':
            temp = 1;
            break;
          case '1':
            temp = 2;
            break;
          case '2':
            temp = 4;
            break;
          case '>':
            temp = 0;
            startx = column;
            starty = row;
            break;
          case '!':
            temp = 0;
            finishx = column;
            finishy = row;
            break;
          default:
            temp = -1;
            break;
        }
        map[row][column] = temp;
        column++;
      }
      column = 0;
      row++;
    }
  }
}
