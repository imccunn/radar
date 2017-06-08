import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

public class RadarPane extends JComponent {
	private static final int SHIP_RADIUS_PX = 5;
	private static final int SHIP_DIAMETER_PX = 2 * SHIP_RADIUS_PX;
	private RadarModel radarModel;
	private double radius = Math.min(getSize().getHeight(), getSize().getWidth());

	public RadarPane(RadarModel radarModel) {
		this.radarModel = radarModel;
	}

	@Override
	public void paint(Graphics g) {
		Dimension size = getSize();
		int radarSize = Math.min(size.width, size.height);

		g.setColor(Color.black);
		g.fillRect(0, 0, size.width, size.height);

		g.setColor(Color.green);
		g.drawOval(0, 0, radarSize, radarSize);


		paintShip(radarSize, g);

		int ox = toComponentX(radarSize, 0);
		int oy = toComponentY(radarSize, 0);
		double theta = radarModel.getTheta();
		int sx = toComponentX(radarSize, Math.cos(theta));
		int sy = toComponentY(radarSize, Math.sin(theta));
		g.setColor(Color.green);
		g.drawLine(ox, oy, sx, sy);
	}

	private void paintShip(double radarSize, Graphics g) {
		List<Ship> ships = radarModel.getShips();
		ships.forEach(ship -> {
			int compX = toComponentX(radarSize, ship.getX());
			int compY = toComponentY(radarSize, ship.getY());
			float opacity = (float) ship.getSignalConfidence();
			Color color = new Color((float) 1.0, 0, 0, opacity);
			g.setColor(color);
			g.drawOval(compX - SHIP_RADIUS_PX, compY - SHIP_RADIUS_PX, SHIP_DIAMETER_PX, SHIP_DIAMETER_PX);
		});
	}

	private static int toComponentX(double radarSize, double worldX) {
		return (int) (0.5 * radarSize * (worldX + 1.0));
	}

	private static int toComponentY(double radarSize, double worldY) {
		return (int) (0.5 * radarSize * (1.0 - worldY));
	}

	public double getRadius() {
		return this.radius;
	}
}
