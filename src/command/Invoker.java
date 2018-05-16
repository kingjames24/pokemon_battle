package command;
/**
 * 
 * invoker class that houses all of the commands, sets them,
 * and invokes them through the command interface
 */
public class Invoker 
{
	private Command choosePokemanCommand; 
	private Command changePokemonCommand;
	private Command selectPokemonCommand; 
	private Command attackPokemonCommand; 
	
	//method that initializes the attack pokemon command variable
	public void setAttackCommand(Command command)
	{
		attackPokemonCommand=command; 
	}
	//method that makes the request to the concrete command to attack
	public void selectAttackButton()
	{
		attackPokemonCommand.execute();
	}
	//method that initializes the select pokemon command variable
	public void setSelectPokemonCommand(Command command)
	{
		selectPokemonCommand=command; 
	}
	//method that makes the request to the concrete command to select an attack
	public void selectPokemonButtonPressed()
	{
		selectPokemonCommand.execute();
	}
	//method that initializes the choose pokemon command variable
	public void setChoosePokemonCommand(Command command)
	{
		choosePokemanCommand = command; 
	}
	//method that makes the request to the concrete command to choose pokemon for the game
	public void choosePokemonButtonPressed()
	{
		choosePokemanCommand.execute(); 
	}
	//method that initializes the change pokemon command variable
	public void setChangePokemonCommand(Command command)
	{
		changePokemonCommand=command; 
	}
	//method that makes the request to the concrete command to change a pokemon  
	public void changePokemonButtonPressed()
	{
		changePokemonCommand.execute();
	}
	
}
