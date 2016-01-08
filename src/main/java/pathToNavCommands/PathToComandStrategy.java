package pathToNavCommands;

import path.Path;
import path.PathPoint;

import java.util.ArrayList;

public interface PathToComandStrategy {
  public ArrayList<Command> convertPath(ArrayList<PathPoint> path);
}

