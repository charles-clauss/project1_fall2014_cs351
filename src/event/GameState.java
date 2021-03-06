/**
 * The GameState class provides the main execution
 * handling for communication between the Ant server and this project. This
 * class provides no functionality of its own, it merely owns instances of each
 * of the important pieces necessary to process the information received and
 * pass the computed decision information back to the server.
 */

package event;

import controller.AntController;
import antworld.data.*;
import client.ClientSocket;
// import gameBoard.AntTable;
import gameBoard.Coordinate;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;

public class GameState
{

  /**
   * @param args
   *          Any arguments passed on execution of the program. This method
   *          provides the main thread of execution and handles any errors that
   *          may occur as a result. The algorithm is essentially to create a
   *          socket connection with the server, send an initial piece of data
   *          to begin communication, receive data and use the AntController to
   *          make decisions based on it, and then repackage those decisions
   *          into a data object that is then sent back to the server. Loop over
   *          this process.
   */
  public static void main(String args[])
  {
    boolean DEBUG = true;
    // effort to reduce our initial connection time
    Coordinate makePic = new Coordinate(20, 20);

    try
    {
      // System.out.println("Got here.");
      NestNameEnum requestedNest = null;
      CommData communication = null;
      AntController control;
      ClientSocket connection = new ClientSocket();
      ObjectOutputStream send = new ObjectOutputStream(
          connection.getOutputStream());
      ObjectInputStream receive = new ObjectInputStream(
          connection.getInputStream());
      int nestSelector = 0;
      /**
       * Creates a connection to the server, asking for the GUEST nest
       */
      while (requestedNest == null)
      {
        System.out.println("Attempting to connect");
        requestedNest = NestNameEnum.values()[nestSelector];
        nestSelector++;
        CommData dataFirstSent = new CommData(NestNameEnum.GUEST,
            TeamNameEnum.Bromegrass);
        dataFirstSent.password = 715779476403L;
        dataFirstSent.requestNestData = true;
        send.writeObject(dataFirstSent.packageForSendToServer());
        send.flush();
        send.reset();

        communication = (CommData) receive.readObject();
        if (communication.errorMsg != null)
        {
          System.out.println(communication.errorMsg);
          requestedNest = null;
          System.out.println("failled to get commdata/nest.");
        }
      }
      System.out.println("Creating controller.");
      control = new AntController(communication);
      // new AntTable(control).setVisible(true);
      /*
       * send.writeObject(communication.packageForSendToServer()); send.flush();
       * send.reset(); communication = (CommData) receive.readObject();
       */

      /**
       * Executes the main reading and writing of objects through
       * the socket connection.  Data is read, and then the ant
       * objects are run to check their previous actions and update
       * them accordingly, and then new actions are calculated and
       * passed back to the server.
       */
      while (connection.isConnected())
      {
        if (DEBUG)
        {
          System.out.println("Executing loop.");
        }

        control.dispatchThreads(communication);
        communication.myAntList = control.getAntList();
        communication.enemyAntSet = null;
        communication.foodSet = null;
        communication.foodStockPile = null;
        communication.nestData = null;
        System.out.println("" + communication);

        send.writeObject(communication.packageForSendToServer());
        send.flush();
        send.reset();
        System.out.println("Sent some stuff.");

        communication = (CommData) receive.readObject();
        // System.out.println("" + communication);

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
      System.out
          .println("Could not read a CommData object from socket stream.");
      e.printStackTrace();
    }
  }
}
