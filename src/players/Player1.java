package players;
import javax.swing.JPanel;

import attack.Attack;
import pokemon.*;
/**
 * 
 * sub-class that represents the type of actions
 * player one can take during the game
 *
 */
public class Player1 extends Players 
{
	/**
	 * public constructor that creates 
	 * an object array that points to
	 * pokemon objects
	 */
	public Player1()
	{
		numberofPokemons = new Pokemon[3]; 
	}	
	
	/**
	 * concrete method that implements the choosing of player one's
	 * pokemon and stores them in the object array variable named 
	 * numberofPokemons(each player is allowed to hold three pokemon) 
	 */
	@Override
	public void choose(String s) 
	{
		
		if(number<3)
		{
			if(s.equalsIgnoreCase("Blastoise"))
			{
				numberofPokemons[number++]= new Blastoise();
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if(s.equalsIgnoreCase("Bulbasaur"))
			{
				numberofPokemons[number++]= new Bulbasaur();
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if(s.equalsIgnoreCase("Bellsprout"))
			{
				numberofPokemons[number++]= new Bellsprout();
				//System.out.println("player1 chose " +numberofPokemons[number-1]+ " ");
			}
			else if (s.equalsIgnoreCase("Charizard"))
			{
				numberofPokemons[number++]= new Charizard();
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if (s.equalsIgnoreCase("Charmander"))
			{
				numberofPokemons[number++]= new Charmander(); 
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if (s.equalsIgnoreCase("Charmeleon"))
			{
				numberofPokemons[number++]= new Charmeleon();
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if (s.equalsIgnoreCase("Ivysaur"))
			{
				numberofPokemons[number++]= new Ivysaur();
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if (s.equalsIgnoreCase("Poliwag"))
			{
				numberofPokemons[number++]= new Poliwag();
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if (s.equalsIgnoreCase("Squirtle"))
			{
				numberofPokemons[number++]= new Squirtle(); 
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if (s.equalsIgnoreCase("Venusaur"))
			{
				numberofPokemons[number++]= new Venusaur();
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if (s.equalsIgnoreCase("Vulpix"))
			{
				numberofPokemons[number++]= new Vulpix(); 
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else if (s.equalsIgnoreCase("Wartortle"))
			{
				numberofPokemons[number++]= new Wartortle();
				//System.out.println("player1 chose " + numberofPokemons[number-1]+ " ");
			}
			else
			{
				; 
			}

		}
		
	}
	/**
	 * method that returns player one's object array as 
	 * referenced by the variable numberofPokemons 
	 */
	public Pokemon[] getPokemon()
	{
		return numberofPokemons; 
	}
	/**
	 * concrete method that changes player one's current pokemon to
	 * a different pokemon stored in the object array
	 */
	@Override
	public void change(int x) 
	{
		
		if(x==0)
		{
			currentPokemon=numberofPokemons[0];
			 
		}
		else if (x==1)
		{
			currentPokemon=numberofPokemons[1]; 
		}
		else if (x==2)
		{
			currentPokemon=numberofPokemons[2]; 
		}
		else
		{
			; 
		}
		
		
	}
	/**
	 * method that sets player one's current opponent, used 
	 * for damage calculation purposes 
	 */
	public void setOpponent(Pokemon opponent)
	{
		currentOpponent=opponent;
	}
	
	/**
	 * concrete method that returns player one's current pokemon
	 */
	@Override
	public Pokemon getCurrentPokemon() 
	{
			return currentPokemon; 
	}
	/**
	 * concrete method that returns player one's current opponent
	 */
	@Override
	public Pokemon getCurrentOpponent() 
	{
		return currentOpponent; 
	}
	/**
	 * concrete method that sets the attack
	 * of player one's current pokemon
	 */
	@Override
	public void select(String s) 
	{
		currentPokemon.setAttack(s);
		
	}

	/**
	 * concrete method that is invoked when 
	 * player one decides to attack player two. Player 
	 * one's current pokemon attacks player two's pokemon
	 */
	@Override
	public void attackMethod() {
		
		this.currentPokemon.primaryAttack.attack(this.currentPokemon, this.currentOpponent); 
		
	}

	/**
	 * concrete method that returns the current attack of 
	 * player one's current pokemon
	 */
	@Override
	public Attack getAttack() 
	{
		
		return currentPokemon.primaryAttack; 
	}
}
