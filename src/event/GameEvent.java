package event;

public class GameEvent
{
  private String eventType; 
  
  public GameEvent(String type){
    this.eventType = type;
  }
  
  public String getType(){
    return this.eventType;
  }
}
