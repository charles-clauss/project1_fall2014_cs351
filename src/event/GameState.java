package event;

import astar.AStar;
import controller.AntController;
import clientPack.antworld.data.*;

public class GameState {
  private AntController control;
  private Observer astar = new AStar();
  private CommData data;
  
  public GameState() {
  }
  
  public static void main(String args[]) {
  }
}
