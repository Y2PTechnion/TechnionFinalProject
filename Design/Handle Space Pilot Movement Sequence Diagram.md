#Volfied arcade game "Handle Space Pilot Movement" UC Sequence Diagram

```PlantUML

@startuml

	title __"Handle Space Pilot Movement" UC sequence diagram__
	autonumber "<b>[00]"
	
	actor "Player 1" as player1
	participant "Space Pilot" as spacePilot
	
	player1 -> spacePilot : moveSpacePilotLocation(delta_x, delta_y)
	activate spacePilot
	spacePilot -> spacePilot : hasSpacePilotReachedLevelBounds()
	deactivate player1
			
	opt Space Pilot has NOT reached level bounds
		spacePilot -> spacePilot : Update Space Pilot location
	end
	
	deactivate spacePilot	
	
@enduml