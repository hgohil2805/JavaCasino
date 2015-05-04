package edu.nyu.cs6015.casino;

import java.util.ArrayList;

public class PokerGame extends Game
{
	Deck currentDeck;
	ArrayList<Card> flop = new ArrayList<Card>();
	int currentPotSize = 0;
	int currentPotRaise;
	public PokerGame()
	{
		super();
		this.gameName = "Poker";
		this.tableSize = 8;
	}
	
	@Override
	public void startGame() 
	{
		this.getFirstRound();
		this.PlayersMakeMoves();
		System.out.println("Adding 3 cards to the flop");
		for(int i=0;i<3;i++)
		{
			flop.add(currentDeck.getTop());
		}
		this.PlayersMakeMoves();
		System.out.println("Adding a card to the flop");
		flop.add(currentDeck.getTop());
		this.PlayersMakeMoves();
		System.out.println("Adding a card to the flop");
		flop.add(currentDeck.getTop());
		this.PlayersMakeMoves();
		System.out.println("The current winner is" + this.getWinners());
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
	
	public void PlayersMakeMoves()
	{
		boolean raise = true;
		one: while(raise)
		{
			Move currentMove = null;
			two : for(Player p : this.currentRoundPlayers)
			{
				System.out.println("Current Pot Raise" +currentPotRaise);
				if(p.currentPlayerStatus != PlayerMove.Fold && p.currentMoneyBet == currentPotRaise )
				{
					raise = false;
					currentMove = p.makeMove();
				}
				else if(p.currentPlayerStatus != PlayerMove.Fold && currentPotRaise > p.currentMoneyBet)
				{
					currentMove = p.forceRaise(currentPotRaise - p.currentMoneyBet); 
				}
				
				if(currentMove != null && currentMove.getMove().equals(PlayerMove.Check))
				{
					continue two;
				}
				else if(currentMove != null && currentMove.getMove().equals(PlayerMove.Fold))
				{
					p.setCurrentStatus(PlayerMove.Fold);
					p.setMoneyLost(p.currentMoneyBet);
				}
				else if(currentMove != null && currentMove.getMove().equals(PlayerMove.Call))
				{
					continue two;
				}
				
				else if(currentMove != null && currentMove.getMove().equals(PlayerMove.Raise))
				{
					int moveMoney = currentPotRaise - currentMove.getBet();
					raise = true;
					if(moveMoney > 0)
					{
						currentPotRaise += moveMoney;
					}
					else
					{
						currentPotRaise += currentMove.getBet();
					}
				}
				
			}
			currentPotRaise = 0;
			break one;
		}
	}
	
	public Player getWinners()
	{
		Player currentWinner = this.currentPlayers.get(0);
		int max = (int)HandValue.getHandValue(currentWinner.getCards(), this.flop, this);
		for(Player p : currentPlayers)
		{
			int currentHandValue = (int)HandValue.getHandValue(p.getCards(), this.flop, this);
			if( currentHandValue > max)
			{
				currentWinner = p;
				max = currentHandValue;
			}
		}
		return currentWinner;
	}
	
	
	public ArrayList<Player> getFirstRound()
	{
		System.out.println("Adding cards to the deck");
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
					if(p.currentMoneyBet == currentPotRaise)
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
						if(p.getCurrentStatus() != PlayerMove.Fold)
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
	
	public void addNToFlop(int n)
	{
		for(int i=0;i<n;i++)
		{
			this.flop.add(currentDeck.getTop());
		}
	}
	
	
	

}
