package KeyController;

/*
 * This Class allows for a singleton implementation of the KeyBoard
 * It then sets boolean values to determine a left or right move for the player 
 */

public class KeyBoardValue {

	private boolean leftButton = false;
	private boolean rightButton = false;
	private static KeyBoardValue instance = new KeyBoardValue();

	private KeyBoardValue() {
	}

	public static KeyBoardValue getInstance() {
		return instance;
	}

	public boolean isLeftButton() {
		return leftButton;
	}

	public void setLeftButton(boolean leftButton) {
		this.leftButton = leftButton;
	}

	public boolean isRightButton() {
		return rightButton;
	}

	public void setRightButton(boolean rightButton) {
		this.rightButton = rightButton;
	}

}