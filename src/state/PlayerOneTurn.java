package state;

import javax.swing.JPanel;

import command.*; 
import maininterface.MainInterface;
import pokemon.Pokemon;
/**
 * sub-class that represents player one's turn in the game
 */
public class PlayerOneTurn extends GameState {

	/**
	 * constructor that contains a reference to 
	 * a maininterface object
	 * @param maininterface a reference to the mainInterface object
	 */
	public PlayerOneTurn(MainInterface maininterface)
	{
		this.maininterface=maininterface;  
	}
	
	@Override
	public void choose(String s) {}

	/**
	 * concrete method that is invoked during the change pokemon stage; creates
	 * the invoker class(aka as the invoker builder) and loads the invoker with the the 
	 * requisite command, which the command class later executes(ie.,changes the player's 
	 * current pokemon)
	 */
	@Override
	public void change(int x) 
	{
		Invoker invoker = new Invoker(); 
		invoker.setChangePokemonCommand(new ChangePokemonCmd(x, maininterface.playerone));
		invoker.changePokemonButtonPressed();
	}
	/**
	 * concrete method that is invoked during the attack selection stage; creates
	 * the invoker class(aka as the invoker builder) and loads the invoker with the the 
	 * requisite command, which the command class later executes(ie., loads pokemon with a 
	 * specific attack)
	 */
	@Override
	public void select(String s) 
	{
		Invoker invoker = new Invoker(); 
		invoker.setSelectPokemonCommand(new SelectAttacksCmd(s, maininterface.playerone));
		invoker.selectPokemonButtonPressed();
	}
	/**
	 * concrete method that is invoked during the battle stage when the attack buttion 
	 * is pressed; creates the invoker class(aka as the invoker builder) and
	 * loads the invoker with the the 
	 * requisite command, which the command class later executes(ie., attacks player's pokemon)
	 */
	@Override
	public void attack() 
	{
		Invoker invoker = new Invoker();
		invoker.setAttackCommand(new AttackCmd(maininterface.playerone));
		invoker.selectAttackButton();
		
	}

	

}
