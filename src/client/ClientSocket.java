package client;

import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;

import antworld.data.*;

/**
 * Class that handles connecting to the given server
 *
 */
public class ClientSocket extends Socket
{
  public ClientSocket() throws UnknownHostException, IOException
  {

    super("b146-76", Constants.PORT);

  }
}
