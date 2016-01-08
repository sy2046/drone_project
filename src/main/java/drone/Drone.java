package drone;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import drone.convertor.DummyPathConverter;
import eventMediatorLocator.EventMediatorLocator;
import path.PathPoint;
import path.Path;
import pathToNavCommands.Command;
import pathToNavCommands.PathToComandStrategy;
import remotes.DroneRemoteIF;
import remotes.MediatorIF;
import remotes.Notifiable;

public class Drone extends UnicastRemoteObject implements DroneRemoteIF,Moveable {
	
	ArrayList<Command> commands;
	MediatorIF mediator;
	PathToComandStrategy convertor;
	String name;

	public Drone(String name) throws RemoteException, MalformedURLException, NotBoundException {
		super();
		this.name =name;
		this.convertor = new DummyPathConverter(this);
        this.mediator = EventMediatorLocator.mediator();
        this.mediator.registerDrone(this);
	}

	@Override
	public void loadPath(ArrayList<PathPoint> path) throws RemoteException {
		this.commands = this.convertor.convertPath(path);
		System.out.println("Path has been loaded successfully ......");
	}


	@Override
	public void goTo(PathPoint point){
        try {
            this.mediator.notifyLocation(name,point);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
	@Override
	public void go() throws RemoteException {
        System.out.println("Drone is up and heading the destination....");
        System.out.print("[");
        //Iterator<Command> ir = commands.iterator();
        for(int i = commands.size()-1 ; i >=0 ; i--){
            Command c = commands.get(i);
            c.execute();
            if(i%50 == 0)  System.out.print("=");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(">]");
        this.mediator.notifyArrivale(this.name);
        System.out.println("Drone has reached its destination .....");
	}

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }


    public static void main(String args[]) throws RemoteException, NotBoundException, MalformedURLException {
        ArrayList<String> drones = EventMediatorLocator.mediator().listDrones();
        int len =0;
        if(drones!=null) len=drones.size();
        DroneRemoteIF drone = new Drone("drone"+len);
        System.out.println("drone"+len+" is up and running");
    }
}
