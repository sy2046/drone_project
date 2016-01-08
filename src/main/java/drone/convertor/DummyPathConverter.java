package drone.convertor;

import java.util.ArrayList;
import java.util.Iterator;

import path.PathPoint;
import pathToNavCommands.Command;
import pathToNavCommands.PathToComandStrategy;
import drone.Moveable;

public class DummyPathConverter implements PathToComandStrategy {

	Moveable drone;
  public DummyPathConverter (Moveable drone) {this.drone = drone; }

@Override
public ArrayList<Command> convertPath(ArrayList<PathPoint> path) {
	ArrayList<Command> commands = new ArrayList<Command>();
	Iterator<PathPoint> iterator = path.iterator();
	while(iterator.hasNext()){
		GoAheadCommand command = new GoAheadCommand(iterator.next());
		command.setDrone(this.drone);
		commands.add(command);
	}
	return commands;
}
  
}

