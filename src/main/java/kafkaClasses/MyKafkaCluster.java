package kafkaClasses;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;
import org.I0Itec.zkclient.ZkClient;
import utils.MyConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by sy306571 on 28/01/16.
 */
public class MyKafkaCluster {
    public static void main(String[] args){

        EmbeddedZookeeper embeddedZookeeper = new EmbeddedZookeeper(MyConstants.KAFKA_ZK_PORT);
        List<Integer> kafkaPorts = new ArrayList<Integer>();
        // -1 for any available port
        kafkaPorts.add(MyConstants.INITIAL_BROKER_PORT);
        EmbeddedKafkaCluster embeddedKafkaCluster = new EmbeddedKafkaCluster(embeddedZookeeper.getConnection(), new Properties(), kafkaPorts);
        try {
            embeddedZookeeper.startup();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("### Embedded Zookeeper connection: " + embeddedZookeeper.getConnection());
        embeddedKafkaCluster.startup();
        System.out.println("### Embedded Kafka cluster broker list: " + embeddedKafkaCluster.getBrokerList());

        //embeddedKafkaCluster.shutdown();
        //embeddedZookeeper.shutdown();


        // Create a ZooKeeper client
        int sessionTimeoutMs = 10000;
        int connectionTimeoutMs = 10000;
        ZkClient zkClient = new ZkClient("localhost:"+ MyConstants.KAFKA_ZK_PORT, sessionTimeoutMs, connectionTimeoutMs, ZKStringSerializer$.MODULE$);

        // Create the topics for the communication between drones and tracers
        int numPartitions = 1;
        int replicationFactor = 1;
        Properties topicConfig = new Properties();
        for(int i=0; i<5; i++){
            AdminUtils.createTopic(zkClient, "drone-in", numPartitions, replicationFactor, topicConfig);
            AdminUtils.createTopic(zkClient, "drone-out", numPartitions, replicationFactor, topicConfig);
        }

    }
}
