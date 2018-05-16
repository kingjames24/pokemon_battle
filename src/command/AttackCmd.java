package command;

import players.Players;
/**
 * 
 * class that represents the concrete command  
 * of attacking other pokemon during battle 
 */
public class AttackCmd implements Command {

	
	private Players player;
	/**
	 * constructor that initializes the class' instance variable
	 * @param player a reference to a player object
	 */
	public AttackCmd (Players player)
	{
		this.player=player; 
	}
	
	/**
	 * concrete implementation of the command interface, 
	 * where calls to various receivers are performed to 
	 * attack pokemon during battle
	 */
	@Override
	public void execute() 
	{
		this.player.attackMethod();

	}

}
