package pathToNavCommands;

import com.google.gson.JsonObject;
import path.Path;
import path.PathPoint;

import java.util.ArrayList;

public interface JsonToCommandStrategy {
  public void executeCommand(JsonObject stringCommand);
}

