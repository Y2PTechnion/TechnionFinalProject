#Volfied arcade game (handle collisions UC) class diagram

```PlantUML

@startuml

title "Handle Collisions UC"-related class diagrams

interface GameContent {
	+initContent()
}

class MyContent {
	+spacePilot()
	+smallEnemies()
	+board()
	+gameControl()
}

MyContent .up.* GameContent : implements

interface Intersectable #back:lightblue {
	+ getIntersectionVertices()
}

class IntersectionAlgorithm #back:lightblue {
	+areIntersecting()
}

enum RegionStatus #back:lightblue {
	+REGION_STATUS_EMPTY
	+REGION_STATUS_CONQUERED
	+REGION_STATUS_TEMP_LINE
}

class Region #back:lightblue {
	-status

	+updateStatus()
	+getStatus()
}

Region ..* Intersectable : implements

RegionStatus *-- Region


class GridLine {
}

class Grid {
	-width
	-height
	-region

	+updateRegionStatus()
	+getRegionStatus()
	+displayGrid()
}

Grid "1" *-down- "many" Region : contains
Grid "1" *-down- "many" GridLine : contains

class Board {
	-grid

	+getGrid()
	+getConqueredPercentage()
}

Board "1" *-left- "many" Grid : contains

class SpacePilot #back:lightblue {
	-health
	-score
	-position
	-isOutsideSafePlace

	+move()
	+attack()
	+takeDamage()
	+getScore()
	+setIsOutsideSafePlace()
}

SpacePilot ..* Intersectable : implements

class SmallEnemy #back:lightblue {
	-health
	-speed
	-position

	+move()
	+takeDamage()
}

SmallEnemy ..* Intersectable : implements

class SmallEnemies {
}

SmallEnemies "1" *-right- "many" SmallEnemy : contains

class GameControl #back:lightblue {
	-level

	+setCurrentLevel()
	+getCurrentLevel()
	+getIsCollision()
	+getIsSpacePilotInSafeArea()
	+getIsAreaConquered()
	+setSpacePilotPositionInBoard()	
}

MyContent "1" *-- "1" GameControl : contains
MyContent "1" *-- "1" Board : contains
MyContent "1" *-- "many" SpacePilot : contains
MyContent "1" *-right- "1" SmallEnemies : contains
MyContent -left-> IntersectionAlgorithm : uses

GameControl --> MyContent : uses


@enduml