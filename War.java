
/* Author: Sam Solheim
 *  Date: 3/21/2021
 *  Sources: None in this class, source used for Deck class is listed in "Deck.java"
 */

package cardGame;

import java.io.IOException;

public class War {
	// Creates private variables for player/computer scores and cards
	private int p_score;
	private int c_score;
	private Card p_card;
	private Card c_card;
	
	// Cards to be used in case of a draw
	private Card p_draw1; private Card p_draw2;
	private Card c_draw1; private Card c_draw2;
	
	
	public War()
	{
		Deck d = new Deck();
		// initializes player/computer scores and cards, shuffles deck 
		p_score = 0; c_score = 0;
		p_card = new Card(2, 'C'); c_card = new Card(2, 'C');
		p_draw1 = new Card(2, 'C'); p_draw2 = new Card(2, 'C');
		c_draw1 = new Card(2, 'C'); c_draw2 = new Card(2, 'C');
		
		d.shuffle();
		System.out.println("The deck has been shuffled...");
		System.out.println();
		
		// Variable to keep track of which round it is
		int round = 1;
		
		// Prompts user to start the game
		System.out.println("Please press ENTER to start the game:");
		System.out.println("_____________________________________________________");
		promptEnterKey();
		System.out.println("Round 1");
		System.out.println();
		
		// Checks if the deck is empty
		while(d.isEmpty() == false)
		{
			// Outputs the current round after the game is initialized in first round, prompts user to press ENTER
			if(round > 1)
			{
				System.out.println("Please press ENTER to start the round:");
				System.out.println();
				System.out.println("_____________________________________________________");
				promptEnterKey();
				System.out.println("Round " + round);
				System.out.println();
			}
			
			// Player and computer both draw cards
			p_card = d.draw();
			c_card = d.draw();
			
			// Checks for case where Player/Computer's card is assigned a value of null
			if(p_card == null || c_card == null)
			{
				end_dialogue();
			}
			
			// Main program for majority of time: when both opponents have values stored in their cards
			else if(p_card != null && c_card != null)
			{	
				// Displays the cards drawn by the Player and the Computer
				System.out.println("Player: " + p_card.getRank() + " " + p_card.getSuit());
				System.out.println("Computer: " + c_card.getRank() + " " + c_card.getSuit());
				
				// Player wins the round
				if(p_card.getRank() > c_card.getRank())
				{
					System.out.println("The player wins this round.");
					p_score += 1;
					round += 1;
				}
				// Computer wins round
				else if(p_card.getRank() < c_card.getRank())
				{
					System.out.println("The computer wins this round.");
					c_score += 1;
					round += 1;
				}
				// Case for a War
				else if(p_card.getRank() == c_card.getRank())
				{
					// Each player draws two more cards
					p_draw1 = d.draw();
					p_draw2 = d.draw();
					c_draw1 = d.draw();
					c_draw2 = d.draw();
					
					if(p_draw1 == null || c_draw1 == null)
					{
						end_dialogue();
					}
					else if(p_draw2 == null || c_draw2 == null)
					{
						special_case_war();
						end_dialogue();
					}
					else
					{
						war_dialogue();
						round += 1;
					}
				}
				
				// Following statement checks if the Deck is empty, and will also check if p_card and c_card have been assigned the value of null.
				// Note: The last two "or's" are used as a safety net  to avoid game-breaking issues encountered earlier in coding process
				else if (d.isEmpty() == true || p_card == null || c_card == null)
				{
					end_dialogue();
				}
			}
		}
	}
	
	public static void main(String args[])
	{
		// Calls the War function to start the game
		War w = new War();
	
	}
	
	// The following method cleans up the main "War" function
	private void war_dialogue()
	{
		System.out.println("It's a War!");
		System.out.println("Press ENTER to play your first card:");
		promptEnterKey();
		
		
		// Player and Computer place their first cards in the war
		System.out.println("Player: " + p_draw1.getRank() + " " + p_draw1.getSuit());
		System.out.println("Computer: " + c_draw1.getRank() + " " + c_draw1.getSuit());
		// Player wins war with first card
		if(p_draw1.getRank() > c_draw1.getRank())
		{
			System.out.println("The player won the war and the round!");
			p_score += 3;
		}
		// Computer wins war with first card
		else if(p_draw1.getRank() < c_draw1.getRank())
		{
			System.out.println("The computer won the war and the round!");
			c_score += 3;
		}
		// Neither wins with first card
		else if(p_draw1.getRank() == c_draw1.getRank())
		{
			System.out.println("It's another tie! The war continues:");
			System.out.println("Press ENTER to play your second card:");
			promptEnterKey();
					
			System.out.println("Player: " + p_draw2.getRank() + " " + p_draw2.getSuit());
			System.out.println("Computer: " + c_draw2.getRank() + " " + c_draw2.getSuit());
			// Player wins with second card
			if(p_draw2.getRank() > c_draw2.getRank())
			{
				System.out.println("The player won the war and the round!");
				p_score += 3;
			}
			// Computer wins with second card
			else if(p_draw2.getRank() < c_draw2.getRank())
			{
				System.out.println("The computer won the war and the round!");
				c_score += 3;
			}
			// Neither wins, ends in stalemate
			else if(p_draw2.getRank() == c_draw2.getRank())
			{
				System.out.println("The war ended in a stalemate!");
			}
		}
	}
	
	// The "special case" refers to a war happening and having enough cards for the first draw of the war, but not the second part of the war
	private void special_case_war()
	{
		System.out.println("It's a War!");
		System.out.println("Press ENTER to play your first card:");
		promptEnterKey();
		
		
		// Player and Computer place their first cards in the war
		System.out.println("Player: " + p_draw1.getRank() + " " + p_draw1.getSuit());
		System.out.println("Computer: " + c_draw1.getRank() + " " + c_draw1.getSuit());
		// Player wins war with first card
		if(p_draw1.getRank() > c_draw1.getRank())
		{
			System.out.println("The player won the war and the round!");
			System.out.println();
			p_score += 3;
		}
		// Computer wins war with first card
		else if(p_draw1.getRank() < c_draw1.getRank())
		{
			System.out.println("The computer won the war and the round!");
			System.out.println();
			c_score += 3;
		}
		// Neither wins with first card
		else if(p_draw1.getRank() == c_draw1.getRank())
		{
			// Nothing needs to be done here, other than printing a blank line for formatting purposes
			System.out.println();
		}
	}
	
	// Starts ending dialogue of game and prints out who won, and the player/computer scores
	private void end_dialogue()
	{
		System.out.println("There are no more cards in the deck. Good game!");
		System.out.println();
		System.out.println("_____________________________________________________");
		System.out.println();
		
		// Case if player wins
		if(p_score > c_score)
		{
			System.out.println("The player wins with a score of " + p_score + "!");
			System.out.println("Computer Score: " + c_score);
		}
		// Case if computer wins
		else if(p_score < c_score)
		{
			System.out.println("The computer wins with a score of " + c_score + "!");
			System.out.println("Player Score: " + p_score);
		}
		// Case if the game ends in a Draw
		else
		{
			System.out.println("It's a tie!");
			System.out.println("Tie Score: " + c_score);
		}
	}
	
	// Code given to prompt the user to click the enter key
	public static void promptEnterKey() 
	{
		try {
			System.in.read(new byte[2]);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
