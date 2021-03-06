package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
// import java.util.Queue;
// import java.util.concurrent.*;

import antworld.data.AntAction;
import antworld.data.AntData;
// import antworld.data.AntType;
import antworld.data.CommData;
import antworld.data.Constants;
import antworld.data.FoodData;
// import antworld.data.FoodType;
// import antworld.data.NestNameEnum;
// import antworld.data.TeamNameEnum;
import event.ExploreEvent;
import event.GameEvent;
import event.GatherEvent;
import gameBoard.AStar;
import gameBoard.Coordinate;

/**
 * Main handler for the ants. Drives the ants based on a thread pool and the
 * communicated data from the server.
 */
public class AntController
{
  private boolean DEBUG = true;
  // private static int corePoolSize = 100;
  // private static int maxPoolSize = 500;
  // private static long time = 5L;
  // private static BlockingQueue<Runnable> runPool = new
  // ArrayBlockingQueue<Runnable>(200);
  // private static ExecutorService exec;
  private List<Ant> ants = new ArrayList<Ant>();
  private static List<Coordinate> nestLocations = new ArrayList<Coordinate>();
  private static HashSet<FoodData> visibleFood = new HashSet<FoodData>();
  private static Coordinate nestCenter;

  // private NestNameEnum nest;
  // private TeamNameEnum team;

  /**
   * Main constructor
   * 
   * @param startingAnts
   *          - the commdata object
   */
  public AntController(CommData startingAnts)
  {
    if (DEBUG) {System.out.println("Entering constructor.");}
    for (AntData ant : startingAnts.myAntList)
    {
      System.out.println("Added an ant #" + ant.id);
      addAnt(ant);
    }

    for (Ant ant : ants)
    {
      ant.nextAction = new AntAction(AntAction.AntActionType.STASIS);
    }
    if (DEBUG){System.out.println("Initializing nest data.");}
    // nest = startingAnts.myNest;
    // team = startingAnts.myTeam;
    setNestLocations(startingAnts);
  }

  /**
   * gives the approximate corner locations for the ant nest so ants can find
   * their way back home. only ran at initial instantiation
   * 
   * @param data
   *          commdata package.
   */
  public void setNestLocations(CommData data)
  {
    nestCenter = new Coordinate(data.nestData[data.myNest.ordinal()].centerX,
        data.nestData[data.myNest.ordinal()].centerY);
    int x = nestCenter.getX();
    int y = nestCenter.getY();
    // Coordinate nestCenter = new Coordinate(x,y);
    Coordinate northCorner = new Coordinate(x - 17, y);
    Coordinate southCorner = new Coordinate(x + 17, y);
    Coordinate eastCorner = new Coordinate(x, y + 17);
    Coordinate westCorner = new Coordinate(x, y - 17);

    nestLocations.add(northCorner);
    nestLocations.add(southCorner);
    nestLocations.add(westCorner);
    nestLocations.add(eastCorner);
  }

  /**
   * Uses pathfinding helper methods to guestimate a place to go
   * 
   * @param location
   *          this current location
   * @return Coordinate with location information
   */
  public static Coordinate getNearestNestCoordinate(Coordinate location)
  {
    int best_guess = Integer.MAX_VALUE, current_guess = Integer.MAX_VALUE;
    Coordinate bestSpot = null;
    for (Coordinate nestPoint : nestLocations)
    {
      current_guess = AStar.euclidDistance(location, nestPoint);
      if (current_guess < best_guess)
      {
        best_guess = current_guess;
        bestSpot = nestPoint;
      }
    }
    return bestSpot;
  }

  /**
   * Gets random ant exit coordinate
   * 
   * @return Coordinate with the relevant positioning information
   */
  public static Coordinate getRandomExitCoordinate()
  {
    int xOffset = Constants.random.nextInt(5) - 2;
    int yOffset = Constants.random.nextInt(5) - 2;
    int pick = Constants.random.nextInt(4);
    Coordinate randomNestPoint = nestLocations.get(pick);
    return new Coordinate(randomNestPoint.getX() + xOffset,
        randomNestPoint.getY() + yOffset);
  }

