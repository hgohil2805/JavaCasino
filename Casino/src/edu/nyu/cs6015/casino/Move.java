package edu.nyu.cs6015.casino;

public class Move 
{
	PlayerMove currentMove;
	int moneyBet;
	
	public Move(PlayerMove name, int bet)
	{
		this.currentMove = name;
		this.moneyBet = bet;
	}
	
	public String toString()
	{
		return this.currentMove + " " + this.moneyBet;
	}
	
	
	public void setMove(PlayerMove move)
	{
		this.currentMove = move;
	}
	
	
	public PlayerMove getMove()
	{
		return this.currentMove;
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
