#Volfied arcade game (Graphics Relationship) entity diagram

```PlantUML

@startuml

title Volfied remake arcade game graphics relationship entity diagram

enum RegionStatus {
	+REGION_STATUS_EMPTY
	+REGION_STATUS_CONQUERED
	+REGION_STATUS_TEMP_LINE
}

class BoardPoint {
}

class Region {
	-location : BoardPoint
	-visible : boolean
	--
	+setConqueredRegion()
	+getNumberOfConqueredRegions()
	+getNumberOfUnconqueredRegions()
	+resetConqueredRegions()
}

Region ||-- RegionStatus
Region ||-down- BoardPoint : is defined by 1

class GridLine {
	-p1 : BoardPoint
	-p2 : BoardPoint
	--
	+isOnLine()
}

GridLine }|-down- BoardPoint : is defined by 2

class Grid {
	* GRID_X_SIZE_IN_CELLS	= 50
	* GRID_Y_SIZE_IN_CELLS 	= 30
	--
	-regions[GRID_X_SIZE_IN_CELLS][GRID_Y_SIZE_IN_CELLS] : Region
	-width
	-height
	-region
	--
	+updateRegionStatus()
	+getRegionStatus()
	+displayGrid()
}


Grid }|-left- Region : contains m
Grid }|-right- GridLine : contains n


class Board {
	* BOARD_X_OFFSET		= 40
	* BOARD_Y_OFFSET 	= 120
	* BOARD_SCALE			= 18
	--
	-grid
	--
	+getGrid()
	+getConqueredPercentage()
}

Board }|-down- Grid : contains

@enduml