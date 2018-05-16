package pokemon;

import attack.RazorShell;
import attack.Scald;

public class Squirtle extends Pokemon {

	public Squirtle()
	{
		maxHitPoints = 44;
		//hitpoints always start at max
		setCurrentHitPoints(44);
		height = 0.51; //meters
		weight = 9.0; //kilos
		attack = 48;
		spAttack = 50;
		defense = 65;
		spDefense = 64;
		speed = 43;
		type = "Water";
	}
	
	@Override
	public String toString() {
		return "Squirtle";
	}

	@Override
	public String getAttack1() {
		// TODO Auto-generated method stub
		return "Scald";
	}

	@Override
	public String getAttack2() {
		// TODO Auto-generated method stub
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
