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
		RIGHT (1,0, 0),
		LEFT(-1,0, 1),
		UP (0,-1, 2),
		DOWN(0,1, 3),
        STOPPED(0,0, 4);
		
		private final int xVec, yVec, index;
		private Direction(int xVec, int yVec, int index) 
        {
			this.xVec   = xVec;
			this.yVec   = yVec;
            this.index  = index;
		}

		public int xVec() 
        {
			return this.xVec;
		}

		public int yVec() 
        {
			return this.yVec;
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
	private Region[][]          regions                             = new Region[TOTAL_GAME_CELLS_IN_X_PER_ROW][TOTAL_GAME_CELLS_IN_Y_PER_COLUMN];
	
	public Grid(Board board) 
    {
		this.board  = board;
		initGridLines();
		initRegions();
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
            
		// Inner lines
		
		//2 lines on 2nd row and 2 symmetric from bottom
//		lines.add(new MazeLine(2,2,10,2));
//		lines.add(new MazeLine(12,2,19,2));
//		lines.add(new MazeLine(2,MAZE_Y_SIZE - 2,10, MAZE_Y_SIZE- 2));
//		lines.add(new MazeLine(12,MAZE_Y_SIZE- 2,19,MAZE_Y_SIZE - 2));
		
		// 1 line on 4th row and one symmetric from bottom
//		lines.add(new MazeLine(2,4,19,4));
//		lines.add(new MazeLine(2,MAZE_Y_SIZE - 4,19, MAZE_Y_SIZE- 4));

		// 2 lines in the middle to create a 1 unit wide passage
//		lines.add(new MazeLine(11,5,11,6));
//		lines.add(new MazeLine(11,MAZE_Y_SIZE - 5,11, MAZE_Y_SIZE- 6));

		//2 lines on 6th row and 2 symmetric from bottom
//		lines.add(new MazeLine(2,6,9,6));
//		lines.add(new MazeLine(13,6,19,6));
//		lines.add(new MazeLine(2,MAZE_Y_SIZE - 6,9, MAZE_Y_SIZE- 6));
//		lines.add(new MazeLine(13,MAZE_Y_SIZE- 6,19,MAZE_Y_SIZE - 6));
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
					regions[column][row] = new Region(column, row);
					numberOfRelevantGameRegions++;
				}
			}
		}

		//  Frame (Grid border lines, ONLY space pilot could navigate on them)
        //  Sets these cells that are on the border of the grid as 'border cells' (ONLY for space pilot to navigate on them)
            //  First row (0)
            for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT != regions[column][0].getRegionStatus())
                {
                    regions[column][0].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
                    //  Removing from the number of relevant game regions the border regions
                    numberOfRelevantGameRegions--;
                }
            }

            //  First column (0)
            for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++) 
            {
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT != regions[0][row].getRegionStatus())
                {
                    regions[0][row].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
                    //  Removing from the number of relevant game regions the border regions
                    numberOfRelevantGameRegions--;
                }
            }

            //  Last row
            for (int column = 0; column < TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
            {
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT != regions[column][TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1].getRegionStatus())
                {
                    regions[column][TOTAL_GAME_CELLS_IN_Y_PER_COLUMN-1].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
                    //  Removing from the number of relevant game regions the border regions
                    numberOfRelevantGameRegions--;
                }
            }

            //  Last column
            for (int row = 0; row < TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++) 
            {
                if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT != regions[TOTAL_GAME_CELLS_IN_X_PER_ROW-1][row].getRegionStatus())
                {
                    regions[TOTAL_GAME_CELLS_IN_X_PER_ROW-1][row].setRegionStatus(RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT);
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
				if ((null != regions()[column][row])
                        && (false == regions()[column][row].isShown())) 
                {
					board.addRegion(regions()[column][row]);
                    regions()[column][row].show();
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
				if (null != regions()[column][row]) 
                {
					board.addRegion(regions()[column][row]);
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
        boolean isTheMovingBlockedByLogic   = false;

        //  First blocking moving according to type of object
		if (gameCharacter instanceof SmallEnemy)
        {
            //  If the method calling is a small enemy
            final RegionStatus  SMALL_ENEMY_DESTINATION_CELL_REGION_STATUS  = regions[destinationPoint.getX()][destinationPoint.getY()].getRegionStatus();

            if (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT ==  SMALL_ENEMY_DESTINATION_CELL_REGION_STATUS)
            {
                //  Do NOT let the small enemies to navigate on the borders of the grid
                isTheMovingBlockedByLogic  = true;
            }
            else if (RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT ==  SMALL_ENEMY_DESTINATION_CELL_REGION_STATUS)
            {
                //  Do NOT let the small enemies to navigate over the regions conquered by the space pilot
                isTheMovingBlockedByLogic  = true;
            }
        }
        else if (gameCharacter instanceof SpacePilot)
        {
            //  If the method calling is the space pilot 
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
                    if (gridBorderOnlyForSpacePilotLine.blocksMove(sourcePoint, destinationPoint)) 
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
		//Check if the point is on any of the lines and if so, return true
		for (GridLine gridSpacePilotLine: gridSpacePilotLines) 
        {
			if (gridSpacePilotLine.isOnLine(x, y)) 
            {
				return true;
			}
		}

		//If reached here, it is not on any line
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

	public void setRegionAsConquered(Region region) 
    {
		region.setConqueredRegion();
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

    /**
        * floodFill algorithm method
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
        * @param (Region[][] region) (The region represented as a 2D array of integers, where each integer represents a color)
        * @param (BoardPoint location) (the starting location to begin the flood fill)
        * @param (int newColor) (the new color to fill the connected region with)
        * @return (Region[][]) (the modified region after the flood fill operation)
        */
    public Region[][] floodFill(Region[][] region, BoardPoint startingBoardPoint, RegionStatus newRegionStatus) 
    {
        final int startingRow                   = startingBoardPoint.getY();
        final int startingColumn                = startingBoardPoint.getX();
        final RegionStatus originalRegionStatus = region[startingColumn][startingRow].getRegionStatus();

        if (originalRegionStatus != newRegionStatus) 
        { 
            //  Avoid infinite recursion if newColor is same as original
            dfs(region, startingBoardPoint, originalRegionStatus, newRegionStatus);
        }

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
        * @param (int[][] image) (The image represented as a 2D array of integers, where each integer represents a color)
        * @param (BoardPoint location) (the node to start the DFS from)
        * @param (int originalColor) (the original color to be replaced)
        * @param (int newColor) (the new color to fill the connected region with)
        * @return (none)
        */
    private void dfs(Region[][] region, BoardPoint boardPoint, RegionStatus originalRegionStatus, RegionStatus newRegionStatus) 
    {
        int row     = boardPoint.getY();
        int column  = boardPoint.getX();

        if (row < 0 || row >= TOTAL_GAME_CELLS_IN_Y_PER_COLUMN 
            || column < 0 || column >= TOTAL_GAME_CELLS_IN_X_PER_ROW 
            || region[column][row].getRegionStatus() != originalRegionStatus) 
        {
            //  Base case: out of bounds or not the target color
            return; 
        }

        //  Change the color of the current pixel
        region[column][row].setRegionStatus(newRegionStatus); 

        //  Explore 4-directional neighbors
        final BoardPoint boardPointDown     = new BoardPoint(column, row + 1);
        final BoardPoint boardPointUp       = new BoardPoint(column, row - 1);        
        final BoardPoint boardPointRight    = new BoardPoint(column + 1, row);
        final BoardPoint boardPointLeft     = new BoardPoint(column - 1, row);
        dfs(region, boardPointDown, originalRegionStatus, newRegionStatus);    //  Down
        dfs(region, boardPointUp, originalRegionStatus, newRegionStatus);      //  Up
        dfs(region, boardPointRight, originalRegionStatus, newRegionStatus);   //  Right
        dfs(region, boardPointLeft, originalRegionStatus, newRegionStatus);    //  Left
    }
}