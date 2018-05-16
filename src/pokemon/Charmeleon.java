package pokemon;

import attack.BlastBurn;
import attack.BlazeKick;

public class Charmeleon extends Pokemon {

	public Charmeleon()
	{
		maxHitPoints = 58;
		//hitpoints always start at max
		setCurrentHitPoints(58);
		height = 1.09; //meters
		weight = 19.0; //kilos
		attack = 64;
		spAttack = 80;
		defense = 58;
		spDefense = 65;
		speed = 80;
		type = "Fire";
	}
	
	@Override
	public String toString() {
		return "Charmeleon";
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
