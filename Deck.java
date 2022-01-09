/* Author: Sam Solheim
 *  Date: 3/21/2021
 *  Sources: https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
 */

package cardGame;

import java.util.Random;

public class Deck{
	private Card[] deck;
	private int top;
	
	public Deck()
	{
		// initializes instances of 'deck' and 'top' variables
		deck = new Card[52];
		top = 0;
		for (int count = 0; count <= 51; count++)
		{
			// The following if statement sets the 'top' card of the deck to 2 of Clubs //
			if (count == 0)
			{
				Card filler = new Card(2, 'C');
				deck[top] = filler;
				// top variable is listed as an int, meaning we do not need to store the Suit as well
			}
			// Nested if statement made for better compartmentalization of the code //
			else if (count > 0 && count <= 51)
			{
				// Finishes up first Suit of cards //
				if (count <= 12)
				{
					Card filler1 = new Card(count+2, 'C');
					deck[count] = filler1;
					// Following statement is used in all subsections to make sure they print the correct card ranks and suits //
					// System.out.println(deck[count].getRank() + " " + deck[count].getSuit());
				}
				// Takes care of Diamond Suit //
				else if (count > 12 && count <= 25)
				{
					Card filler2 = new Card(count-11, 'D');
					deck[count] = filler2;
					// System.out.println(deck[count].getRank() + " " + deck[count].getSuit());
				}
				// Takes care of Suit of Spades //
				else if (count > 25 && count <= 38)
				{
					Card filler2 = new Card(count-24, 'S');
					deck[count] = filler2;
					// System.out.println(deck[count].getRank() + " " + deck[count].getSuit());
				}
				// Creates cards for Suit of Hearts, does not need upper bound because of nested else-if and for statement //
				else if (count > 38)
				{
					Card filler3 = new Card(count-37, 'H');
					deck[count] = filler3;
					// System.out.println(deck[count].getRank() + " " + deck[count].getSuit());
				}	
			}
		}
		  // This was used to double check that the above code would actually print out the correct values, and had the correct overall length
		  // System.out.println(deck.length);
		  // System.out.println(top); 
	}
	
	public void shuffle()
	{
		for(int x = 0; x < 52; x++)
		{
			// Following section was influenced by resource linked above //
			Random rand = new Random();
			int upperbound = 52;
			int rand_int = rand.nextInt(upperbound);
			// Utilizes the swap method to switch the spots of two cards in the deck
			swap(x, rand_int);
		}
		
		// The following lines were used to make sure that the shuffle method was working as expected, 
		// accidentally put it above earlier, gave misleading results by printing values multiple times if they were switched to the value of
		// 'x' earlier on.
		/*
		for (int y = 0; y < 52; y++)
		{
			System.out.println(deck[y].getRank() + " " + deck[y].getSuit());
		}
		*/
	}
	
	public Card draw()
	{
		// Increments 'top' variable each time method is run, keeps place since top isn't local variable
		// System.out.println(deck[top].getRank() + " " + deck[top].getSuit());
		top += 1;
		//Following line stops error message from popping up when top >= 52
		if (top >= 52)
			return null;
		return deck[top];
	}
	
	public boolean isEmpty()
	{
		// Returns 'true' only if the index of the deck array is greater than 51, this checks if there are any cards left in the deck
		if (top > 51)
			return true;
		else if (top <= 51)
			return false;
		return null != null;
	}
	
	// The following method is used to swap the places of two different cards in the array
	private void swap(int i, int j)
	{
		Card SwapCard = new Card(2, 'C');
		// Local var 'SwapCard' needed to save the value of one of the cards before replacing it with the value of the second card
		SwapCard = deck[i];
		deck[i] = deck[j];
		deck[j] = SwapCard;
	}
	
}