package edu.nyu.cs6015.casino.AppicationScreens;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.nyu.cs6015.casino.Game;





public class GameHomePage extends JFrame
{
	public GameHomePage(final Game g)
	{
		System.out.println("Dafuq?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setSize(1000,700);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);	
	    JButton button = new JButton("<html><body><b><font size=5 face=\"Arial\">OK</b></body></html>");
	    setLayout(new GridBagLayout());
	    GridBagConstraints  gc = new GridBagConstraints();
	   // gc.anchor = GridBagConstraints.LINE_END;
	    
	    //top content
	    JTextArea text = new JTextArea();
	    text.setText("Hello");
	    text.setMargin(new Insets(10, 10, 10, 10));
	    gc.fill = GridBagConstraints.BOTH;
	    gc.weighty = 0.2;
	    gc.gridy = 0;
	    add(text,gc);
	    setVisible(true);
	    
	    
	    //bottom text are
	  
	   
	    /*gc.fill = GridBagConstraints.BOTH;
	    gc.weightx = 1;
		gc.insets = new Insets(10, 10, 10, 10);
	    gc.weighty = 0.1;
	    gc.gridx = 0;
	    gc.gridy = 1;
	    add(button,gc);
	    */
	    
	    //buttons panel 
	    JPanel buttonPanel = new JPanel();
	    //Left panel one
	    JPanel panelOne = new JPanel(new GridLayout(2,1)); 
	    JButton callButton = new JButton("Call");
	    
	    callButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.format("Starting %s", g.getName());
				System.out.println();
				System.out.println("Call button called");
				
			}
	    	
	    });
	    
	    
	    callButton.setPreferredSize(new Dimension(150,125));
	    JButton raiseButton  = new JButton("Raise");
	    raiseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.format("Starting %s", g.getName());
				System.out.println();
				System.out.println("Raise button called");
			}
	    	
	    });
	    panelOne.add(callButton);
	    panelOne.add(new JLabel(""));
	    panelOne.add(new JLabel(""));
	    panelOne.add(new JLabel(""));
	    panelOne.add(new JLabel(""));
	    
	    panelOne.add(raiseButton);
	    panelOne.add(new JLabel(""));
	    panelOne.add(new JLabel(""));
	    panelOne.add(new JLabel(""));
	    panelOne.add(new JLabel(""));
	    JButton foldButton = new JButton("Fold");
	    foldButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.format("Starting %s", g.getName());
				System.out.println();
				System.out.println("Fold button called");
			}
	    	
	    });
	    foldButton.setPreferredSize(new Dimension(150,125));
	    JButton checkButton = new JButton("check");
	    
	    checkButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.format("Starting %s", g.getName());
				System.out.println();
				System.out.println("Check button called");
				
			}
	    	
	    });
	    
	    
	    //Right panel two
	    
	    
	    JPanel panelTwo = new JPanel(new GridLayout(2,1));
	    panelTwo.add(foldButton);
	    panelTwo.add(checkButton);
	    /*
	    buttonPanel.add(callButton,BorderLayout.NORTH);
	    buttonPanel.add(raiseButton);
	    buttonPanel.add(foldButton);
	    buttonPanel.add(checkButton);
	    
	    */
	    
	    
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
	
}
