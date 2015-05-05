package edu.nyu.cs6015.casino.AppicationScreens;

import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import edu.nyu.cs6015.casino.BlackJackGame;
import edu.nyu.cs6015.casino.Player;
import edu.nyu.cs6015.casino.Roulette;

public class RouletteScreen extends JFrame 
{
	
	ArrayList<String> holder = new ArrayList<String>();
	Roulette currentGameInstance;
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
	JTextArea numberText;
	int count = 0;
	public RouletteScreen(final Roulette g)
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
	    
	    //top text content field
	    text  = new JTextArea();
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
	    gc.gridx = 0;
	    gc.gridy = 3;
	    //gc.weightx = 0.1;
	    gc.weighty = 0.01;
	    numberText = new JTextArea();
	    numberText.setText("Enter number here");
	    add(numberText,gc);
	    raiseAmount.setLabelTable(raiseAmount.createStandardLabels(10));
	    gc.gridx = 0;
	    gc.gridy = 4;
	    add(raiseAmount,gc);
	    setVisible(true);
	    //startGame();
	}
	public void addButtons()
	{
		//buttons panel 
	    buttonPanel = new JPanel();
	    //Left panel one
	    panelOne = new JPanel(new GridLayout(2,1)); 
	    callButton = new JButton("Place bet on Number");
	    
	    callButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				numberBet();
			}
	    	
	    });
	    
	    //callButton.setPreferredSize(new Dimension(150,125));
	    raiseButton  = new JButton("Place Bet On Odd Number");
	    raiseButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				oddBet();
			}
	    	
	    });
	    
	    panelOne.add(callButton);
	    panelOne.add(raiseButton);
	    foldButton = new JButton("Place Bet On Even Number");
	    foldButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				evenBet();
			}
	    	
	    });
	    //foldButton.setPreferredSize(new Dimension(150,125));
	    stayButton = new JButton("Place Bet on Red Color");
	    stayButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				redColorBet();
				
			}
	    });
	    
	    hitButton = new JButton("Place Bet On Black Color");
	    hitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				blackColorBet();
				
			}
	    	
	    });
	    
	    //Right panel two
	    
	    
	    JPanel panelTwo = new JPanel(new GridLayout(3,1));
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
	
	
	public void numberBet()
	{
		currentPlayer.addCurrentGameBet(raiseAmount.getValue());
		currentGameInstance.addNumberBet(currentPlayer, Integer.parseInt(numberText.getText()));
	}
	
	public void oddBet()
	{
		currentPlayer.addCurrentGameBet(raiseAmount.getValue());
		currentGameInstance.addoddBet(currentPlayer);
	}
	
	public void evenBet()
	{
		currentPlayer.addCurrentGameBet(raiseAmount.getValue());
		currentGameInstance.addEvenBet(currentPlayer);
	}
	
	public void redColorBet()
	{
		currentPlayer.addCurrentGameBet(raiseAmount.getValue());
		currentGameInstance.addRedColorBet(currentPlayer);
	}
	
	public void blackColorBet()
	{
		currentPlayer.addCurrentGameBet(raiseAmount.getValue());
		currentGameInstance.addBlackColorBet(currentPlayer);
	}
}
