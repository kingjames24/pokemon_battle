package observer;

import players.Players;
/**
 * 
 * interface that contains the method 
 * declaration that observers must implement
 * to get game updates
 *
 */
public interface Observer 
{
	public void update(Players player1, Players player2); 
}
