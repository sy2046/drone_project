package drone;


import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import drone.convertor.DummyStringConverter;
import kafka.api.OffsetRequest;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import path.PathPoint;
import pathToNavCommands.StringToCommandStrategy;
import remotes.DroneRemoteIF;
import tracer.ConsumerTest;
import utils.MyConstants;

public class Drone implements DroneRemoteIF,Moveable {

    String[] commands;
    Producer<String,String> producer;
    private final ConsumerConnector consumer;
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

        producer = new Producer<>(config);

        Properties props2 = new Properties();
        props2.put("zookeeper.connect", "localhost:"+MyConstants.KAFKA_ZK_PORT);
        props2.put("group.id", name+"consumer");
        props2.put("zookeeper.session.timeout.ms", "400");
        props2.put("zookeeper.sync.time.ms", "200");
        props2.put("auto.commit.interval.ms", "1000");
        //props2.put("startOffsetTime",);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                new ConsumerConfig(props2));
	}

	@Override
	public void loadPath(String[] commands) {
		this.commands = commands;
		System.out.println("Path has been loaded successfully ......");
        //System.out.println(commands);
	}

    public void run(int a_numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(name+"-in", a_numThreads);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(name+"-in");

        // now launch all the threads
        //
        ExecutorService executor = Executors.newFixedThreadPool(a_numThreads);

        // now create an object to consume the messages
        //
        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.submit(new ConsumerThread(stream, threadNumber,this));
            threadNumber++;
        }
    }

	@Override
	public void goTo(PathPoint point){
        Gson gson = new Gson();
        String msg = gson.toJson(point);
        KeyedMessage<String, String> data = new KeyedMessage<>(name+"-out", msg);
        //System.out.println(msg);
        producer.send(data);
    }

	@Override
	public void go() {
        System.out.println("Drone is up and heading the destination....");
        //System.out.print("[");

        for(int i = commands.length-1 ; i >=0 ; i--){
            converter.executeCommand(commands[i]);

            //System.out.print("=");
        }
        //System.out.println(">]");
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
        /*ArrayList<String> path = new ArrayList<>();
        path.add("goAhead 12 12 12");
        path.add("goAhead 23 24 24");
        path.add("goAhead 21 24 23");

        try {
            drone.loadPath(path);
            drone.go();
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
        Drone[] drones = new Drone[MyConstants.NUMBER_OF_DRONES];
        for(int i=0; i<MyConstants.NUMBER_OF_DRONES; i++){
            drones[i] = new Drone("drone"+i);
            drones[i].run(1);
            System.out.println("drone"+i+" is up and running");
        }

    }
}
