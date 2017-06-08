/**
 * Created by imccunn on 6/5/17.
 */
public class Point {
    private double x;
    private double y;
    private double radius;
    private double theta;

    public Point(double radius, double theta) {
        this.radius = radius;
        this.theta = theta;
        this.setCartesianFromPolar();
    }

    public double getTheta() {
        return theta;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private void setCartesianFromPolar() {
        this.x = radius * Math.cos(this.theta);
        this.y = radius * Math.sin(this.theta);
    }
}
