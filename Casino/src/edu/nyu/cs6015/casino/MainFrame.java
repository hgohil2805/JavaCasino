package edu.nyu.cs6015.casino;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame 
{
	private DetailsPanel detailspanel;
	public MainFrame(String title)
	{
		super(title);
		setLayout(new BorderLayout());
		
		final JTextArea textArea = new JTextArea();
		
		JButton button  = new JButton("Click me!");
		
		Container c = getContentPane();
		
		c.add(textArea, BorderLayout.CENTER);
		
		c.add(button, BorderLayout.SOUTH);
		detailspanel = new DetailsPanel();
		c.add(detailspanel,BorderLayout.WEST);
		button.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				textArea.append("abc");
				
			}
			
		});
		
		detailspanel.addDetailListener(new DetailListener()
		{
			public void detailEventOccured(DetailEvent event)
			{
				String text = event.getText();
				textArea.append(text);
			}
				
		});
		
	}
	}
	
	//set layout manager
	
	
	//create swing components
	
	//add components to content pane
