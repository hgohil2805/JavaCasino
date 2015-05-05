package edu.nyu.cs6015.casino.AppicationScreens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.nyu.cs6015.casino.BlackJackGame;
import edu.nyu.cs6015.casino.Game;
import edu.nyu.cs6015.casino.PokerGame;
import edu.nyu.cs6015.casino.Roulette;



public class WelcomeScreen extends JFrame {

	private static final long serialVersionUID = -8315658054698527320L;
	String[] algoList = new String[] { "Poker", "BlackJack", "Roulette","Add Player" };
	public static Color color = new Color(44, 144, 151);

	public WelcomeScreen() {
		setSize(350, 175);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)(screenSize.getWidth()/2.5), (int) (screenSize.getHeight()/3));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Casino Entrance!");
		setLayout(new GridLayout(2, 1, 0, 0));
		add(createHeading());
		add(createAlgorithmsList());
		setVisible(true);
		
	}

	public JPanel createAlgorithmsList() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 0, 1));
		JComboBox<String> j = new JComboBox<String>(algoList);
		panel.add(j);
		panel.add(createContinueButton(j));
		return panel;
	}

	public JButton createContinueButton(final JComboBox<String> list) {
		JButton b = new JButton("<html><body><b><font size=5 face=\"Arial\">Continue</b></body></html>");
		b.setForeground(color);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game  g = null;
				switch ((String) list.getSelectedItem()) {
				case "Poker":
					g = new PokerGame();
					System.out.println("Starting new Poker game");
					new PokerHomePage((PokerGame)g);
					break;
					
				case "BlackJack":
					g = new BlackJackGame();
					new BlackJackScreen((BlackJackGame) g);
					System.out.println("Starting new BlackJack");
					break;
				case "Roulette":
					g = new Roulette();
					System.out.println("Starting new Roulette");
					new RouletteScreen((Roulette) g);
					break;
				}
			}
		});
		return b;
	}

	public JPanel createHeading() {
		JPanel p = new JPanel(new GridLayout(1, 1));
		JLabel label = new JLabel("<html><body><font size=6 face=\"Arial\">Select A Game From The Menu Below</body></html>", 
				SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		p.setBackground(color);
		p.add(label);
		return p;
	}
}
