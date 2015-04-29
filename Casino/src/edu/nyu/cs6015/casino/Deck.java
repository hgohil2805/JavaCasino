package edu.nyu.cs6015.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import edu.nyu.cs6015.casino.Card;
public class Deck 
{
	ArrayList<Card> cards;
	//Game currentUsedOn;
	private String cardName[] = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
	private Card[] unsortedCards;
	private static Random rand = new Random();
	public Deck()
	{
		unsortedCards = new Card[52];
		System.out.println("Creating a list of cards for the current deck");
		int currentCard = 0;
		for(int i=0;i<13;i++)
		{
			unsortedCards[currentCard] = new Card(cardName[i],i+1,Suit.Clubs);
			currentCard++;
		}
		for(int i=0;i<13;i++)
		{
			unsortedCards[currentCard] = new Card(cardName[i],i+1,Suit.Diamonds);
			currentCard++;
		}
		for(int i=0;i<13;i++)
		{
			unsortedCards[currentCard] = new Card(cardName[i],i+1,Suit.Hearts);
			currentCard++;
		}
		for(int i=0;i<13;i++)
		{
			unsortedCards[currentCard] = new Card(cardName[i],i+1,Suit.Spades);
			currentCard++;
		}
		this.cards = new ArrayList<Card>(Arrays.asList(unsortedCards));
	}
	
	public void printDeck()
	{
		System.out.println("cards size"+cards.size());
		for(Card currentCard : this.cards)
			System.out.println(currentCard);
	}
	
	public Card getTop()
	{
		Card tempCard =  cards.get(0);
		cards.remove(0);
		return tempCard;
	}
	
	public Card peekTopCard()
	{
		return this.cards.get(0);
	}
	
	
	public void shuffleDeck()
	{
		for(int i =0;i < this.cards.size();i++)
		{
			int randomNum = Deck.getRandomInRange(i, this.cards.size() - 1);
			Collections.swap(cards, i, randomNum);
		}
	}
	
	
	public void sortDeck()
	{
		cards.removeAll(cards);
		Collections.addAll(cards, unsortedCards);
	}
	
	public void removeCard(Card c)
	{
		cards.remove(c);
	}
	
	public void removeCard(int value, Suit s)
	{
		for (Card c: cards)
		{
			if(c.getValue() == value && c.getSuit() == s)
			{
				cards.remove(c);
			}
		}
	}
	
	
	public static int getRandomInRange(int min, int max)
	{
		int randomNumber = rand.nextInt((max - min) + 1) + min;
		return randomNumber;
	}
	
}
