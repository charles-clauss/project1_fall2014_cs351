package controller;

import java.util.ArrayList;
import java.util.concurrent.*;
import clientPack.antworld.data.*;

public class AntController {
  private ExecutorService exec;
  private CommData cd;
  private ArrayList<Ant> ants;

  public AntController(NestNameEnum nest, TeamNameEnum team) {
	cd  = new CommData(nest, team);
    for(AntData data : cd.myAntList) {
      ants.add(AntFactory.makeant(data));
    }
  }

  public void dispatchThreads() {
	exec = Executors.newFixedThreadPool(8);
	//get data communicated at this time step
	//check to see if there are new ants to be made
	for(Ant ant : ants) {
	  exec.execute(ant);
	}
	exec.shutdown();
	while(!exec.isTerminated()) {}
  }
}
