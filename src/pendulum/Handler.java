package pendulum;

public class Handler {
	
	private Simulation simulation;

	public Handler(Simulation simulation) {
		this.simulation = simulation;
	}

	public int getWidth() {
		return simulation.getWidth();
	}

	public int getHeight() {
		return simulation.getHeight();
	}

}
