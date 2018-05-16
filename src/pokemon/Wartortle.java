package pokemon;

import attack.RazorShell;
import attack.Scald;

public class Wartortle extends Pokemon {

	public Wartortle()
	{
		maxHitPoints = 59;
		//hitpoints always start at max
		setCurrentHitPoints(59);
		height = 0.99; //meters
		weight = 22.5; //kilos
		attack = 63;
		spAttack = 65;
		defense = 80;
		spDefense = 80;
		speed = 58;
		type = "Water";
	}
	
	@Override
	public String toString() {
		return "Wartortle";
	}

	@Override
	public String getAttack1() {

		return "Scald";
	}

	@Override
	public String getAttack2() {
	
		return "RazorShell";
	}
	@Override
	public void setAttack(String s) 
	{
		
		
				if(s.equalsIgnoreCase("Scald"))
				{
					primaryAttack= new Scald();  
				}
				else if (s.equalsIgnoreCase("RazorShell"))
				{
					primaryAttack = new RazorShell(); 
				}
		
	
		
	}
	
}
