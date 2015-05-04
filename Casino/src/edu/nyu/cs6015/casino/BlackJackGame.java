package edu.nyu.cs6015.casino;

public class BlackJackGame extends Game
{
	Deck currentDeck;
	public BlackJackGame()
	{
		super();
		this.gameName = "BlackJack";
		this.tableSize = 7;
	}
	@Override
	public void startGame() 
	{
		Player Dealer = new Player("Dealer",25,1000,this);
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
			Move currentMove = null;
			two :for(Player p : this.currentRoundPlayers)
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
				else if(currentMove != null && currentMove.getMove().equals(PlayerMove.Bust))
				{
					p.setCurrentStatus(PlayerMove.Fold);
					p.setMoneyLost(p.currentMoneyBet);
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
			Player currentWinner = this.getWinner();
			int currentWinnerValue = (int)HandValue.getHandValue(currentWinner.getCards(),null, this);
			
			Dealer.addCard(currentDeck.getTop());
			Dealer.addCard(currentDeck.getTop());
			int dealerHand = (int)HandValue.getHandValue(Dealer.getCards(),null, this);
			if(currentWinnerValue > dealerHand)
			{
				System.out.println("The current winner is " + this.getWinner().getName());
			}
			else
			{
				System.out.println("Dealer wins!!!");
			}
			break one;
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
		int winnerValue = (int)HandValue.getHandValue(Winner.currentCards,null, this);
		for(Player currentPlayer:this.currentRoundPlayers)
		{
			if(currentPlayer.getCurrentStatus().equals(PlayerMove.Fold))
				continue;
			int currentPlayerValue = (int)HandValue.getHandValue(currentPlayer.getCards(),null, this);
			if(winnerValue < currentPlayerValue)
			{
				Winner.Lose(Winner.currentMoneyBet);
				Winner = currentPlayer;
				winnerValue = currentPlayerValue;
			}
		}
		return Winner;
	}
	
	

}
