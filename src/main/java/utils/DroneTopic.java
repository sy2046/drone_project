package utils;

/**
 * Created by sy306571 on 27/01/16.
 */
import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer;
import kafka.utils.ZKStringSerializer$;
import org.I0Itec.zkclient.ZkClient;

import java.util.Properties;

/**
 * Created by sy306571 on 27/11/15.
 */
public class DroneTopic{
    public static void main(String[] args){
        // Create a ZooKeeper client
        int sessionTimeoutMs = 10000;
        int connectionTimeoutMs = 10000;
        ZkClient zkClient = new ZkClient("localhost:2181", sessionTimeoutMs, connectionTimeoutMs, ZKStringSerializer$.MODULE$);

        // Create a topic named "myTopic" with 8 partitions and a replication factor of 3
        String topicName = "drone34";
        int numPartitions = 1;
        int replicationFactor = 1;
        Properties topicConfig = new Properties();
        AdminUtils.createTopic(zkClient, topicName, numPartitions, replicationFactor, topicConfig);
    }
}
