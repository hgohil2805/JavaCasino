package edu.nyu.cs6015.casino;

import java.util.ArrayList;
import java.util.Scanner;

public class Player 
{
	String name;
	int age;
	int totalMoney;
	int moneyLost;
	int moneyWon;
	int currentMoneyBet;
	Object  currentHandValue;
	PlayerMove currentPlayerStatus;
	ArrayList<Card> currentCards = new ArrayList<Card>();
	Game currentGame;
	static Scanner in = new Scanner(System.in);
	public Player(String name,int age,int money, Game g)
	{
		this.name = name;
		this.age = age;
		this.totalMoney = money;
		this.currentGame = g;
	}
	
	public Player()
	{
		System.out.println("Created a new player");
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public int getAge()
	{
		return this.age;
	}	
	
	public void setTotalMoney(int money)
	{
		this.totalMoney = money;
	}
	
	public int getTotalMoney()
	{
		return this.totalMoney;
	}
	
	public void setMoneyLost(int lost)
	{
		this.totalMoney = this.totalMoney - lost;
		this.moneyLost += lost;
	}
	
	public int getMoneyLost()
	{
		return this.moneyLost;
	}
	
	public void setMoneyWon(int won)
	{
		this.totalMoney = this.totalMoney+won;
		this.moneyLost += won;
	}
	
	public void addCard(Card c)
	{
		System.out.println("Adding card "+c);
		this.currentCards.add(c);
		this.currentHandValue = HandValue.getHandValue(this.currentCards, null, this.currentGame);
	}
	
	public ArrayList<Card> getCards()
	{
		return this.currentCards;
	}
	
	public Move makeMove()
	{
		if(this.currentGame.getClass().equals(BlackJackGame.class))
			System.out.println("The current value of the card is:"+(int)currentHandValue);
		System.out.println("#################################");
		System.out.println("The available options for you are: ");
		System.out.println("\n 1 to check/stay \n 2 to raise \n 3 to fold ");
		if(this.currentGame.getClass().equals(BlackJackGame.class))
		{
			System.out.println("\n 4 to hit ");
		}
		int input;
		input = in.nextInt();
		if(this.currentGame.getClass().equals(BlackJackGame.class) && input <= 0 ||input >4 )
		{
			System.out.println("Please enter a valid number");
			this.makeMove();
		}
		Move currentMove = new Move (PlayerMove.Check,0);
		switch(input)
		{
		case 1: 
		{
			System.out.println("You have selected to check");
			currentMove.setMove(PlayerMove.Check);
			currentMove.setBet(0);
			this.currentPlayerStatus = PlayerMove.Check;
			break;
		}
		case 2: 
		{
			System.out.println("You have selected to raise");
			if(this.totalMoney > this.currentMoneyBet + 20)
			{
				this.currentPlayerStatus = PlayerMove.Raise;
				currentMove = this.raiseMove(0);
			}
			else
			{
				System.out.println("You do not have enough money to raise");
				System.out.println("Your current bet stays as is");
			}
			break;
		}
		
		case 3:
		{
			System.out.println("You have selected to fold like a bitch");
			currentMove.setMove(PlayerMove.Fold);
			currentMove.setBet(0);
			this.currentPlayerStatus = PlayerMove.Fold;
			this.Fold();
			break;
		}
		
		case 4:
		{
			System.out.println("You have decided to hit, You will be given one more card");
			Card c = this.currentGame.getCurrentDeck().getTop();
			this.currentCards.add(c);
			System.out.println("Adding card "+c);
			currentHandValue  = (int)HandValue.getHandValue(this.currentCards,null, this.currentGame);
			if((int)currentHandValue > 21)
			{
				System.out.println("You are bust!");
				currentMove.setMove(PlayerMove.Bust);
				currentMove.setBet(0);
				return currentMove;
			}
			else
			{
				this.makeMove();
			}
		}
		default:
		{
			System.out.println("Select an option that exists!");
			break;
		}
		}
		return currentMove;
	}
	
	public void checkMove()
	{
		Move checkMove = new Move(PlayerMove.Check,0);
		this.currentPlayerStatus = PlayerMove.Check;
		System.out.println("checked" + checkMove);
	}
	
	public Move raiseMove(int toRaise)
	{
		Move raiseMove = new Move(PlayerMove.Raise,0);
		one:while(true)
		{
		System.out.println("\n Press \n 1 to raise 20 \n 2 to raise 40 \n 3 to raise 100 \n 4 to enter your own number \n 5 to cancel raise");
		int choice ;
		choice = in.nextInt();
		this.currentPlayerStatus = PlayerMove.Raise;
		switch(choice)
		{
		case 1: 
		{
			System.out.println("Raising by 20");
			if(this.totalMoney > this.currentMoneyBet+ 20 +toRaise)
			{
				this.currentMoneyBet += 20;
				raiseMove.setBet(20+toRaise);
				break one;
			}
			else 
			{
				System.out.format("Cannot raise by %d , you only have %d" ,20+toRaise, this.totalMoney - this.currentMoneyBet); 
				continue;
			}
		}
		case 2: 
		{
			System.out.println("Raising by 40");
			if(this.totalMoney > this.currentMoneyBet+ 40 + toRaise)
			{
				this.currentMoneyBet += 40;
				//Move playerMove  = new Move("raise",40 + toRaise);
				raiseMove.setBet(40+toRaise);
				break one;
			}
			else
			{
				System.out.format("Cannot raise by %d , you only have %d" ,40 + toRaise, this.totalMoney - this.currentMoneyBet); 
				continue;
			}
			
		}
		case 3: 
		{
			System.out.println("Raising by 100");
			if(this.totalMoney > this.currentMoneyBet+ 100 + toRaise)
			{
				this.currentMoneyBet += 100;
				//Move playerMove  = new Move("raise",100);
				raiseMove.setBet(100+toRaise);
				break one;
			}
			else
			{
				System.out.format("Cannot raise by %d , you only have %d" ,100 + toRaise, this.totalMoney - this.currentMoneyBet); 
				continue;
			}
		}
		case 4: 
		{
			System.out.println("Enter the number you want to raise by");
			int n = in.nextInt();
			if(this.totalMoney > this.currentMoneyBet+ n + toRaise)
			{
				this.currentMoneyBet += n;
				raiseMove.setBet(n+toRaise);
				break one;
			}
			else
			{
				System.out.format("Cannot raise by %d , you only have %d" ,n +toRaise, this.totalMoney - this.currentMoneyBet); 
				continue;
			}	
		}
		case 5:
		{
			this.makeMove();
			break one;
		}
		}
		}
		return raiseMove;
	}
	
	
	public Move Fold()
	{
		System.out.println("You have selected to fold");
		this.totalMoney = this.totalMoney - this.currentMoneyBet;
		this.currentPlayerStatus = PlayerMove.Fold;
		Move playerMove = new Move(PlayerMove.Fold,0);
		return playerMove;
	}
	
	public void playerLosesMoney(int n)
	{
		this.totalMoney -= n;
	}
	
	public void playerLosesHand()
	{
		this.totalMoney -= this.currentMoneyBet;
	}
	
	public void setCurrentGame(Game g)
	{
		this.currentGame = g;
	}
	
	public Game getCurrentGame()
	{
		return this.currentGame;
	}
	
	public void calculateCurrentHandValue()
	{
		if(this.currentGame.getClass().equals(BlackJackGame.class))
			this.currentHandValue = (int)HandValue.getHandValue(this.currentCards,null, this.currentGame);
		else
			this.currentHandValue = (PokerHands)HandValue.getHandValue(this.currentCards, this.currentGame.getFlop(), this.currentGame);
	}
	
	public void resetCard()
	{
		this.currentCards = new ArrayList<Card>();
	}
	
	
	public Move forceRaise(int n)
	{
		Boolean call  = true;
		one: while(call)
		{
		System.out.format("The other players have raised %d, \n press 1 to raise equal  \n 2 to fold \n 3 to raise more \n 4 to go all in",n);
		int userInput = in.nextInt();
		Move playerMove = new Move(PlayerMove.Check,0);
		if(userInput == 1 && n<this.totalMoney)
		{
			System.out.format("Raising by %d",n);
			playerMove = new Move(PlayerMove.Call,n);
			this.currentPlayerStatus = PlayerMove.Fold;
			this.currentMoneyBet += n;
			this.totalMoney -= n;
			call = false;
			return playerMove;
		}
		else if(userInput == 2)
		{
			playerMove.setBet(0);
			playerMove.setMove(PlayerMove.Fold);
			this.currentPlayerStatus = PlayerMove.Fold;
			call = false;
			return this.Fold();
			
		}
		else if(userInput == 3 && n < this.totalMoney )
		{
			return this.raiseMove(n);
		}
		else if(userInput == 4 && n < this.totalMoney)
		{
			this.currentMoneyBet += n;
			this.totalMoney -= n;
			call  = false;
			return this.goAllIn();
		}
		else
		{
			System.out.println("You do not have enough money to match the bid");
			continue one;
		}
		}
		return null;
	}
	
	public Move goAllIn()
	{
		Move playerMove = new Move(PlayerMove.AllIn,this.totalMoney - this.currentMoneyBet);
		this.currentPlayerStatus = PlayerMove.AllIn;
		return playerMove;
	}
	
	public int getCurrentBet()
	{
		return this.currentMoneyBet;
	}
	
	public void reset()
	{
		this.currentCards.clear();
		this.currentHandValue = 0;
		this.currentMoneyBet = 0;
	}
	
	public void Wins(int n)
	{
		this.totalMoney = n;
		this.moneyWon += n;
	}
	
	public void Lose(int n)
	{
		this.totalMoney -= n;
		this.moneyLost += n;
	}
	
	public void setCurrentStatus(PlayerMove move)
	{
		this.currentPlayerStatus = move;
	}
	
	public PlayerMove getCurrentStatus()
	{
		return this.currentPlayerStatus;
	}
}
