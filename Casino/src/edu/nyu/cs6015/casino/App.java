package edu.nyu.cs6015.casino;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App 
{
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
			JFrame frame = new MainFrame("Casino Bitches!!");
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(900,600);
			}
		});
		
	}
}
