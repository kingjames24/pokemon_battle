package state;
/**
 * abstract superclass that contains variables and methods used
 * by all concrete sub-classes (avoids code duplication)
 */
import javax.swing.JPanel;

import maininterface.MainInterface;
import pokemon.*;

public abstract class GameState 
{
	protected MainInterface maininterface; 
	
	public abstract void choose(String s); 
	public abstract void change(int x);
	public abstract void select(String s); 
	public abstract void attack(); 
}
