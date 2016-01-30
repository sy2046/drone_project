import drone.Drone;
import endPoints.AdressEndPoints;
import path.Path;
import path.PathPoint;
import pathFinder.GooglePathFinder;
import pathFinder.PathPlannerStrategy;
import remotes.DroneRemoteIF;
import remotes.TracerIF;
import tracer.Tracer;
import utils.MyConstants;

import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    public static void main(String args[]) {
        int n = MyConstants.NUMBER_OF_DRONES;
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(new Simulation.TracerTask(i));
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
            Random genetator = new Random();
            //String drone = EventMediatorLocator.mediator().allocateDrone();
               /* String dest[] = {"Dijon", "Lille", "Rennes", "Tours", "Caen", "Nantes", "Angers"};
                Random random = new Random();
                int idx1 = random.nextInt(7);
                //String destination = dest[idx1];
                String destination = "Paris";
                int idx2 = random.nextInt(7);
                if (idx2 == idx1) {
                    idx2 = (idx2 + 1) % dest.length;
                }*/

                //String start = dest[idx2];
                String start = "Meaux";
                //PathPlannerStrategy pathPlanner = new GooglePathFinder();
                //System.out.println(start + ":" + destination);
                //Path p = pathPlanner.findPath(new AdressEndPoints(start, destination));
                //int nb_topics_per_zookeeper = MyConstants.NUMBER_OF_DRONES/MyConstants.NUMBER_OF_ZOOKEEPER;
                Tracer tracer = new Tracer("tracer" + id, "drone" + id, "localhost:" + MyConstants.KAFKA_ZK_PORT); //+ (id/nb_topics_per_zookeeper)));
                tracer.run(1);
        }

    }
}