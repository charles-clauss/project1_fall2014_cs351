package clientPack.antworld.data;

import java.io.Serializable;
import java.util.ArrayList;

public class CommData implements Serializable
{
  private static final long serialVersionUID = Constants.VERSION;

  public long timestamp; // walltime - latency debugging
  public int tick; // simtime - if received by serv and past this value, data dropped

  public final NestNameEnum myNestName;
  public TeamNameEnum myTeam;
  
  //To get the location of your own nest, use: 
  //    nestData[myNestName.ordinal()].centerX;
  //    nestData[myNestName.ordinal()].centerY;
  
  

  // Return myAntList ordered where the ant's actions are executed from first
  // element to last.
  // Set Ant's action
  // Add ants you want to birth to myAntList.
  public ArrayList<AntData> myAntList = new ArrayList<AntData>();

  
  
  // To reduce network traffic, set each of these to null when returning
  // CommData to server
  public NestData[] nestData; // set to null before sending to server.
  public int[] foodStockPile; // set to null before sending to server.
  public AntData[] enemyAntList = new AntData[0]; // set to null before sending to server.
  public FoodData[] foodList = new FoodData[0]; // set to null before sending to server.

  //The server will automatically set requestNestData=true whenever
  //   a new client attaches or whenever a client changes nest homes.
  public boolean requestNestData = false;
  public boolean returnToNestOnDisconnect = true;

  public CommData(NestNameEnum nestName, TeamNameEnum team)
  {
    this.myNestName = nestName;
    this.myTeam = team;
  }
}