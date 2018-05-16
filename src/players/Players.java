package players;

import javax.swing.JPanel;

import attack.Attack;
import pokemon.Pokemon;
/**
 * 
 * abstract super class that contains variables and
 * methods that both players use throughout the game
 * (avoids code duplication)
 *
 */
public abstract class Players 
{
	protected Pokemon[] numberofPokemons; 
	protected int number; 
	protected Pokemon currentOpponent; 
	protected Pokemon currentPokemon;
	
	public abstract void choose(String s);
	public abstract Pokemon[] getPokemon(); 
	public abstract void change(int x); 
	public abstract void setOpponent(Pokemon opponent); 
	public abstract Pokemon getCurrentPokemon(); 
	public abstract Pokemon getCurrentOpponent(); 
	public abstract void select (String s); 
	public abstract void attackMethod();
	public abstract Attack getAttack(); 
}
