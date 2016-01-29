import drone.Drone;
import path.PathPoint;
import pathToNavCommands.StringCommandsProvider;
import tracer.Tracer;
import utils.MyConstants;

import java.util.ArrayList;

/**
 * Created by sy306571 on 28/01/16.
 */
public class Simulation {
    public static void main(String args[]) throws Exception {

        StringCommandsProvider provider = new StringCommandsProvider("drone-in");
        ArrayList<PathPoint> path = new ArrayList<>();
        for(int i=0; i<23; i++){
            path.add(new PathPoint(12,11,10));
        }

        provider.setPath(path);
        provider.sendStringCommands();
        Tracer tracer = new Tracer("un_tracer","drone-out","localhost:"+ MyConstants.KAFKA_ZK_PORT);
        tracer.run(1);
    }
}
