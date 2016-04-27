package game;

import java.awt.Graphics;
import KeyController.KeyBoardValue;

/*
 * Contains an player Constructor (X, Y, width, height) 
 * Controls the position of the Player via X and Y coordinates
 * Controls the Movement of the Enemy
 * Controls the keys used to move the player, via a singleton pattern
 * The tick method is used to process the movement of the player. Executed from the Game Class
 * The Draw player method will draw a scaled version of the chosen player image, in this case a Hero Man
 */

public class Player {

	private int x;
	private int y;
	private int width = 20;
	private int height = 20;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void drawPlayer(Graphics g) {
		ImageLoad myImage = new ImageLoad();
		myImage.getImageP();
		g.drawImage(myImage.getImageP(), x, y, 25, 25, null);
	}

	public void move(int moveAmount) {
		x += moveAmount;
	}

	@SuppressWarnings("static-access")
	public void tick() {
		KeyBoardValue myKey = KeyBoardValue.getInstance();
		if (KeyBoardValue.getInstance().isLeftButton()) {
			move(-10);
		}

		if (myKey.getInstance().isRightButton()) {
			move(10);
		}
	}

}
