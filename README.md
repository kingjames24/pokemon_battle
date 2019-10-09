# README: 

[![IMAGE ALT TEXT HERE](http://img.youtube.com/vi/usbbpN5EdIk/0.jpg)](https://youtu.be/usbbpN5EdIk)

This Pokemon game application was done for Design Patterns. Team members included: Adam Standke, Stephen Clabaugh II, Gegory Whitman, and Gabriel Webbe.
The application is a fully functional game in which two players can each choose three pokemon from three different Pokemon classes and then battle each other
in typical fashion. The battle moves include attacking or switching the current Pokemon, in which case the player loses his or her turn. The
game is fully functional and after a player's three Pokemon are completly dead, a winner is crowned. Game was entirely created using the java language/libraries.

![](https://github.com/kingjames24/pokemon_battle/blob/master/UpdatedUmlDiagramProject%20(1).png)

The above picture represents the underlying software architecture used in the design process. Other than the singleton pattern, which represents the mainInterface class, the three most important software patterns in use are the state, observer, and command pattern. The state pattern keeps track of the game as each player progresses through the game. The observer pattern is used to update the score status during the game through the Scoreboard class. And the command pattern is used to map user input  to actions within the game.(The strategy pattern was also used to group together the different Pokemon attacks, but plays a less direct role in the app.) 
	

