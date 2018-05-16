package pokemon;

import attack.CottonGuard;
import attack.LeafBlade;
import attack.RazorShell;
import attack.Scald;

public class Blastoise extends Pokemon 
{

	public Blastoise()
	{
		maxHitPoints = 79;
		//hitpoints always start at max
		setCurrentHitPoints(79);
		height = 1.60; //meters
		weight = 85.5; //kilos
		attack = 83;
		spAttack = 85;
		defense = 100;
		spDefense = 105;
		speed = 78;
		type = "Water";
	}
	
	@Override
	public String toString() {
		return "Blastoise";
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
