package remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import path.Path;
import path.PathPoint;
import pathToNavCommands.Command;

public interface ConfigurableRemoteIF extends Remote{
  public void loadPath(ArrayList<PathPoint> path) throws RemoteException;
}

