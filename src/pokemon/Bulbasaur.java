package pokemon;

import attack.CottonGuard;
import attack.LeafBlade;

public class Bulbasaur extends Pokemon 
{

	public Bulbasaur()
	{
		maxHitPoints = 45;
		//hitpoints always start at max
		setCurrentHitPoints(45);
		height = 0.71; //meters
		weight = 6.9;  //kilos
		attack = 49;
		spAttack = 65;
		defense = 49;
		spDefense = 65;
		speed = 45;
		type = "Grass";
	}
	
	@Override
	public String toString() {
		return "Bulbasaur";
	}

	@Override
	public String getAttack1() {
		
		return "CottonGuard";
	}

	@Override
	public String getAttack2() {
		
		return "LeafBlade";
	}

	@Override
	public void setAttack(String s) 
	{
		
		
				if(s.equalsIgnoreCase("CottonGuard"))
				{
					primaryAttack= new CottonGuard(); 
				}
				else if (s.equalsIgnoreCase("LeafBlade"))
				{
					primaryAttack = new LeafBlade(); 
				}
		
		
	}
}
