package controller;

import java.util.ArrayDeque;
import clientPack.antworld.data.*;

public abstract class Ant implements Runnable {
  protected AntData data;
  protected int queueCap = 10;
  protected ArrayDeque<AntAction> actions;
  public Ant() {
    actions = new ArrayDeque<AntAction>(queueCap);
  }
  public AntAction getNextAction() {
	return actions.poll();
  }
  public void findPath(int x, int y) {
	//Use astar to retrieve a path and create an action list
  }
  public void goToNest() {
    //Find closest nest point and get a path there
  }
  public void heal() {
    //logic to either go to the nest or wait inside the nest
  }
}
