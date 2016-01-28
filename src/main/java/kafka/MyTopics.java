package kafka;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;
import org.I0Itec.zkclient.ZkClient;
import utils.MyConstants;

import java.util.Properties;

/**
 * Created by sy306571 on 28/01/16.
 */
public class MyTopics {
    // Create a ZooKeeper client
    int sessionTimeoutMs = 10000;
    int connectionTimeoutMs = 10000;
    ZkClient zkClient = new ZkClient("localhost:"+ MyConstants.KAFKA_ZK_PORT, sessionTimeoutMs, connectionTimeoutMs, ZKStringSerializer$.MODULE$);

    // Create the topics for the communication between drones and tracers
    /*String topicName = MyConstants.TOPIC_NAME;
    int numPartitions = MyConstants.NUM_PARTITIONS;
    int replicationFactor = 1;
    Properties topicConfig = new Properties();
    AdminUtils.createTopic(zkClient, topicName, numPartitions, replicationFactor, topicConfig);*/
}
