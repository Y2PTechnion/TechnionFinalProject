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
 * SmallEnemy class
 * 
 * @implNote This class implements a new SmallEnemy that extends (derives from)
 *              GameCharacter base class
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class SmallEnemy extends GameCharacter 
{
//  Private variables for the class

    /**
        * SmallEnemy constructor method
        * 
        * @implNote Constructor method to construct one SmallEnemy object within the grid
        *
        * @param (String smallEnemyId) (Small Enemy ID)
		* @param (Grid grid) (grid)
        * @return (none)
        */
	public SmallEnemy(String smallEnemyId, Grid grid) 
    {
        //  Calls the base class (GameCharacter) constructor method
        super(smallEnemyId, grid);

        //  Sets the initial direction policy to down and current direction to right
        directionPolicy     = Direction.DOWN;
	    currentDirection    = Direction.RIGHT;
	}	

	private void updateDirectionPolicy() 
    {
		switch (currentDirection) 
        {
            case RIGHT: 
            case LEFT: 
            {
                //  If the number falls between 0 and 0.5 and the policy is right or left, then move down, otherwise up
                directionPolicy = (Math.random() <= 0.5 ? Direction.DOWN : Direction.UP);
                break;
            }

            case UP:
            case DOWN: 
            {
                //  If the number falls between 0 and 0.5 and the policy is up or down, then move right, otherwise left
                directionPolicy = (Math.random() <= 0.5 ? Direction.RIGHT : Direction.LEFT);
                break;
            }

            case STOPPED: 
            {
                //  Do nothing
                break;
            }
		}
	}

    /**
        * move method
        * 
        * @implNote move method that overrides based GameCharacter method and implemented
        *               according to small enemy requirements
        *
        * @param none
        * @return (none)
        */
    @Override
	public void move() 
    {
		//  First try to move according to policy
		BoardPoint desired  = new BoardPoint(getLocation().getRow() + directionPolicy.yVec(),
            getLocation().getColumn() + directionPolicy.xVec());
		
//  if move is possible, i.e., grid does not block
		if (false == grid.blocksMove(getLocation(), desired, this)) 
        {
			currentDirection = directionPolicy;
            setLocation(desired);

			//  After moving to next location, update movement direction randomly for next movement
			updateDirectionPolicy();
		}
        else
        {
            //  If reached here, desired policy is not applicable, move in current direction
            BoardPoint next = new BoardPoint(getLocation().getRow() + currentDirection.yVec(),
                    getLocation().getColumn() + currentDirection.xVec());
            
            if (true == grid.blocksMove(getLocation(), next, this)) 
            {
                switch (currentDirection) 
                {
                    case RIGHT: 
                    {
                        //  If the current direction is right and reached the limits, then move left
                        currentDirection = Direction.LEFT;
                        break;
                    }

                    case LEFT: 
                    {
                        //  If the current direction is left and reached the limits, then move right
                        currentDirection = Direction.RIGHT;
                        break;
                    }

                    case UP: 
                    {
                        //  If the current direction is up and reached the limits, then move down
                        currentDirection = Direction.DOWN;
                        break;
                    }

                    case DOWN: 
                    {
                        //  If the current direction is down and reached the limits, then move up
                        currentDirection = Direction.UP;
                        break;
                    }

                    case STOPPED: 
                    {
                        //  Do nothing
                        break;
                    }
                }

                updateDirectionPolicy();

                //  recalculate next point according to new direction
                next = new BoardPoint(getLocation().getRow() + currentDirection.yVec(),
                                        getLocation().getColumn() + currentDirection.xVec());
            }

            //  move to next point
            setLocation(next);
        }
	}
}		