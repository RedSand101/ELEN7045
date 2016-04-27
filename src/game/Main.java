package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import KeyController.KeyInput;

/*
 * Controls the Layout of the screen (size, scale and width)
 * Executes the main method to establish the dimensions and create a Game object
 * This is labeled as a Dodger object and the added to the JPanel
 * The game (dodger) is then started.
 * The start method check game state and start an additional thread
 * The Stop method checks game state and will set the state to false
 * The run method controls the game tick (while game is running), while also rendering and painting the screen
 * Once the running loop ends a call to present the game over screen is executed
 */

public class Main extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 160;
	public static final int HEIGHT = 120;
	public static final int SCALE = 4;

	boolean running;
	static JFrame frame = new JFrame("Dodger Plus 2.0");
	static JPanel panel = new JPanel(new BorderLayout());

	Game game;
	Thread thread;
	Image dbImage;
	Graphics dbg;

	public static void main(String[] args) {

		Main dodger = new Main();

		frame.addKeyListener(new KeyInput());
		panel.add(dodger, BorderLayout.CENTER);
		frame.setIconImage(getIcon());
		frame.setContentPane(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		dodger.start();
	}

	public Main() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		game = new Game();
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
	}

	public void run() {
		double unprocessedSeconds = 0;
		long lastTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;

		while (running) {
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;
			if (passedTime < 0)
				passedTime = 0;
			if (passedTime > 100000000)
				passedTime = 100000000;

			unprocessedSeconds += passedTime / 1000000000.0;

			boolean ticked = false;
			while (unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;

				tickCount++;
				if (tickCount % 60 == 0) {
					lastTime += 1000;
				}
			}

			if (ticked) {
				render();
				paintScreen();
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		End();
	}

	public void tick() {
		running = game.tick(running);
	}

	/*
	 * clears the background Draws the raw game elements
	 */

	public void render() {
		if (dbImage == null) {
			dbImage = createImage(SCALE * WIDTH, SCALE * HEIGHT);

			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			} else
				dbg = dbImage.getGraphics();
		}

		dbg.setColor(Color.white);
		dbg.fillRect(0, 0, SCALE * WIDTH, SCALE * HEIGHT);
		game.draw(dbg);
	}

	/*
	 * Used active rendering to put the buffered image on-screen Sync'ed the
	 * display on some systems
	 */

	private void paintScreen() {
		Graphics g;
		try {
			g = this.getGraphics();
			if ((g != null) && (dbImage != null))
				g.drawImage(dbImage, 0, 0, null);
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
		} catch (Exception e) {
			System.out.println("Graphics context error: " + e);
		}
	}

	private static BufferedImage getIcon() {
		String imagePath = "raindrop.png";
		InputStream imgStream = Game.class.getResourceAsStream(imagePath);
		BufferedImage myImg = null;
		try {
			myImg = ImageIO.read(imgStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return myImg;
	}

	private void End() {
		JFrame frame1 = new JFrame();
		frame1.setLayout(new GridBagLayout());
		JPanel panel = new JPanel();
		JLabel jlabel = new JLabel("ELEN7045 - Game Over");
		jlabel.setFont(new Font("Verdana", 1, 20));
		frame.setIconImage(getIcon());
		panel.add(jlabel);
		panel.setBorder(new LineBorder(Color.WHITE));
		frame1.add(panel, new GridBagConstraints());
		frame1.setSize(400, 400);
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setVisible(true);
	}

}