#Volfied arcade game "Update Grid" UC (Handle Background Logic) Sequence Diagram

```PlantUML

@startuml

	title __"Update Grid" part of Handle Background Logic UC sequence diagram__
	autonumber "<b>[00]"
	
	actor "MyPeriodicLoop" as myPeriodicLoop
	participant "Collision Algorithm" as collisionAlgorithm
	participant "Space Pilot" as spacePilot
	participant "Small Enemy" as smallEnemy
	
	myPeriodicLoop -> collisionAlgorithm : activateCollisionAlgorithm()
	
	collisionAlgorithm -> spacePilot : getSpacePilotLocation()
	activate spacePilot
	activate collisionAlgorithm
	spacePilot --> collisionAlgorithm : <<spacePilotLocation>>
	deactivate spacePilot
		
	loop n times (as number of small enemies)
		collisionAlgorithm -> smallEnemy : getEnemyLocation(n)
		activate smallEnemy
		smallEnemy --> collisionAlgorithm : <<enemyLocation_n>>
		deactivate smallEnemy
		collisionAlgorithm -> collisionAlgorithm : verifyCollision(spacePilotLocation, enemyLocation_n)
		
		opt There is Collison
			collisionAlgorithm -> spacePilot : Inform Space Pilot that there was collision
			activate spacePilot
			spacePilot --> collisionAlgorithm : <<return>>
			deactivate spacePilot
		end
	end
	
	deactivate collisionAlgorithm	
	

@enduml