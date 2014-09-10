package controller;

import java.util.ArrayDeque;

import antworld.data.*;
import event.Observer;
import event.GameEvent;

//Make this extend an Observer class that can be notified
//by a global event handler that holds game state
public abstract class Ant implements Runnable, Observer
{
  protected int queueCap = 10;
  protected ArrayDeque<AntAction> actions;
  protected GameEvent currentTask;
  protected GameEvent newTask;
  public AntAction nextAction;
  
  public NestNameEnum nest;
  public TeamNameEnum team;
  public AntType antType;
  public int id;
  public int xPos, yPos;
  public FoodType carryType;
  public int carryUnits;
  public int ticksUntilNextAction;
  public int health;
  public boolean underground = true;
  public boolean alive = true;
  public Ant(AntData data)
  {
    actions = new ArrayDeque<AntAction>(queueCap);
    this.nest = data.nestName;
    this.team = data.teamName;
    this.antType = data.antType;
    this.id = data.id;
    this.xPos = data.gridX;
    this.yPos = data.gridY;
    this.carryType = data.carryType;
    this.carryUnits = data.carryUnits;
    this.ticksUntilNextAction = data.ticksUntilNextAction;
    this.health = data.health;
    this.underground = data.underground;
    this.alive= data.alive;
  }
  public AntData createAntData()
  {
    AntData copy = new AntData(id, antType, nest, team);
    copy.id = this.id;
    copy.gridX = this.xPos;
    copy.gridY = this.yPos;
    copy.carryType = this.carryType;
    copy.carryUnits = this.carryUnits;
    copy.myAction = this.nextAction;
    copy.ticksUntilNextAction = this.ticksUntilNextAction;
    copy.health = this.health;
    copy.underground = this.underground;
    copy.alive = this.alive;
    return copy;
  }
  public void setNextAction()
  {
    if(!actions.isEmpty())
    {
      nextAction = actions.poll();
    }
    else
    {
      nextAction = new AntAction(AntAction.AntActionType.STASIS);
    }
  }
  public void update(GameEvent ge)
  {
  }
  public void run()
  {
    //check for event update
    //do any path finding
    //setNextAction and update data based on that action
  }
  public boolean equals(Object o)
  {
    if(o instanceof AntData)
    {
      if(((AntData)o).id == this.id)
      {
        return true;
      }
    }
    return false;
  }
}
