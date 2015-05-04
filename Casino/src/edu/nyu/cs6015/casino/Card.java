package edu.nyu.cs6015.casino;

//import javax.swing.Icon;


public class Card 
{

	private String name;
	private int value;
	private Suit cardSuit;
	//private Icon imageIcon;
	public Card(String name, int value, Suit s)
	{
		this.name = name;
		this.value = value;
		this.cardSuit = s;
	}
	
	public Card()
	{
		System.out.println("Empty Card created");
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public Suit getSuit()
	{
		return this.cardSuit;
	}
	
	public String getName(Card c)
	{
		return c.name;
	}
	
	public int getValue(Card c)
	{
		return c.value;
	}
	
	public Suit getSuit(Card c)
	{
		return c.cardSuit;
	}
	
	public void setName(String nameChangeTo)
	{
		this.name = nameChangeTo;
	}
	
	public void setValue(int changeValueTo)
	{
		this.value = changeValueTo;
	}
	
	public void setSuit(Suit s)
	{
		this.cardSuit = s;
	}
	
	public void setName(String nameChangeTo, Card c)
	{
		c.name = nameChangeTo;
	}
	
	public void setValue(int changeValueTo, Card c)
	{
		c.value = changeValueTo;
	}
	
	public void setSuit(Suit s, Card c)
	{
		 c.cardSuit = s;
	}
	
	public String toString()
	{
		return name + " Of " + cardSuit;
	}
	
	
	
}
