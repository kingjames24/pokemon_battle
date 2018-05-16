package pokemon;
import attack.*;

public abstract class Pokemon {

	/**
	 * There are physical and special attacks, which calculate damage based
	 *   upon the pokemon's appropriate stat. spAttack is used for a special
	 *   attack move.
	 *   
	 * There are two defense values as well, physical and special. Regular defense
	 *    stat protects against physical attacks, and special defense protects
	 *    against special attack.
	 *    
	 * The speed stat of a pokemon determines who goes first. The pokemon with
	 *    the higher speed moves first.
	 */
	
	int maxHitPoints;      //maximum hit points
	int currentHitPoints;  //current hit points
	double height;         //height of the pokemon
	double weight;         //weight of the pokemon
	int attack;            //physical attack power of the pokemon
	int spAttack;          //special attack power of the pokemon
	int defense;           //physical defense of the pokemon
	int spDefense;         //special defense of the pokemon
	int speed;             //speed of the pokemon
	public Attack primaryAttack=null;  
	protected String type;
	/**
	 * 
	 * @return pokemons maximum hitpoints
	 */
	
	//abstract method that loads a pokemon with a specific attack
	public abstract void setAttack(String s); 
			
	public int getMaxHitPoints()
	{
		return this.maxHitPoints;
	}
	
	/**
	 * 
	 * @return pokemons current hitpoints
	 */
	public int getCurrentHitPoints()
	{
		return this.currentHitPoints;
	}
	
	/**
	 * 
	 * @return pokemons attack power
	 */
	public int getAttack()
	{
		return this.attack;
	}
	public abstract String getAttack1();
	public abstract String getAttack2(); 
	/**
	 * 
	 * @return pokemons special attack power
	 */
	public int getSpAttack()
	{
		return this.spAttack;
	}
	
	/**
	 * 
	 * @return pokemons defense rating
	 */
	public int getDefense()
	{
		return this.defense;
	}
	
	/**
	 * 
	 * @return pokemons special defense rating
	 */
	public int getSpDefense()
	{
		return this.spDefense;
	}
	
	/**
	 * 
	 * @return pokemons speed
	 */
	public int getSpeed()
	{
		return this.speed;
	}
	
	/**
	 * 
	 * @return pokemons height in meters
	 */
	public double getHeight()
	{
		return this.height;
	}
	
	/**
	 * 
	 * @return pokemons weight in kilograms
	 */
	public double getWeight()
	{
		return this.weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCurrentHitPoints(int currentHitPoints) {
		this.currentHitPoints = currentHitPoints;
	}
}