  /**
   * Checks each of the ant's actions against what the server
   * reports, and tries again to execute that action a certain
   * number of times before trying something else.
   * 
   * @param data
   *          - the returned communication data from the server
   */
  public void dispatchThreads(CommData data)
  {
    // exec = new ThreadPoolExecutor(corePoolSize, maxPoolSize, time,
    // TimeUnit.SECONDS, runPool);
    // List<Integer> foodData = new ArrayList<Integer>();
    int antIndex;
    /*
     * int total = 0; for(Integer value : data.foodStockPile) {
     * foodData.add(value); total += value;
     * 
     * } if(DEBUG){ System.out.println("Current food amount = " + total);
     * System.out.println("Food Types = " +
     * foodData.get(FoodType.BASIC.ordinal()) + " " +
     * foodData.get(FoodType.ATTACK.ordinal()) + " " +
     * foodData.get(FoodType.CARRY.ordinal()) + " " +
     * foodData.get(FoodType.DEFENCE.ordinal()) + " " +
     * foodData.get(FoodType.MEDIC.ordinal()) + " " +
     * foodData.get(FoodType.SPEED.ordinal())); }
     */
    AntController.visibleFood = data.foodSet;
    AntData temp;

    /*
     * for(FoodType ft : FoodType.values()) {
     * if(data.foodStockPile[ft.ordinal()] > 12 * ants.size()) { if (DEBUG){
     * System.out.println("Birthing some ants!"); } switch(ft) { case BASIC:
     * addAnt(AntFactory.birthAnt(AntType.BASIC, nest, team)); break; case
     * ATTACK: addAnt(AntFactory.birthAnt(AntType.ATTACK, nest, team)); break;
     * case CARRY: addAnt(AntFactory.birthAnt(AntType.CARRY, nest, team));
     * break; case DEFENCE: addAnt(AntFactory.birthAnt(AntType.DEFENCE, nest,
     * team)); break; case SPEED: addAnt(AntFactory.birthAnt(AntType.SPEED,
     * nest, team)); break; case MEDIC:
     * addAnt(AntFactory.birthAnt(AntType.MEDIC, nest, team)); break; case
     * VISION: addAnt(AntFactory.birthAnt(AntType.VISION, nest, team)); break;
     * case WATER: break; case UNKNOWN: break; } } }
     */
    int updateLimit = 2;
    int updateCount = 0;
    for (Ant ant : ants)
    {
      if (DEBUG) {System.out.println("Checking ant #" + ant.id);}
      if (ant.nextAction.type == AntAction.AntActionType.STASIS)
      {
        ant.update(getEvent());
        updateCount++;
        if (updateCount > updateLimit)
        {
          break;
        }
      }
      antIndex = data.myAntList.indexOf(ant);
      if (antIndex == -1)
      {
        removeAnt(ant);
        continue;
      }
      temp = data.myAntList.get(antIndex);
      ant.ticksUntilNextAction = temp.ticksUntilNextAction;
      if (ant.getFailCount() > 10)
      {
        ant.xPos = temp.gridX;
        ant.yPos = temp.gridY;
        ant.update(new ExploreEvent());
        ant.resetFailCount();
      }
      if (ant.nextAction.type == AntAction.AntActionType.MOVE)
      {
        if (temp.gridX == ant.xPos && temp.gridY == ant.yPos)
        {
          ant.setSuccess();
        }
        else
        {
          // System.out.println("Failed move for ant #" + ant.id);
          ant.setFailure();
        }
      }
      if (ant.nextAction.type == AntAction.AntActionType.PICKUP)
      {
        if (ant.carryUnits <= temp.carryUnits)
        {
          ant.setSuccess();
          ant.carryUnits = temp.carryUnits;
        }
        else
        {
          Coordinate tempPos = new Coordinate(temp.gridX, temp.gridY);
          FoodData nearbyFood = getNearestFood(tempPos);
          Coordinate foodPos = new Coordinate(nearbyFood.gridX,
              nearbyFood.gridY);
          if (AStar.euclidDistance(tempPos, foodPos) == 1)
          {
            ant.setFailure();
          }
          else
          {
            ant.update(getEvent());
          }
        }
      }
      if (ant.nextAction.type == AntAction.AntActionType.DROP)
      {
        if (ant.carryUnits != 0)
        {
          ant.setFailure();
        }
        else
        {
          ant.setSuccess();
        }
      }
      if (temp.myAction.type == AntAction.AntActionType.DIED)
      {
        removeAnt(ant);
        continue;
      }

      ant.run();
    }
    // exec.shutdown();
    // while(!exec.isTerminated()) {}
  }

  public void addAnt(AntData data)
  {
    ants.add(AntFactory.makeAnt(data));
  }

  public void addAnt(Ant ant)
  {
    ants.add(ant);
  }

  public void removeAnt(Ant ant)
  {
    ants.remove(ant);
  }

  public ArrayList<AntData> getAntList()
  {
    ArrayList<AntData> antCopy = new ArrayList<AntData>();
    for (Ant ant : ants)
    {
      antCopy.add(ant.createAntData());
    }
    return antCopy;
  }

  public void setVisibleFood(HashSet<FoodData> food)
  {
    AntController.visibleFood = food;
  }

  /**
   * Find's the nearest food to the ant.
   * 
   * @param location
   *          - location coordinate
   * @return Returns a FoodData object
   */
  public static FoodData getNearestFood(Coordinate location)
  {
    int best_guess = Integer.MAX_VALUE, current_guess = Integer.MAX_VALUE;
    FoodData bestFood = null;
    for (FoodData food : visibleFood)
    {
      if (food.getCount() <= 0)
      {
        continue;
      }
      current_guess = AStar.euclidDistance(location, new Coordinate(food.gridX,
          food.gridY));
      if (current_guess < best_guess)
      {
        best_guess = current_guess;
        bestFood = food;
      }
    }
    return bestFood;
  }
  /**
   * Creates a food event if there is any food
   * that can be seen in the communicated data,
   * and otherwise explores to find food.
   * @return A GameEvent of a particular sub class.
   */
  public static GameEvent getEvent()
  {
    GameEvent e;
    if (!visibleFood.isEmpty())
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
