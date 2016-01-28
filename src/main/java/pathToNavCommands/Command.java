package pathToNavCommands;

import drone.Moveable;
import path.PathPoint;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Command extends Serializable{
  void execute(Moveable drone, String[] params);
}

