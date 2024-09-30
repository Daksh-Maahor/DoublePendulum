package pendulum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import pendulum.display.Display;
import pendulum.renderer.Renderer;

public class Simulation implements Runnable {
	
	private String title;
	private int width, height;

	private Display display;

	private boolean running = false;
	private Thread thread;

	private Graphics g;
	private BufferStrategy bs;
	
	private Handler handler;
	private Renderer renderer;

	public Simulation(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	private void init() {
		display = new Display(title, width, height);

		handler = new Handler(this);
		
		renderer = new Renderer(handler);
	}

	@Override
	public void run() {
		init();

		int fps = 100;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				System.out.println(ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	private void update() {
		renderer.tick();
	}

	private void tick() {
		// Update everything
		update();
		// Render everything
		render();
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();

		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		// clear
		g.clearRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		// draw
		renderer.render(g);
		// show
		bs.show();
		g.dispose();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
