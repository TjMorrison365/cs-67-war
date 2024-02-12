/*
 * Author : Tj Morrison
 * Date Created : 10/16/22
 * Date Updated : 10/24/22
 * 
 * Project Name : War Game
 * Purpose : Play the card game War with the computer.
 * 
 * Sources: NA
 * 
 * Things to note:
 * 
 * *** This game has a set number of cards it can input and output each turn.
 * Meaning a lot of the "You tried to pick a card" messages aren't ever going to
 * be used. Finding those would eliminate a lot of unnecessary code.
 * 
 * *** I used individual variables for you_card and you_card_primary + etc...
 * These could be turned into an array since they would make sense in an ordered
 * array like that. It would be better organized having 1 array instead of
 * 3 variables.
 * 
 * *** Setting up better dialog flexible word methods could be an option as it
 * would save me a lot of code and could be easier to read. But it comes at the
 * cost of having a more complicated War class with more methods to replace
 * the if/else string literals... Whether the effort of filtering through all
 * that is worth it is yet to be known, but considering it's size I think it's
 * just an alternative.
 * 
 */




package cardGame;

import java.io.IOException;

public class War {

	public static void main(String[] args) {
		
		// Initialize Variables
		
		Deck d = new Deck();
		d.shuffle();
		String[] turnlist = new String[2];
		turnlist[0] = "You";
		turnlist[1] = "Opponent";
		Card you_card = new Card(0, 'H');
		Card opp_card = new Card(0, 'H');
		Card you_card_primary = new Card(0, 'H');
		Card opp_card_primary = new Card(0, 'H');
		Card you_card_secondary = new Card(0, 'H');
		Card opp_card_secondary = new Card(0, 'H');
		int you_score = 0;
		int opp_score = 0;
		boolean war_end_early;
		
		System.out.println("Welcome to WAR! This program was based"
				+ " off the based off the original card game.");
		
		
		// --- Game loop ---
		while (!d.isEmpty()) {
			System.out.println("");
			System.out.println(" * * * To start the next round press ENTER! * * *");
			promptEnterKey();
			
			//Draw first cards
			for (int i = 0; i < turnlist.length; i++) {
				if (turnlist[i] == "You")
					if (!d.isEmpty())
						you_card = PlayerDraw(turnlist[i], d, you_card_secondary, 0);
					else
						System.out.println("You try to pick a card but realize the deck is empty!");
				
				else
					if (!d.isEmpty())
						opp_card = PlayerDraw(turnlist[i], d, opp_card_secondary, 0);
					else
						System.out.println("Your opponent tries to pick a card but realizes the deck is empty!");
						
			}
			System.out.println("===============================================");
			
			
			//Check for war
			if (you_card.getRank() == opp_card.getRank()) {
				war_end_early = false;
				System.out.println("WAR!");
				for (int i = 0; i < turnlist.length; i++) {
					
					// Draw 2 cards (First initial card + Tie token)
					if (turnlist[i] == "You") {
						if (!d.isEmpty())
							you_card_secondary = PlayerDraw(turnlist[i], d, you_card_secondary, 1);
						else {
							System.out.println("You try to pick a card but realize the deck is empty!");
							war_end_early = true;
							break; // Prevent duplicate print with opponent draw card
						}
						
						if (!d.isEmpty())
							you_card_primary = PlayerDraw(turnlist[i], d, you_card_secondary, 0);
						else {
							System.out.println("You try to pick a card but realize the deck is empty!");
							war_end_early = true;
							break; // Prevent duplicate print with opponent draw card
						}
					}
					else {
						if (!d.isEmpty()) {
							opp_card_secondary = PlayerDraw(turnlist[i], d, opp_card_secondary, 1);
						}
						else {
							System.out.println("Your opponent tries to pick a card but realizes the deck is empty!");
							war_end_early = true;
							break; // Prevent duplicate print with next draw card
						}
						
						if (!d.isEmpty())
							opp_card_primary = PlayerDraw(turnlist[i], d, opp_card_secondary, 0);
						else {
							System.out.println("Your opponent tries to pick a card but realizes the deck is empty!");
							break; // Consistency
						}
					}
				}
				
				
				//WAR - Score Recall
				if (war_end_early == false) {
					System.out.println("-----------------------------------------------");
					if (you_card_primary.getRank() == opp_card_primary.getRank()) {
						System.out.println("You both tie, and must now look to the first card you drew to even the score!");
						for (int i = 0; i < turnlist.length; i++) {
							// Find print dialog for the secondary cards after a tie
							if (turnlist[i] == "You")
								you_card_secondary = PlayerDraw(turnlist[i], d, opp_card_secondary, 2);
							else
								opp_card_secondary = PlayerDraw(turnlist[i], d, opp_card_secondary, 2);
						}
					}
					else if (you_card_primary.getRank() > opp_card_primary.getRank()) {
						// Print for when you win war
						System.out.println("YOU WIN this war and you collect all the cards!");
						System.out.print("Your first card was ");
						CardDesc(you_card);
						System.out.print("Your second card was ");
						CardDesc(you_card_primary);
						System.out.print("Your last card was ");
						CardDesc(you_card_secondary);
						System.out.print("Your opponent's first card was ");
						CardDesc(you_card);
						System.out.print("Your opponent's second card was ");
						CardDesc(you_card_primary);
						System.out.print("Your opponent's last card was ");
						CardDesc(you_card_secondary);
						System.out.print("You scored ");
						you_score += (you_card.getRank() + you_card_primary.getRank() +
								you_card_secondary.getRank() + opp_card.getRank() +
								opp_card_secondary.getRank() + opp_card_secondary.getRank());
						System.out.print(you_card.getRank() + you_card_primary.getRank() +
								you_card_secondary.getRank() + opp_card.getRank() +
								opp_card_secondary.getRank() + opp_card_secondary.getRank());
						System.out.print(" points this round!\n");
					}
					else {
						// Print for when opponent wins war
						System.out.println("YOU LOST this war and your opponent collects all the cards!");
						System.out.print("Your first card was ");
						CardDesc(you_card);
						System.out.print("Your second card was ");
						CardDesc(you_card_primary);
						System.out.print("Your last card was ");
						CardDesc(you_card_secondary);
						System.out.print("Your opponent's first card was ");
						CardDesc(you_card);
						System.out.print("Your opponent's second card was ");
						CardDesc(you_card_primary);
						System.out.print("Your opponent's last card was ");
						CardDesc(you_card_secondary);
						System.out.print("Your opponent scored ");
						opp_score += (you_card.getRank() + you_card_primary.getRank() +
								you_card_secondary.getRank() + opp_card.getRank() +
								opp_card_secondary.getRank() + opp_card_secondary.getRank());
						System.out.print(you_card.getRank() + you_card_primary.getRank() +
								you_card_secondary.getRank() + opp_card.getRank() +
								opp_card_secondary.getRank() + opp_card_secondary.getRank());
						System.out.print(" points this round!\n");
					}
				
					if (you_card_secondary.getRank() == opp_card_secondary.getRank()) {
						System.out.println("You tie once again! All cards are scrapped.");
					}
				}
			}
			//NOT WAR - Score recall
			else if (you_card.getRank() > opp_card.getRank()) {
				you_score += you_card.getRank() + opp_card.getRank();
				System.out.println("You scored " +
				(you_card.getRank() + opp_card.getRank()) +
				" points this round!");
			}
			else {
				opp_score += you_card.getRank() + opp_card.getRank();
				System.out.println("Your opponent scored " +
				(you_card.getRank() + opp_card.getRank()) +
				" points this round!");
			}
		}
		
		//End Game loop - Total Score Recall
		System.out.println("-----------------------------------------------");
		System.out.println("All cards have been used, GAME OVER!");
		System.out.println("===============================================");
		if (you_score == opp_score)
			System.out.println("You both tie with " + you_score + " points!");
		else if (you_score > opp_score)
			System.out.println("You win with " + you_score + " points!");
		else
			System.out.println("Your opponent wins with " + opp_score + " points!");
	}
	
	
	
