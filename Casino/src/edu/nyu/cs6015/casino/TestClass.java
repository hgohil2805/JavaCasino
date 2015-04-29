package edu.nyu.cs6015.casino;

public class TestClass 
{

		public static void main(String args[])
		{
			Deck newDeck = new Deck();
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
		}
}
