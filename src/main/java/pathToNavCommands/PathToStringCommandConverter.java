package pathToNavCommands;

import drone.convertor.GoAheadCommand;
import path.PathPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sy306571 on 28/01/16.
 */
public class PathToStringCommandConverter {

    public static List<String> convertPath(List<PathPoint> path) {
        List<String> stringCommands = new ArrayList<>();

        for(PathPoint point : path){
            String command = "goAhead "+ point.getX()+ " "+ point.getY() + " " + point.getZ();
            stringCommands.add(command);
        }

        return stringCommands;
    }

}
