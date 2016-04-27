package KeyController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * The KeyInput Class extends keyAdaptor and manages the key strokes of the player.
 * The Keyboard value and KeyConfig classes are also used within this class
 * The KeyConfig allows for parameterized key stroke changes. 
 * The Key Pressed method will set the left or right button boolean value to "True".
 * The Key released method will re-set the left or right button boolean value to "False". 
 */

public class KeyInput extends KeyAdapter {

	public KeyInput() {
	}

	public void keyPressed(KeyEvent e) {

		KeyBoardValue myKey = KeyBoardValue.getInstance();
		KeyConfig myConfig = KeyConfig.getInstance();
		int key = e.getKeyCode();

		if (key == myConfig.leftKey) {
			myKey.setLeftButton(true);
		}

		if (key == myConfig.rightKey) {
			myKey.setRightButton(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		KeyBoardValue myKey = KeyBoardValue.getInstance();
		KeyConfig myConfig = KeyConfig.getInstance();

		int key = e.getKeyCode();

		if (key == myConfig.leftKey) {
			myKey.setLeftButton(false);
		}

		if (key == myConfig.rightKey) {
			myKey.setRightButton(false);
		}
	}

}