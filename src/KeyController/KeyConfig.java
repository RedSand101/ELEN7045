package KeyController;

import java.awt.event.KeyEvent;

/*
 * This Class is used as a configuration class for the game. 
 * Values are located as parameters in this class, and are easily modifiable to any keys for the player 
 * The getter and setter methods are used to change the values of each integer below. 
 * The future purpose of this class is to enable the game to be configurable with minimal code change.   
 */

public class KeyConfig {

	int rightKey = KeyEvent.VK_RIGHT;
	int leftKey = KeyEvent.VK_LEFT;
	private static KeyConfig instance = new KeyConfig();

	public KeyConfig() {
	}

	public static KeyConfig getInstance() {
		return instance;
	}

	public int getRightKey() {
		return rightKey;
	}

	public void setRightKey(int rightKey) {
		this.rightKey = rightKey;
	}

	public int getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(int leftKey) {
		this.leftKey = leftKey;
	}

}