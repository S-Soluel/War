/* This class file is not part of your assignment. But you can use
it to test whether or not your Deck class is working properly. */

package cardGame;

public class DeckTest {
	public static void main(String[] args)
	{
		Deck d = new Deck();
		d.shuffle();
		System.out.println();
		
		while(d.isEmpty() == false)
		{
			d.draw();
		}
		if (d.isEmpty() == true)
		{
			System.out.println();
			System.out.println("There are no more cards in the deck. Good game!");
		}
		
		
		
		//System.out.println("The top card is: " + c.getRank() + " " + c.getSuit());
		
		/*
		while(!(d.isEmpty()))
		{
			Card c = d.draw();
			System.out.println("Here is the card: " + c.getSuit() + ", " + c.getRank());
		} */
	}
}


