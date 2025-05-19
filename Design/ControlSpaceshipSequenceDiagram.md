#Volfied arcade game "Control Spaceship" US Sequence Diagram

```PlantUML

@startuml

	title __"Control Spaceship" US part of Handle Space Pilot UC sequence diagram__
	autonumber "<b>[00]"
	
	actor "Player" as player
	participant "MyKeyboardListener" as myKeyboardListener
	participant "MyPeriodicLoop" as myPeriodicLoop
	participant "GameControl" as gameControl
	participant "Board" as board
	participant "Space Pilot" as spacePilot
	participant "GameCanvas" as canvas
		
	player -> myKeyboardListener : cursor key pressed
	activate myKeyboardListener
	myKeyboardListener -> myKeyboardListener : directionalKeyPressed()
	activate myKeyboardListener
	myKeyboardListener -> spacePilot : moveLocation()
	myKeyboardListener --> player : <<return>>
	deactivate myKeyboardListener
	deactivate myKeyboardListener
	activate myPeriodicLoop
	myPeriodicLoop -> myPeriodicLoop : execute()
	activate myPeriodicLoop
	myPeriodicLoop -> gameControl : gameStep()
	activate gameControl
	gameControl -> board : updateSpacePilot()
	activate board
	board -> spacePilot : getLocation()
	activate spacePilot
	spacePilot --> board : <<location>>
	deactivate spacePilot
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