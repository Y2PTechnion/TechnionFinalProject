/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            MyGame.java
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
		WEST (1,0, 0),
		EAST (-1,0, 1),
		NORTH (0,-1, 2),
		SOUTH (0,1, 3),
        STOPPED(0,0, 4),
        NORTH_WEST (1,-1, 5),
        NORTH_EAST (-1,-1, 6),
        SOUTH_WEST (1,1, 7),
        SOUTH_EAST (-1,1, 8);
		
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
    //  Region[column][row]
	private Region[][]          regions                             = new Region[TOTAL_GAME_CELLS_IN_Y_PER_COLUMN][TOTAL_GAME_CELLS_IN_X_PER_ROW];
	
	public Grid(Board board) 
    {
		this.board  = board;
		initGridLines();
		initRegions();
	}

    public boolean getIsBoardPointACornerForEnemies(BoardPoint boardPoint)
    {
        final int       NORTH_CORNER    = BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_Y_PER_COLUMN/2;
        final int       SOUTH_CORNER    = TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-NORTH_CORNER;
        final int       WEST_CORNER     = BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_X_PER_ROW/2;
        final int       EAST_CORNER     = TOTAL_GAME_CELLS_IN_X_PER_ROW-WEST_CORNER;
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
        final int   SOUTH_CORNER    = TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-NORTH_CORNER;
        final int   WEST_CORNER     = BORDER_CELLS_ONLY_FOR_SPACE_PILOT_IN_X_PER_ROW/2;
        final int   EAST_CORNER     = TOTAL_GAME_CELLS_IN_X_PER_ROW-WEST_CORNER;
  

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

        Region.resetConqueredRegions();
    }

	public void addGridSpacePilotLines(BoardPoint firstSpacePilotLinePoint, BoardPoint secondSpacePilotLinePoint) 
    {
		//  Internal space pilot lines
//		gridSpacePilotLines.add(new GridLine(firstSpacePilotLinePoint, secondSpacePilotLinePoint));
        //  Add the space pilot line to the board
        board.addGridSpacePilotLine(new GridLine(firstSpacePilotLinePoint, secondSpacePilotLinePoint));
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
        * blocksMove method
        * 
        * @implNote this function checks if the move from sourcePoint to destinationPoint is blocked 
        *           by any of the grid lines, border lines or space pilot lines, or any other constraints.
        *
        * @param BoardPoint sourcePoint (source point) 
        * @param BoardPoint destinationPoint (destination point)
        * @param GameCharacter gameCharacter (the game character that is trying to move)
        * @return boolean (true if the move is blocked, false otherwise)
        */
	public boolean blocksMove(BoardPoint sourcePoint, BoardPoint destinationPoint, GameCharacter gameCharacter) 
    {
        boolean             isTheMovingBlockedByLogic       = false;
        final int           DESTINATION_ROW                 = destinationPoint.getRow();
        final int           DESTINATION_COLUMN              = destinationPoint.getColumn();
        final RegionStatus  DESTINATION_CELL_REGION_STATUS  = regions[DESTINATION_ROW][DESTINATION_COLUMN].getRegionStatus();

        //  First blocking moving according to type of object
		if (gameCharacter instanceof SmallEnemy)
        {


            if ((DESTINATION_ROW < 0 || DESTINATION_ROW >= Grid.getTotalGameCellsInYPerColumn())
                || (DESTINATION_COLUMN < 0 || DESTINATION_COLUMN >= Grid.getTotalGameCellsInXPerRow()))
            {   
                //  The destination is outside the boundaries
                isTheMovingBlockedByLogic  = true;
            }   
            else
            {
                //  If the method calling is a small enemy
 
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT ==  DESTINATION_CELL_REGION_STATUS)
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
            //  If the method calling is the space pilot 
            if (RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT ==  DESTINATION_CELL_REGION_STATUS)
            {
                //  Do NOT let the space pilot to navigate over the regions conquered yet
                isTheMovingBlockedByLogic  = true;
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
        final RegionStatus  ORIGINAL_REGION_STATUS  = region[STARTING_ROW][STARTING_COLUMN].getRegionStatus();

        if (ORIGINAL_REGION_STATUS != newRegionStatus) 
        { 
            //  Avoid infinite recursion if newRegionStatus is same as original
            if (false == dfs(region, startingBoardPoint, ORIGINAL_REGION_STATUS, newRegionStatus))
            {
                //  If one of the region status is SMALL_ENEMY_OVER
                region  = null;
            }
        }

        //  After the DFS is done, return the modified region
        //  This will have all connected regions with the newRegionStatus
        return region;
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
        * @return (boolean) (returns true if the DFS was successful, false otherwise)
        */
    private boolean dfs(Region[][] region, BoardPoint boardPoint, RegionStatus originalRegionStatus, RegionStatus newRegionStatus) 
    {
        final int           ROW             = boardPoint.getRow();
        final int           COLUMN          = boardPoint.getColumn();
        final RegionStatus  REGION_STATUS   = region[ROW][COLUMN].getRegionStatus();
        final boolean       IS_REGION_STATUS_SMALL_ENEMY_OVER 
                                            = (RegionStatus.REGION_STATUS_SMALL_ENEMY_OVER == REGION_STATUS);  

        if (false == IS_REGION_STATUS_SMALL_ENEMY_OVER) 
        {
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
                if (false == dfs(region, BOARD_POINT_DOWN, originalRegionStatus, newRegionStatus))  //  Down
                {
                    //  If the DFS down was not successful, return false
                    return false;
                }
                if (false == dfs(region, BOARD_POINT_UP, originalRegionStatus, newRegionStatus))    //  Up
                {
                    //  If the DFS down was not successful, return false
                    return false;
                }
                if (false == dfs(region, BOARD_POINT_RIGHT, originalRegionStatus, newRegionStatus)) //  Right
                {
                    //  If the DFS down was not successful, return false
                    return false;
                }
                if (false == dfs(region, BOARD_POINT_LEFT, originalRegionStatus, newRegionStatus))  //  Left
                {
                    //  If the DFS down was not successful, return false
                    return false;
                }
            }
            else
            {
                //  Base case: out of bounds or not the target region status
            }            

            return true;
        }
    
        return false;   //  If the region status is SMALL_ENEMY_OVER
    }

    /**
        * volfiedGameCompletionAlgorithm algorithm method
        * 
        * @implNote volfiedGameCompletionAlgorithm is a completion algorithm needed to set the empty
        *           cells between conquered cells as conquered cells
        *
        * @param (none) 
        * @return (none)
        */
    public void volfiedGameCompletionAlgorithm() 
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

        //  Convert all the temporal cells that are in the border as 'border conquered' horizontal
        for (int row = 1; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1; row++)
        {
            for (int column = 1; column < TOTAL_GAME_CELLS_IN_X_PER_ROW-2; column++) 
            {
                final RegionStatus  REGION_STATUS_CURRENT       = regions()[row][column].getRegionStatus();
                final RegionStatus  REGION_STATUS_RIGHT         = regions()[row][column+1].getRegionStatus();
                final boolean       CURRENT_REGION_CONQUERED    = (REGION_STATUS_CURRENT == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_CURRENT == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
                final boolean       RIGHT_REGION_CONQUERED      = (REGION_STATUS_RIGHT == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_RIGHT == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);

                if ((true == CURRENT_REGION_CONQUERED) && (false == RIGHT_REGION_CONQUERED))
                {
                    //  Left region is conquered, right region is not, therefore left region is border
                    regions()[row][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT);
                }
                else if ((false == CURRENT_REGION_CONQUERED) && (true == RIGHT_REGION_CONQUERED))
                {
                    //  Left region is not conquered, right region is conquered, therefore right region is border
                    regions()[row][column+1].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT);
                }
                else if ((true == CURRENT_REGION_CONQUERED) && (true == RIGHT_REGION_CONQUERED))
                {
                    //  Left and right regions are conquered, therefore right region is not border
                    regions()[row][column+1].setRegionStatus(RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
                }
                else if ((false == CURRENT_REGION_CONQUERED) && (false == RIGHT_REGION_CONQUERED))
                {
                    //  Nothing to do
                }
            }
        }

        //  Convert all the temporal cells that are in the border as 'border conquered' vertical
        for (int column = 1; column < TOTAL_GAME_CELLS_IN_X_PER_ROW-1; column++)
        {
            for (int row = 1; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-2; row++)     
            {
                final RegionStatus  REGION_STATUS_CURRENT       = regions()[row][column].getRegionStatus();
                final RegionStatus  REGION_STATUS_NEXT          = regions()[row+1][column].getRegionStatus();
                final boolean       CURRENT_REGION_CONQUERED    = (REGION_STATUS_CURRENT == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_CURRENT == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
                final boolean       NEXT_REGION_CONQUERED       = (REGION_STATUS_NEXT == RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                                        || (REGION_STATUS_NEXT == RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);

                if ((true == CURRENT_REGION_CONQUERED) && (false == NEXT_REGION_CONQUERED))
                {
                    //  Top region is conquered, bottom region is not, therefore top region is border
                    regions()[row][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT);
                }
                else if ((false == CURRENT_REGION_CONQUERED) && (true == NEXT_REGION_CONQUERED))
                {
                    //  Top region is not conquered, bottom region is conquered, therefore bottom region is border
                    regions()[row+1][column].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT);
                }
                else if ((true == CURRENT_REGION_CONQUERED) && (true == NEXT_REGION_CONQUERED))
                {
                    //  Top and bottom regions are conquered, therefore bottom region is not border
                    regions()[row+1][column].setRegionStatus(RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
                }
                else if ((false == CURRENT_REGION_CONQUERED) && (false == NEXT_REGION_CONQUERED))
                {
                    //  Nothing to do
                }
            }
        }
    }
}