/**
 * 
 */
package astar;

import java.io.*;


/**
 * @author agonzales
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {
  public static int[][] map;
  public static int startx;
  public static int starty;
  public static int finishx;
  public static int finishy;

  public ReadMap() {
  }

  public void readMap() {
    map = new int[30][28];
    File file = new File("/Users/cclauss/Documents/workspace/cs351lab1/MapWeights");
    FileReader filereader;
    try {
      filereader = new FileReader(file);
    } catch (FileNotFoundException e) {
      System.out.println("Error opening file MapWeights");
      return;
    }
    BufferedReader reader = new BufferedReader(filereader);
    String line;
      try {
        int row = 0;
        int column = 0;
        int temp = 0;
        while((line = reader.readLine()) != null) {
          for(char c : line.toCharArray()) {
            switch(c) {
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
                startx = row;
                starty = column;
                break;
              case '!':
                temp = 0;
                finishx = row;
                finishy = column;
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
      } catch (IOException e) {
        System.out.println("Error during parsing.");
      }
  }


  public static void main(String[] args) {
    ReadMap star = new ReadMap();
    star.readMap();
    for(int i = 0; i < 30; i++) {
      for(int j = 0; j < 28; j++) {
        
      }
    }
  }

}
