package drone;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;

import com.google.gson.Gson;
import drone.convertor.DummyStringConverter;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import path.PathPoint;
import pathToNavCommands.StringToCommandStrategy;
import remotes.DroneRemoteIF;
import utils.MyConstants;

public class Drone implements DroneRemoteIF,Moveable {

    ArrayList<String> commands;
    Producer<String,String> producer;
	StringToCommandStrategy converter;
	String name;

	public Drone(String name)  {

        this.name = name;
        this.converter = new DummyStringConverter(this);

        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:"+ MyConstants.INITIAL_BROKER_PORT);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //Partitionnement pas important pour l'instant
        //props.put("partitioner.class", "SimplePartitioner");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);

        producer = new Producer<String,String>(config);
	}

	@Override
	public void loadPath(ArrayList<String> commands) {
		this.commands = commands;
		System.out.println("Path has been loaded successfully ......");
	}


	@Override
	public void goTo(PathPoint point){
        Gson gson = new Gson();
        String msg = gson.toJson(point);
        KeyedMessage<String, String> data = new KeyedMessage<>(name, msg);
        //System.out.println(msg);
        producer.send(data);
    }

	@Override
	public void go() throws RemoteException {
        System.out.println("Drone is up and heading the destination....");
        System.out.print("[");

        for(int i = commands.size()-1 ; i >=0 ; i--){
            converter.executeCommand(commands.get(i));

            //if(i%50 == 0)  System.out.print("=");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println(">]");
        System.out.println("Drone has reached its destination .....");
	}

    @Override
    public String getName(){
        return this.name;
    }


   public static void main(String args[]) {
        //ArrayList<String> drones = EventMediatorLocator.mediator().listDrones();
        //int len =0;
        //if(drones!=null) len=drones.size();
        DroneRemoteIF drone = new Drone("drone");
        ArrayList<String> path = new ArrayList<>();
        path.add("goAhead 12 12 12");
        path.add("goAhead 23 24 24");
        path.add("goAhead 21 24 23");

        try {
            drone.loadPath(path);
            drone.go();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("drone 33 is up and running");
    }
}
