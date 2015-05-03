package edu.nyu.cs6015.casino.AppicationScreens;

import java.awt.EventQueue;

import edu.nyu.cs6015.casino.*;

public class App {

	public App() {
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				new WelcomeScreen();
			}
		});
	}
}
