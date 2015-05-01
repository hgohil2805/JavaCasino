package edu.nyu.cs6015.casino;

public enum PokerHands 
{
	RoyalFlush("RoyalFlush",9), StraightFlush("StraightFlush",8), FourOfAKind("4OfAKind",7), FullHouse("FullHouse",6),
	Flush("Flush",5), Straight("Straight",4), ThreeOfAKind("3OfAKind",3), TwoPair("2Pair",2),
	OnePair("1Pair",1), HighCard("HighCard",0);
	
	private String name;
	int value;
	
	private PokerHands(String name, int value)
	{
		this.name = name;
		this.value = value;
	}
	public int getValue()
	{
		return this.value;
	}
	public String toString()
	{
		return this.name;
	}
	
	public String getName(int value)
	{
		System.out.println(value);
		for (PokerHands p : PokerHands.values())
		{
			if(p.value == value);
				{
					System.out.println("p value" +p.value);
				}
		}
		return null;
	}
}
