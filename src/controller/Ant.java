package controller;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import antworld.data.*;
import event.GatherEvent;
import event.Observer;
import event.GameEvent;
import gameBoard.AStar;
import gameBoard.Coordinate;

//Make this extend an Observer class that can be notified
//by a global event handler that holds game state
public abstract class Ant implements Runnable, Observer
{
  protected int queueCap = 20;
  protected Queue<AntAction> actions;
  protected GameEvent currentTask;
  protected GameEvent newTask;
  protected boolean actionSuccess = true;
  
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
    this.alive = data.alive;
  }
  
  public AntData createAntData()
  {
    AntData copy = new AntData(id, antType, nest, team);
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
    if(this.underground)
    {
      Coordinate exit = AntController.getRandomExitCoordinate();
      nextAction = new AntAction(AntAction.AntActionType.EXIT_NEST, exit.getX(), exit.getY());
    }
    else if(!actions.isEmpty())
    {
      nextAction = actions.poll();
      if(nextAction.type == AntAction.AntActionType.MOVE)
      {
        xPos += nextAction.direction.deltaX();
        yPos += nextAction.direction.deltaY();
      }
      if(nextAction.type == AntAction.AntActionType.DROP)
      {
        carryUnits = 0;
      }
      if(nextAction.type == AntAction.AntActionType.PICKUP)
      {
        carryUnits = 1;
      }
    }
    else
    {
      nextAction = new AntAction(AntAction.AntActionType.STASIS);
      update(AntController.getEvent());
    }
  }
  
  public void update(GameEvent ge)
  {
    currentTask = ge;
    if(ge.getType() == "gatherFood")
    {
      Coordinate myPos = new Coordinate(this.xPos, this.yPos);
      FoodData food = AntController.getNearestFood(myPos);
      List<Coordinate> moves = AStar.findPath(myPos, new Coordinate(food.gridX, food.gridY));
      actions.clear();
      for(int i = 0; i < moves.size() - 3; i++)
      {
        actions.add(new AntAction(AntAction.AntActionType.MOVE, Coordinate.getDirection(moves.get(i), moves.get(i+1))));
      }
      actions.add(new AntAction(AntAction.AntActionType.PICKUP,
                  Coordinate.getDirection(moves.get(moves.size() - 2), moves.get(moves.size() - 1)),
                  antType.getCarryCapacity()));
      myPos = moves.get(moves.size() - 2);
      List<Coordinate> movesHome = AStar.findPath(myPos, AntController.getNearestNestCoordinate(myPos));
      for(int i = 0; i < moves.size() - 3; i++)
      {
        actions.add(new AntAction(AntAction.AntActionType.MOVE, Coordinate.getDirection(movesHome.get(i), movesHome.get(i+1))));
      }
      actions.add(new AntAction(AntAction.AntActionType.DROP,
                                Coordinate.getDirection(movesHome.get(movesHome.size() - 2), movesHome.get(movesHome.size() - 1)),
                                antType.getCarryCapacity()));
    }
  }
  
  public void run()
  {
    if(ticksUntilNextAction == 0)
    {
      if(actionSuccess)
      {
        setNextAction();
      }
    }
  }
  
  public void setFailure()
  {
    actionSuccess = false;
  }
  
  public void setSuccess()
  {
    actionSuccess = true;
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
