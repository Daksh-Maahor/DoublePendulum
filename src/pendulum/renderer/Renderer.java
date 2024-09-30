package pendulum.renderer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import pendulum.Handler;
import pendulum.utils.Utils;

public class Renderer {

	private Handler handler;

	private List<Pendulum> pendulums;
	
	private int numPends;

	public Renderer(Handler handler) {
		this.handler = handler;
		
		numPends = 1;

		pendulums = new ArrayList<Pendulum>();

		for (int i = 0; i < numPends; i++) {
			pendulums.add(new Pendulum(Utils.getRandomColor(true), 320, 200, 150, 150, Math.PI * 2/3 + Math.PI * (i) / (180 * numPends), Math.PI / 4 + Math.PI * (i) / (180 * numPends), 0, 0, 100, 100));
		}
	}

	public void tick() {
		for (Pendulum p : pendulums) {
			p.tick();
		}
	}

	public void render(Graphics g) {
		for (Pendulum p : pendulums) {
			p.render(g);
		}
	}

}
