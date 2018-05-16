package state;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import maininterface.MainInterface;
import observer.Observer;
import players.Players;
import pokemon.Pokemon;
/**
 * 
 * sub-class that represents the current score of the game, 
 * where the class keeps track of how many of the player's pokemon
 * have died in battle. Also class implements the observer interface 
 * found in the observer package, and thus receives updates when a pokemon
 * has died in battle
 *
 */
public class Score extends GameState implements Observer
{	
	Boolean[] player1score;  
	Boolean[] player2score; 
	/**
	 * constructor that contains a reference to 
	 * a maininterface object and references to object arrays of 
	 * boolean type, which keeps track of number of pokemon dead 
	 * @param maininterface a reference to the mainInterface object
	 */
	public Score(MainInterface maininterface)
	{
		player1score = new Boolean[3]; 
		player2score = new Boolean[3]; 
		this.maininterface=maininterface; 
		Arrays.fill(player1score, false);
		Arrays.fill(player2score, false);
	}
	
	
	@Override
	public void choose(String s) {}

	@Override
	public void change(int x) {}

	@Override
	public void select(String s) {}

	@Override
	public void attack() {}

	/**
	 * method that is invoked when a pokemon has taken a battle hit and
	 * lost its health; this method keeps track of how many of the player's pokemon
	 * are dead.The first player to kill all three of the other player's pokemon wins
	 * the game
	 */
	@Override
	public void update(Players player1, Players player2) 
	{
		int i=0, j=0; 
		Pokemon[] p1 = player1.getPokemon(); 
		Pokemon[] p2 = player2.getPokemon(); 
		for(Pokemon one: p1)
		{
			if(one.getCurrentHitPoints()<=0)
			{
				player1score[i]=true; 
				if (player1score[0] && player1score[1] && player1score[2])
				{
					
					maininterface.displaycredits(2);
					
				}
			}
			i++; 
		}
		for(Pokemon two: p2)
		{
			if(two.getCurrentHitPoints()<=0)
			{
				player2score[j]=true; 
				if (player2score[0] && player2score[1] && player2score[2])
				{
					
					maininterface.displaycredits(1);
				}
			}
			j++; 
		}
	}
}
