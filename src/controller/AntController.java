package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import antworld.data.AntData;
import event.GameEvent;

public class AntController
{
  private ExecutorService exec = Executors.newFixedThreadPool(4);
  private List<Ant> ants = new ArrayList<Ant>();
  private List<GameEvent> events = Collections.synchronizedList(new ArrayList<GameEvent>());

  public AntController(ArrayList<AntData> startingAnts)
  {
    for(AntData ant : startingAnts)
    {
      addAnt(ant);
    }
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
}