	public static Card PlayerDraw(String current_player, Deck d, Card secondary_card, int war_stage) {
		/*
		 * Setting card types and printing dialog. Heavily utilizes the CardDesc method.
		 * 
		 * current_player : Manages print dialog and variable setting for card scores.
		 * d : Deck that is currently being used in the game
		 * secondary_card : Passed when printing the card type in war score recall
		 * war_stage : Sets the operation code for the state of the game to output proper dialog.
		 * 
		 * returns : Card - c
		 * 
		 * */
		
		Card c = new Card(0, 'H');
		if (war_stage == 2) { // Revealing mystery card if first war phase ended in a tie.
			c = secondary_card;
			if (current_player == "You")
				System.out.print("You flip over your first card to reveal a ");
			else 
				System.out.print("Your opponent flips over their first card to reveal a ");
			
			CardDesc(c);
		}
		else if (war_stage == 1) { // Drawing 1 mystery card for war phase.
			c = d.draw();
			if (current_player == "You")
				System.out.println("You draw a random card from the deck and place it face down.");
			else
				System.out.println("Your opponent draws a random card from the deck and places it face down.");
		}
		else {
			c = d.draw();
			if (current_player == "You")
				System.out.print("You draw ");
			else 
				System.out.print("Your opponent draws ");
			
			CardDesc(c);
		}
		return c;
	}
	
	public static void CardDesc(Card c) {
		/*
		 * Prints the card descriptions
		 * 
		 * c : A card object you want to describe in system print.
		 * 
		 */
		if (c.getRank() > 10) {
			if (c.getRank() == 11)
				System.out.print("a Jack");
			else if (c.getRank() == 12)
				System.out.print("a Queen");
			else if (c.getRank() == 13)
				System.out.print("a King");
			else if (c.getRank() == 14)
				System.out.print("an Ace");
		}
		else
			System.out.print("a " + c.getRank());
		
		if (c.getSuit() == 'C')
			System.out.print(" of Clubs");
		else if (c.getSuit() == 'D')
			System.out.print(" of Diamonds");
		else if (c.getSuit() == 'S')
			System.out.print(" of Spades");
		else if (c.getSuit() == 'H')
			System.out.print(" of Hearts");
		
		System.out.print(" worth " + c.getRank() + " points!\n");
	}
	
	public static void promptEnterKey() {
		try {
			System.in.read(new byte[2]);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
