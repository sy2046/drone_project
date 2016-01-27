import endPoints.AdressEndPoints;
import eventMediatorLocator.EventMediatorLocator;
import maps.GoogleMap;
import maps.MapIF;
import path.Path;
import pathFinder.GooglePathFinder;
import pathFinder.PathPlannerStrategy;
import remotes.TracableRemoteIF;
import remotes.TracerIF;
import tracer.Tracer;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by mohannad on 02/01/16.
 */
public class App {

    public static void mainnn(String args []) throws Exception {
        // ArrayList<String> drones = EventMediatorLocator.mediator().listDrones();
        //Collections.sort(drones);
        /*
        Scanner scanner = new Scanner(System.in);
        String drone = null;
        if((drones!=null)&&(drones.size()>0)){
            System.out.println("Available drones :");
            for(int i=0 ; i< drones.size()  ; i++){
                System.out.println(i+": "+drones.get(i));
            }
            System.out.print("Please Enter drone's number :");

            int id = scanner.nextInt();
            try {
                drone = drones.get(id);
            }catch (RuntimeException e){
                e.printStackTrace();
            }*/
     /*   ArrayList<String> drones = EventMediatorLocator.mediator().listDrones();
        Collections.sort(drones);
        Scanner scanner = new Scanner(System.in);
        String drone = null;
        if((drones!=null)&&(drones.size()>0)){
            System.out.println("Available drones :");
            for(int i=0 ; i< drones.size()  ; i++){
                System.out.println(i+": "+drones.get(i));
            }
            System.out.print("Please Enter drone's number :");

            int id = scanner.nextInt();
            try {
                 drone = drones.get(id);
            }catch (RuntimeException e){
                e.printStackTrace();
            }

            PathPlannerStrategy pathPlanner = new GooglePathFinder();
            Path p = pathPlanner.findPath(new AdressEndPoints("Paris","Lille"));
            EventMediatorLocator.mediator().loadPath(drone,p.getPathPoints());
            TracerIF tracer = new Tracer(drone,"tracer"+id);
            System.out.print("Press Enter to start:");
            scanner.nextLine();
            scanner.nextLine();
            EventMediatorLocator.mediator().startDelivary(drone);

        }else{
            System.err.println("there is no available drones ...");
            System.exit(-1);
        }
    }

    public static void main(String args []) throws Exception {

            String drone =EventMediatorLocator.mediator().allocateDrone();
            if(drone!=null){
            String dest [] = {"Dijon","Lille","Rennes","Tours","Caen","Nantes","Angers"};
            Random random = new Random();
            int idx1 = random.nextInt(7);
            String destination = dest[idx1];
            int idx2 = random.nextInt(7);
            if(idx2==idx1){
                idx2 = (idx2+1)%dest.length;
            }
            String start = dest[idx2];
            PathPlannerStrategy pathPlanner = new GooglePathFinder();
                System.out.println(start+":"+destination);
            Path p = pathPlanner.findPath(new AdressEndPoints(start,destination));
            EventMediatorLocator.mediator().loadPath(drone,p.getPathPoints());
            TracerIF tracer = new Tracer(drone,"tracer"+drone.charAt(drone.length()-1));
            ////////////////////////////////////

            MapIF map = new GoogleMap(p);

            JFrame frame = new JFrame(drone+": "+start+"===> "+destination);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(map, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);

            ///////////////////////////////////

            Scanner scanner = new Scanner(System.in);
            System.out.print("Press Enter to start:");
            scanner.nextLine();
            EventMediatorLocator.mediator().startDelivary(drone);

        }else{
            System.err.println("there is no available drones ...");
            System.exit(-1);
        }*/
    }
}
