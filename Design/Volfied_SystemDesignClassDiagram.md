#Volfied arcade game (system design) class diagram

```PlantUML

@startuml

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

interface Intersectable {
	+ getIntersectionVertices()
}

class IntersectionAlgorithm {
	+areIntersecting()
}

class Cell {
	-status

	+updateStatus()
	+getStatus()
}

class Grid {
	-width
	-height
	-cell

	+updateCellStatus()
	+getCellStatus()
	+displayGrid()
}

Grid "1" *-right- "many" Cell : contains

class Board {
	-grid

	+getGrid()
	+getConqueredPercentage()
}

Board "1" *-- "many" Grid : contains

class SpacePilot {
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

class SmallEnemy {
	-health
	-speed
	-position

	+move()
	+takeDamage()
}

SmallEnemy ..* Intersectable : implements

class SmallEnemies {
}

SmallEnemies "1" *-- "many" SmallEnemy : contains

class GameControl {
	-level

	+setCurrentLevel()
	+getCurrentLevel()
	+getIsCollition()
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