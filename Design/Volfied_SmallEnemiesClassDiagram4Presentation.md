#Volfied arcade game (small enemies) class diagram

```PlantUML

@startuml

title Volfied remake arcade game small enemies (and its relationship) class diagram

interface GameContent {
	+initContent()
}

class MyContent {
	+smallEnemies()
	+board()
	+gameControl()
}

MyContent .up.* GameContent : implements

interface Intersectable {
	+ getIntersectionVertices()
}


enum Direction {
	+RIGHT
	+LEFT
	+UP
	+DOWN
	+STOPPED
}

class Grid {
}

Direction *-down- Grid

class Board #back:lightblue {
	+initBoard()
	-addSmallEnemiesToCanvas()
	+updateSmallEnemiesInCanvas()
}

Board "1" *-left- "many" Grid : contains

class SmallEnemy #back:lightblue {
	+SmallEnemy(String, Grid)
	+move()
	-updateDirectionPolicy()
}

class GameCharacter #back:lightblue {
	+GameCharacter(String Grid)
	+setLocation(BoardPoint)
	+setLocation(int x, int y)	
	+getLocation() : BoardPoint
	+setCurrentDirection(Direction)
	+getCurrentDirection() : Direction
	+getImageWidth() : int
	+getImageHeight() : int
	+name() : String
	+move()
	+getIntersectionVertices() : ScreenPoint[]
	--
	-location : BoardPoint
	-name : String
	-imageWidth : int
	-imageHeight : int
	#grid : Grid
	#directionPolicy : Direction
	#currentDirection : Direction
}


SmallEnemy -left-|> GameCharacter  : is a
GameCharacter .left.* Intersectable : implements
GameCharacter -> Grid : points to a

class SmallEnemies #back:lightblue {
	+SmallEnemies(int quantityOfSmallEnemiesInGame)
	+initSmallEnemies(Grid)
	+move()
	+getSmallEnemies() : SmallEnemy[]
	--
	-smallEnemies : SmallEnemy[]
}

SmallEnemies "1" *-left- "many" SmallEnemy : contains

class GameControl {
	+gameStep()
}

MyContent "1" *-right- "1" GameControl : contains
MyContent "1" *-left- "1" Board : contains
MyContent "1" *-down- "1" SmallEnemies : contains

GameControl --> MyContent : uses


@enduml