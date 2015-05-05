package edu.nyu.cs6015.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Roulette extends Game
{
	HashMap<Integer, ArrayList<Player>> numberBet = new HashMap<Integer,ArrayList<Player>>();
	ArrayList<Player> redColorBet = new ArrayList<Player>();
	ArrayList<Player> BlackColorBet = new ArrayList<Player>();
	ArrayList<Player> oddBet = new ArrayList<Player>();
	ArrayList<Player> evenBet = new ArrayList<Player>();
	int[] blackNumbers = {2,4,6,8,10,11,14,15,17,20,22,24,26,28,29,31,33,35};
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
	
	public Player getNextPlayer(Player p)
	{
		System.out.println(currentPlayers.indexOf(p));
		if(this.currentPlayers.contains(p))
		{
			System.out.println("Player exist on the list");
			int index = currentPlayers.indexOf(p);
			if(index == currentPlayers.size() - 1)
			{
				System.out.println("Last player");
				return null;
			}
			else 
				{
				System.out.println("Next player!");
				return currentPlayers.get(index + 1); 
				}
		}
		return null;
	}
	
	public boolean isBlackNumber(int num)
	{
		return Arrays.asList(blackNumbers).contains(num);
	}
	
	public int getRolledNumber()
	{
		Random rand = new Random();
	    int randomNum = rand.nextInt((37 - 0) + 1) + 0;
	    return randomNum;
	}
	
	public boolean isLastPlayer(Player p)
	{
		if(currentPlayers.indexOf(p) == currentPlayers.size() - 1)
		{
			return true;
		}
		return false;
	}
	
	public ArrayList<Player> getWinners()
	{
		int rollNumber = getRolledNumber();
		System.out.println("Rolled Number is" +rollNumber);
		ArrayList<Player> winners = new ArrayList<Player>();
		if(numberBet.containsKey(rollNumber))
		{
			winners.addAll(numberBet.get(rollNumber));
		}
		if(isBlackNumber(rollNumber))
		{
			winners.addAll(BlackColorBet);
		}
		if(!isBlackNumber(rollNumber))
		{
			winners.addAll(redColorBet);
		}
		if(rollNumber %2 == 0)
		{
			winners.addAll(evenBet);
		}
		if(rollNumber %2 != 0)
		{
			winners.addAll(oddBet);
		}
		return winners;
	}
	
	
}


