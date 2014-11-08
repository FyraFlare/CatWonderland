import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;


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

//		long beginLoopTime;
//		long endLoopTime;
//		long currentUpdateTime = System.nanoTime();
//		long lastUpdateTime;
//		long deltaLoop;
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

		while (running) {
			
			update();
			render();

			try {
				Thread.sleep(1000/desiredFPS);
			} catch (InterruptedException e) {
				
			}
		}

	}

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