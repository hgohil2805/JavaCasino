package edu.nyu.cs6015.casino;

public enum Suit 
{
	Hearts("Hearts"), Diamonds("Diamonds"), Spades("Spade"), Clubs("Clubs");
	
	private String name;
	
	private Suit(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
