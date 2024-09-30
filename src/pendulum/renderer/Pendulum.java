package pendulum.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Pendulum {

	public static final double g = 9.8;

	private double xPivot, yPivot;

	private double L1, L2, theta1, theta2, omega1, omega2, alpha1, alpha2;

	private double M1, M2;

	private Color color;

	private List<Integer> xPoints;
	private List<Integer> yPoints;

	public Pendulum(Color color, double xPivot, double yPivot, double L1, double L2, double theta1, double theta2,
			double omega1, double omega2, double M1, double M2) {
		this.color = color;
		this.xPivot = xPivot;
		this.yPivot = yPivot;

		this.L1 = L1;
		this.L2 = L2;

		this.theta1 = theta1;
		this.theta2 = theta2;

		this.omega1 = omega1;
		this.omega2 = omega2;

		alpha1 = 0;
		alpha2 = 0;

		this.M1 = M1;
		this.M2 = M2;

		xPoints = new ArrayList<Integer>();
		yPoints = new ArrayList<Integer>();
	}

	public void tick() {

		alpha1 = (-g * (2 * M1 + M2) * Math.sin(theta1) - M2 * g * Math.sin(theta1 - 2 * theta2)
				- 2 * M2 * Math.sin(theta1 - theta2)
						* (omega2 * omega2 * L2 + omega1 * omega1 * L1 * Math.cos(theta1 - theta2)))
				/ (L1 * (2 * M1 + M2 - M2 * Math.cos(2 * theta1 - 2 * theta2)));

		alpha2 = (2 * Math.sin(theta1 - theta2)
				* (omega1 * omega1 * L1 * (M1 + M2) + g * (M1 + M2) * Math.cos(theta1)
						+ omega2 * omega2 * L2 * M2 * Math.cos(theta1 - theta2)))
				/ (L2 * (2 * M1 + M2 - M2 * Math.cos(2 * theta1 - 2 * theta2)));

		omega1 += alpha1 / 10;
		omega2 += alpha2 / 10;

		theta1 += omega1 / 10;
		theta2 += omega2 / 10;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (xPivot - 5), (int) (yPivot - 5), 10, 10);

		double x1 = xPivot + L1 * Math.sin(theta1);
		double y1 = yPivot + L1 * Math.cos(theta1);

		double x2 = xPivot + L1 * Math.sin(theta1) + L2 * Math.sin(theta2);
		double y2 = yPivot + L1 * Math.cos(theta1) + L2 * Math.cos(theta2);

		g.drawLine((int) xPivot, (int) yPivot, (int) (x1), (int) (y1));
		g.fillOval((int) (x1 - 10), (int) (y1 - 10), 20, 20);

		g.drawLine((int) (x1), (int) (y1), (int) (x2), (int) (y2));
		g.fillOval((int) (x2 - 10), (int) (y2 - 10), 20, 20);
		
		xPoints.add((int) x2);
		yPoints.add((int) y2);
		
		if (xPoints.size() > 100) {
			xPoints.remove(0);
			yPoints.remove(0);
		}
		
		for (int i = 0; i<xPoints.size() - 1; i++) {
			g.drawLine(xPoints.get(i), yPoints.get(i), xPoints.get(i+1), yPoints.get(i+1));
		}
	}

}
