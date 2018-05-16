package pokemon;

import attack.CottonGuard;
import attack.LeafBlade;

public class Venusaur extends Pokemon {

	public Venusaur()
	{
		maxHitPoints = 80;
		//hitpoints always start at max
		setCurrentHitPoints(80);
		height = 2.01; //meters
		weight = 100.0; //kilos
		attack = 82;
		spAttack = 100;
		defense = 83;
		spDefense = 100;
		speed = 80;
		type = "Grass";
	}
	
	@Override
	public String toString() {
		return "Venusaur";
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
