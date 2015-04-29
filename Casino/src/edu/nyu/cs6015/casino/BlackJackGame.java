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
		boolean raise;
		for(int i=0 ; i < 2;i++)
		{
			for(Player p : this.currentRoundPlayers)
			{
				p.addCard(currentDeck.getTop());
			}
		}
		for(Player p: this.currentRoundPlayers)
		{
			p.calculateCurrentHandValue();
			Move currentMove = p.makeMove();
			if(currentMove.getMoveName().equals("raise"))
			{
				raise = true;
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
