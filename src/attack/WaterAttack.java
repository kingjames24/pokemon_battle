package attack;

import pokemon.Pokemon;

public abstract class WaterAttack implements Attack 
{
	public abstract void attack(Pokemon current, Pokemon opponent);
	
}
