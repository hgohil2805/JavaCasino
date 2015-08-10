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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import edu.nyu.cs6015.casino.Card;
import edu.nyu.cs6015.casino.Game;
import edu.nyu.cs6015.casino.Player;
import edu.nyu.cs6015.casino.PlayerMove;
import edu.nyu.cs6015.casino.PokerGame;





public class PokerHomePage extends JFrame
{
	ArrayList<String> holder = new ArrayList<String>();
	PokerGame currentGameInstance;
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
	Font font;
	int count = 0;
	public PokerHomePage(final PokerGame g)
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
		PokerGame g = new PokerGame();
		Player one = new Player("PlayerOne",23,1000,g);
		Player two = new Player("PlayerTwo",23,1000,g);
		Player three = new Player("PlayerThree",21,1000,g);
		g.addPlayer(one);
		g.addPlayer(two);
		g.addPlayer(three);
		currentGameInstance = (PokerGame) g;
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
	    callButton = new JButton("<html><body><b><font size=5 face=\"Arial\">Call</b></body></html>");
	    
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
	    raiseButton  = new JButton("<html><body><b><font size=5 face=\"Arial\">Raise</b></body></html>");
	    raiseButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				raiseMove();
			}
	    	
	    });
	    
	    panelOne.add(callButton);
	    
	    panelOne.add(raiseButton);
	    foldButton = new JButton("<html><body><b><font size=5 face=\"Arial\">Fold</b></body></html>");
	    foldButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				foldMove();
			}
	    	
	    });
	    //foldButton.setPreferredSize(new Dimension(150,125));
	    checkButton = new JButton("<html><body><b><font size=5 face=\"Arial\">Check</b></body></html>");
	    checkButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				checkMove();
				
			}
	    });
	    
	    
	    //Right panel two
	    
	    
	    JPanel panelTwo = new JPanel(new GridLayout());
	    panelTwo.add(foldButton);
	    panelTwo.add(checkButton);
	   
	    
	    
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
		Player winner;
		Player p = currentGameInstance.getNextPlayer(currentPlayerChance,move);
		if(currentGameInstance.getPlayers().size() == 1)
		{
			winner = p;
		}
		currentPlayer = p;
		if(p == null && count == 0)
		{
			p = currentGameInstance.getFirstPlayer();
			currentGameInstance.addNToFlop(3);
			count ++;
		}
		else if(p == null && count == 1)
		{
			p = currentGameInstance.getFirstPlayer();
			currentGameInstance.addNToFlop(1);
			count ++;
		}
		else if(p == null && count == 2)
		{
			p = currentGameInstance.getFirstPlayer();
			currentGameInstance.addNToFlop(1);
			count++;
		}
		else if(p == null && count == 3)
		{
			winner = currentGameInstance.getWinners();
			winnerFound = true;
			text.setText("");
			text.setText("Winner is: \n" + winner.getName());
		}
		if(p != null && !winnerFound)
		{		
		currentPlayer = p;
		text.setText("");
		text.append("\n "+p.getName());
		text.append("\nYour current list of cards are ");
		for(Card c : p.getCards())
		{
			text.append(c +"\t");
		}
		if(currentGameInstance.getFlop().size() > 1)
		{
			text.append("Cards on the flop");
		for(Card c : currentGameInstance.getFlop())
		{
			text.append("\n" +c);
		}
		}
		if(currentPlayer.getCurrentRoundBet() < currentGameInstance.getCurrentPotRaise() && currentPlayer.hasMoney())
		{
			int moneyLeft = currentPlayer.getTotalMoney() - currentPlayer.getCurrentRoundBet();
			System.out.println("The money left for the player ," +moneyLeft);
			System.out.println("Current players current round bet" +currentPlayer.getCurrentRoundBet() );
			System.out.println("Current round raise" +currentGameInstance.getCurrentPotRaise());
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
	
	public void checkMove()
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
		for(Card c :currentPlayer.getCards())
		{
			text.append(c + "\t");
		}
		text.append("\n");
		callButton.setText("<html><body><b><font size=5 face=\"Arial\">AllIn</b></body></html>");
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
}
