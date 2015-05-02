package edu.nyu.cs6015.casino;

import java.util.EventListener;

public interface DetailListener extends EventListener 
{
	public void detailEventOccured(DetailEvent event);
}
