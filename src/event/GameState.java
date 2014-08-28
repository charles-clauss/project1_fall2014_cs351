package event;

import astar.AStar;
import controller.AntController;
import clientPack.antworld.data.*;
import client.ClientSocket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;

public class GameState {
  private AntController control;
  private Observer astar = new AStar();
  private static CommData dataSent;
  private static CommData dataReceived;
  
  public GameState() {
  }
  
  public static void main(String args[]) {
	try {
	  ClientSocket connection = new ClientSocket();
	  ObjectOutputStream send = new ObjectOutputStream(connection.getOutputStream());
	  ObjectInputStream receive = new ObjectInputStream(connection.getInputStream());
	  //Initialize Ant Controller, needs to be refactored first.
	  send.writeObject((dataSent = new CommData(NestNameEnum.FIRE, TeamNameEnum.Buffalograss)));
	  while(connection.isConnected()) {
        dataReceived = (CommData)receive.readObject();
        //Read pertinent data and process with Ant Controller
        //Create a new CommData object
        //Populate it with AntData that has the moves of each ant
        dataSent = new CommData(NestNameEnum.FIRE, TeamNameEnum.Buffalograss);
        send.writeObject(dataSent);
	  }
	  connection.close();
	} catch (UnknownHostException e) {
	  System.out.println("Host name invalid.");
	  e.printStackTrace();
	} catch (IOException e) {
	  System.out.println("Error reading/writing via socket.");
	  e.printStackTrace();
	} catch (ClassNotFoundException e) {
	  System.out.println("Could not read a CommData object from socket stream.");
	  e.printStackTrace();
	}
  }
}
