package controller;

import java.util.concurrent.*;
import clientPack.antworld.data.*;

public class AntController {
  ExecutorService exec = Executors.newFixedThreadPool(4);
  AntFactory af = new AntFactory();
  CommData cd = new CommData(NestNameEnum.WOOD, TeamNameEnum.Antithesis);

  for(int i = 0; i < cd.myAntList.size(); i++) {
    //exec.submit(new af.makeAnt(cd.myAntList.get(i)));
  }
  /*
  for(AntData data : cd.myAntList) {
    exec.submit(new af.makeAnt(data.antType));
  }
  exec.shutdown();
  while(!exec.isTerminated()) {} */
}
