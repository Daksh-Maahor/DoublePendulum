package pendulum;

public class Launcher {
	
	public static void main(String[] args) {
		Simulation simulation = new Simulation("Double Pendulum", 640, 640);
		simulation.start();
	}

}
