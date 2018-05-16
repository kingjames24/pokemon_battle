package pokemon;

import attack.CottonGuard;
import attack.LeafBlade;

public class Ivysaur extends Pokemon {

	public Ivysaur()
	{
		maxHitPoints = 60;
		//hitpoints always start at max
		setCurrentHitPoints(60);
		height = 0.99; //meters
		weight = 13.0; //kilos
		attack = 62;
		spAttack = 80;
		defense = 63;
		spDefense = 80;
		speed = 60;
		type = "Grass";
	}
	
	@Override
	public String toString() {
		return "Ivysaur";
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
