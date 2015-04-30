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
				else if(currentMove.getMove().equals(PlayerMove.Raise))
				{
					currentPotRaise += currentMove.getBet();
				}
				
			}
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
		// TODO Auto-generated method stub
		
	}

}
