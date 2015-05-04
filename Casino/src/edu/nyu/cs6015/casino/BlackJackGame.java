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
				Winner.Lose(Winner.currentRoundBet);
				Winner = currentPlayer;
				winnerValue = currentPlayerValue;
			}
		}
		return Winner;
	}
	
	

}
