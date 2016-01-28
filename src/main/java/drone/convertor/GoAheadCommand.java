package drone.convertor;

import path.PathPoint;
import pathToNavCommands.Command;
import drone.Moveable;;import java.rmi.RemoteException;

public class GoAheadCommand implements Command {
	
	public GoAheadCommand() {

	}
	
	@Override
	public void execute(Moveable drone, String[] params)  {
		PathPoint point = new PathPoint(Double.parseDouble(params[1]),Double.parseDouble(params[2]),
				Double.parseDouble(params[3]));
		drone.goTo(point);
	}

}
