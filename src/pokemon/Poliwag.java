package pokemon;

import attack.RazorShell;
import attack.Scald;

public class Poliwag extends Pokemon {

	public Poliwag()
	{
		maxHitPoints = 40;
		//hitpoints always start at max
		setCurrentHitPoints(40);
		height = 0.61; //meters
		weight = 12.4; //kilos
		attack = 50;
		spAttack = 40;
		defense = 40;
		spDefense = 40;
		speed = 90;
		type = "Water";
	}
	
	@Override
	public String toString() {
		return "Poliwag";
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
