package edu.nyu.cs6015.casino;

import java.util.ArrayList;
import java.util.Scanner;

public class HandValue 
{
	public static Scanner in = new Scanner(System.in);
	ArrayList<Card> tempCards;
	
	public HandValue()
	{
		System.out.println("Hand object created");
	}
	
	public static int getHandValue(ArrayList<Card> playerCard, ArrayList<Card> tableCards, Game g)
	{
		int value = 0;
		if(g.getClass().equals(BlackJackGame.class) && playerCard != null)
		{
			for(Card c: playerCard)
			{
				if(c.getValue() == 1 && playerCard.size() > 1)
				{
					System.out.println("You've got an ace!");
					if(playerCard.size() > 1)
					{
						System.out.println("The other card is");
						for(Card otherCards: playerCard)
						{
							if(otherCards.equals(c))
								continue;
							else
							{
								System.out.println("Other card is" + otherCards);
								System.out.println("\nSelect 1 to select Ace as 1 \n or \nSelect 2 to select Ace as 11");
								int userInput = in.nextInt();
								if(userInput == 1)
								{
									value += 1;
								}
								else if(userInput == 2)
									value+= 11;
								else
									{
									System.out.println("You did not enter a valid number");
									System.out.println("Selecting the card as 1");
									value += 1;
									}
							}
						}
					}
				}
				
				else if (c.getValue() > 10)
				{
					value += 10;
				}
				else
				{
					value += c.getValue();
				}
			}
			return value;
		}
		return -1;
	}
	
}
