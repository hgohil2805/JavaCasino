package edu.nyu.cs6015.casino.AppicationScreens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public abstract class Algorithm {
	private String algoName;
	private String source;
	private String body;
	
	public static String encrypt = "Encrypt";
	public static String decrypt = "Decrypt";
	
	
	public Algorithm(String name, String body, String source){
		this.algoName = name;
		this.source = source;
		this.body = body;
	}
	
	public JPanel getAlgoPane() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weighty = 0.10;
		final JLabel link = new JLabel("<html><body><h1><font face=\"Arial\"><a>"
				+ this.algoName + "</a></font></h1></body></html>", SwingConstants.CENTER);
		link.setForeground(WelcomeScreen.color);
		link.setBorder(BorderFactory.createLineBorder(Color.black));
		link.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				link.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				link.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI(source));
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		pane.add(link, c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 10, 10);
		c.ipadx = 10;
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridwidth = 1;

		JTextPane editor = new JTextPane();
		editor.setMargin(new Insets(0, 10, 0, 0));
		editor.setContentType("text/html");
		editor.setText(body);
		editor.setEditable(false);
		pane.add(new JScrollPane(editor), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 10, 10);
		c.ipadx = 10;
		c.weightx = 0.5;
		c.weighty = 0.1;
		c.gridwidth = 1;
		JButton button = new JButton("<html><body><b><font size=5 face=\"Arial\">"
				+ "Start Game</b></body></html>");
		button.setForeground(WelcomeScreen.color);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		pane.add(button, c);
		return pane;
	}
	
	//public abstract void executeAlgorithm(AlgorithmDataHolder input) throws Exception;
	
	public String getAlgoName(){
		return algoName;
	}
}
