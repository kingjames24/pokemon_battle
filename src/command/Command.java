package command;
/**
 * interface that contains one method that
 * the each concrete command implements to 
 * perform the requested action on specific receivers (ie.,
 * the objects found in the players, pokemon,  
 * and/or attack packages)
 */
public interface Command 
{
	public void execute(); 
}
