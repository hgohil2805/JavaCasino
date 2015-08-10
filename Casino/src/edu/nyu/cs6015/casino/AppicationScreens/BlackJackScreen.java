package edu.nyu.cs6015.casino.AppicationScreens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import edu.nyu.cs6015.casino.BlackJackGame;
import edu.nyu.cs6015.casino.Card;
import edu.nyu.cs6015.casino.Game;
import edu.nyu.cs6015.casino.Player;
import edu.nyu.cs6015.casino.PlayerMove;
import edu.nyu.cs6015.casino.PokerGame;





public class BlackJackScreen extends JFrame
{
	ArrayList<String> holder = new ArrayList<String>();
	BlackJackGame currentGameInstance;
	JTextArea text;
	Player currentPlayer;
	GridBagConstraints  gc;
	JScrollPane scroll;
	JSlider raiseAmount;
	JPanel buttonPanel;
	JPanel panelOne;
	JButton callButton;
	JButton raiseButton ;
	JButton foldButton;
	JButton checkButton;
	JButton stayButton;
	JButton hitButton;
	Font font;
	int count = 0;
	public BlackJackScreen(final BlackJackGame g)
	{
		currentGameInstance = g;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setSize(1000,700);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);	
	    JButton button = new JButton("<html><body><b><font size=5 face=\"Arial\">OK</b></body></html>");
	    setLayout(new GridBagLayout());
	     gc = new GridBagConstraints();
	   // gc.anchor = GridBagConstraints.LINE_END;
	     font = new Font("Verdana", Font.BOLD, 12);
	    //top text content field
	    text  = new JTextArea();
	    text.setForeground(Color.BLUE);
	    text.setFont(font);
	    text.setText("Hello");
	    
	    scroll = new JScrollPane(text);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    text.setMargin(new Insets(10, 10, 10, 10));
	    gc.fill = GridBagConstraints.BOTH;
	    gc.weighty = 0.2;
	    gc.gridy = 1;
	    add(scroll,gc);
	    addButtons();
		raiseAmount = new JSlider(JSlider.HORIZONTAL,0,100,50);
		raiseAmount.setMinorTickSpacing(2);
	    raiseAmount.setMajorTickSpacing(10);
	    raiseAmount.setPaintTicks(true);
	    raiseAmount.setPaintLabels(true);

