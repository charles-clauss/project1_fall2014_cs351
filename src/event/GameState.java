/**
 * @author Charles Clauss
 * 
 * The GameState class provides the main execution handling
 * for communication between the Ant server and this project.
 * This class provides no functionality of its own, it merely
 * owns instances of each of the important pieces necessary
 * to process the information received and pass the computed
 * decision information back to the server.
 */

package event;

import gameBoard.AStar;
import controller.AntController;
import antworld.data.*;
import client.ClientSocket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;

public class GameState
{

  
  /**
   * @param args Any arguments passed on execution of the program.
   * 
   * This method provides the main thread of execution and
   * handles any errors that may occur as a result.
   * 
   * The algorithm is essentially to create a socket connection with
   * the server, send an initial piece of data to begin communication,
   * receive data and use the AntController to make decisions based
   * on it, and then repackage those decisions into a data object
   * that is then sent back to the server.  Loop over this process.
   */
  public static void main(String args[])
  {
    try
    {
      System.out.println("Got here.");
      AntController control = new AntController();
      CommData dataFirstSent = new CommData(NestNameEnum.LEMON, TeamNameEnum.Bromegrass);
      dataFirstSent.password = 715779476403L;
      ClientSocket connection = new ClientSocket();
      ObjectOutputStream send = new ObjectOutputStream(connection.getOutputStream());
      ObjectInputStream receive = new ObjectInputStream(connection.getInputStream());
      //Initialize Ant Controller, needs to be refactored first.
      send.writeObject(dataFirstSent.packageForSendToServer());
      send.flush();
      send.reset();
      System.out.println("Got here too.");
      while(connection.isConnected())
      {
        System.out.println("Executing loop.");
        CommData dataReceived = (CommData) receive.readObject();
        System.out.println(dataReceived.errorMsg);
        //System.out.println(dataReceived.toString());
        //Read pertinent data and process with Ant Controller
        //Create a new CommData object
        //Populate it with AntData that has the moves of each ant
        CommData dataSent = new CommData(NestNameEnum.LEMON, TeamNameEnum.Bromegrass);
        for(AntData data : dataSent.myAntList) {
          data.myAction = new AntAction(AntAction.AntActionType.EXIT_NEST, dataReceived.nestData[NestNameEnum.BLACK_GARDEN.ordinal()].centerX, dataReceived.nestData[NestNameEnum.BLACK_GARDEN.ordinal()].centerY);
        }
        send.writeObject(dataSent.packageForSendToServer());
        send.flush();
        send.reset();
        System.out.println("Sent some stuff.");
      }
      connection.close();
    }
    catch (UnknownHostException e)
    {
      System.out.println("Host name invalid.");
      e.printStackTrace();
    }
    catch (IOException e)
    {
      System.out.println("Error reading/writing via socket.");
      e.printStackTrace();
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Could not read a CommData object from socket stream.");
      e.printStackTrace();
    }
  }
}
