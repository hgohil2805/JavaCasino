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
	int currentHandValue;
	ArrayList<Card> currentCards = new ArrayList<Card>();
	Game currentGame;
	static Scanner in = new Scanner(System.in);
	public Player(String name,int age,int money)
	{
		this.name = name;
		this.age = age;
		this.totalMoney = money;
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
		this.currentCards.add(c);
	}
	
	public ArrayList<Card> getCards()
	{
		return this.currentCards;
	}
	
	public Move makeMove()
	{
		System.out.println("The current value of the card is:"+this.currentHandValue);
		System.out.println("#################################");
		System.out.println("The available options for you are: ");
		System.out.println("\n 1 to check \n 2 to raise \n 3 to fold ");
		int input;
		input = in.nextInt();
		Move currentMove = new Move (PlayerMove.Check,0);
		switch(input)
		{
		case 1: 
		{
			System.out.println("You have selected to check");
			currentMove.setMove(PlayerMove.Check);
			currentMove.setBet(0);
			break;
		}
		case 2: 
		{
			System.out.println("You have selected to raise");
			if(this.totalMoney > this.currentMoneyBet + 20)
			{
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
			this.Fold();
			break;
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
		System.out.println("checked" + checkMove);
	}
	
	public Move raiseMove(int toRaise)
	{
		Move raiseMove = new Move(PlayerMove.Raise,0);
		one:while(true)
		{
		System.out.println("\n Press \n 1 to raise 20 \n 2 to raise 40 \n 3 to raise 100 \n 4 to enter your own number \n 5 to cancel raise");
		int choice  ;
		choice = in.nextInt();
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
				//Move playerMove  = new Move("raise",n+toRaise);
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
	
	
	public void Fold()
	{
		System.out.println("You have selected to fold");
		this.totalMoney = this.totalMoney - this.currentMoneyBet;
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
		this.currentHandValue = HandValue.getHandValue(this.currentCards,null, this.currentGame);
	}
	
	public void resetCard()
	{
		this.currentCards = new ArrayList<Card>();
	}
	
	
	public Move forceRaise(int n)
	{
		one: while(true)
		{
		System.out.format("The other players have raised %d, \n press 1 to raise equal  \n 2 to fold \n 3 to raise more \n 4 to go all in",n);
		int userInput = in.nextInt();
		Move playerMove = new Move(PlayerMove.Check,0);
		if(userInput == 1 && n<this.totalMoney)
		{
			System.out.format("Raising by %d",n);
			playerMove = new Move(PlayerMove.Call,n);
			this.currentMoneyBet += n;
			this.totalMoney -= n;
		}
		else if(userInput == 2)
		{
			playerMove.setBet(0);
			playerMove.setMove(PlayerMove.Fold);
			this.Fold();
		}
		else if(userInput == 3 && n < this.totalMoney )
		{
			return this.raiseMove(n);
		}
		else if(userInput == 4 && n < this.totalMoney)
		{
			this.currentMoneyBet += n;
			this.totalMoney -= n;
			return this.goAllIn();
		}
		else
		{
			System.out.println("You do not have enough money to match the bid");
			continue one;
		}
		}
	}
	
	public Move goAllIn()
	{
		Move playerMove = new Move(PlayerMove.AllIn,this.totalMoney - this.currentMoneyBet);
		return playerMove;
	}
	
	public int getCurrentBet()
	{
		return this.currentMoneyBet;
	}
}
