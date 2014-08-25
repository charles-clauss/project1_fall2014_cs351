package controller;

import java.util.concurrent.*;
import clientPack.antworld.data.*;

public class AntController {
  ExecutorService exec = Executors.newFixedThreadPool(4);
  AntFactory af = new AntFactory();
  CommData cd = new CommData(NestNameEnum.WOOD, TeamNameEnum.Antithesis);

  for(AntData data : cd.myAntList) {
    exec.submit(new af.makeAnt(data));
  }
  exec.shutdown();
  while(!exec.isTerminated()) {}
}
