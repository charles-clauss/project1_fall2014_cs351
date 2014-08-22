package clientPack.antworld.data;

import java.io.Serializable;

public class NestData implements Serializable {
  private static final long serialVersionUID = Constants.VERSION;

  public final NestNameEnum nestName;
  public final int centerX, centerY;
  public TeamNameEnum team = TeamNameEnum.LUKONIAN_NEAR_BRAINLESS_BOTS;
  
  public int score;
  
  public NestData(int id, int x, int y)
  { 
    nestName = NestNameEnum.values()[id];
    this.centerX = x;
    this.centerY = y;
    score = 0;
    if (id!=0)  team = TeamNameEnum.NONE;
  }
  
  public String toString()
  {
     return "[nestName="+nestName+", x="+centerX+", y="+centerY+"]"; 
  }
}
