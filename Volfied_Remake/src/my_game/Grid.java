/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            Grid.java
// Semester:         Spring 2025
//
// Author:           YuvalYossiPablo
// Email:            
// CS Login:         
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Yossi Huttner
// Email:            yossihuttner@yahoo.com
// CS Login:         yossef.h@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Yuval Shechter
// Email:            yuvalshe@gmail.com
// CS Login:         y.shechter@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Pablo Daniel Jelsky
// Email:            PabloDanielJelsky@gmail.com
// CS Login:         pablo.jelsky@campus.technion.ac.il
// Lecturer's Name:  Rami Marelly, Ph.D.
// Lab Section:      00860222
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   The headers in this file were taken as an example from
//                   https://pages.cs.wisc.edu/~cs302/resources/guides/commenting.html
//
//////////////////////////// 80 columns wide //////////////////////////////////

package my_game;

import java.util.ArrayList;

import my_game.Region.RegionStatus;

public class Grid 
{
	public enum Direction 
    {
		WEST (-1,0, 0),
		EAST (1,0, 1),
		NORTH (0,-1, 2),
		SOUTH (0,1, 3),
        STOPPED(0,0, 4),
        NORTH_WEST (-1,-1, 5),
        NORTH_EAST (1,-1, 6),
        SOUTH_WEST (-1,1, 7),
        SOUTH_EAST (1,1, 8);
		
		private final int xVector, yVector, index;
		private Direction(int xVector, int yVector, int index) 
        {
			this.xVector    = xVector;
			this.yVector    = yVector;
            this.index  = index;
		}

		public int xVector() 
        {
			return this.xVector;
		}

		public int yVector() 
        {
			return this.yVector;
		}

		public int index() 
        {
			return this.index;
		}
	}

//  Private constants for the class
    // Grid size in cells
    // Each cell is 18x18 pixels, so the grid total size in pixels is 936x576 (52x32 cells)
    private static final int    BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_X_PER_ROW          = 2;
    private static final int    BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_Y_PER_COLUMN       = 2;
    private static final int    GAME_CELLS_FOR_ENEMIES_AND_SPACE_PILOT_IN_X_PER_ROW     = 50;
    private static final int    GAME_CELLS_FOR_ENEMIES_AND_SPACE_PILOT_IN_Y_PER_COLUMN  = 30;

	public static final int     TOTAL_GAME_CELLS_IN_X_PER_ROW       = BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_X_PER_ROW 
                                                                + GAME_CELLS_FOR_ENEMIES_AND_SPACE_PILOT_IN_X_PER_ROW;
	public static final int     TOTAL_GAME_CELLS_IN_Y_PER_COLUMN    = BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_Y_PER_COLUMN
                                                                + GAME_CELLS_FOR_ENEMIES_AND_SPACE_PILOT_IN_Y_PER_COLUMN;	
	
//  Private variables for the class
	private Board               board                               = null;
	private ArrayList<GridLine> gridBorderOnlyForSpacePilotLines    = new ArrayList<GridLine>();
    private ArrayList<GridLine> gridSpacePilotLines                 = new ArrayList<GridLine>();
    private BoardPoint          lastSpacePilotPointInSafeZone       = null;
    //  Region[row][column]
	private Region[][]          regions                             = null;
	
