package path;

import java.io.Serializable;

public class PathPoint implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double x;
	private double y;
	private double z;

	public PathPoint(double x, double y, double z) {

		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "{\n" +
				"  'log':" + x +
				",\n  'lat':" + y +
				"\n}";
	}

	public static void main(String args[]){
		System.out.println(new PathPoint(4,1,5));
	}
}