	    // We'll just use the standard numeric labels for now...
	    raiseAmount.setLabelTable(raiseAmount.createStandardLabels(10));
	    gc.gridx = 0;
	    gc.gridy = 3;
	    add(raiseAmount,gc);
	    setVisible(true);
	    startGame();
	}
	
	
	public void startGame()
	{	
		BlackJackGame g = new BlackJackGame();
		Player one = new Player("PlayerOne",23,1000,g);
		Player two = new Player("PlayerTwo",23,1000,g);
		Player three = new Player("PlayerThree",21,1000,g);
		g.addPlayer(one);
		g.addPlayer(two);
		g.addPlayer(three);
		currentGameInstance = (BlackJackGame) g;
		ArrayList<Player> playerCards = currentGameInstance.getFirstRound();
		this.currentPlayer = playerCards.get(0);
		this.text.disable();
		this.text.repaint();
		firstScreen();
	}
	
	
	
	
	public void addButtons()
	{
		//buttons panel 
	    buttonPanel = new JPanel();
	    //Left panel one
	    panelOne = new JPanel(new GridLayout()); 
	    callButton = new JButton("Call");
	    
	    callButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(callButton.getText() == "AllIn")
					{
					allInMove();
					
					}
				else 
				{
					callMove();
				}
			}
	    	
	    });
	    
	    //callButton.setPreferredSize(new Dimension(150,125));
	    raiseButton  = new JButton("Raise");
	    raiseButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				raiseMove();
			}
	    	
	    });
	    
	    panelOne.add(callButton);
	    panelOne.add(raiseButton);
	    foldButton = new JButton("Fold");
	    foldButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				foldMove();
			}
	    	
	    });
	    //foldButton.setPreferredSize(new Dimension(150,125));
	    stayButton = new JButton("Stay");
	    stayButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StayMove();
				
			}
	    });
	    
	    hitButton = new JButton("Hit");
	    hitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				hitMove();
				
			}
	    	
	    });
	    
	    //Right panel two
	    
	    
	    JPanel panelTwo = new JPanel(new GridLayout());
	    panelTwo.add(foldButton);
	    panelTwo.add(stayButton);
	    panelTwo.add(hitButton);
	    
	    
	    buttonPanel.add(panelOne);
	    buttonPanel.add(panelTwo);
	    
	    //Adding to grid layout
	    gc.fill = GridBagConstraints.BOTH;
	    gc.weightx = 1;
		gc.insets = new Insets(10, 10, 10, 10);
	    gc.weighty = 0.1;
	    gc.gridx = 0;
	    gc.gridy = 2;
	    add(buttonPanel,gc);
	}
	
	
	public void getNextScreen(Player currentPlayerChance,PlayerMove move)
	{
		boolean winnerFound = false;
		Player p = currentGameInstance.getNextPlayer(currentPlayerChance,move);
		currentPlayer = p;
		
		if(p == null && count == 0)
		{
			Player winner = currentGameInstance.getWinner();
			winnerFound = true;
			text.setText("");
			text.setText("Winner is: \n" + winner.getName());
			winner.Wins(currentGameInstance.getPotMoney());
		}
		
		if(p != null && !winnerFound)
		{		
		currentPlayer = p;
		text.setText("");
		text.append("\n "+p.getName());
		
		if(currentGameInstance.getPlayerValue(p) > 0 )
		{
			text.append("\nYour current value of cards is "+currentGameInstance.getPlayerValue(p));
		}
		else 
		{
			int value = 0;
			for(Card c : p.getCards())
		{
			if(c.getValue() == 1)
				{
				Object[] options = {"Select Ace as 1",
                "Select Ace as 11"};
				int n = JOptionPane.showOptionDialog(this,
						"You've got an ace, Select the value you want",
						"Select Value of Ace",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     //do not use a custom Icon
						options,  //the titles of buttons
						options[0]); //default button title
				if(n == 0)
				{
					text.append("\n Selecting value of card as 1");
					text.append(c + "(1) \t");
					value += 1;
				}
				else if(n == 1)
				{
					text.append("\n selecting value of card as 11");
					text.append(c + "(11) \t");
					value += 11;
				}
				}
			else
			{
				value += c.getValue();
				text.append(c + "\t");
			}
		}
			currentGameInstance.setPlayerValue(p, value);
			}
		if(currentPlayer.getCurrentRoundBet() < currentGameInstance.getCurrentPotRaise() && currentPlayer.hasMoney())
		{
			int moneyLeft = currentPlayer.getTotalMoney() - currentPlayer.getCurrentRoundBet();
			callButton.setVisible(true);
			int amount = currentGameInstance.getCurrentPotRaise() - currentPlayer.getCurrentRoundBet() ;
			callButton.setText("Call ("+amount +")");
		}
		else if(currentPlayer.getCurrentBet() == currentGameInstance.getCurrentPotRaise())
		{
			callButton.setText("All IN");
		}
		}
		
	}
	
	
	public void raiseMove()
	{
		currentPlayer.addCurrentRoundBet(raiseAmount.getValue());
		currentPlayer.addCurrentGameBet(raiseAmount.getValue());
		currentGameInstance.addCurrentPotRaise(raiseAmount.getValue());
		currentGameInstance.addPotMoney(raiseAmount.getValue());
		getNextScreen(currentPlayer,PlayerMove.Raise);
	}
	
	public void callMove()
	{
		int amount = currentGameInstance.getCurrentPotRaise() - currentPlayer.getCurrentRoundBet();
		currentPlayer.clearCurrentRoundBet();
		currentPlayer.addCurrentGameBet(amount);
		//currentGameInstance.addCurrentPotRaise(amount);
		currentGameInstance.addPotMoney(amount);
		getNextScreen(currentPlayer,PlayerMove.Call);
	}
	
	public void StayMove()
	{
		getNextScreen(currentPlayer,PlayerMove.Check);	
	}
	
	public void foldMove()
	{
		currentPlayer.Fold();
		getNextScreen(currentPlayer,PlayerMove.Fold);
		currentGameInstance.removePlayer(currentPlayer);
	}
	
	public void firstScreen()
	{
		text.setText("");
		text.append("\n Hello " +currentPlayer.getName());
		text.append("\n Your current list of cards are \n ");
		int value = 0;
		for(Card c :currentPlayer.getCards())
		{
			if(c.getValue() == 1)
			{
				Object[] options = {"Select Ace as 1",
            "Select Ace as 11"};
			int n = JOptionPane.showOptionDialog(this,
					"You've got an ace, Select the value you want",
					"Select Value of Ace",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
			if(n == 0)
			{
				value += 1;
				text.append(c +"(1) \t");
			}
			else
			{
				value += 11;
				text.append(c +"(11) \t");
			}
			}
			else 
			{
				value += c.getValue();
				text.append("\n" +c);
			}	
		}
		currentGameInstance.updatePlayerValue(currentPlayer, value);
		callButton.setText("AllIn");
	}
	
	public void allInMove()
	{
		int raiseAmount = currentPlayer.getTotalMoney() - currentPlayer.getCurrentRoundBet();
		System.out.println("All In Amount is " +raiseAmount);
		currentPlayer.addCurrentRoundBet(raiseAmount);
		currentPlayer.addCurrentGameBet(raiseAmount);
		currentPlayer.setCurrentStatus(PlayerMove.AllIn);
		int moneleft = currentPlayer.getTotalMoney() - currentPlayer.getCurrentRoundBet();
		System.out.println("The amount of money remaining for the player is"+moneleft );
		currentGameInstance.addCurrentPotRaise(raiseAmount);
		currentGameInstance.addPotMoney(raiseAmount);
		getNextScreen(currentPlayer,PlayerMove.AllIn);
	}
	
	public void hitMove()
	{
		Card c = currentGameInstance.getCurrentDeck().getTop();
		currentGameInstance.updatePlayerValue(currentPlayer, c.getValue());
		int value = currentGameInstance.getPlayerValue(currentPlayer);
		System.out.println(value);
		if(value == 21)
		{
			text.append("\n You have Won");
		}
		else if(value > 21)
		{
			text.append("\n You have lost!!");
		}
		else
		{
		currentPlayer.addCard(c);
		text.append("\n" +c);
		firstScreen();
		}
	}
}
