package pokemon;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPokemon {

	/**
	 * This test tests the initialization of a pokemon
	 */
	@Test
	public void testInitialization()
	{
		//create a bellsprout
		Pokemon bellsprout = new Bellsprout();
		//test if bellsprout is a bellsprout
		assertTrue(bellsprout instanceof Bellsprout);
		
		assertEquals(50, bellsprout.getCurrentHitPoints());
	}

}
