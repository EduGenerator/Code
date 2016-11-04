package memoryGame;

import javax.swing.JButton;

public class Card extends JButton {

	private int value;
	private boolean found = false;

	public Card(){
		value = 0;
	}
	public boolean getFound(){
		return found;
	}
	public void found(){
		found = true;
	}
	public int getValue(){
		return value;
	}

	public void setValue(int v){
		value = v;
	}


}
