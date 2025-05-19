#Volfied arcade game "Move Small Enemies" US Sequence Diagram

```PlantUML

@startuml

	title __"Move Small Enemies" US part of Handle Small Enemy UC sequence diagram__
	autonumber "<b>[00]"
	
	participant "MyPeriodicLoop" as myPeriodicLoop
	participant "GameControl" as gameControl
	participant "Board" as board
	participant "SmallEnemy" as smallEnemy
	participant "GameCanvas" as canvas
		
	activate myPeriodicLoop
	myPeriodicLoop -> myPeriodicLoop : execute()
	activate myPeriodicLoop
	myPeriodicLoop -> gameControl : gameStep()
	activate gameControl
	gameControl -> smallEnemy : move()
	activate smallEnemy
	smallEnemy --> gameControl : <<return>>
	deactivate smallEnemy
	gameControl -> board : updateSmallEnemies()
	activate board
	board -> smallEnemy : getLocation()
	activate smallEnemy
	smallEnemy --> board : <<location>>
	deactivate smallEnemy
	board -> canvas : moveShapeToLocation(location)
	activate canvas
	canvas --> board : <<return>>
	deactivate canvas
	board --> gameControl : <<return>>	
	deactivate board
	gameControl --> myPeriodicLoop : <<return>>
	deactivate gameControl
	deactivate myPeriodicLoop
	deactivate myPeriodicLoop


@enduml