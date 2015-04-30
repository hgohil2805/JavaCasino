package edu.nyu.cs6015.casino;

public class BlackJackGame extends Game
{
	public BlackJackGame()
	{
		super();
		this.gameName = "BlackJack";
		this.tableSize = 7;
	}
	@Override
	public void startGame() 
	{
		// TODO Auto-generated method stub
		Deck currentDeck = decksUsed.get(0);
		currentDeck.sortDeck();
		currentDeck.shuffleDeck();
		boolean raise = true;
		for(int i=0 ; i < 2;i++)
		{
			for(Player p : this.currentRoundPlayers)
			{
				p.addCard(currentDeck.getTop());
			}
		}
		
		int currentPotRaise = 0;
		one: while(raise)
		{
			Move currentMove = new Move(PlayerMove.Check,0);
			two :for(Player p : this.currentRoundPlayers)
			{
				if(p.currentMoneyBet == currentPotRaise )
				{
					raise = false;
					currentMove = p.makeMove();
				}
				else if(currentPotRaise > p.currentMoneyBet)
				{
					currentMove = p.forceRaise(currentPotRaise - p.currentMoneyBet); 
				}
				
				if(currentMove.getMove().equals(PlayerMove.Check))
				{
					continue two;
				}
				else if(currentMove.getMove().equals(PlayerMove.Fold))
				{
					this.currentRoundPlayers.remove(p);
					p.setMoneyLost(p.currentMoneyBet);
				}
				else if(currentMove.getMove().equals(PlayerMove.Call))
				{
					continue two;
				}
				else if(currentMove.getMove().equals(PlayerMove.Raise))
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
			
			System.out.println("The winner is " + this.getWinner().getName());
		}
		
		
		
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
		Player Winner =this.currentRoundPlayers.get(0);
		int winnerValue = HandValue.getHandValue(Winner.currentCards,null, this);
		for(Player currentPlayer:this.currentRoundPlayers)
		{
			int currentPlayerValue = HandValue.getHandValue(currentPlayer.getCards(),null, this);
			if(winnerValue < currentPlayerValue)
			{
				Winner = currentPlayer;
				winnerValue = currentPlayerValue;
			}
		}
		return Winner;
	}

}
