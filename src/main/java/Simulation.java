import drone.Drone;
import endPoints.AdressEndPoints;
import path.Path;
import pathFinder.GooglePathFinder;
import pathFinder.PathPlannerStrategy;
import pathToNavCommands.StringCommandsProvider;
import remotes.DroneRemoteIF;
import remotes.TracerIF;
import tracer.Tracer;
import utils.MyConstants;

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
            if (id != 0) {
                String dest[] = {"Dijon", "Lille", "Rennes", "Tours", "Caen", "Nantes", "Angers"};
                Random random = new Random();
                int idx1 = random.nextInt(7);
                String destination = dest[idx1];
                int idx2 = random.nextInt(7);
                if (idx2 == idx1) {
                    idx2 = (idx2 + 1) % dest.length;
                }
                String start = dest[idx2];
                PathPlannerStrategy pathPlanner = new GooglePathFinder();
                System.out.println(start + ":" + destination);
                Path p = pathPlanner.findPath(new AdressEndPoints(start, destination));
                // EventMediatorLocator.mediator().loadPath(drone, p.getPathPoints());
                Tracer tracer = new Tracer("tracer" + id, "drone" + id, "localhost:" + MyConstants.KAFKA_ZK_PORT);
                tracer.run(1);
                StringCommandsProvider provider = new StringCommandsProvider("drone"+id);
                provider.setPath(p.getPathPoints());
                provider.sendStringCommands();
                try {
                    Thread.sleep(genetator.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //EventMediatorLocator.mediator().startDelivary(drone);

            }
        }

    }
}