    public Grid(Grid gridToCopyFromOnlyToCompare) 
    {   
//        this.board  = gridToCopyFromOnlyToCompare.board;
        this.regions    = new Region[TOTAL_GAME_CELLS_IN_Y_PER_COLUMN][TOTAL_GAME_CELLS_IN_X_PER_ROW];
        initRegions();

        for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++) 
        {
            for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
  //              this.regions[row][column] = gridToCopyFromOnlyToCompare.regions[row][column];
                this.regions[row][column].setRegionStatus(gridToCopyFromOnlyToCompare.regions[row][column].getRegionStatus());
            }
        }

       lastSpacePilotPointInSafeZone       = new BoardPoint(0, 0);
    }

	public Grid(Board board) 
    {
		this.board      = board;
        this.regions    = new Region[TOTAL_GAME_CELLS_IN_Y_PER_COLUMN][TOTAL_GAME_CELLS_IN_X_PER_ROW];
		initGridLines();
		initRegions();
        lastSpacePilotPointInSafeZone       = new BoardPoint(0, 0);
	}

    public boolean getIsBoardPointACornerForEnemies(BoardPoint boardPoint)
    {
        final int       NORTH_CORNER    = BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_Y_PER_COLUMN/2;
        final int       SOUTH_CORNER    = TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1-NORTH_CORNER;
        final int       WEST_CORNER     = BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_X_PER_ROW/2;
        final int       EAST_CORNER     = TOTAL_GAME_CELLS_IN_X_PER_ROW-1-WEST_CORNER;
        final boolean   IS_BOARD_POINT_A_CORNER_FOR_ENEMIES   =
            /* North-West */
            (((NORTH_CORNER == boardPoint.getRow()) 
                && (WEST_CORNER == boardPoint.getColumn()))
            /* North-East */
            || ((NORTH_CORNER == boardPoint.getRow()) 
                && (EAST_CORNER == boardPoint.getColumn()))
            /* South-West */
            || ((SOUTH_CORNER == boardPoint.getRow()) 
                && (WEST_CORNER == boardPoint.getColumn()))
            /* South-East */
            || ((SOUTH_CORNER == boardPoint.getRow()) 
                && (EAST_CORNER == boardPoint.getColumn())));

        return IS_BOARD_POINT_A_CORNER_FOR_ENEMIES;
    }

    public Direction getCornerForEnemies(BoardPoint boardPoint)
    {
        Direction   corner          = Direction.STOPPED;
        final int   NORTH_CORNER    = BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_Y_PER_COLUMN/2;
        final int   SOUTH_CORNER    = TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1-NORTH_CORNER;
        final int   WEST_CORNER     = BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_X_PER_ROW/2;
        final int   EAST_CORNER     = TOTAL_GAME_CELLS_IN_X_PER_ROW-1-WEST_CORNER;

        if ((NORTH_CORNER == boardPoint.getRow()) 
                && (WEST_CORNER == boardPoint.getColumn()))
        {
            /* North-West */
            corner  = Direction.NORTH_WEST;
        }
        else if ((NORTH_CORNER == boardPoint.getRow()) 
                && (EAST_CORNER == boardPoint.getColumn()))
        {
            /* North-East */
            corner  = Direction.NORTH_EAST;
        }
        else if ((SOUTH_CORNER == boardPoint.getRow()) 
                && (WEST_CORNER == boardPoint.getColumn()))
        {
            /* South-West */
            corner  = Direction.SOUTH_WEST;
        }
        else if ((SOUTH_CORNER == boardPoint.getRow()) 
                && (EAST_CORNER == boardPoint.getColumn()))
        {
            /* South-East */
            corner  = Direction.SOUTH_EAST;
        }

        return corner;
    }

    public static int getTotalGameCellsInXPerRow() 
    {
        return TOTAL_GAME_CELLS_IN_X_PER_ROW;
    }

    public static int getTotalGameCellsInYPerColumn() 
    {
        return TOTAL_GAME_CELLS_IN_Y_PER_COLUMN;
    }

	private void initGridLines() 
    {

		// Frame (Grid border lines, ONLY space pilot could navigate on them)
        //  First row (0)
        gridBorderOnlyForSpacePilotLines.add(new GridLine(0, 0, 
                TOTAL_GAME_CELLS_IN_X_PER_ROW, 0));

        //  First column (0)
        gridBorderOnlyForSpacePilotLines.add(new GridLine(0, 0, 
                0, TOTAL_GAME_CELLS_IN_Y_PER_COLUMN));

        //  Last row
        gridBorderOnlyForSpacePilotLines.add(new GridLine(0, TOTAL_GAME_CELLS_IN_Y_PER_COLUMN, 
                TOTAL_GAME_CELLS_IN_X_PER_ROW, TOTAL_GAME_CELLS_IN_Y_PER_COLUMN));

        //  Last column
        gridBorderOnlyForSpacePilotLines.add(new GridLine(TOTAL_GAME_CELLS_IN_X_PER_ROW, 0, 
            TOTAL_GAME_CELLS_IN_X_PER_ROW, TOTAL_GAME_CELLS_IN_Y_PER_COLUMN));
	}
	
	public void initRegions() 
    {
		int numberOfRelevantGameRegions = 0;
		for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++) 
        {
			for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
//				if (!isOnGridLine(column, row)) 
                {
					regions[row][column] = new Region(row, column);
					numberOfRelevantGameRegions++;
				}
			}
		}

		//  Frame (Grid border lines, ONLY space pilot could navigate on them)
        //  Sets these cells that are on the border of the grid as 'border cells' (ONLY for space pilot to navigate on them)
            //  First row (0)
            for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT != regions[0][column].getRegionStatus())
                {
                    regions[0][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
                    //  Removing from the number of relevant game regions the border regions
                    numberOfRelevantGameRegions--;
                }
            }

            //  First column (0)
            for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++) 
            {
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT != regions[row][0].getRegionStatus())
                {
                    regions[row][0].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
                    //  Removing from the number of relevant game regions the border regions
                    numberOfRelevantGameRegions--;
                }
            }

            //  Last row
            for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT != regions[TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1][column].getRegionStatus())
                {
                    regions[TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
                    //  Removing from the number of relevant game regions the border regions
                    numberOfRelevantGameRegions--;
                }
            }

            //  Last column
            for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++) 
            {
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT != regions[row][TOTAL_GAME_CELLS_IN_X_PER_ROW-1].getRegionStatus())
                {
                    regions[row][TOTAL_GAME_CELLS_IN_X_PER_ROW-1].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
                    //  Removing from the number of relevant game regions the border regions
                    numberOfRelevantGameRegions--;
                }
            }

        //  Set the maximum number of relevant regions for game in a grid
		Region.setMaximumNumberOfRelevantRegionsForGameInGrid(numberOfRelevantGameRegions);
	}

    public void resetRegions() 
    {
		for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++) 
        {
			for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
				if ((null != regions()[row][column])
                        && (false == regions()[row][column].isShown())) 
                {
					board.addRegion(regions()[row][column]);
                    regions()[row][column].show();
				}
			}
		}

        regions()[0][0].resetConqueredRegions();
    }

    public void resetConqueringRegions() 
    {
		for (int row = 1; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++) 
        {
			for (int column = 1; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
                final RegionStatus  REGION_STATUS   = regions()[row][column].getRegionStatus();
                if (RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == REGION_STATUS)
                {   
                    regions()[row][column].setRegionStatus(RegionStatus.REGION_STATUS_EMPTY);
                }
			}
		}
    }

	public void addGridSpacePilotLines(BoardPoint firstSpacePilotLinePoint, BoardPoint secondSpacePilotLinePoint) 
    {
		//  Internal space pilot lines
//		gridSpacePilotLines.add(new GridLine(firstSpacePilotLinePoint, secondSpacePilotLinePoint));
        //  Add the space pilot line to the board
        board.addGridSpacePilotLine(new GridLine(firstSpacePilotLinePoint, secondSpacePilotLinePoint));
    }

	public void hideUnusedGridLines() 
    {
        //  Remove unused space pilot line from the board
        board.hideUnusedGridLines();
    }

	public void addGridToBoard() 
    {
        //  Add grid border lines to board
		for (GridLine gridBorderOnlyForSpacePilotLine : gridBorderOnlyForSpacePilotLines()) 
        {
			board.addGridBorderLine(gridBorderOnlyForSpacePilotLine);
		}
		
        //  Add regions into the board
		for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++) 
        {
			for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
				if (null != regions()[row][column]) 
                {
					board.addRegion(regions()[row][column]);
				}
			}
		}
	}

    /**
        * isCurrentMoveBlockedByLogicOrGameConstraints method
        * 
        * @implNote this function checks if the move from sourcePoint to destinationPoint is blocked 
        *           by any of the grid lines, border lines or space pilot lines, or any other constraints.
        *
        * @param BoardPoint sourcePoint (source point) 
        * @param BoardPoint destinationPoint (destination point)
        * @param GameCharacter gameCharacter (the game character that is trying to move)
        * @return boolean (true if the move is blocked, false otherwise)
        */
	public boolean isCurrentMoveBlockedByLogicOrGameConstraints(BoardPoint sourcePoint, BoardPoint destinationPoint, GameCharacter gameCharacter) 
    {
        boolean             isTheMovingBlockedByLogic       = false;
        final int           DESTINATION_ROW                 = destinationPoint.getRow();
        final int           DESTINATION_COLUMN              = destinationPoint.getColumn();

        //  First blocking moving according to type of object
		if (gameCharacter instanceof SmallEnemy)
        {
            if ((DESTINATION_ROW <= 0 || DESTINATION_ROW >= Grid.getTotalGameCellsInYPerColumn())
                || (DESTINATION_COLUMN <= 0 || DESTINATION_COLUMN >= Grid.getTotalGameCellsInXPerRow()))
            {   
                //  The destination is outside the boundaries
                isTheMovingBlockedByLogic  = true;
            }   
            else
            {
                final RegionStatus  DESTINATION_CELL_REGION_STATUS  = regions[DESTINATION_ROW][DESTINATION_COLUMN].getRegionStatus();

                //  If the method calling is a small enemy
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT ==  DESTINATION_CELL_REGION_STATUS)
                {
                    //  Do NOT let the small enemies to navigate on the borders of the grid
                    isTheMovingBlockedByLogic  = true;
                }
                else if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE ==  DESTINATION_CELL_REGION_STATUS)
                {
                    //  Do NOT let the small enemies to navigate on the borders of the grid
                    isTheMovingBlockedByLogic  = true;
                }
                else if (RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT ==  DESTINATION_CELL_REGION_STATUS)
                {
                    //  Do NOT let the small enemies to navigate over the regions conquered by the space pilot
                    isTheMovingBlockedByLogic  = true;
                }
                else if (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == DESTINATION_CELL_REGION_STATUS)
                {
                    //  Do NOT let the small enemies to navigate over the border regions conquered by the space pilot
                    isTheMovingBlockedByLogic  = true;
                }
            }
        }
        else if (gameCharacter instanceof SpacePilot)
        {
            if ((DESTINATION_ROW < 0 || DESTINATION_ROW >= Grid.getTotalGameCellsInYPerColumn())
                || (DESTINATION_COLUMN < 0 || DESTINATION_COLUMN >= Grid.getTotalGameCellsInXPerRow()))
            {   
                //  The destination is outside the boundaries
                isTheMovingBlockedByLogic  = true;
            }   
            else
            {
                final RegionStatus  DESTINATION_CELL_REGION_STATUS  = regions[DESTINATION_ROW][DESTINATION_COLUMN].getRegionStatus();

                //  If the method calling is the space pilot 
                if (RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT ==  DESTINATION_CELL_REGION_STATUS)
                {
                    //  Do NOT let the space pilot to navigate over the regions conquered yet
                    isTheMovingBlockedByLogic  = true;
                }
                else if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE ==  DESTINATION_CELL_REGION_STATUS)
                {
                    //  Do NOT let the space pilot to navigate on the borders of the grid
                    isTheMovingBlockedByLogic  = true;
                }
                else if (RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING ==  DESTINATION_CELL_REGION_STATUS)
                {
                    //  Do NOT let the space pilot to navigate on the green lines (conquering) he was doing
                    isTheMovingBlockedByLogic  = true;
                }
                else if (RegionStatus.REGION_STATUS_EMPTY == DESTINATION_CELL_REGION_STATUS)
                {
                    final RegionStatus  SOURCE_CELL_REGION_STATUS  = regions[sourcePoint.getRow()][sourcePoint.getColumn()].getRegionStatus();
                    //  If the destination region status is outside safe zone
                    if ((RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT ==  SOURCE_CELL_REGION_STATUS)
                        || (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT ==  SOURCE_CELL_REGION_STATUS))
                    {
                        //  Set this point as the last space pilot in safe zone
                        this.lastSpacePilotPointInSafeZone.set(sourcePoint);
                    }
                }
            }
        }
        else
        { 
            //  Should not happen
            System.out.println("Calling to blocksMove() from an undefined object");
            isTheMovingBlockedByLogic  = true;
        }

        if (false == isTheMovingBlockedByLogic)
        {
            //  Only check if still the moving has not been blocked

            //  Check if any of the lines blocks the move and if so, return true
            for (GridLine gridSpacePilotLine : gridSpacePilotLines) 
            {
                if (gridSpacePilotLine.blocksMove(sourcePoint, destinationPoint))   
                {
                    isTheMovingBlockedByLogic  = true;
                }
            }

            if (false == isTheMovingBlockedByLogic)
            {
                //  Only check if still the moving has not been blocked

                for (GridLine gridBorderOnlyForSpacePilotLine : gridBorderOnlyForSpacePilotLines()) 
                {
                    if (true == gridBorderOnlyForSpacePilotLine.blocksMove(sourcePoint, destinationPoint)) 
                    {
                        isTheMovingBlockedByLogic  =  true;
                    }
                }
            }
        }

        if (false == isTheMovingBlockedByLogic)
        {
            //  Only check if the moving has not been blocked
            if (gameCharacter instanceof SmallEnemy)
            {
                //  Only in case of small enemies
                //  Sets the previous region as empty
// TODO: Causes a problem               this.regions()[sourcePoint.getRow()][sourcePoint.getColumn()].setRegionStatus(RegionStatus.REGION_STATUS_EMPTY);
                //  Sets the current region as 'small enemy over'
// TODO: Causes a problem               this.regions()[destinationPoint.getRow()][destinationPoint.getColumn()].setRegionStatus(RegionStatus.REGION_STATUS_SMALL_ENEMY_OVER);
            }
        }

		//  If reached here, there is no blocking moving by logic
		return isTheMovingBlockedByLogic;
	}

	public boolean isOnGridLine(int x, int y) 
    {
		//  Check if the point is on any of the lines and if so, return true
		for (GridLine gridSpacePilotLine: gridSpacePilotLines) 
        {
			if (true == gridSpacePilotLine.isOnLine(x, y)) 
            {
				return true;
			}
		}

		//  If reached here, it is not on any line
		return false;
	}

    public BoardPoint getLastSpacePilotPointInSafeZone()
    {
        return this.lastSpacePilotPointInSafeZone;
    }

	public int getCurrentNumberOfUnconqueredRegions() 
    {
		return Region.getNumberOfUnconqueredRegions();
	}

	public double getPercentageOfConqueredRegions() 
    {
		return (Region.getNumberOfConqueredRegions() * 100.0 / Region.getMaximumNumberOfRelevantRegionsForGameInGrid());
	}

	public ArrayList<GridLine> gridBorderOnlyForSpacePilotLines() 
    {
		return this.gridBorderOnlyForSpacePilotLines;
	}

	public ArrayList<GridLine> gridSpacePilotLines() 
    {
		return this.gridSpacePilotLines;
	}

	public Region[][] regions() 
    {
		return this.regions;
	}

    public int updateNumberOfConqueredRegions()
    {
        int getNumberOfConqueredRegions = 0;

        for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++)
        {
            for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
                if ((RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT == regions()[row][column].getRegionStatus())
                    || (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == regions()[row][column].getRegionStatus()))
                {
                    getNumberOfConqueredRegions++;
                    board.updateRegion(regions()[row][column]); 
                }
            }
        }

        return getNumberOfConqueredRegions;
    }

    public int calculateNumberOfConqueredRegions()
    {
        int getNumberOfConqueredRegions = 0;

        for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++)
        {
            for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
                if ((RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT == regions()[row][column].getRegionStatus())
                    || (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == regions()[row][column].getRegionStatus()))
                {
                    getNumberOfConqueredRegions++;
                }
            }
        }

        return getNumberOfConqueredRegions;
    }

    /**
        * floodFillAlgorithm algorithm method
        * 
        * @implNote The Flood Fill algorithm is used to determine and modify a connected region 
        *           of a multi-dimensional array (often a 2D grid or image). It is commonly used 
        *           in image editing software for the "bucket fill" tool and in games like Minesweeper.
        *           Starting from a given seed point, the algorithm identifies all adjacent cells that 
        *           share a specific "target" color or value and replaces them with a new "replacement" 
        *           color or value. This process continues recursively or iteratively until the entire 
        *           connected region is processed.
        *
        * @implNote This implementation is not 'generic', it is adapted to the Volfied Remake game
        *
        * @param (Region[][] region) (The region represented as a 2D array of Region, where each one having a region status)
        * @param (BoardPoint location) (the starting location to begin the flood fill)
        * @param (RegionStatus newRegionStatus) (the new region status to set the connected region)
        * @return (Region[][]) (the modified region after the flood fill operation)
        */
    public Region[][] floodFillAlgorithm(Region[][] region, BoardPoint startingBoardPoint, RegionStatus newRegionStatus) 
    {
        final int           STARTING_ROW            = startingBoardPoint.getRow();
        final int           STARTING_COLUMN         = startingBoardPoint.getColumn();

        if ((STARTING_ROW >= 0) && (STARTING_ROW < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN)
            && (STARTING_COLUMN >= 0) || (STARTING_COLUMN < TOTAL_GAME_CELLS_IN_X_PER_ROW)) 
        {
            final RegionStatus  ORIGINAL_REGION_STATUS  = region[STARTING_ROW][STARTING_COLUMN].getRegionStatus();

            if (ORIGINAL_REGION_STATUS != newRegionStatus) 
            { 
                //  Avoid infinite recursion if newRegionStatus is same as original
                dfs(region, startingBoardPoint, ORIGINAL_REGION_STATUS, newRegionStatus);
            }

            //  After the DFS is done, return the modified region
            //  This will have all connected regions with the newRegionStatus
            return region;
        }   
        else    
        {
            //  If the starting point is out of bounds, return the original region
            System.out.println("Starting point is out of bounds: " + startingBoardPoint);
            return null; // or throw an exception
        }
    }

    /**
        * dfs algorithm method
        * 
        * @implNote Recursive (DFS - Depth-First Search) Approach
        *           Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. 
        *           The algorithm starts at the root node (selecting some arbitrary node as the root node in the case of a graph) 
        *           and explores as far as possible along each branch before backtracking. 
        *           Extra memory, usually a stack, is needed to keep track of the nodes discovered so far along a specified 
        *           branch which helps in backtracking of the graph.
        *
        * @implNote This implementation is not 'generic', it is adapted to the Volfied Remake game
        *
        * @param (Region[][] region) (The image represented as a 2D array of Region, where each integer represents a region status)
        * @param (BoardPoint boardPoint) (the node to start the DFS from)
        * @param (int originalRegionStatus) (the original region status to be replaced)
        * @param (int newRegionStatus) (the new region status to set the connected region)
        * @return (none)
        */
    private void dfs(Region[][] region, BoardPoint boardPoint, RegionStatus originalRegionStatus, RegionStatus newRegionStatus) 
    {
        final int           ROW             = boardPoint.getRow();
        final int           COLUMN          = boardPoint.getColumn();
        final RegionStatus  REGION_STATUS   = region[ROW][COLUMN].getRegionStatus();
        //  If the region status is not SMALL_ENEMY_OVER, proceed with DFS
        //  Check if the current region is within bounds and has the original region status
        if (ROW >= 0 && ROW < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN 
            && COLUMN >= 0 && COLUMN < TOTAL_GAME_CELLS_IN_X_PER_ROW 
            && REGION_STATUS == originalRegionStatus) 
        {
            //  Change the region status of the current region
            region[ROW][COLUMN].setRegionStatus(newRegionStatus);

            //  Explore 4-directional neighbors
            final BoardPoint BOARD_POINT_DOWN   = new BoardPoint(ROW + 1, COLUMN);
            final BoardPoint BOARD_POINT_UP     = new BoardPoint(ROW - 1, COLUMN);
            final BoardPoint BOARD_POINT_RIGHT  = new BoardPoint(ROW, COLUMN + 1);
            final BoardPoint BOARD_POINT_LEFT   = new BoardPoint(ROW, COLUMN - 1);
            dfs(region, BOARD_POINT_DOWN, originalRegionStatus, newRegionStatus);   //  Down
            dfs(region, BOARD_POINT_UP, originalRegionStatus, newRegionStatus);     //  Up
            dfs(region, BOARD_POINT_RIGHT, originalRegionStatus, newRegionStatus);  //  Right
            dfs(region, BOARD_POINT_LEFT, originalRegionStatus, newRegionStatus);   //  Left
        }
        else
        {
            //  Base case: out of bounds or not the target region status
        }
    }

    /**
        * zoneCaptureGameCompletionAlgorithm algorithm method
        * 
        * @implNote zoneCaptureGameCompletionAlgorithm is a completion algorithm needed to set the empty
        *           cells between conquered cells as conquered cells
        *
        * @param (none) 
        * @return (none)
        */
    public void zoneCaptureGameCompletionAlgorithm() 
    {
        //  Convert all the temporal 'conquering' cells as 'border conquered'
        FixConversionFromConqueringCellsToBorderConquered();
        
        //  Convert all the temporal cells that are in the border as 'border' conquered
        FixConversionAllTemporalRegionsInBorderAsBorderConquered();

        //  Convert all the border ONLY for pilot cells that are relevant to NOT relevant
        fixBorderOnlyForPilotRegionsFromRelevantToNotRelevant();

        //  Fix issue that there are 'thin' conquered borders close to game borders
        //  The idea is to find all the conquered borders and not relevant game borders
        //  We'll do it in two-phase, first the game borders, later all the others
        fixThinBordersRegions();
    }

    private void FixConversionFromConqueringCellsToBorderConquered()
    {
        //  Convert all the temporal 'conquering' cells as 'border conquered'
        for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++)
        {
            for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
                final RegionStatus  REGION_STATUS   = regions()[row][column].getRegionStatus();
                if (REGION_STATUS == RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING)
                {
                    regions()[row][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT);
                }
            }
        }
    }

    private void FixConversionAllTemporalRegionsInBorderAsBorderConquered()
    {
        //  Convert all the temporal cells that are in the border as 'border' conquered
        for (int row = 1; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1; row++)
        {
            for (int column = 1; column < TOTAL_GAME_CELLS_IN_X_PER_ROW-1; column++) 
            {
                final RegionStatus  REGION_STATUS_CURRENT       = regions()[row][column].getRegionStatus();
                final RegionStatus  REGION_STATUS_LEFT          = regions()[row][column-1].getRegionStatus();
                final RegionStatus  REGION_STATUS_RIGHT         = regions()[row][column+1].getRegionStatus();
                final RegionStatus  REGION_STATUS_BOTTOM        = regions()[row+1][column].getRegionStatus();
                final RegionStatus  REGION_STATUS_BOTTOM_RIGHT  = regions()[row+1][column+1].getRegionStatus();
                final RegionStatus  REGION_STATUS_BOTTOM_LEFT   = regions()[row+1][column-1].getRegionStatus();
                final RegionStatus  REGION_STATUS_TOP           = regions()[row-1][column].getRegionStatus();
                final RegionStatus  REGION_STATUS_TOP_RIGHT     = regions()[row-1][column+1].getRegionStatus();
                final RegionStatus  REGION_STATUS_TOP_LEFT      = regions()[row-1][column-1].getRegionStatus();
                final boolean       CURRENT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES     
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_CURRENT);
                final boolean       LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES    
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_LEFT);
                final boolean       RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES     
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_RIGHT);
                final boolean       BOTTOM_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES    
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_BOTTOM);
                final boolean       BOTTOM_RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES     
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_BOTTOM_RIGHT);
                final boolean       BOTTOM_LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES    
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_BOTTOM_LEFT);
                final boolean       TOP_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES     
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_TOP);
                final boolean       TOP_RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES    
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_TOP_RIGHT);
                final boolean       TOP_LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES    
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_TOP_LEFT);

                if (true == CURRENT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES)
                {
                    //  If the current region is not allowed to small enemies
                    if (((true == LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES) && (true == RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES))
                        && ((true == BOTTOM_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES) && (true == BOTTOM_RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES))
                        && ((true == BOTTOM_LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES) && (true == TOP_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES))
                        && ((true == TOP_RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES) && (true == TOP_LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES)))
                    {
                        //  If all the regions surrounding the cell are NOT allowed to small enemies, therefore current region is conquered
                        regions()[row][column].setRegionStatus(RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
                    }   
                    else if (((true == LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES) && (false == RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES))
                            || (false == LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES) && (true == RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES))
                    {
                        //  If one of the horizontals adjacents regions is NOT allowed to small enemies but the other adjacen region is, 
                        //  therefore current region is border conquered
                        regions()[row][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT);
                    } 
                    else if (((true == TOP_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES) && (false == BOTTOM_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES))
                            || (false == TOP_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES) && (true == BOTTOM_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES))
                    {
                        //  If one of the vertical adjacents regions is NOT allowed to small enemies but the other adjacen region is, 
                        //  therefore current region is border conquered
                        regions()[row][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT);
                    }
                    else
                    {
                        //  If the current region is conquered, but none of the above conditions are met, 
                        //  then it is a border region conquered by space pilot
                        regions()[row][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT);
                    }
                }
                else
                {

                }
            }
        }
    }

    private void fixBorderOnlyForPilotRegionsFromRelevantToNotRelevant()
    {
        //  Convert all the border ONLY for pilot cells that are relevant to NOT relevant
        //  Row 0
        for (int column = 1; column < TOTAL_GAME_CELLS_IN_X_PER_ROW-1; column++)
        {
            final int           ROW                             = 0;
            final RegionStatus  REGION_STATUS_BOTTOM            = regions()[ROW+1][column].getRegionStatus();
            final RegionStatus  REGION_STATUS_BOTTOM_RIGHT      = regions()[ROW+1][column+1].getRegionStatus();
            final boolean       BOTTOM_REGION_CONQUERED         = (REGION_STATUS_BOTTOM == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_BOTTOM == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
            final boolean       BOTTOM_RIGHT_REGION_CONQUERED   = (REGION_STATUS_BOTTOM_RIGHT == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_BOTTOM_RIGHT == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
            
            if ((false == BOTTOM_REGION_CONQUERED) && (true == BOTTOM_RIGHT_REGION_CONQUERED))
            {
                //  Bottom region is NOT conquered but bottom right is, therefore right border region is relevant
                regions()[ROW][column+1].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
            else if ((true == BOTTOM_REGION_CONQUERED) && (true == BOTTOM_RIGHT_REGION_CONQUERED))
            {
                //  Bottom and bottom right regions are conquered, therefore border region is NOT relevant
                regions()[ROW][column+1].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE);
            }
            else if ((true == BOTTOM_REGION_CONQUERED) && (false == BOTTOM_RIGHT_REGION_CONQUERED))
            {
                //  Bottom region is conquered and bottom right region is NOT conquered, therefore border region is relevant
                regions()[ROW][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
            else
            {
                //  Bottom and bottom right regions are NOT conquered, therefore border region is relevant
                regions()[ROW][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
        }

        //  Column 0
        for (int row = 1; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1; row++)
        {
            final int           COLUMN                          = 0;
            final RegionStatus  REGION_STATUS_RIGHT             = regions()[row][COLUMN+1].getRegionStatus();
            final RegionStatus  REGION_STATUS_RIGHT_BOTTOM      = regions()[row+1][COLUMN+1].getRegionStatus();
            final boolean       RIGHT_REGION_CONQUERED  = (REGION_STATUS_RIGHT == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_RIGHT == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
            final boolean       RIGHT_BOTTOM_REGION_CONQUERED   = (REGION_STATUS_RIGHT_BOTTOM == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                || (REGION_STATUS_RIGHT_BOTTOM == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);

            if ((false == RIGHT_REGION_CONQUERED) && (true == RIGHT_BOTTOM_REGION_CONQUERED))
            {
                //  Right region is NOT conquered but bottom right is, therefore bottom right border region is relevant
                regions()[row+1][COLUMN].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
            else if ((true == RIGHT_REGION_CONQUERED) && (true == RIGHT_BOTTOM_REGION_CONQUERED))
            {
                //  Right and bottom right regions are conquered, therefore border region is NOT relevant
                regions()[row+1][COLUMN].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE);
            }
            else if ((true == RIGHT_REGION_CONQUERED) && (false == RIGHT_BOTTOM_REGION_CONQUERED))
            {
                //  Right region is conquered and bottom right region is NOT conquered, therefore border region is relevant
                regions()[row][COLUMN].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
            else
            {
                //  Right and bottom right regions are NOT conquered, therefore border region is relevant
                regions()[row][COLUMN].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
        }

        //  Column TOTAL_GAME_CELLS_IN_X_PER_ROW-1
        for (int row = 1; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1; row++)
        {
            final int           COLUMN                          = TOTAL_GAME_CELLS_IN_X_PER_ROW-1;
            final RegionStatus  REGION_STATUS_LEFT              = regions()[row][COLUMN-1].getRegionStatus();
            final RegionStatus  REGION_STATUS_LEFT_BOTTOM       = regions()[row+1][COLUMN-1].getRegionStatus();
            final boolean       LEFT_REGION_CONQUERED   = (REGION_STATUS_LEFT == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_LEFT == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
            final boolean       LEFT_BOTTOM_REGION_CONQUERED    = (REGION_STATUS_LEFT_BOTTOM == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                || (REGION_STATUS_LEFT_BOTTOM == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);

            if ((false == LEFT_REGION_CONQUERED) && (true == LEFT_BOTTOM_REGION_CONQUERED))
            {
                //  Left region is NOT conquered but bottom left is, therefore bottom left border region is relevant
                regions()[row+1][COLUMN].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
            else if ((true == LEFT_REGION_CONQUERED) && (true == LEFT_BOTTOM_REGION_CONQUERED))
            {
                //  Left and bottom left regions are conquered, therefore border region is NOT relevant
                regions()[row+1][COLUMN].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE);
            }
            else if ((true == LEFT_REGION_CONQUERED) && (false == LEFT_BOTTOM_REGION_CONQUERED))
            {
                //  Left region is conquered and bottom left region is NOT conquered, therefore border region is relevant
                regions()[row][COLUMN].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
            else
            {
                //  Left and bottom left regions are NOT conquered, therefore border region is relevant
                regions()[row][COLUMN].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
        }

        //  Row TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1
        for (int column = 1; column < TOTAL_GAME_CELLS_IN_X_PER_ROW-1; column++)
        {
            final int           ROW                         = TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1;
            final RegionStatus  REGION_STATUS_TOP           = regions()[ROW-1][column].getRegionStatus();
            final RegionStatus  REGION_STATUS_TOP_RIGHT     = regions()[ROW-1][column+1].getRegionStatus();
            final boolean       TOP_REGION_CONQUERED        = (REGION_STATUS_TOP == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_TOP == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
            final boolean       TOP_RIGHT_REGION_CONQUERED  = (REGION_STATUS_TOP_RIGHT == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_TOP_RIGHT == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
            
            if ((false == TOP_REGION_CONQUERED) && (true == TOP_RIGHT_REGION_CONQUERED))
            {
                //  Top region is NOT conquered but top right is, therefore right border region is relevant
                regions()[ROW][column+1].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
            else if ((true == TOP_REGION_CONQUERED) && (true == TOP_RIGHT_REGION_CONQUERED))
            {
                //  Top and top right regions are conquered, therefore border region is NOT relevant
                regions()[ROW][column+1].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE);
            }
            else if ((true == TOP_REGION_CONQUERED) && (false == TOP_RIGHT_REGION_CONQUERED))
            {
                //  Top region is conquered and top right region is NOT conquered, therefore border region is relevant
                regions()[ROW][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
            else
            {
                //  Top and top right regions are NOT conquered, therefore border region is relevant
                regions()[ROW][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
            }
        }
    }

    private void fixThinBordersRegions()
    {
        //  Fix issue that there are 'thin' conquered borders close to game borders
        //  The idea is to find all the conquered borders and not relevant game borders
        //  We'll do it in two-phase, first the game borders, later all the others
        for (int row = 1; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1; row++)
        {   
            final RegionStatus  REGION_STATUS_LEFT  = regions()[row][TOTAL_GAME_CELLS_IN_X_PER_ROW-2].getRegionStatus();
            final RegionStatus  REGION_STATUS_RIGHT = regions()[row][1].getRegionStatus();
            final boolean       LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES    
                                            = isRegionNotAllowedToSmallEnemies(REGION_STATUS_LEFT);
            final boolean       RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES     
                                            = isRegionNotAllowedToSmallEnemies(REGION_STATUS_RIGHT);

            if (true == LEFT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES)
            {
                //  In this configuration set the current region as game bordered conquered by space pilot
                regions()[row][TOTAL_GAME_CELLS_IN_X_PER_ROW-1].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE);
            }

            if (true == RIGHT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES)
            {
                //  In this configuration set the current region as game bordered conquered by space pilot
                regions()[row][0].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE);
            }
        }        

        for (int column = 1; column < TOTAL_GAME_CELLS_IN_X_PER_ROW-1; column++)
        {   
            final RegionStatus  REGION_STATUS_TOP       = regions()[TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-2][column].getRegionStatus();
            final RegionStatus  REGION_STATUS_BOTTOM    = regions()[1][column].getRegionStatus();
            final boolean       TOP_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES    
                                            = isRegionNotAllowedToSmallEnemies(REGION_STATUS_TOP);
            final boolean       BOTTOM_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES     
                                            = isRegionNotAllowedToSmallEnemies(REGION_STATUS_BOTTOM);

            if (true == TOP_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES)
            {
                //  In this configuration set the current region as game bordered conquered by space pilot
                regions()[TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE);
            }

            if (true == BOTTOM_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES)
            {
                //  In this configuration set the current region as game bordered conquered by space pilot
                regions()[0][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE);
            }
        }       

        for (int row = 1; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1; row++)
        {
            for (int column = 1; column < TOTAL_GAME_CELLS_IN_X_PER_ROW-1; column++) 
            {
                final RegionStatus  REGION_STATUS_CURRENT       = regions()[row][column].getRegionStatus();
                final RegionStatus  REGION_STATUS_LEFT          = regions()[row][column-1].getRegionStatus();
                final RegionStatus  REGION_STATUS_RIGHT         = regions()[row][column+1].getRegionStatus();
                final RegionStatus  REGION_STATUS_BOTTOM        = regions()[row+1][column].getRegionStatus();
                final RegionStatus  REGION_STATUS_TOP           = regions()[row-1][column].getRegionStatus();
                final boolean       CURRENT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES     
                                                = isRegionNotAllowedToSmallEnemies(REGION_STATUS_CURRENT);

                if (true == CURRENT_REGION_NOT_ALLOWED_TO_SMALL_ENEMIES)
                {
                    if ((RegionStatus.REGION_STATUS_EMPTY == REGION_STATUS_LEFT)
                        || (RegionStatus.REGION_STATUS_EMPTY == REGION_STATUS_RIGHT)
                        || (RegionStatus.REGION_STATUS_EMPTY == REGION_STATUS_BOTTOM)
                        || (RegionStatus.REGION_STATUS_EMPTY == REGION_STATUS_TOP))
                    {
                        //  In this configuration set the current region as bordered conquered by space pilot
                        regions()[row][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT);
                    }
                }
            }
        }
    }

    public static boolean isRegionNotAllowedToSmallEnemies(RegionStatus regionStatus)
    {
        final boolean    REGION_NOT_ALLOWED_TO_SMALL_ENEMIES = (regionStatus == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                                || (regionStatus == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT)
                                                || (regionStatus == RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT)
                                                || (regionStatus == RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE);

        return REGION_NOT_ALLOWED_TO_SMALL_ENEMIES;
    } 
  

    /**
        * moveDirection method
        * 
        * @implNote this function returns the direction while an object is moving from
        *           source to destination
        *
        * @param BoardPoint sourcePoint (source point) 
        * @param BoardPoint destinationPoint (destination point)
        * @return Direction (move direction)
        */
	public Direction moveDirection(BoardPoint sourcePoint, BoardPoint destinationPoint) 
    {
        Direction   moveDirection   = Direction.STOPPED;

        if (!sourcePoint.isEqual(destinationPoint))
        {
            //  If both points are different
            if (sourcePoint.getRow() == destinationPoint.getRow())
            {
                //  If both points are in the same row
                if (destinationPoint.getColumn() > sourcePoint.getColumn())
                {   
                    moveDirection   = Direction.EAST;
                }
                else
                {
                    moveDirection   = Direction.WEST;
                }
            }
            else if (sourcePoint.getColumn() == destinationPoint.getColumn())
            {
                //  If both points are in the same column
                if (destinationPoint.getRow() > sourcePoint.getRow())
                {   
                    moveDirection   = Direction.SOUTH;
                }
                else
                {
                    moveDirection   = Direction.NORTH;
                }
            }
            else
            {
                //  The points are in different rows and columns
                if (destinationPoint.getColumn() > sourcePoint.getColumn())
                {   
                    if (destinationPoint.getRow() > sourcePoint.getRow())
                    {
                        moveDirection   = Direction.SOUTH_EAST;
                    }
                    else
                    {
                        moveDirection   = Direction.NORTH_EAST;
                    }
                }
                else
                {
                    if (destinationPoint.getRow() > sourcePoint.getRow())
                    {
                        moveDirection   = Direction.SOUTH_WEST;
                    }
                    else
                    {
                        moveDirection   = Direction.NORTH_WEST;
                    }
                }
            }
        }

        return moveDirection;

    }
}