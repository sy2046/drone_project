package maps;

import path.Path;
import path.PathPoint;
import pathFinder.PathPlannerStrategy;

import java.awt.*;

/**
 * Created by mohannad on 02/01/16.
 */
public abstract class   MapIF extends Component {
    public abstract boolean isPassable(double x, double y,double z);
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract void paintMap(Path path);
    public abstract void setPossition(PathPoint possition);
}
