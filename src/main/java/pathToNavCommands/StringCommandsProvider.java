package pathToNavCommands;

import com.google.gson.Gson;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import path.PathPoint;
import utils.MyConstants;

import java.util.List;
import java.util.Properties;

/**
 * Created by sy306571 on 28/01/16.
 */
public class StringCommandsProvider {
    private String drone;
    private Producer<String,String> producer;
    private List<PathPoint> path;


    public StringCommandsProvider(String drone){
        this.drone = drone;

        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:"+ MyConstants.INITIAL_BROKER_PORT);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //Partitionnement pas important pour l'instant
        //props.put("partitioner.class", "SimplePartitioner");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);

        producer = new Producer<String, String>(config);
    }

    public void setPath(List<PathPoint> path){
        this.path = path;
    }

    public void sendStringCommands(){
        List<String> stringCommands = PathToStringCommandConverter.convertPath(path);
        Gson gson = new Gson();
        String msg;
        for(String command : stringCommands){
            msg = gson.toJson(command);
            KeyedMessage<String, String> data = new KeyedMessage<>(drone, msg);
            //System.out.println(msg);
            producer.send(data);
        }
        msg = "Finished";
        KeyedMessage<String, String> fin = new KeyedMessage<>(drone, msg);
        producer.send(fin);
    }
}
