package edu.nyu.cs6015.casino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HandValue 
{
	public static Scanner in = new Scanner(System.in);
	ArrayList<Card> tempCards;
	
	public HandValue()
	{
		System.out.println("Hand object created");
	}
	
	public static Object getHandValue(ArrayList<Card> playerCard, ArrayList<Card> tableCards, Game g)
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
		
		else if(g.getClass().equals(PokerGame.class))
		{
			return PokerHands.OnePair;
		}
		return -1;
	}
	
	private PokerHands isFlush(ArrayList<Card> cards)
	{
		HashMap<Suit, ArrayList<Card>> suitCount = new HashMap<Suit,ArrayList<Card>>();
		
		for(Card c : cards)
		{
			if(suitCount.containsKey(c.getSuit()))
			{
				ArrayList<Card> temp = suitCount.get(c.getSuit());
				temp.add(c);
				suitCount.put(c.getSuit(), temp);
			}
			else
			{
				ArrayList<Card> cardList = new ArrayList<Card>();
				cardList.add(c);
				suitCount.put(c.getSuit(), cardList);
			}
		}
		for(Suit s : suitCount.keySet())
		{
			ArrayList<Card> temp = suitCount.get(s);
			if(temp.size()> 4)
			{
				PokerHands series = this.isStraight(temp);
				if(series != null && this.isRoyalStraight(cards))
				{
					return PokerHands.RoyalFlush;
				}
				else if(series != null)
					return PokerHands.StraightFlush;
				else 
				{
					return PokerHands.Flush;
				}
			}
		}
		return null;
	}
	
	private PokerHands isStraight(ArrayList<Card> c)
	{
		ArrayList<Integer> elements = new ArrayList<Integer>();
		int count = 1;
		int max = 0;
		for(Card currentCard : c)
		{
			if(currentCard.getValue() == 1)
			{
				elements.add(1);
				elements.add(14);
			}
			else
				elements.add(currentCard.getValue());
		}
		Collections.sort(elements);
		for(int i = 1;i < elements.size() ;i++)
		{
			if(elements.get(i-1) + 1 == elements.get(i))
			{
				count++;
			}
			else 
			{
				count = 1; 
			}
			if(max < count)
			{
				max = count;
			}
		}
		if(max >=5)
		{
			return PokerHands.Straight;
		}
		else 
		{
			return null;
		}
	}
	
	private boolean isRoyalStraight(ArrayList<Card> c)
	{
		ArrayList<Integer> elements = new ArrayList<Integer>();
		for(Card currentCard : c)
		{
			if(currentCard.getValue() == 1)
			{
				elements.add(14);
			}
			else
			{
				elements.add(currentCard.getValue());
			}
		}
		Collections.sort(elements);
		for(int i=10; i <=14; i++)
		{
			if(elements.contains(i))
				continue;
			else 
			{
				return false;
			}
		}
		return true;
	}
	
	private PokerHands getCount(ArrayList<Card> c)
	{
		HashMap<Integer, Integer> cardCount = new HashMap<Integer,Integer>();
		for(Card currentCard : c)
		{
			if(cardCount.containsKey(currentCard.getValue()))
			{
				cardCount.put(currentCard.getValue(), cardCount.get(currentCard.getValue()) + 1);
			}
			else
			{
				cardCount.put(currentCard.getValue(), 0);
			}
		}
		
		int twoPairCount = 0;
		int threePairCount = 0;
		for(Integer currentCard : cardCount.keySet())
		{
			int currentCount = cardCount.get(currentCard);
			if(currentCount > 4)
			{
				System.out.println("Incorrect Card count! Something is wrong!");
			}
			else if(currentCount == 4)
			{
				System.out.println("Four of a kind found");
				return PokerHands.FourOfAKind;
			}
			else if(currentCount == 3)
			{
				System.out.println("3 of a kind found!");
				threePairCount++;
			}
			else if(currentCount == 2)
			{
				twoPairCount++;
			}
		}
		if(threePairCount >= 1 && twoPairCount >= 1)
		{
			return PokerHands.FullHouse;
		}
		else if(threePairCount >= 1)
		{
			return PokerHands.ThreeOfAKind;
		}
		else if(twoPairCount == 2)
		{
			return PokerHands.TwoPair;
		}
		else if(twoPairCount == 1)
		{
			return PokerHands.OnePair;
		}
		return null;
	}
	
	 public int getHighCard(ArrayList<Card> cardList)
	 {
		 int max = 0;
		 for(Card c : cardList)
		 {
			 if(c.getValue() > max)
			 {
				 max = c.getValue();
			 }
		 }
		 return max;
	 }
	
}
