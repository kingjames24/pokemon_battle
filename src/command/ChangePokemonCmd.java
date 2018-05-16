package command;

import players.Players;
/**
 * 
 * class that represents the concrete command  
 * of changing a pokemon
 */
public class ChangePokemonCmd implements Command {

	private int pokemon; 
	private Players player; 
	/**
	 * constructor that initializes the class' instance variables
	 * @param x an int type, that represents a position in an object array 
	 * @param player a reference to a player object
	 */
	public ChangePokemonCmd(int x, Players player)
	{
		this.pokemon=x; 
		this.player=player; 
	}
	/**
	 * concrete implementation of the command interface, 
	 * where calls to various receivers are performed to 
	 * change a player's current pokemon
	 */
	@Override
	public void execute() {
		player.change(pokemon); 

	}

}
