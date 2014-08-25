package controller;

import java.util.concurrent.*;
import clientPack.antworld.data.*;

public class AntController {
  ExecutorService exec = Executors.newFixedThreadPool(4);

  for(AntData data : CommData.myAntList) {
    exec.submit(new AntFactory.makeAnt(data.antType));
  }
  exec.shutdown();
  exec.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
}
