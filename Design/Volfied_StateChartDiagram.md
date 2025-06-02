#Volfied arcade game (system design) state chart diagram

```PlantUML

@startuml

@startuml

state "Game Initialization" as GameInitialization
state "Game Active" as GameActive

[*] -right-> GameInitialization : Start Game

GameInitialization -right-> GameActive : Start Current Player
state GameActive {

	


	[*] -down-> SpacePilotIdle
		state "Space Pilot Idle" as SpacePilotIdle
		state isInsideConqueredZone <<choice>>	
		state isThereCollision <<choice>>		
		SpacePilotIdle -down-> isInsideConqueredZone : Player Moves Space Pilot\n / <b>Game Control</b> -> Space Pilot move
		state "Space Pilot Damaged" as SpacePilotDamaged
		state "Conquering Zone" as SpacePilotConqueringZone

		isInsideConqueredZone --> isThereCollision : [moved to\nnon-conquered zone]
		isInsideConqueredZone --> SpacePilotIdle : [moved inside\nconquered zone]	
		isThereCollision --> SpacePilotConqueringZone : [no collision with\nsmall enemy]
		isThereCollision --> SpacePilotDamaged : [collision with\nsmall enemy]
		
		SpacePilotConqueringZone --> SpacePilotIdle : Update score
		SpacePilotDamaged -->  SpacePilotIdle : Reduce lives
		
	||
	
	state "Small Enemy Idle" as SmallEnemyIdle
	[*] -down-> SmallEnemyIdle
		SmallEnemyIdle -up-> SmallEnemyIdle : 300 ms\n/ <b>Game Control</b> -> Small Enemy Random move

	||
	[*] -down-> GameControlIdle
		state "Game Control Idle" as GameControlIdle
		state "Game Control Moving" as GameControlMoving
		state "Game Control Step" as GameControlStep
		GameControlIdle	--> GameControlMoving : Small Enemy Random move\n/ Update Small Enemy position
		GameControlIdle	--> GameControlStep : 300 ms
		GameControlIdle	--> GameControlMoving : Space Pilot move\n/ Update Space Pilot position
		GameControlMoving --> GameControlIdle

}

state GameOver {
}

GameOver --> [*] : Game Ended


@enduml