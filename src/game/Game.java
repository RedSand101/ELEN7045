package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

/*
 * Game Class to control flow through the Dodger Plus Game
 * The Game class extends JPanel and defines a number of variables (Player, Array of enemies, Random generator, enemy delay timer)
 * The Game method create the player and enemy array objects
 * The newEnemey method creates a random speed for the enemy as well as generates a new enemy based on the timeSinceLastEnemy value
 * The removeEnemies method loops through the array and removes enemies that have moved passed a Y coordinate value of Zero
 * The Draw method Draws both players and enemies for the Game
 * The Game Tick method is the core processing of the game, this method will cycle all movement (Players and Enemies) via their tick methods
 * 	Basic Flow:
 * 		Tick Player, Generate New Enemy (if required), Tick All enemies, while ticking all enemies check for collisions
 * 		If a valid intersection occurs between player and enemy change the game state (Game Over)
 * 		If no collision has occurred, remove invalid enemies (y < 0), add +1 to the timer that controls enemy generation
 *  The Collision detection method accepts both player and enemy objects and checks if they intersect.
 */

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	Player player;
	List<Enemy> enemies;
	Random rnd = new Random();
	int timeSinceLastEnemy = 0;

	public Game() {
		player = new Player(Main.WIDTH * Main.SCALE / 2, Main.HEIGHT * Main.SCALE - 20);
		enemies = new ArrayList<Enemy>();
	}

	public void newEnemy() {
		int speed = 1 + rnd.nextInt(5);
		if (timeSinceLastEnemy > 30) {
			enemies.add(new RainDrops(rnd.nextInt(Main.WIDTH * Main.SCALE), 0, 30, speed));
			timeSinceLastEnemy = 0;
		}
	}

	public void removeEnemies() {
		for (Iterator<Enemy> i = enemies.iterator(); i.hasNext();) {
			Enemy enemy = i.next();
			if (enemy.getY() - enemy.getHeight() > Main.HEIGHT * Main.SCALE) {
				i.remove();
			}
		}
	}

	public void draw(Graphics g) {
		player.drawPlayer(g);
		for (Enemy enemy : enemies) {
			enemy.drawEnemy(g);
		}
	}

	public boolean tick(boolean gameState) {

		player.tick();
		newEnemy();
		for (Enemy enemy : enemies) {
			enemy.tick();
			gameState = collisionDetect(player, enemy);
			if (gameState == false) {
				break;
			}
		}

		removeEnemies();
		timeSinceLastEnemy++;
		return gameState;
	}

	public boolean collisionDetect(Player myPlay, Enemy myEnemy) {

		Rectangle rect1 = new Rectangle(myPlay.getX(), myPlay.getY(), myPlay.getWidth(), myPlay.getHeight());
		Rectangle rect2 = new Rectangle(myEnemy.getX(), myEnemy.getY(), myEnemy.getHeight(), myEnemy.getHeight());

		if (rect1.intersects(rect2)) {
			return false;
		} else {
			return true;
		}
	}
}
