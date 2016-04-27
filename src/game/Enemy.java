package game;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Contains an enemy Constructor (X, Y, Size, Speed, Color) 
 * Controls the position of the enemy via X and Y coordinates
 * Controls the Size of the Enemy
 * Controls the Speed of the enemy
 * Controls the Movement of the Enemy   
 * The tick method is used to process the movement of the enemy. Executed from the Game Class
 * The Draw enemy method will draw a scaled version of the chosen enemy image, in this case a raindrop
 */

public abstract class Enemy {

	private int x;
	private int y;
	private int size;
	private int speed;
	ImageLoad imageLoad;

	public Enemy(int x, int y, int size, int speed) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.speed = speed;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getHeight() {
		return size;
	}

	public abstract Color getColor();

	public void move() {
		y += speed;
	}

	public void tick() {
		move();
	}

	public void drawEnemy(Graphics g) {
		ImageLoad myImage = new ImageLoad();
		myImage.getImageE();
		g.drawImage(myImage.getImageE(), x, y, 30, 30, null);
	}
}