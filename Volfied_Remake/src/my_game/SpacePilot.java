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

import my_game.Grid.Direction;

/**
 * SpacePilot class
 * 
 * @implNote This class implements a new SpacePilot that extends (derives from)
 *              GameCharacter base class
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class SpacePilot extends GameCharacter 
{
//  Private variables for the class
    private int             imageIndex              = 0;

    //  Array of different types of images of space pilot
	private final String[]	images = {"YellowSpaceshipRight", "YellowSpaceshipLeft",
			"YellowSpaceshipUp", "YellowSpaceshipDown", "YellowSpaceshipUp"};

    /**
        * SpacePilot constructor method
        * 
        * @implNote Constructor method to construct one SpacePilot object within the grid
        *
        * @param (String spacePilotId) (Space Pilot ID)
        * @return (none)
        */
	public SpacePilot(String spacePilotId, Grid grid) 
    {
        //  Calls the base class (GameCharacter) constructor method
        super(spacePilotId, grid);

        //  Sets the initial direction policy, previous and current direction to stopped
	    directionPolicy     = Direction.STOPPED;
	    currentDirection    = Direction.STOPPED;

        //  Saves the current index of the image
        this.imageIndex     = this.currentDirection.index();
	}	

    /**
        * directionalKeyPressed method
        * 
        * @implNote directionalKeyPressed method that receives from the keyboard handler
        *               the arrow key that was pressed by the player
        *
        * @param (Direction direction) (arrow key direction)
        * @return (none)
        */
	public void directionalKeyPressed(Direction direction) 
    {
        //  Updates the image index according to arrow key pressed
        imageIndex          = direction.index();

        //  Updates current direction
        currentDirection    = direction;
	}
	
    /**
        * getImageName method
        * 
        * @implNote getImageName getter method returns the name of the current
        *               image in use by the space pilot
        * @param (none)
        * @return (String) (image's name)
        */
	public String getImageName() 
    {
		return images[imageIndex];
	}

    /**
        * move method
        * 
        * @implNote move method that overrides based GameCharacter method and implemented
        *               according to space pilot requirements
        *
        * @param none
        * @return (none)
        */
    @Override
	public void move() 
    {
		//  Saves the direction policy as current direction
		directionPolicy = currentDirection;
				
		// First try to move according to policy
		BoardPoint desired = new BoardPoint(getLocation().getRow() + currentDirection.yVector(),
                                            getLocation().getColumn() + currentDirection.xVector());

		//  if move is possible, i.e., grid does not block
        BoardPoint current = getLocation();

		if (false == grid.blocksMove(current, desired, this)) 
        {
            setLocation(desired);
			return;
        }
		
	// If reached here, desired policy is not applicable, move in opposite direction
	//	BoardPoint next = new BoardPoint(location.x + currentDirection.xVec(), location.y + currentDirection.yVec());
	//	if (grid.blocksMove(location, next)) {
	//		switch (currentDirection) {
	//			case RIGHT:
	//				currentDirection = Direction.LEFT;
	//				break;

	//			case LEFT:
	//				currentDirection = Direction.RIGHT;
	//				break;

	//			case UP:
	//				currentDirection = Direction.DOWN;
	//				break;

	//			case DOWN:
	//				currentDirection = Direction.UP;
	//				break;

	//		}
			// recalculate next BoardPoint according to new direction
	//		next = new BoardPoint(location.x + currentDirection.xVec(), location.y + currentDirection.yVec());
	//	}
		// move to next BoardPoint
	//	location.x = next.x;
	//	location.y = next.y;
	}
}	