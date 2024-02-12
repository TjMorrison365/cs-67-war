/*
 * Author : Unknown, Tj Morrison
 * Date Created : NA
 * Date Updated : 10/25/22
 * 
 * Project Name : Deck of Cards
 * Purpose : Build a Deck of Cards.
 * 
 * Sources: NA
 * 
 */

package cardGame;

import java.util.Random;

public class Deck {
	private Card[] deck = new Card[52];
	private int top = 51;
	
	public Deck()
	{
		char[] cardsuits = new char[4];
		// Clubs, Diamonds, Spades, Hearts
		cardsuits[0] = 'C'; 
		cardsuits[1] = 'D';
		cardsuits[2] = 'S';
		cardsuits[3] = 'H';
		for (int i = 0; i < 52; i++) {
			Card newcard = new Card((i%13)+2, cardsuits[i*4/52]);
			deck[i] = newcard;
		}
	}
	
	public void shuffle()
	{
		for (int i = 0; i < 52; i++) {
			swap(i, top);
		}
	}
	
	public Card draw()
	{
		top = top - 1;
		return deck[top + 1];
	}
	
	public boolean isEmpty()
	{
		return top < 0;
	}
	
	private void swap(int i, int j)
	{
		Random rand = new Random();
		Card tempcard = deck[i];
		int temp_rand = rand.nextInt(j);
		if (deck.length > 1) {
			while (temp_rand == i)
				temp_rand = rand.nextInt(j);
			deck[i] = deck[temp_rand];
			deck[temp_rand] = tempcard;
		}
	}
	
}