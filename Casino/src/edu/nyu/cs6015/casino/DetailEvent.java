package edu.nyu.cs6015.casino;

import java.util.EventObject;

public class DetailEvent extends EventObject 
{
	private String text;
	public DetailEvent(Object source, String text)
	{
		
		super(source);
		
		this.text = text;
	}
	
	public String getText()
	{
		return this.text;
	}
}
