package edu.nyu.cs6015.casino;

public class Move 
{
	String moveName;
	int moneyBet;
	
	public Move(String name, int bet)
	{
		this.moveName = name;
		this.moneyBet = bet;
	}
	
	public String toString()
	{
		return this.moveName + " " + this.moneyBet;
	}
	
	
	public void setMoveName(String move)
	{
		this.moveName = move;
	}
	
	public String getMoveName()
	{
		return this.moveName;
	}
	
	public void setBet(int n)
	{
		this.moneyBet = n;
	}
	
	public int getBet()
	{
		return this.moneyBet;
	}
	
}
