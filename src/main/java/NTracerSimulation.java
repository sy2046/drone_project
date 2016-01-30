import endPoints.AdressEndPoints;
import path.Path;
import pathFinder.GooglePathFinder;
import pathFinder.PathPlannerStrategy;
import tracer.Tracer;
import utils.MyConstants;

import java.util.Random;

public class NTracerSimulation {
    public static void main(String args[]) {
        int n = MyConstants.NUMBER_OF_DRONES;
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(new NTracerSimulation.TracerTask(i));
            t.start();
        }
    }

    static class TracerTask implements Runnable {

        int id;

        public TracerTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
                //int nb_topics_per_zookeeper = MyConstants.NUMBER_OF_DRONES/MyConstants.NUMBER_OF_ZOOKEEPER;
                Tracer tracer = new Tracer("tracer" + id, "drone" + id, "localhost:" + MyConstants.KAFKA_ZK_PORT); //+ (id/nb_topics_per_zookeeper)));
                tracer.run(1);
        }

    }
}