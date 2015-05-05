package edu.nyu.cs6015.casino;

import java.util.ArrayList;
import java.util.HashMap;

public class BlackJackGame extends Game
{
	Deck currentDeck;
	int currentPotRaise;
	
	HashMap<Player, Integer> playerCardValue = new HashMap<Player,Integer>();
	public BlackJackGame()
	{
		super();
		this.gameName = "BlackJack";
		this.tableSize = 7;
	}
	@Override
	public void startGame() 
	{
	}

	@Override
	public void pauseGame() 
	{
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
	public void resetGame() 
	{
		this.currentRoundPlayers = this.currentPlayers;
		for(Player p : currentPlayers)
		{
			p.reset();
			if(p.getTotalMoney() <= 0)
			{
				this.currentRoundPlayers.remove(p);
				this.currentPlayers.remove(p);
			}
		}
	}
	
	public Player getWinner()
	{
		Player winner = null;
		for(Player p : playerCardValue.keySet())
		{
			if(winner == null)
			{
				winner = p;
			}
			else if(playerCardValue.get(winner) < playerCardValue.get(p))
			{
				winner = p;
			}
		}
		return winner;
	}
	
	
	public ArrayList<Player> getFirstRound()
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
		
		return currentRoundPlayers;
	}
	
	
	public Player getNextPlayer(Player currentPlayer,PlayerMove move)
	{
		Player nextPlayer = null;
		if(currentRoundPlayers.contains(currentPlayer))
		{
			int index = currentRoundPlayers.indexOf(currentPlayer);
			boolean raise = true;
			if(index == currentRoundPlayers.size() - 1)
			{
				if(move == PlayerMove.Raise)
				{
					for(Player p : currentRoundPlayers)
					{
						if(p.getCurrentStatus() != PlayerMove.Fold)
						{
							return p;
						}
					}
				}
				else
				{
					for(Player p : currentRoundPlayers)
				{
					if(p.currentRoundBet == currentPotRaise)
					{
						raise = false;
					}
					else break;
				}
				
				if(!raise)
				{
					this.currentPotRaise = 0;
					return null;
				}
				else
				{
					for(Player p : currentRoundPlayers)
					{
						if(p.getCurrentStatus() != PlayerMove.Fold && !p.getCurrentStatus().equals(PlayerMove.AllIn))
						{
							return p;
						}
					}
				}
			}}
			
			else
			{
				index += 1;
				for(int i=index;i < currentPlayers.size();i++)
				{
					Player p = currentPlayers.get(i);
					if(p.currentPlayerStatus == PlayerMove.Fold)
					{
						continue;
					}
					else
						return p;
				}
			}
		}
		return null;
	}
	
	public void addCurrentPotRaise(int n)
	{
		this.currentPotRaise += n; 
	}
	
	public int getCurrentPotRaise()
	{
		return this.currentPotRaise;
	}
	
	public void setPlayerValue(Player p, int value)
	{
		if(this.playerCardValue.containsKey(p))
		{
			this.playerCardValue.remove(p);
			
		}
		this.playerCardValue.put(p, value);	
	}

	
	public int getPlayerValue(Player p)
	{
		if(this.playerCardValue.containsKey(p))
		{
			return this.playerCardValue.get(p);
		}
		else
			return -1;
	}
	
	public void updatePlayerValue(Player p, int value)
	{
		if(this.playerCardValue.containsKey(p))
		{
			int n = playerCardValue.get(p);
			n += value;
			playerCardValue.put(p, n);
		}
		else
		{
			playerCardValue.put(p, value);
		}
	}
}
