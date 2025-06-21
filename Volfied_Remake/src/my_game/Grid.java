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

public class Grid {
	
	public enum Direction {
		RIGHT (1,0, 0),
		LEFT(-1,0, 1),
		UP (0,-1, 2),
		DOWN(0,1, 3),
        STOPPED(0,0, 4);
		
		private final int xVec, yVec, index;
		private Direction(int xVec, int yVec, int index) {
			this.xVec   = xVec;
			this.yVec   = yVec;
            this.index  = index;
		}

		public int xVec() {
			return this.xVec;
		}

		public int yVec() {
			return this.yVec;
		}

		public int index() {
			return this.index;
		}
	}
	
//  Private constants for the class
    // Grid size in cells
    // Each cell is 18x18 pixels, so the grid size in pixels is 900x540
	public static final int     GRID_X_SIZE_IN_CELLS    = 50;
	public static final int     GRID_Y_SIZE_IN_CELLS    = 30;	
	
//  Private variables for the class
	private Board               board                   = null;
	private ArrayList<GridLine> gridLimitLines          = new ArrayList<GridLine>();
    private ArrayList<GridLine> lines                   = new ArrayList<GridLine>();
	private Region[][]          regions                 = new Region[GRID_X_SIZE_IN_CELLS][GRID_Y_SIZE_IN_CELLS];
	
	public Grid(Board board) {
		this.board  = board;
		initGridLines();
		initRegions();
	}

	private void initGridLines() {
		// Frame
		gridLimitLines.add(new GridLine(0, 0, GRID_X_SIZE_IN_CELLS, 0));
		gridLimitLines.add(new GridLine(0, 0, 0, GRID_Y_SIZE_IN_CELLS));
		gridLimitLines.add(new GridLine(0, GRID_Y_SIZE_IN_CELLS, GRID_X_SIZE_IN_CELLS, GRID_Y_SIZE_IN_CELLS));
		gridLimitLines.add(new GridLine(GRID_X_SIZE_IN_CELLS, 0, GRID_X_SIZE_IN_CELLS, GRID_Y_SIZE_IN_CELLS));
		
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
	
	public void initRegions() {
		int numRegions = 0;
		for (int y = 1; y < GRID_Y_SIZE_IN_CELLS; y++) {
			for (int x = 1; x < GRID_X_SIZE_IN_CELLS; x++) {
				if (!isOnGridLine(x, y)) {
					regions[x][y] = new Region(x,y);
					numRegions++;
				}
			}
		}
        //  Set the maximum number of regions in a grid
		Region.setMaximumNumberOfRegionsInGrid(numRegions);
	}

    public void resetRegions() {
		for (int y = 1; y < GRID_Y_SIZE_IN_CELLS; y++) {
			for (int x = 1; x < GRID_X_SIZE_IN_CELLS; x++) {
				if ((null != regions()[x][y])
                        && (false == regions()[x][y].isShown())) {
					board.addRegion(regions()[x][y]);
                    regions()[x][y].show();
				}
			}
		}
        Region.resetConqueredRegions();
    }

	public void addGridLines(BoardPoint p1, BoardPoint p2) {
		// Internal lines
		lines.add(new GridLine(p1, p2));
    }

	public void addGridToBoard() {
		int i = 0;
		for (GridLine gridLimitLine : gridLimitLines()) {
			board.addGridLimitLine(gridLimitLine, ++i);
		}
		
		for (int y = 1; y < GRID_Y_SIZE_IN_CELLS; y++) {
			for (int x = 1; x < GRID_X_SIZE_IN_CELLS; x++) {
				if (null != regions()[x][y]) {
					board.addRegion(regions()[x][y]);
				}
			}
		}
	}

	public boolean blocksMove(BoardPoint p1, BoardPoint p2) {
		
		//  Check if any of the lines blocks the move and if so, return true
		for (GridLine line : lines) {
			if (line.blocksMove(p1, p2)) {
				return true;
			}
		}

        for (GridLine gridLimitLine : gridLimitLines()) {
			if (gridLimitLine.blocksMove(p1, p2)) {
				return true;
			}
        }

		//  If reached here, there is no blocking line
		return false;
	}

	public boolean isOnGridLine(int x, int y) {
		
		//Check if the point is on any of the lines and if so, return true
		for (GridLine line: lines) {
			if (line.isOnLine(x, y)) {
				return true;
			}
		}
		//If reached here, it is not on any line
		return false;
	}

	public int getCurrentNumberOfUnconqueredRegions( ) {
		return Region.getNumberOfUnconqueredRegions();
	}

	public double getPercentageOfConqueredRegions( ) {
		return (Region.getNumberOfConqueredRegions() * 100.0 / Region.getMaximumNumberOfRegionsInGrid());
	}

	public void setRegionAsConquered() {
		Region.setConqueredRegion();
	}
	
	public ArrayList<GridLine> gridLimitLines() {
		return this.gridLimitLines;
	}

	public ArrayList<GridLine> lines() {
		return this.lines;
	}

	public Region[][] regions() {
		return this.regions;
	}
}