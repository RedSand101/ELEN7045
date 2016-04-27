package game;

import java.awt.Color;

/*
 * RainDrops Class extends the EnemyClass and has a constructor for Creating RainDrops 
 */

public class RainDrops extends Enemy {

	public RainDrops(int x, int y, int size, int speed) {
		super(x, y, size, speed);
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

}