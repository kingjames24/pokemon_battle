package pokemon;

import attack.BlastBurn;
import attack.BlazeKick;

public class Charmander extends Pokemon {

	public Charmander()
	{
		maxHitPoints = 39;
		//hitpoints always start at max
		setCurrentHitPoints(39);
		height = 0.61; //meters
		weight = 8.5; //kilos
		attack = 52;
		spAttack = 60;
		defense = 43;
		spDefense = 50;
		speed = 65;
		type = "Fire";
	}
	
	@Override
	public String toString() {
		return "Charmander";
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
