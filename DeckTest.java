/* This class file is not part of your assignment. But you can use
it to test whether or not your Deck class is working properly. */

package cardGame;

public class DeckTest {
	public static void main(String[] args)
	{
		Deck d = new Deck();
		d.shuffle();
		
		while(!(d.isEmpty()))
		{
			Card c = d.draw();
			System.out.println("Here is the card: " + c.getSuit() + ", " + c.getRank());
		}
	}
}