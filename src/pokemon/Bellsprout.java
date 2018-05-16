package pokemon;

import attack.CottonGuard;
import attack.LeafBlade;

public class Bellsprout extends Pokemon {

	
	//Bellsprout is a wild card in the grass types. Bellsprout
	//could perhaps know poison moves and grass. Poison > Grass
	
	/**
	 * Constructor for bellsprout
	 */
	public Bellsprout()
	{
		maxHitPoints = 50;
		//hitpoints always start at max
		setCurrentHitPoints(50);
		height = 0.71; //meters
		weight = 4.0;  //kilos
		attack = 75;
		spAttack = 70;
		defense = 35;
		spDefense = 30;
		speed = 40;
		type = "Grass";
		
	}
	
	@Override
	public String toString() {
		return "Bellsprout";
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
