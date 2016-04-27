package game;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;

/*
 * The imageLoad Class extends JPanel and defines two image variables, Enemy and Player images.
 * The Image load method loads the required image for the player and enemy, checks for Null values in case they are not found
 * The getImageP and getImageE methods allow for the images to be retrieved for the draw methods within the enemy and player classes
 */

public class ImageLoad extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image imageP;
	private Image imageE;

	public ImageLoad() {

		URL playerUrl = this.getClass().getResource("Hero.png");
		imageP = Toolkit.getDefaultToolkit().getImage(playerUrl);
		if (playerUrl == null) {
			System.out.println("Player Image Not Found");
		}

		URL enemyUrl = this.getClass().getResource("raindrop.png");
		imageE = Toolkit.getDefaultToolkit().getImage(enemyUrl);
		if (enemyUrl == null) {
			System.out.println("Enemy Image Not Found");
		}
	}

	public Image getImageP() {
		return imageP;
	}

	public Image getImageE() {
		return imageE;
	}
}