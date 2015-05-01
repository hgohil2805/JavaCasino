package edu.nyu.cs6015.casino;

import java.util.ArrayList;

public class PokerGame extends Game
{
	Deck currentDeck;
	ArrayList<Card> flop = new ArrayList<Card>();
	int currentPotSize = 0;
	public PokerGame()
	{
		super();
		this.gameName = "Poker";
		this.tableSize = 8;
	}
	
	@Override
	public void startGame() 
	{
		currentDeck = this.decksUsed.get(0);
		currentDeck.sortDeck();
		currentDeck.shuffleDeck();
		for(int i=0 ; i < 2;i++)
		{
			for(Player p : this.currentRoundPlayers)
			{
				p.addCard(currentDeck.getTop());
			}
		}
		
		for(int i=0;i<3;i++)
		{
			flop.add(currentDeck.getTop());
		}
		
	}

	@Override
	public void pauseGame() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quitGame() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetGame() 
	{
		// TODO Auto-generated method stub
		
	}
	

}
