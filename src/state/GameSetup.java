package state;

import javax.swing.JPanel;

import command.ChoosePokemonCmd;
import command.Invoker;
import maininterface.MainInterface;
import pokemon.Pokemon;
/**
 * 
 * sub-class that represents the beginning state of the game, 
 * where each player chooses his/her pokemon from the 
 * pokemon selection screen
 */
public class GameSetup extends GameState 
{
	private int count=0; 
	/**
	 * constructor that contains a reference to 
	 * a maininterface object
	 * @param maininterface a reference to the mainInterface object
	 */
	public GameSetup(MainInterface maininterface)
	{
		this.maininterface=maininterface;  
	}
	/**
	 * concrete method that is invoked during the pokemon selection stage; creates
	 * the invoker class(aka as the invoker builder) and loads the invoker with the the 
	 * requisite command, which the command class later executes
	 */
	@Override
	public void choose(String s) 
	{
		count++; 
		if (count<=3)
		{
			Invoker invoker = new Invoker(); 
			invoker.setChoosePokemonCommand(new ChoosePokemonCmd(s, maininterface.playerone));
			invoker.choosePokemonButtonPressed();
		}
		else
		{
			Invoker invoker = new Invoker(); 
			invoker.setChoosePokemonCommand(new ChoosePokemonCmd(s, maininterface.playertwo));
			invoker.choosePokemonButtonPressed();
		}
		

	}

	@Override
	public void change(int x) {}

	@Override
	public void select(String s) {}

	@Override
	public void attack() {}

	

	

	



}
