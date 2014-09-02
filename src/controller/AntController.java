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
    //get data communicated at this time step
    //check to see if there are new ants to be made
    for(Ant ant : ants)
    {
      exec.execute(ant);
    }
    exec.shutdown();
    while(!exec.isTerminated()) {}
  }
  
  public void addAnt(AntData data) {
    ants.add(AntFactory.makeant(data));
  }
}
