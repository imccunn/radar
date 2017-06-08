/**
 * Created by imccunn on 5/26/17.
 */
public class Ship {
    private double x;
    private double y;
    private double signalConfidence;
    private Point point;

    public Ship(Point point, double signalConfidence) {
        this.point = point;
        this.x = point.getX();
        this.y = point.getY();

        // This is just the opacity value for the ship. [IDM]
        this.signalConfidence = signalConfidence;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSignalConfidence() {
        return this.signalConfidence;
    }

    public Point getPoint() {
        return point;
    }

    public void update(boolean wasFound) {
        if (wasFound) {
            this.signalConfidence = 1;
        } else {
            if (this.signalConfidence <= 0.03) this.signalConfidence = 0.001;
            else this.signalConfidence -= 0.01;
        }
    }
}
