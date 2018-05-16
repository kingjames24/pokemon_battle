package command;

import players.Players;
/**
 * 
 * class that represents the concrete command  
 * of choosing  pokemon during the beginning of the 
 * game (ie., during the pokemon selection stage) 
 */
public class ChoosePokemonCmd implements Command {

	private String pokemon; 
	private Players player; 
	/**
	 * constructor that initializes the class' instance variables
	 * @param pokemon a object of type string that represents a type of pokemon 
	 * @param player a reference to a player object
	 */
	public ChoosePokemonCmd(String pokemon, Players player)
	{
		this.pokemon=pokemon; 
		this.player=player; 
	}
	/**
	 * concrete implementation of the command interface, 
	 * where calls to various receivers are performed to 
	 * select the pokemon used in the game
	 */
	@Override
	public void execute() 
	{
		this.player.choose(this.pokemon);
	}

}
