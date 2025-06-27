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

public class GameControl 
{
//  Private variables for the class
    private MyContent   content = null;
	private Board       board   = null;

    public GameControl(MyContent content) 
    {
        this.content    = content;
        this.board      = content.getBoard();
    }

	public void gameStep() 
    {
        //  Logic section of gameStep()
            Region region       = conquerCurrentRegion(content.spacePilot().getLocation());
            //  Get space pilot source location
            final BoardPoint    sourceLocation      = content.spacePilot().getLocation();
            //  Try to move the space pilot, if it was moved
            content.spacePilot().move();
            //  Get space pilot destination location
            final BoardPoint    destinationLocation = content.spacePilot().getLocation();
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
		Region  region  = content.grid().regions()[location.getColumn()][location.getRow()];

		if (true == region.isShown()) 
        {
			region.hide();
			content.score().add(1);
			content.grid().setRegionAsConquered(region);
            content.score().setConqueredRegionsPercentage(content.grid().getPercentageOfConqueredRegions());

            //  Next lines won't be relevant later, only for fun by now
			if (0 == content.grid().getCurrentNumberOfUnconqueredRegions()) 
            {
				content.statusLine().showText("Great JOB !!!", Color.YELLOW, 5000);
			}

			return region; 
		}

		return null;
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