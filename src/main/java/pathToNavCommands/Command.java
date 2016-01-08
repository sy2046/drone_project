package pathToNavCommands;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Command extends Serializable{
  public void execute() throws RemoteException;
}

