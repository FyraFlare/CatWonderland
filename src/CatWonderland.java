import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class CatWonderland implements Runnable {

	final int WIDTH = 800;
	final int HEIGHT = 600;

	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	Director director;
	
	public CatWonderland() {
		frame = new JFrame("Basic Game");

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);

		canvas.addMouseListener(new MouseControl());
		canvas.addKeyListener(Input.getInstance());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		canvas.requestFocus();
		
		director = new Director();
	}

	private class MouseControl extends MouseAdapter {

	}

	long desiredFPS = 60;
	long desiredDeltaLoop = (1000 * 1000 * 1000) / desiredFPS;

	boolean running = true;

	public void run() {

		long beginLoopTime;
		long endLoopTime;
//		long currentUpdateTime = System.nanoTime();
//		long lastUpdateTime;
		long deltaLoop;
//
//		while (running) {
//			beginLoopTime = System.nanoTime();
//
//			render();
//
//			lastUpdateTime = currentUpdateTime;
//			currentUpdateTime = System.nanoTime();
//			update((int) ((currentUpdateTime - lastUpdateTime) / (1000 * 1000)));
//
//			endLoopTime = System.nanoTime();
//			deltaLoop = endLoopTime - beginLoopTime;
//
//			if (deltaLoop > desiredDeltaLoop) {
//				// Do nothing. We are already late.
//			} else {
//				try {
//					Thread.sleep((desiredDeltaLoop - deltaLoop) / (1000 * 1000));
//				} catch (InterruptedException e) {
//					// Do nothing
//				}
//			}
//		}
//		
		long sleepTime;

		while (running) {
			
			beginLoopTime = System.nanoTime();
			
			update();
			render();

			endLoopTime = System.nanoTime();

			deltaLoop = (endLoopTime - beginLoopTime) / (1000*1000);
			sleepTime = 1000/desiredFPS - deltaLoop;
			sleepTime = Math.max(sleepTime, 0);
//			System.out.println(sleepTime);
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				
			}
		}
		
//		runGameLoop();
		
//		Timer timer = new Timer(1000/60, new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				update();
//				render();
//			}
//		});
//		timer.start();
	}

//	private int fps = 60;
//	private int frameCount = 0;

	// Starts a new thread and runs the game loop in it.
//	public void runGameLoop() {
//		Thread loop = new Thread() {
//			public void run() {
//				gameLoop();
//			}
//		};
//		loop.start();
//	}
//
//	// Only run this in another Thread!
//	private void gameLoop() {
//		// This value would probably be stored elsewhere.
//		final double GAME_HERTZ = 60.0;
//		// Calculate how many ns each frame should take for our target game
//		// hertz.
//		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
//		// At the very most we will update the game this many times before a new
//		// render.
//		// If you're worried about visual hitches more than perfect timing, set
//		// this to 1.
//		final int MAX_UPDATES_BEFORE_RENDER = 5;
//		// We will need the last update time.
//		double lastUpdateTime = System.nanoTime();
//		// Store the last time we rendered.
//		double lastRenderTime = System.nanoTime();
//
//		// If we are able to get as high as this FPS, don't render again.
//		final double TARGET_FPS = 60;
//		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
//
//		// Simple way of finding FPS.
//		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
//
//		while (running) {
//			double now = System.nanoTime();
//			int updateCount = 0;
//
//			// Do as many game updates as we need to, potentially playing
//			// catchup.
//			while (now - lastUpdateTime > TIME_BETWEEN_UPDATES
//					&& updateCount < MAX_UPDATES_BEFORE_RENDER) {
//				update();
//				lastUpdateTime += TIME_BETWEEN_UPDATES;
//				updateCount++;
//			}
//
//			// If for some reason an update takes forever, we don't want to do
//			// an insane number of catchups.
//			// If you were doing some sort of game that needed to keep EXACT
//			// time, you would get rid of this.
//			if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
//				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
//			}
//
//			// Render. To do so, we need to calculate interpolation for a smooth
//			// render.
////			float interpolation = Math.min(1.0f,
////					(float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
//			render();
//			lastRenderTime = now;
//
//			// Update the frames we got.
//			int thisSecond = (int) (lastUpdateTime / 1000000000);
//			if (thisSecond > lastSecondTime) {
//				System.out.println("NEW SECOND " + thisSecond + " "
//						+ frameCount);
//				fps = frameCount;
//				frameCount = 0;
//				lastSecondTime = thisSecond;
//			}
//
//			// Yield until it has been at least the target time between renders.
//			// This saves the CPU from hogging.
//			while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS
//					&& now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
//				Thread.yield();
//
//				// This stops the app from consuming all your CPU. It makes this
//				// slightly less accurate, but is worth it.
//				// You can remove this line and it will still work (better),
//				// your CPU just climbs on certain OSes.
//				// FYI on some OS's this can cause pretty bad stuttering. Scroll
//				// down and have a look at different peoples' solutions to this.
//				try {
//					Thread.sleep(1);
//				} catch (Exception e) {
//				}
//
//				now = System.nanoTime();
//			}
//		}
//	}

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		draw(g);
		g.dispose();
		bufferStrategy.show();
	}

	protected void update() {		
		director.update();
	}

	protected void draw(Graphics2D g) {
		director.draw(g);
	}

	public static void main(String[] args) {
		CatWonderland ex = new CatWonderland();
		new Thread(ex).start();
	}

}