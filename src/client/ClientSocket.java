package client;

import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;

public class ClientSocket extends Socket {
  private static final String host = "We don't have host info yet.";
  private static final int port = 0;
  public ClientSocket() throws UnknownHostException, IOException {
	super(host, port);
  }
}
