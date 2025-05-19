#Volfied arcade game "Percentage Territory Occupied" US Sequence Diagram

```PlantUML

@startuml

	title __"Percentage Territory Occupied" US part of Handle Dashboard UC sequence diagram__
	autonumber "<b>[00]"
	
	participant "MyPeriodicLoop" as myPeriodicLoop
	participant "GameControl" as gameControl
	participant "Board" as board
	participant "GameDashboard" as dashboard
		
	activate myPeriodicLoop
	myPeriodicLoop -> myPeriodicLoop : execute()
	activate myPeriodicLoop
	myPeriodicLoop -> gameControl : gameStep()
	activate gameControl
	gameControl -> board : getConqueredPercentage()
	activate board
	board --> gameControl : <<percentage>>
	deactivate board
	board -> dashboard : setPercentage(percentage)
	activate dashboard
	dashboard --> board : <<return>>
	deactivate dashboard
	board --> gameControl : <<return>>	
	deactivate board
	gameControl --> myPeriodicLoop : <<return>>
	deactivate gameControl
	deactivate myPeriodicLoop
	deactivate myPeriodicLoop


@enduml