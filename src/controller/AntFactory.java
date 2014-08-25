package controller;

import clientPack.antworld.data.*;

public class AntFactory {
  public AntFactory() {
  }
  public static Ant makeant(AntData data) {
    switch(data.antType) {
      case DEFENCE:
        return new DefenceAnt(data);
      case ATTACK:
        return new AttackAnt(data);
      case SPEED:
        return new SpeedAnt(data);
      case VISION:
        return new VisionAnt(data);
      case CARRY:
        return new CarryAnt(data);
      case MEDIC:
        return new MedicAnt(data);
      default:
        return new BasicAnt(data);
    }
  }
}
