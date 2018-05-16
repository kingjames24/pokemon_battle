package command;

import players.Players;
/**
 * 
 * class that represents the concrete command  
 * of choosing a pokemon's current attack
 */
public class SelectAttacksCmd implements Command {

	private String attack; 
	private Players player; 
	/**
	 * constructor that initializes the class' instance variables
	 * @param a an object of type string that represents the type of attack 
	 * the player wants his/her pokemon to be loaded with
	 * @param player a reference to a player object
	 */
	public SelectAttacksCmd(String a, Players player)
	{
		attack = a; 
		this.player=player; 
	}
	
	
	/**
	 * concrete implementation of the command interface, 
	 * where calls to various receivers are performed to 
	 * load the specific attack for a pokemon
	 */
	@Override
	public void execute() 
	{
		player.select(attack);
	}

}
