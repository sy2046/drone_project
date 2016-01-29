package drone.convertor;

import java.util.HashMap;

import pathToNavCommands.Command;
import drone.Moveable;
import pathToNavCommands.StringToCommandStrategy;

public class DummyStringConverter implements StringToCommandStrategy {

	private static HashMap<String, Command> commands = new HashMap<>();
	static{
		commands.put("goAhead", new GoAheadCommand());
	}

	Moveable drone;
  	public DummyStringConverter (Moveable drone) {this.drone = drone; }

@Override
public void executeCommand(String stringCommand) {
	String[] params = stringCommand.split("\\s+");
	Command command = commands.get(params[0].substring(1));
	command.execute(drone,params);
}
  
}

