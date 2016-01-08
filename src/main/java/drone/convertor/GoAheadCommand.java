package drone.convertor;

import path.PathPoint;
import pathToNavCommands.Command;
import drone.Moveable;;import java.rmi.RemoteException;

public class GoAheadCommand implements Command {
	
	PathPoint point;
	Moveable drone;
	
	public GoAheadCommand(PathPoint point) {
		this.point = point;
	}
	
	@Override
	public void execute()  {

			this.drone.goTo(this.point);
	}
	
	public void setDrone(Moveable d){
		this.drone =d;
	}

}
