package pokemon;

import attack.BlastBurn;
import attack.BlazeKick;
import attack.CottonGuard;
import attack.LeafBlade;

public class Charizard extends Pokemon {

	public Charizard()
	{
		maxHitPoints = 78;
		//hitpoints always start at max
		setCurrentHitPoints(78);
		height = 1.70; //meters
		weight = 90.5; //kilos
		attack = 84;
		spAttack = 109;
		defense = 78;
		spDefense = 85;
		speed = 100;
		type = "Fire";
	}
	
	@Override
	public String toString() {
		return "Charizard";
	}

	@Override
	public String getAttack1() {
	
		return "BlastBurn";
	}

	@Override
	public String getAttack2() {
		
		return "BlazeKick";
	}
	
	@Override
	public void setAttack(String s) 
	{
		
		
				if(s.equalsIgnoreCase("BlastBurn"))
				{
					primaryAttack= new BlastBurn();  
				}
				else if (s.equalsIgnoreCase("BlazeKick"))
				{
					primaryAttack = new BlazeKick();  
				}
		
		
	}
}
