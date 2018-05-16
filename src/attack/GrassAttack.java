package attack;

import pokemon.Pokemon;

public abstract class GrassAttack implements Attack 
{
	public abstract void attack(Pokemon current, Pokemon opponent);
}
