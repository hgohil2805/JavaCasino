package edu.nyu.cs6015.casino;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

public class DetailsPanel extends JPanel 
{
	
	private EventListenerList listenerList = new EventListenerList();
	private static final long serialVersionUID = 1L;

	public DetailsPanel()
	{
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Personal Details"));
		
		JLabel nameLabel = new JLabel("Name : ");
		JLabel occLabel = new JLabel("Occupation : ");
		final JTextField nameField = new JTextField(10);
		final JTextField occField = new JTextField(10);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nameField.getText();
				String occupation = occField.getText();
				
				String text = name + " : " + occupation;
				fireDetailEvent(new DetailEvent(this,text));
			}
		});
		setLayout(new GridBagLayout());
		GridBagConstraints  gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_END;
		//First column ///////
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		
		add(nameLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		this.add(occLabel,gc);
		
		///Second column//
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		
		this.add(nameField,gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		
		this.add(occField,gc);
		
		
		//final row
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		this.add(addButton,gc);
	}
	
	
	public void fireDetailEvent(DetailEvent event)
	{
		Object[] listeners = listenerList.getListenerList();
		for(int i=0;i<listeners.length;i += 2)
		{
			if(listeners[i] == DetailListener.class)
			{
				((DetailListener) listeners[i+1]).detailEventOccured(event);
			}
		}
	}
	public void addDetailListener(DetailListener listener)
	{
		listenerList.add(DetailListener.class, listener);
	}
	
	public void removeDetailListener(DetailListener detailListener)
	{
		
	}
	
}
