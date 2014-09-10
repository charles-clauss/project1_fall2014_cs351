package controller;

import java.util.ArrayList;
import java.util.concurrent.*;
import antworld.data.*;

public class AntController
{
  private ExecutorService exec;
  private ArrayList<Ant> ants;

  public AntController()
  {
  }

  public void dispatchThreads()
  {
    exec = Executors.newFixedThreadPool(4);
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
