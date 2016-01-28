import drone.Drone;
import endPoints.AdressEndPoints;
import eventMediatorLocator.EventMediatorLocator;
import path.Path;
import pathFinder.GooglePathFinder;
import pathFinder.PathPlannerStrategy;
import remotes.TracerIF;
import tracer.Tracer;
import utils.MyConstants;

import java.util.Random;

/**
 * Created by sy306571 on 28/01/16.
 */
public class Simulation {
    public static void main(String args[]) throws Exception {
        /*String dest [] = {"Dijon","Lille","Rennes","Tours"};
        Random random = new Random();
        int idx = random.nextInt(4);
        String destination = dest[idx];
        PathPlannerStrategy pathPlanner = new GooglePathFinder();
        Path p = pathPlanner.findPath(new AdressEndPoints("Paris",destination));
        EventMediatorLocator.mediator().loadPath("drone",p.getPathPoints());
        TracerIF tracer = new Tracer("un tracer","drone","d");*/
        //Envoi du path (les prints permettent de verifier que c bon)

       /* Drone drone = new Drone("drone");
        Tracer tracer = new Tracer("tracer","drone","localhost:"+ MyConstants.KAFKA_ZK_PORT);
        tracer.sendPath(...);*/
        //Fin !
    }
}
