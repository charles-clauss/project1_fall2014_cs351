package controller;

import java.util.concurrent.*;
import clientPack.antworld.data.*;

public class AntController {
  private ExecutorService exec = Executors.newFixedThreadPool(4);
  private CommData cd = new CommData(NestNameEnum.WOOD, TeamNameEnum.Antithesis);

  public AntController() {
    initialize(cd);
  }
  
  public void initialize(CommData data) {
  }
  
  public void dispatchThreads() {
    for(AntData data : cd.myAntList) {
      exec.submit(AntFactory.makeant(data));
    }
    exec.shutdown();
    while(!exec.isTerminated()) {}
  }
}
