package memoryGame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Memory extends JFrame implements ActionListener{
	private final int SIZE = 4;
	private Card[] card = new Card [SIZE * SIZE];
	int clicked = 1;
	int pairs = 0;
	int fails = 0;
	Card firstCard;
	boolean gameOver = false;

	public static void main(String[] args){
		new Memory();
		JFrame frame;
	}
	public Memory(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(SIZE,SIZE));
		setVisible(true);
		setSize(500, 500);
		int indx;
		for (int r = 0 ; r < SIZE ; r++)
		{
			for (int c = 0 ; c < SIZE ; c++)
			{
				indx = r * SIZE + c;
				card [indx] = new Card ();
				card[indx].addActionListener(this);
				add(card[indx]);
			}
		}
		for (int i = 1 ; i <= SIZE * SIZE / 2 ; i++)
		{
			for (int j = 0 ; j < 2 ; j++)
			{
				do
				{
					indx = (int) (Math.random () * SIZE * SIZE);
				}
				while (card [indx].getValue () != 0);
				card [indx].setValue (i);
			}
		}


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameOver){
			return;
		}
		if(e.getSource().getClass() == Card.class){
			Card c = (Card)e.getSource();
			if(c.getFound()){
				return;
			}
			if (clicked == 1)
			{
				clearCards();
				firstCard = c;
			}
			else if (clicked == 2)
			{
				if(c == firstCard){
					return;
				}
				if(firstCard.getValue() == c.getValue()){
					pairs++;
					firstCard.setBackground(Color.GREEN);
					c.setBackground(Color.GREEN);
					firstCard.found();
					c.found();
					
				}
				else{
					fails++;
				}
				clicked = 0;
			}
			c.setText(c.getValue() + "");
			clicked++;
			if(fails > 8 || pairs == SIZE*SIZE / 2){
				gameOver = true;
			}
		}
	}
	public void clearCards(){
		for(int i = 0; i < card.length;i++){
			if(!card[i].getFound()){
				card[i].setText("");
			}
		}
	}
	public void showCards(){
		for(int i = 0; i < card.length;i++){
			card[i].setText(card[i].getValue() + " ");
		}
	}
	
}
