package edu.nyu.cs6015.casino;

public enum PokerHands 
{
	RoyalFlush("RoyalFlush"), StraightFlush("StraightFlush"), FourOfAKind("4OfAKind"), FullHouse("FullHouse"),
	Flush("Flush"), Straight("Straight"), ThreeOfAKind("3OfAKind"), TwoPair("2Pair"),
	OnePair("1Pair"), HighCard("HighCard");
	private String name;
	
	private PokerHands(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
