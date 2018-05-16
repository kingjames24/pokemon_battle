package attack;

import pokemon.Pokemon;

public abstract class FireAttack implements Attack
{
	public abstract void attack(Pokemon current, Pokemon opponent);
}
