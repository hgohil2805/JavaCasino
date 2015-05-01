package edu.nyu.cs6015.casino;

public class TestClass 
{

		public static void main(String args[])
		{
			
			// Random test
			/*Deck newDeck = new Deck();
			newDeck.printDeck();
			System.out.println("Testing case");
			newDeck.shuffleDeck();
			newDeck.printDeck();
			System.out.println("After shuffle");
			System.out.println(newDeck.getTop());
			System.out.println(newDeck.getTop());
			System.out.println("Sorting deck");
			newDeck.sortDeck();
			newDeck.printDeck();
			Player p = new Player();
			p.setTotalMoney(1000);
			p.makeMove();
			*/
			
			//BlackJack Test 
			/*Game g = new BlackJackGame();
			Player one = new Player("ice",23,1000,g);
			Player two = new Player("iceman",23,5000,g);
			Player three = new Player("abc",21,100,g);
			g.addPlayer(one);
			g.addPlayer(two);
			g.addPlayer(three);
			g.startGame();*/
			
			
			//Poker test
			
			Game g = new PokerGame();
			Player one = new Player("ice",23,1000,g);
			Player two = new Player("iceman",23,5000,g);
			Player three = new Player("abc",21,100,g);
			g.addPlayer(one);
			g.addPlayer(two);
			g.addPlayer(three);
			g.startGame();
		}
}
