package pathToNavCommands;

import com.google.gson.JsonObject;
import drone.Moveable;
import path.PathPoint;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Command extends Serializable{
  void execute(Moveable drone, JsonObject params);
}

