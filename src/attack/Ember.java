package attack;

import pokemon.Pokemon;

public class Ember extends FireAttack {

	@Override
	public void attack(Pokemon current, Pokemon opponent) {
		
		
		int baseDamage = current.getAttack();
		String opponentType = opponent.getType();
		
		if(opponentType == "Fire") {
			opponent.setCurrentHitPoints(opponent.getCurrentHitPoints()-(baseDamage*1));
		}
		
		if(opponentType == "Water")
		{
			opponent.setCurrentHitPoints((int) (opponent.getCurrentHitPoints()-(baseDamage*.5)));
		}
		
		if(opponentType == "Grass")
		{
			opponent.setCurrentHitPoints(opponent.getCurrentHitPoints()-(baseDamage*2));
		}
		
	}

}
