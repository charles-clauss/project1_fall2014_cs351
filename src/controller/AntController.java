package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import antworld.data.AntData;
import antworld.data.CommData;
import event.GameEvent;
import gameBoard.Coordinate;

public class AntController
{
  private ExecutorService exec = Executors.newFixedThreadPool(4);
  private List<Ant> ants = new ArrayList<Ant>();
  private List<GameEvent> events = Collections.synchronizedList(new ArrayList<GameEvent>());
  private List<Coordinate> nestLocations = new ArrayList<Coordinate>();

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
    int x = data.nestData[data.myNest.ordinal()].centerX;
    int y = data.nestData[data.myNest.ordinal()].centerY;
    //Coordinate nestCenter = new Coordinate(x,y);    
    Coordinate northCorner = new Coordinate(x-18, y);
    Coordinate southCorner = new Coordinate(x+18, y);
    Coordinate eastCorner = new Coordinate(x, y+18);
    Coordinate westCorner = new Coordinate(x, y-18);
    
    this.nestLocations.add(northCorner);
    this.nestLocations.add(southCorner);
    this.nestLocations.add(westCorner);
    this.nestLocations.add(eastCorner);
    
  }

  public void dispatchThreads()
  {
    for(Ant ant : ants)
    {
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
  
  
  public GameEvent getEvent (){
    if (!events.isEmpty()){
      return events.get(0);
    }else{
      GameEvent e = new GameEvent("gatherFood");
      return e;
    }
  }
}
