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

import java.awt.Color;
import my_base.MyContent;
import my_game.Region.RegionStatus;

public class GameControl 
{
//  Private variables for the class
    private MyContent           content                                     = null;
	private Board               board                                       = null;
    static  private boolean     getSpacePilotIsOutsideSafeZone              = false;
    static  private BoardPoint  boardPointInsideThePotentialConqueredZone   = new BoardPoint(0, 0);
    static  private int         spacePilotLeftistColumnWhenOutsideSafeZone  = Grid.getTotalGameCellsInXPerRow();
    static  private int         spacePilotRightestColumnWhenOutsideSafeZone = 0;
    static  private int         spacePilotSouthestRowWhenOutsideSafeZone    = Grid.getTotalGameCellsInYPerColumn();
    static  private int         spacePilotNorthestRowWhenOutsideSafeZone    = 0;

    public GameControl(MyContent content) 
    {
        this.content    = content;
        this.board      = content.getBoard();
    }

	public void gameStep() 
    {
        Region                  region                  = conquerCurrentRegion(content.spacePilot().getLocation());
        final BoardPoint        sourceLocation          = content.spacePilot().getLocation();
        Region[][]              regionsAfterFloodFill   = null;

        //  Logic section of gameStep()         
            //  Space pilot moving method
            final boolean       regionsWereConquered    = spacePilotMoving(sourceLocation, regionsAfterFloodFill);
            final BoardPoint    destinationLocation     = content.spacePilot().getLocation();
            //  Try to move the small enemies randomally
            content.smallEnemies().move();
            //  Handle collisions between small enemies and space pilot
		    handleCollisions();

        //  Graphics (canvas) section of gameStep()
        //      Update space pilot in canvas
		board.updateSpacePilotInCanvas();

        if ((sourceLocation.getColumn() != destinationLocation.getColumn())
            || (sourceLocation.getRow() != destinationLocation.getRow()))
        {
            //  Update grid space pilot lines in canvas
            content.grid().addGridSpacePilotLines(sourceLocation, destinationLocation);
        }

        if (true == regionsWereConquered)
        {
            content.grid().volfiedGameCompletionAlgorithm();
            //  Update the number of conquered regions
            final int NUMBER_OF_CONQUERED_REGIONS   = content.grid().updateNumberOfConqueredRegions();
            content.grid().regions()[0][0].setNumberOfConqueredRegions(NUMBER_OF_CONQUERED_REGIONS);

            

//            for (int row = 0; row < Grid.TOTAL_GAME_CELLS_IN_Y_PER_COLUMN; row++)
//            {
//                for (int column = 0; column < Grid.TOTAL_GAME_CELLS_IN_X_PER_ROW; column++) 
//                {
//                    if (RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT == regionsAfterFloodFill[row][column].getRegionStatus())
//                    {
//                        content.getBoard().updateRegion(regionsAfterFloodFill[row][column]);   
//                   }
//                }
//            }
        }

        //      Update small enemies in canvas
		board.updateSmallEnemiesInCanvas();

        //  Update section of gameStep()
		if (null != region) 
        {
//  TODO: Update region to new color			board.updateRegion(region);
   //         board.updateSpacePilotGridLine(rg);
            System.out.println("Space pilot at: " + region.getLocation().getRow() + ", " 
                + region.getLocation().getColumn() + ", " + region.getGuid() + ", " + region.getRegionStatus());
		}

        content.score().setConqueredRegionsPercentage(content.grid().getPercentageOfConqueredRegions());
		board.updateScore();
		content.statusLine().refresh();
		board.updateStatusLine();
//		content.historyRecorder().recordState();
//      checkGameOver();
	}

	private void handleCollisions() 
    {
		SpacePilot 		spacePilot   	= content.spacePilot();
		SmallEnemies	smallEnemies	= content.smallEnemies();
		
		for (SmallEnemy s : smallEnemies.getSmallEnemies()) 
        {
			if (s.getLocation().isEqual(spacePilot.getLocation())) 
            {
				content.score().reset();
				content.statusLine().showText("Oops ...", Color.RED, 2000);
                //  Reset the grid
                content.grid().resetRegions();
				return;
			}
		}
	}

