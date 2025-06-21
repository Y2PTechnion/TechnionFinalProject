#Volfied arcade game (space pilot) class diagram

```PlantUML

@startuml

title Volfied remake arcade game space pilot (and its relationship) class diagram

interface GameContent {
	+initContent()
}

class MyContent {
	+spacePilot()
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

class BoardPoint {
}

Direction *-down- Grid

class Board #back:lightblue {
	+initBoard()
	-addSpacePilotToCanvas(SpacePilot)
	+updateSpacePilotInCanvas()
}

Board "1" *-left- "many" Grid : contains

class SpacePilot #back:lightblue {
	+SpacePilot(String, Grid)
	+directionalKeyPressed(Direction)
	+getImageName() : String
	+move()
	--
	-imageIndex : int
	-images[] : String
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


SpacePilot -left-|> GameCharacter  : is a
GameCharacter .left.* Intersectable : implements
GameCharacter -> Grid : points to a
GameCharacter "1" *-- "1" BoardPoint : contains


class GameControl {
	+gameStep()
}

MyContent "1" *-right- "1" GameControl : contains
MyContent "1" *-left- "1" Board : contains
MyContent "1" *-down- "many" SpacePilot : contains

GameControl --> MyContent : uses


@enduml