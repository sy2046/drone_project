package tracer;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import eventMediatorLocator.EventMediatorLocator;
import maps.MapIF;
import path.PathPoint;
import remotes.MediatorIF;
import remotes.Notifiable;
import remotes.TracableRemoteIF;
import remotes.TracerIF;

public class Tracer extends UnicastRemoteObject implements TracerIF {

	MediatorIF mediator;
	String drone ;
	String name;
    MapIF map;
	public Tracer(String drone,String name) throws Exception{
		super();
		this.drone = drone;
		this.name=name;
		this.mediator = EventMediatorLocator.mediator();
		this.mediator.registerTracer(drone,this);
		System.out.println(this.name +" is ready to trace "+drone);
	}


	@Override
	public void notify(PathPoint p) throws RemoteException {
		System.out.println(p);
		if(this.map!=null) this.map.setPossition(p);
	}
	
	@Override
	public void done() throws RemoteException {
		System.out.println("Drone has reached its destination .....");
		
	}


	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

    @Override
    public void setMap(MapIF map) throws RemoteException {
        this.map = map;
    }
}
