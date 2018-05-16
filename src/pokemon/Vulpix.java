package pokemon;

import attack.BlastBurn;
import attack.BlazeKick;

public class Vulpix extends Pokemon {

	public Vulpix()
	{
		maxHitPoints = 38;
		//hitpoints always start at max
		setCurrentHitPoints(38);
		height = 0.61; //meters
		weight = 9.9; //kilos
		attack = 41;
		spAttack = 50;
		defense = 40;
		spDefense = 65;
		speed = 65;
		type = "Fire";
	}
	
	@Override
	public String toString() {
		return "Vulpix";
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
