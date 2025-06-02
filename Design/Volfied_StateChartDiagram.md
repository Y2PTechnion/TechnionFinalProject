#Volfied arcade game (system design) state chart diagram

```PlantUML

@startuml

@startuml

state "Game Initialization" as GameInitialization
state "Game Active" as GameActive

[*] -down-> GameInitialization : Start Game

GameInitialization -down-> GameActive : Start Current Player
state GameActive {
	[*] -down-> SpacePilotIdle
		state "<b>Space Pilot</b> Idle" as SpacePilotIdle
		state "<b>Space Pilot</b> Damaged" as SpacePilotDamaged : Exit/Reduces Space Pilot life
		state "<b>Space Pilot</b> Killed" as SpacePilotKilled : Exit/<b>Game Over</b>

		state isGameOver <<choice>>			
		
		SpacePilotIdle -up-> SpacePilotIdle : Player Moves Space Pilot\n / <b>Game Control</b> -> Space Pilot move
		SpacePilotIdle -down-> SpacePilotDamaged : [Reduce Lives]
		SpacePilotDamaged --> isGameOver
		isGameOver --> SpacePilotKilled : [if no life left]
		isGameOver --> SpacePilotIdle : [if still there\nis life left]
		SpacePilotKilled -down-> ToGameOver <<exitPoint>> 
		
	||
	
	state "<b>Small Enemy</b> Idle" as SmallEnemyIdle
	[*] -down-> SmallEnemyIdle
		SmallEnemyIdle -up-> SmallEnemyIdle : 300 ms\n/ <b>Game Control</b> -> Small Enemy Random move

	||
	[*] -down-> GameControlIdle
		state "<b>Game Control</b> Idle" as GameControlIdle
		state "<b>Game Control</b> Step" as GameControlStep : Exit/Verifies collision\n& closing region	
		
		state isInsideConqueredZone <<choice>>	
		state isThereCollision <<choice>>	
		state hasSpacePilotWon <<choice>>			
		
		state "<b>Game Control</b> Collision" as Collision : Exit/<b>Space Pilot</b> -> Reduce lives
		state "<b>Game Control</b> Conquering Zone" as SpacePilotConqueringZone : Exit/Update conquered zones
		state "<b>Game Control</b> Win" as GameControlWin : Exit/<b>Game Over</b>
		
		isInsideConqueredZone --> GameControlStep : [if moved to\nnon-conquered zone]
		isInsideConqueredZone --> GameControlIdle : [if moved inside\nconquered zone]	

		GameControlIdle	--> GameControlStep : [Small Enemy Random move]\n/ Update Small Enemy position
		GameControlIdle	--> isInsideConqueredZone : [Space Pilot move]\n/ Update Space Pilot position
		GameControlStep --> isThereCollision
		isThereCollision --> GameControlIdle : [if no collision\n& not conquered region]
		isThereCollision --> SpacePilotConqueringZone : [if no collision \n& conquered region]
		isThereCollision --> Collision : [if there is collision]
		
		SpacePilotConqueringZone --> hasSpacePilotWon
		hasSpacePilotWon --> GameControlIdle : [Space Pilot has not\nyet conquered the territory]
		hasSpacePilotWon --> GameControlWin : [Space Pilot has\nconquered ALL the territory]		
		
			
		GameControlWin --> ToGameOverWon <<exitPoint>>
		
		Collision -->  GameControlIdle
}

state GameOver : Exit/Update\nstatistics and score 



GameActive -right-> GameOver

GameOver --right> [*] : Game Ended


@enduml