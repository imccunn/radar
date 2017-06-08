import java.util.List;

public class RadarModel {
	private static final double NUM_TICKS = 360;
	private static final double INCREMENT = (2.0 * Math.PI) / NUM_TICKS;
	private static final double TOLERANCE = 0.001;
	private static final int RADIUS = 300;

	private double theta = 0.0;

    private List<Ship> ships;

    public static int getRADIUS() {
        return RADIUS;
    }

    public RadarModel(List<Ship> ships) {
        this.ships = ships;
    }

	public void update() {
		this.theta += INCREMENT;
		if (Math.abs(2.0 * Math.PI - theta) < TOLERANCE) {
			this.theta = 0.0;
		}
		ships.forEach(ship -> {
		    boolean wasFound = Math.abs(this.theta - ship.getPoint().getTheta()) < 0.01;
		    ship.update(wasFound);
        });
	}

	public List<Ship> getShips() {
	    return this.ships;
    }

    public double getTheta() {
        return theta;
    }
}
