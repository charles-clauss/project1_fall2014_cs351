package controller;

import clientPack.antworld.data.AntType;

public class AntFactory {
  public static Ant makeAnt(AntType type) {
    switch(type) {
      case DEFENCE:
        return new DefenceAnt();
      case ATTACK:
        return new AttackAnt();
      case SPEED:
        return new SpeedAnt();
      case VISION:
        return new VisionAnt();
      case CARRY:
        return new CarryAnt();
      case MEDIC:
        return new MedicAnt();
      default:
        return new BasicAnt();
    }
  }
}