	public Region conquerCurrentRegion(BoardPoint location) 
    {
        final   int locationRow     = location.getRow();
        final   int locationColumn  = location.getColumn();

		Region      region          = content.grid().regions()[locationRow][locationColumn]; 

		if (true == region.isShown()) 
        {
			region.hide();
//			content.score().add(1);
//			content.grid().setRegionAsConquered(region);

            //  Next lines won't be relevant later, only for fun by now
			if (0 == content.grid().getCurrentNumberOfUnconqueredRegions()) 
            {
//				content.statusLine().showText("Great JOB !!!", Color.YELLOW, 5000);
			}

			return region; 
		}

		return null;
	}

    private boolean spacePilotMoving(BoardPoint sourceLocation, Region[][] regionsAfterFloodFill)
    {
        boolean     regionsWereConquered                        = false;

        //  Try to move the space pilot, if it was moved
        content.spacePilot().move();

        //  Get space pilot destination location
        final BoardPoint    DESTINATION_LOCATION        = content.spacePilot().getLocation();
        Region              destinationRegion           = content.grid().regions()[DESTINATION_LOCATION.getRow()][DESTINATION_LOCATION.getColumn()]; 
        final RegionStatus  DESTINATION_REGION_STATUS   = destinationRegion.getRegionStatus(); 

        switch (DESTINATION_REGION_STATUS)
        {
            case REGION_STATUS_CONQUERED_BY_SPACE_PILOT:
            case REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT:
            case REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT:
            {
                //  The space pilot has reached a region that is already conquered (safe zone)

                if (true == getSpacePilotIsOutsideSafeZone)
                {
                    //  Find a board point inside the potential conquered zone 
                    //  to start the flood fill algorithm
                    getBoardPointInsideThePotentialConqueredZone();
   
                    //  The space pilot has reached one of the safe places
                    //  after conquering
                    //  Perform flood fill algorithm
                    regionsAfterFloodFill = content.grid().floodFillAlgorithm(
                                        content.grid().regions(), 
                                        boardPointInsideThePotentialConqueredZone, 
                                        RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);

                    if (null != regionsAfterFloodFill)
                    {
                        regionsWereConquered                    = true;
                    }
                    else
                    {
                        //  If one of the region status is SMALL_ENEMY_OVER
                        regionsWereConquered                    = false;
                        //  Reverse all what should be done
                    }

                    //  Reset the limits of space pilot when outside safe zone
                    resetLimitsOfSpacePilotWhenOutsideSafeZone();
                }
                else
                {
                    //  Update the limits of space pilot when even when inside safe zone
                    updateLimitsOfSpacePilotWhenOutsideSafeZone(sourceLocation);
                    updateLimitsOfSpacePilotWhenOutsideSafeZone(DESTINATION_LOCATION);
                }

                //  The space pilot has reached a region that is already conquered
                getSpacePilotIsOutsideSafeZone  = false;
                break;
            }

            case REGION_STATUS_SPACE_PILOT_CONQUERING:
            {
                //  Do nothing, the region is being conquered
                break;
            }

            case REGION_STATUS_EMPTY:
            {
                if (false == getSpacePilotIsOutsideSafeZone)
                {
                    //  The space pilot has in this movement
                    //  go out from the safe zone
                    //  The space pilot has reached a region that is NOT already conquered
                    getSpacePilotIsOutsideSafeZone          = true;
                }

                //  Set the region as a region being conquered
                destinationRegion.setRegionStatus(RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING);

                //  Update the limits of space pilot when outside safe zone
                updateLimitsOfSpacePilotWhenOutsideSafeZone(sourceLocation);
                updateLimitsOfSpacePilotWhenOutsideSafeZone(DESTINATION_LOCATION);
                break;
            }

            case REGION_STATUS_SPACE_PILOT_OVER:
            case REGION_STATUS_SMALL_ENEMY_OVER:
            default:
            {
                System.out.println("In gameStep() should not happen");
            }
        }

        return regionsWereConquered;
    }

    private void updateLimitsOfSpacePilotWhenOutsideSafeZone(BoardPoint boardPoint)
    {
        if (boardPoint.getColumn() < spacePilotLeftistColumnWhenOutsideSafeZone)
        {
            spacePilotLeftistColumnWhenOutsideSafeZone  = boardPoint.getColumn();
        }
        if (boardPoint.getColumn() > spacePilotRightestColumnWhenOutsideSafeZone)
        {
            spacePilotRightestColumnWhenOutsideSafeZone = boardPoint.getColumn();
        }       
        if (boardPoint.getRow() < spacePilotSouthestRowWhenOutsideSafeZone)
        {
            spacePilotSouthestRowWhenOutsideSafeZone    = boardPoint.getRow();
        }   
        if (boardPoint.getRow() > spacePilotNorthestRowWhenOutsideSafeZone)
        {
            spacePilotNorthestRowWhenOutsideSafeZone    = boardPoint.getRow();
        }  
    }

