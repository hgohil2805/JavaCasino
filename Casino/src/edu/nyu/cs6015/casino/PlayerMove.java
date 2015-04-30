package edu.nyu.cs6015.casino;

public enum PlayerMove 
{
	Raise("Raise"), Check("Check"), Fold("Fold"), Call("Call"), AllIn("AllIn"), Bust("Bust");
	
	private String name;
	
	private PlayerMove(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
