package edu.nyu.cs6015.casino;

import java.util.ArrayList;
import java.util.HashMap;

public class Roulette extends Game
{
	HashMap<Integer, ArrayList<Player>> numberBet = new HashMap<Integer,ArrayList<Player>>();
	ArrayList<Player> redColorBet = new ArrayList<Player>();
	ArrayList<Player> BlackColorBet = new ArrayList<Player>();
	ArrayList<Player> oddBet = new ArrayList<Player>();
	ArrayList<Player> evenBet = new ArrayList<Player>();
	
	
	@Override
	public void startGame() {
		
		
	}

	@Override
	public void pauseGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quitGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetGame() {
		// TODO Auto-generated method stub
		
	}
	
	public void addNumberBet(Player p, int num)
	{
		ArrayList<Player> temp;
		if(numberBet.containsKey(num))
		{
			temp = numberBet.get(num);
		}
		else
		{
			temp = new ArrayList<Player>();
		}
		temp.add(p);
		numberBet.put(num, temp);
	}
	
	public void addRedColorBet(Player p)
	{
		this.redColorBet.add(p);
	}
	
	public ArrayList<Player> getRedColorBet()
	{
		return this.redColorBet;
	}
	
	public void addBlackColorBet(Player p)
	{
		this.BlackColorBet.add(p);
	}
	
	public ArrayList<Player> getBlackColorBet()
	{
		return this.BlackColorBet;
	}
	
	public void addoddBet(Player p)
	{
		this.oddBet.add(p);
	}
	
	public ArrayList<Player> getOddBet()
	{
		return this.oddBet;
	}
	
	public void addEvenBet(Player p)
	{
		this.evenBet.add(p);
	}
	
	public ArrayList<Player> getEvenBet()
	{
		return this.evenBet;
	}
}


