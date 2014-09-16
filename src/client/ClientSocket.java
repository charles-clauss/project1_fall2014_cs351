package client;

import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;

import antworld.data.*;

public class ClientSocket extends Socket
{
  public ClientSocket() throws UnknownHostException, IOException
  {

    super("b146-51", Constants.PORT);

  }
}
