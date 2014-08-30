package client;

import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;

public class ClientSocket extends Socket
{
  private static final String HOST = "127.0.0.1";
  private static final int PORT = 12321;
  public ClientSocket() throws UnknownHostException, IOException
  {
    super(HOST, PORT);
  }
}