    private void resetLimitsOfSpacePilotWhenOutsideSafeZone()
    {
        spacePilotLeftistColumnWhenOutsideSafeZone  = Grid.getTotalGameCellsInXPerRow();
        spacePilotRightestColumnWhenOutsideSafeZone = 0;
        spacePilotSouthestRowWhenOutsideSafeZone    = Grid.getTotalGameCellsInYPerColumn();
        spacePilotNorthestRowWhenOutsideSafeZone    = 0;
    }

    private void getBoardPointInsideThePotentialConqueredZone()
    {
        //  Find a board point inside the potential conquered zone 
        //  to start the flood fill algorithm
        final int   AVERAGE_COLUMN  = (int) (spacePilotLeftistColumnWhenOutsideSafeZone 
                                        + spacePilotRightestColumnWhenOutsideSafeZone) / 2;
        final int   AVERAGE_ROW     = (int) (spacePilotSouthestRowWhenOutsideSafeZone 
                                        + spacePilotNorthestRowWhenOutsideSafeZone) / 2;

        if (spacePilotLeftistColumnWhenOutsideSafeZone != spacePilotRightestColumnWhenOutsideSafeZone)
        {
            //  If the space pilot has NOT made a vertical line
            boardPointInsideThePotentialConqueredZone.setColumn(AVERAGE_COLUMN);
        }
        else
        {
            //  If the space pilot has made a vertical line
            if (spacePilotLeftistColumnWhenOutsideSafeZone < (Grid.getTotalGameCellsInXPerRow() / 2))
            {
                //  If the vertical line is at the left of the middle line
                spacePilotLeftistColumnWhenOutsideSafeZone  -= 1;
            }
            else
            {
                //  If the vertical line is at the right of the middle line
                spacePilotLeftistColumnWhenOutsideSafeZone  += 1;
            }
            boardPointInsideThePotentialConqueredZone.setColumn(spacePilotLeftistColumnWhenOutsideSafeZone);
        }

        if (spacePilotSouthestRowWhenOutsideSafeZone != spacePilotNorthestRowWhenOutsideSafeZone)
        {
            //  If the space pilot has NOT made a horizontal line
            boardPointInsideThePotentialConqueredZone.setRow(AVERAGE_ROW);
        }
        else
        {
            //  If the space pilot has made a vertical line
            if (spacePilotSouthestRowWhenOutsideSafeZone < (Grid.getTotalGameCellsInYPerColumn() / 2))
            {
                //  If the horizontal line is at the top of the middle line
                spacePilotSouthestRowWhenOutsideSafeZone    -= 1;
            }
            else
            {
                //  If the horizontal line is at the bottom of the middle line
                spacePilotSouthestRowWhenOutsideSafeZone  += 1;
            }
            boardPointInsideThePotentialConqueredZone.setRow(spacePilotSouthestRowWhenOutsideSafeZone);
        }  
    } 


    private void conquerRegion(BoardPoint startPoint, int currentLine) 
    {
//  In the game Volfied, the algorithm to determine if a region is conquered involves several steps. 
//  Here's a simplified version of how it works:

//  1)  Start Drawing: When the player starts drawing a line, the game keeps track of the starting point and the current position of the line.
//  2)  Detect Line Closure: The game continuously checks if the line has closed a loop by intersecting with itself or the boundary of the play area.
//  3)  Flood Fill Algorithm: Once a loop is detected, the game uses a flood fill algorithm to determine the enclosed area. This algorithm works by starting from a point inside the loop and "filling" outwards until it hits the boundary of the loop.
//  4)  Check for Enemies: After filling the area, the game checks if any enemies are present within that area. If there are no enemies, the area is considered conquered.
//  5)  Update Game State: If the area is conquered, the game updates the map to reflect the new territory and adjusts the player's score accordingly.

    //  if lineClosesLoop(currentLine) {
    //      enclosedArea = floodFill(startPoint, currentLine);
    //       if noEnemiesInArea(enclosedArea) {
    //            markAreaAsConquered(enclosedArea);
    //            updateScore();
    //        }
    //}
    }
}