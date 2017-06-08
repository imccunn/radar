import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RadarSweep extends JFrame {
	private static final Dimension JFRAME_SIZE = new Dimension(600, 600);
	private static final int FRAME_RATE = 30;
	private static final int FRAME_PERIOD = 1000 / FRAME_RATE;
	private static final int NUM_SHIPS = 15;

	private RadarModel radarModel;
	private RadarPane radarPane;
	private Timer timer;

	public RadarSweep() {
		super("Radar Sweep");
		List<Ship> ships = new ArrayList<Ship>();

		this.radarModel = buildModel(15);
		this.radarPane = new RadarPane(radarModel);
		this.timer = new Timer(FRAME_PERIOD, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				radarModel.update();
				radarPane.repaint();
			}
		});
	}

	public void start() {
		setSize(JFRAME_SIZE);
		setLocationRelativeTo(null);
		setContentPane(radarPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		timer.start();
	}

	public static void main(String[] args) {
		new RadarSweep().start();
	}

	private RadarModel buildModel(int numShips) {
		List<Ship> ships = new ArrayList<>();
		for (int i = 0; i < numShips; i++) {
			// FIXME: New positions do not exclude positions of ships that have already been generated. [IDM]
			double radiusExclusionPercentage = 0.5;
			double randRadius = rand(radiusExclusionPercentage, 1.0);
			double randTheta = randomTheta();
			Point shipCoord = new Point(randRadius, randTheta);
			ships.add(new Ship(shipCoord, 0));
		}
		return new RadarModel(ships);
	}

	private double rand(double min, double max) {
		return Math.random() * (max - min) + min;
	}

	private double randomTheta() {
		return Math.random() * 2 * Math.PI;
	}
}
