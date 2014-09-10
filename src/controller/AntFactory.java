package controller;

import antworld.data.*;

public class AntFactory
{
  public static Ant makeAnt(AntData data)
  {
    switch(data.antType)
    {
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
  public static Ant birthAnt(AntType type, NestNameEnum nest, TeamNameEnum team)
  {
    switch(type)
    {
      case DEFENCE:
        return new DefenceAnt(new AntData(Constants.UNKNOWN_ANT_ID, type, nest, team));
      case ATTACK:
        return new AttackAnt(new AntData(Constants.UNKNOWN_ANT_ID, type, nest, team));
      case SPEED:
        return new SpeedAnt(new AntData(Constants.UNKNOWN_ANT_ID, type, nest, team));
      case VISION:
        return new VisionAnt(new AntData(Constants.UNKNOWN_ANT_ID, type, nest, team));
      case CARRY:
        return new CarryAnt(new AntData(Constants.UNKNOWN_ANT_ID, type, nest, team));
      case MEDIC:
        return new MedicAnt(new AntData(Constants.UNKNOWN_ANT_ID, type, nest, team));
      default:
        return new BasicAnt(new AntData(Constants.UNKNOWN_ANT_ID, type, nest, team));
    }
  }
}
