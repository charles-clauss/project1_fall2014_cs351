package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

import antworld.data.AntAction;
import antworld.data.AntData;
import antworld.data.CommData;
import antworld.data.Constants;
import antworld.data.FoodData;
import event.ExploreEvent;
import event.GameEvent;
import event.GatherEvent;
import gameBoard.AStar;
import gameBoard.Coordinate;

public class AntController
{
  private ExecutorService exec = Executors.newFixedThreadPool(4);
  private List<Ant> ants = new ArrayList<Ant>();
  private static List<Coordinate> nestLocations = new ArrayList<Coordinate>();
  private static HashSet<FoodData> visibleFood;
  private static Coordinate nestCenter;

  public AntController(CommData startingAnts)
  {
    for(AntData ant : startingAnts.myAntList)
    {
      addAnt(ant);
    }
    
    setNestLocations(startingAnts);
  }
  /**
   * gives the appoximate corner locations for the ant nest
   * so ants can find their way back home. only ran at initial instantiation
   * @param data commdata package. 
   */
  public void setNestLocations(CommData data){
    nestCenter = new Coordinate(data.nestData[data.myNest.ordinal()].centerX, data.nestData[data.myNest.ordinal()].centerY);
    int x = nestCenter.getX();
    int y = nestCenter.getY();
    //Coordinate nestCenter = new Coordinate(x,y);    
    Coordinate northCorner = new Coordinate(x-18, y);
    Coordinate southCorner = new Coordinate(x+18, y);
    Coordinate eastCorner = new Coordinate(x, y+18);
    Coordinate westCorner = new Coordinate(x, y-18);
    
    nestLocations.add(northCorner);
    nestLocations.add(southCorner);
    nestLocations.add(westCorner);
    nestLocations.add(eastCorner);
  }
  
  public static Coordinate getNearestNestCoordinate(Coordinate location)
  {
    int best_guess = Integer.MAX_VALUE, current_guess = Integer.MAX_VALUE;
    Coordinate bestSpot = null;
    for(Coordinate nestPoint : nestLocations)
    {
      current_guess = AStar.euclidDistance(location, nestPoint);
      if(current_guess < best_guess)
      {
        best_guess = current_guess;
        bestSpot = nestPoint;
      }
    }
    return bestSpot;
  }
  
  public static Coordinate getRandomExitCoordinate()
  {
    int xOffset = Constants.random.nextInt(3) - 1;
    int yOffset = Constants.random.nextInt(3) - 1;
    int pick = Constants.random.nextInt(4);
    Coordinate randomNestPoint = nestLocations.get(pick);
    return new Coordinate(randomNestPoint.getX() + xOffset, randomNestPoint.getY() + yOffset);
  }

  public void dispatchThreads(CommData data)
  {
    AntController.visibleFood = data.foodSet;
    AntData temp;
    for(Ant ant : ants)
    {
      temp = data.myAntList.get(data.myAntList.indexOf(ant));
      if(ant.nextAction.type == AntAction.AntActionType.MOVE)
      {
        if(temp.gridX == ant.xPos && temp.gridY == ant.yPos)
        {
          ant.setSuccess();
        }
        else
        {
          ant.setFailure();
        }
      }
      if(ant.nextAction.type == AntAction.AntActionType.PICKUP)
      {
        if(ant.carryUnits <= temp.carryUnits)
        {
          ant.setSuccess();
        }
        else
        {
          Coordinate tempPos = new Coordinate(temp.gridX, temp.gridY);
          FoodData nearbyFood = getNearestFood(tempPos);
          Coordinate foodPos = new Coordinate(nearbyFood.gridX, nearbyFood.gridY);
          if(AStar.euclidDistance(tempPos, foodPos) == 1)
          {
            ant.setFailure();
          }
          else
          {
            ant.update(getEvent());
          }
        }
      }
      if(ant.nextAction.type == AntAction.AntActionType.DROP)
      {
        if(ant.carryUnits != 0)
        {
          ant.setFailure();
        }
        else
        {
          ant.setSuccess();
        }
      }
      if(temp.myAction.type == AntAction.AntActionType.DIED)
      {
        removeAnt(ant);
        continue;
      }
      exec.execute(ant);
    }
    exec.shutdown();
    while(!exec.isTerminated()) {}
  }
  
  public void addAnt(AntData data)
  {
    ants.add(AntFactory.makeAnt(data));
  }
  
  public void removeAnt(Ant ant)
  {
    ants.remove(ant);
  }
  
  public ArrayList<AntData> getAntList()
  {
    ArrayList<AntData> antCopy = new ArrayList<AntData>();
    for(Ant ant : ants)
    {
      antCopy.add(ant.createAntData());
    }
    return antCopy;
  }
  
  public void setVisibleFood(HashSet<FoodData> food)
  {
    AntController.visibleFood = food;
  }
  
  public static FoodData getNearestFood(Coordinate location)
  {
    int best_guess = Integer.MAX_VALUE, current_guess = Integer.MAX_VALUE;
    FoodData bestFood = null;
    for(FoodData food : visibleFood)
    {
      current_guess = AStar.euclidDistance(location, new Coordinate(food.gridX, food.gridY));
      if(current_guess < best_guess)
      {
        best_guess = current_guess;
        bestFood = food;
      }
    }
    return bestFood;
  }
  
  public static GameEvent getEvent ()
  {
    GameEvent e;
    if(!visibleFood.isEmpty())
    {
      e = new GatherEvent();
    }
    else
    {
      e = new ExploreEvent();
    }
    return e;
  }
}
