package pathToNavCommands;

import drone.convertor.GoAheadCommand;
import path.PathPoint;

import java.util.ArrayList;

/**
 * Created by sy306571 on 28/01/16.
 */
public class PathToStringCommandConverter {

    public static ArrayList<String> convertPath(ArrayList<PathPoint> path) {
        ArrayList<String> stringCommands = new ArrayList<>();

        for(PathPoint point : path){
            String command = "goAhead "+ point.getX()+ " "+ point.getY() + " " + point.getZ();
            stringCommands.add(command);
        }

        return stringCommands;
    }

}
