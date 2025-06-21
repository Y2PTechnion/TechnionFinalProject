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

import ui_elements.ScreenPoint;
import base.Intersectable;
import my_game.Grid.Direction;

/**
 * GameCharacter class
 * 
 * @implNote This class implements a new GameCharacter that implements the Intersectable interfaces 
 *              to detect the 'collision' with another objets.
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class GameCharacter implements Intersectable {
//  Private constants for the class
	private final int           IMAGE_WIDTH         = 18;
	private final int           IMAGE_HEIGHT        = 18;

//  Private variables for the class
	private     BoardPoint 		location            = null;
	private     String 			name                = "";
	private     int         	imageWidth          = 0;
	private     int         	imageHeight         = 0;
    
//  Protected variables for the class
    protected   Grid       	    grid                = null;
	protected   Direction 		directionPolicy     = Direction.STOPPED;
	protected   Direction 		currentDirection    = Direction.STOPPED;
	protected   int             speedCellsPerCycle  = 1;


    /**
        * GameCharacter constructor method
        * 
        * @implNote Constructor method to construct one GameCharacter object in the grid
        *
        * @param (String gameCharacterId) (Game Character ID)
        * @param (Grid grid) (grid)
        * @return (none)
        */
	public GameCharacter(String gameCharacterId, Grid grid) {
		this.name           = gameCharacterId;
        this.grid           = grid;

        //  Sets the image dimensions
        this.imageWidth     = this.IMAGE_WIDTH;
        this.imageHeight    = this.IMAGE_HEIGHT;
	}	

    /**
        * getLocation method
        * 
        * @implNote getLocation getter method to return the current location as BoardPoint
        *
        * @param none
        * @return (BoardPoint)
        */
	public BoardPoint getLocation() {
		return this.location;
	}
	
    /**
        * setLocation method
        * 
        * @implNote setLocation setter method to set the current location as BoardPoint
        *
        * @param (BoardPoint location) (the location to set)
        * @return (none)
        */
	public void setLocation(BoardPoint location) {
		this.location   = location;
	}

    /**
        * setLocation method
        * 
        * @implNote setLocation setter method to set the current location as two coordinates
        *
        * @param (int x) (x coordinate)
        * @param (int y) (y coordinate)
        * @return (none)
        */
    public void setLocation(int x, int y) {
    	this.location.setX(x);
		this.location.setY(y);
    }

    /**
        * getCurrentDirection method
        * 
        * @implNote getCurrentDirection getter method to return the current direction as Direction
        *
        * @param none
        * @return (Direction)
        */
	public Direction getCurrentDirection() {
		return currentDirection;
	}

    /**
        * setCurrentDirection method
        * 
        * @implNote setCurrentDirection setter method to set the current direction as Direction
        *
        * @param Direction direction (the direction to set)
        * @return (none)
        */
	public void setCurrentDirection(Direction direction) {
		currentDirection    = direction;
	}

    /**
        * getImageWidth method
        * 
        * @implNote getImageWidth getter method to return the game character image width
        *
        * @param none
        * @return (image width)
        */
	public int getImageWidth() {
		return imageWidth;
	}
	
    /**
        * getImageHeight method
        * 
        * @implNote getImageHeight getter method to return the game character image height
        *
        * @param none
        * @return (image height)
        */
	public int getImageHeight() {
		return imageHeight;
	}

    /**
        * name method
        * 
        * @implNote name getter method to return the game character name (ID) as a string
        *
        * @param none
        * @return (String name)
        */
	public String name() {
		return this.name;
	}

    /**
        * move method
        * 
        * @implNote move method to be overridden by derived classed to implement it
        *
        * @param none
        * @return (none)
        */
	protected void move() {

	}
	
    //  Intersectable base class method to be implemented
    //  Intersectable base class method to be implemented
    //  Intersectable base class method to be implemented
    @Override
    public ScreenPoint[] getIntersectionVertices() {
        final int WIDTH     = this.imageWidth;
        final int HEIGHT    = this.imageHeight;

        final int LEFT_X    = this.location.getX();
        final int TOP_Y     = this.location.getY();

        //  Create the vertices of the rectangle representing the GameCharacter
        //  to be used for intersection detection
        ScreenPoint[] vertices = {
                new ScreenPoint(LEFT_X, TOP_Y),
                new ScreenPoint(LEFT_X + WIDTH, TOP_Y),
                new ScreenPoint(LEFT_X + WIDTH, TOP_Y + HEIGHT),
                new ScreenPoint(LEFT_X, TOP_Y + HEIGHT)
        };

        return vertices;
    }
}