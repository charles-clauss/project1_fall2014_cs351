package controller;

import java.util.ArrayDeque;

import astar.Vertex;
import clientPack.antworld.data.*;
import event.Observer;
import event.GameEvent;

//Make this extend an Observer class that can be notified
//by a global event handler that holds game state
public abstract class Ant extends Observer implements Runnable
{
  protected AntData data;
  protected Vertex position;
  protected int queueCap = 10;
  protected ArrayDeque<AntAction> actions;
  public Ant()
  {
    actions = new ArrayDeque<AntAction>(queueCap);
  }
  public AntAction getNextAction()
  {
    if(!actions.isEmpty())
    {
      return actions.poll();
    }
    else return new AntAction(AntAction.AntActionType.STASIS);
  }
  public void findPath()
  {
    //Need to be able to tell astar what your current goal is
    //only call when about to run out of actions to perform
    //or some other event occurred
  }
  public void goToNest()
  {
    //Find closest nest point and get a path there
  }
  public void heal()
  {
    //logic to either go to the nest or wait inside the nest
  }
  public void update(GameEvent ge)
  {
  }
}
