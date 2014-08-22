package clientPack.antworld.data;

import java.io.Serializable;
import clientPack.antworld.*;

public class AntData implements Comparable<AntData>, Serializable {
  private static final long serialVersionUID = Constants.VERSION;

  public NestNameEnum nestName;
  public int id = Constants.UNKNOWN_ANT_ID;
  
  public int gridX, gridY;
  public boolean alive = true;
 
  public AntType antType;
  public FoodType carryType = null;
  public int carryUnits = 0;
  public AntAction currentAction;
  public int ticksUntilNextAction = 0;

  
  public int health;
  
  public boolean underground = true;

  
  public AntData(AntType type)
  {
    antType = type;
    health = type.getMaxHealth();
    currentAction = new AntAction(AntActionType.BIRTH);
  }
  

  
  public String toString()
  {
    String out = "Ant: [nestName="+nestName+", "+ antType;
    if (underground) out += " underground ]";
    else out += ", x="+gridX+", y="+gridY+"]"; 
    
    return out;
  }



  @Override
  public int compareTo(AntData otherAnt)
  {
    return id - otherAnt.id;
  }



}
