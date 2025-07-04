/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            SmallEnemy.java
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
import my_game.Region.RegionStatus;

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
    private boolean isRunning   = true;

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

        //  Sets the initial direction policy randomally
        //  Generate a random number between 0 and 1
        double randomValue  = Math.random();
        isRunning           = true;

        if (randomValue <= 0.125)
        {
            directionPolicy = Direction.SOUTH;
        }
        else if (randomValue <= 0.25)
        {
            directionPolicy = Direction.NORTH;
        }
        else if (randomValue <= 0.375)
        {
            directionPolicy = Direction.WEST;
        }
        else if (randomValue <= 0.50)
        {
            directionPolicy = Direction.EAST;
        }
        else if (randomValue <= 0.625)
        {
            directionPolicy = Direction.NORTH_EAST;
        }
        else if (randomValue <= 0.75)
        {
            directionPolicy = Direction.NORTH_WEST;
        }
        else if (randomValue <= 0.875)
        {
            directionPolicy = Direction.SOUTH_EAST;
        }
        else
        {
            directionPolicy = Direction.SOUTH_WEST;
        }
	}	

    /**
        * move updateNormalDirectionPolicy
        * 
        * @implNote updateNormalDirectionPolicy method that updates the direction policy
        *               according to the current direction policy, the idea is to have 1/3
        *               probability to change to different directions
        *
        * @param (none)
        * @return (none)
        */
	private void updateNormalDirectionPolicy() 
    {
        //  Generate a random number between 0 and 1
        double randomValue  = Math.random();

		switch (directionPolicy) 
        {
            case WEST:
            {
                //  If the number falls between 0 and 0.33, move east, 0.33 and 0.67 move north-east, otherwise south-east
                if (randomValue <= 0.33) 
                {
                    directionPolicy = Direction.EAST;
                } 
                else if (randomValue <= 0.67) 
                {
                    directionPolicy = Direction.NORTH_EAST;
                } 
                else 
                {
                    directionPolicy = Direction.SOUTH_EAST;
                }   
                break;
            }

            case EAST: 
            {
                //  If the number falls between 0 and 0.33, move west, 0.33 and 0.67 move north-west, otherwise south-west
                if (randomValue <= 0.33) 
                {
                    directionPolicy = Direction.WEST;
                } 
                else if (randomValue <= 0.67) 
                {
                    directionPolicy = Direction.NORTH_WEST;
                } 
                else 
                {
                    directionPolicy = Direction.SOUTH_WEST;
                }   
                break;
            }

            case NORTH:
            {
                //  If the number falls between 0 and 0.33, move south, 0.33 and 0.67 move south-west, otherwise south-east
                if (randomValue <= 0.33) 
                {
                    directionPolicy = Direction.SOUTH;
                } 
                else if (randomValue <= 0.67) 
                {
                    directionPolicy = Direction.SOUTH_WEST;
                } 
                else 
                {
                    directionPolicy = Direction.SOUTH_EAST;
                }   
                break;
            }

            case SOUTH: 
            {
                //  If the number falls between 0 and 0.33, move north, 0.33 and 0.67 move north-west, otherwise north-east
                if (randomValue <= 0.33) 
                {
                    directionPolicy = Direction.NORTH;
                } 
                else if (randomValue <= 0.67) 
                {
                    directionPolicy = Direction.NORTH_WEST;
                } 
                else 
                {
                    directionPolicy = Direction.NORTH_EAST;
                }   
                break;
            }

            case NORTH_WEST:
            case NORTH_EAST:
            {
                //  If the number falls between 0 and 0.33, move south-west, 0.33 and 0.67 move south, otherwise south-east
                if (randomValue <= 0.33) 
                {
                    directionPolicy = Direction.SOUTH_WEST;
                } 
                else if (randomValue <= 0.67) 
                {
                    directionPolicy = Direction.SOUTH;
                } 
                else 
                {
                    directionPolicy = Direction.SOUTH_EAST;
                }   
                break;
            }

            case SOUTH_WEST:
            case SOUTH_EAST:
            {
                //  If the number falls between 0 and 0.33, move north-west, 0.33 and 0.67 move north, otherwise north-east
                if (randomValue <= 0.33) 
                {
                    directionPolicy = Direction.NORTH_WEST;
                } 
                else if (randomValue <= 0.67) 
                {
                    directionPolicy = Direction.NORTH;
                } 
                else 
                {
                    directionPolicy = Direction.NORTH_EAST;
                }   
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
        * updateCornerDirectionPolicy method
        * 
        * @implNote updateCornerDirectionPolicy method that updates the direction policy
        *               according to the corner direction when it is reached
        *               Note: this method is called only when the enemy reaches a corner
        *               and in the current implementation, it will go only in diagonal of the corner
        *
        * @param (Direction corner) (corner direction)
        * @return (none)
        */
	private void updateCornerDirectionPolicy(Direction corner) 
    {
        //  Generate a random number between 0 and 1
        //  double randomValue  = Math.random();

        switch (corner)
        {
            case NORTH_WEST:
            {
                //  Right direction for corner
                directionPolicy = Direction.SOUTH_EAST;
                break;
            }

            case NORTH_EAST:
            {
                //  Right direction for corner
                directionPolicy = Direction.SOUTH_WEST;
                break;
            }


            case SOUTH_WEST:
            {
                //  Right direction for corner
                directionPolicy = Direction.NORTH_EAST;
                break;
            }

            case SOUTH_EAST:
            {
                //  Right direction for corner
                directionPolicy = Direction.NORTH_WEST;
                break;
            }

        	case WEST:
            case EAST:
            case NORTH:
            case SOUTH:
            case STOPPED:
            default:
            {
                System.out.println("Error in corner in updateCornerDirectionPolicy()");
                break;
            }
        }
	}

    /**
        * isSmallEnemyRunning method
        * 
        * @implNote returns to the caller the status (if running or not) of the small enemy
        *
        * @param (none)
        * @return (boolean)
        */
	public boolean isSmallEnemyRunning()
    {
        return isRunning;
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
		BoardPoint desired  = new BoardPoint(getLocation().getRow() + directionPolicy.yVector(),
            getLocation().getColumn() + directionPolicy.xVector());

        //  Check if the small enemy is in a conquered region and therefore could not move
        RegionStatus    SMALL_ENEMY_REGION_STATUS   = grid.regions()[getLocation().getRow()][getLocation().getColumn()].getRegionStatus();
        if ((RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT == SMALL_ENEMY_REGION_STATUS)
            || (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == SMALL_ENEMY_REGION_STATUS))
        {
            isRunning   = false;
        }

        if (true == isRunning)
        {
            if (true == grid.getIsBoardPointACornerForEnemies(desired))
            {
                //  If it is a corner, then update policy
                final Direction CORNER = grid.getCornerForEnemies(desired);
                //  Update direction policy for corner points
                updateCornerDirectionPolicy(CORNER);
                desired  = new BoardPoint(getLocation().getRow() + directionPolicy.yVector(),
                    getLocation().getColumn() + directionPolicy.xVector());
            }

            if (false == grid.isCurrentMoveBlockedByLogicOrGameConstraints(getLocation(), desired, this)) 
            {
                //  If move is possible, i.e., grid does not block,
                //  continue the current direction in the same direction polity as now
                setLocation(desired);
            }
            else
            {
                //  If reached here, desired policy is not applicable, update policy
                if (false == grid.getIsBoardPointACornerForEnemies(desired))
                {
                    //  Update direction policy for 'normal' points (no corners)
                    updateNormalDirectionPolicy();
                }
                else
                {
                    final Direction CORNER = grid.getCornerForEnemies(desired);
                    //  Update direction policy for corner points
                    updateCornerDirectionPolicy(CORNER);
                }

                BoardPoint next = new BoardPoint(getLocation().getRow() + directionPolicy.yVector(),
                        getLocation().getColumn() + directionPolicy.xVector());
                
                if (true == grid.isCurrentMoveBlockedByLogicOrGameConstraints(getLocation(), next, this)) 
                {
                    //  If reached here, desired policy is not applicable, update policy
                    if (false == grid.getIsBoardPointACornerForEnemies(next))
                    {
                        //  Update direction policy for 'normal' points (no corners)
                        updateNormalDirectionPolicy();
                    }
                    else
                    {
                        final Direction CORNER = grid.getCornerForEnemies(next);
                        //  Update direction policy for corner points
                        updateCornerDirectionPolicy(CORNER);
                    }

                    //  recalculate next point according to new policy
                    next = new BoardPoint(getLocation().getRow() + directionPolicy.yVector(),
                                            getLocation().getColumn() + directionPolicy.xVector());

                    if (false == grid.isCurrentMoveBlockedByLogicOrGameConstraints(getLocation(), next, this))
                    {
                        //  move to next point
                        setLocation(next);
                    }
                    else
                    {
                        //  Do NOT move, let's try next cycle
                    }
                }
                else
                {
                    //  move to next point
                    setLocation(next);
                }
            }
        }
	}
}		