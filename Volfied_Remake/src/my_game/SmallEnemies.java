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

/**
 * SmallEnemies class
 * 
 * @implNote This class implements a grouping class to group all the enemies together
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class SmallEnemies 
{
    //  Private variables for the class
	private SmallEnemy[] smallEnemies   = null;

  /**
        * SmallEnemies constructor method
        * 
        * @implNote Constructor method to construct one SmallEnemies object with
        *               the quantity of small enemies to be created.
        *
        * @param (int quantityOfSmallEnemiesInGame)
        * @return (none)
        */
	public SmallEnemies(int quantityOfSmallEnemiesInGame) 
    {
        //  Defines the array of small enemies depending on the quantity
        //  set by the application
        smallEnemies    = new SmallEnemy[quantityOfSmallEnemiesInGame];
    }
     
  /**
        * initSmallEnemies method
        * 
        * @implNote initSmallEnemies method to construct all the small enemies to
        *               be located (randomally) in the grid
        *
        * @param (Grid grid)
        * @return (none)
        */
	public void initSmallEnemies(Grid grid) 
    {
        //  Define the small enemy counter
        int         smallEnemyCounter                       = 0;
        int         smallEnemyFirstRandomColumn             = 0;
        int         smallEnemyFirstRandomRow                = 0;

        //  Define the minimum and maximum numbers for randomally rows and columns
        final   int FIRST_RELEVANT_COLUMN_FOR_SMALL_ENEMIES = 1;
        final   int FIRST_RELEVANT_ROW_FOR_SMALL_ENEMIES    = 1;
        final   int LAST_RELEVANT_COLUMN_FOR_SMALL_ENEMIES  = Grid.getTotalGameCellsInXPerRow() - 1;
        final   int LAST_RELEVANT_ROW_FOR_SMALL_ENEMIES     = Grid.getTotalGameCellsInYPerColumn() - 1;
        final   int RELEVANT_COLUMN_RANGE_FOR_SMALL_ENEMIES = LAST_RELEVANT_COLUMN_FOR_SMALL_ENEMIES 
                                                                - FIRST_RELEVANT_COLUMN_FOR_SMALL_ENEMIES;
        final   int RELEVANT_ROW_RANGE_FOR_SMALL_ENEMIES    = LAST_RELEVANT_ROW_FOR_SMALL_ENEMIES 
                                                                - FIRST_RELEVANT_ROW_FOR_SMALL_ENEMIES;

        //  'Navigate' into the small enemies array
        for (SmallEnemy s : smallEnemies) 
        {
            //  Create a new SmallEnemy object in the grid
		    s   = new SmallEnemy("smallEnemy" + (smallEnemyCounter + 1), grid);

            //  Get the column and row first random numbers inside the allowed limits
            smallEnemyFirstRandomColumn                     = (int) (Math.random() * RELEVANT_COLUMN_RANGE_FOR_SMALL_ENEMIES)    
                                                                + FIRST_RELEVANT_COLUMN_FOR_SMALL_ENEMIES;
            smallEnemyFirstRandomRow                        = (int) (Math.random() * RELEVANT_ROW_RANGE_FOR_SMALL_ENEMIES) 
                                                                + FIRST_RELEVANT_ROW_FOR_SMALL_ENEMIES;

            //  Set randomally each one of the enemies in the board inside the grid limits
		    s.setLocation(new BoardPoint(smallEnemyFirstRandomRow, smallEnemyFirstRandomColumn));

            //  Set the SmallEnemy in the array ofthe SmallEnemies
		    smallEnemies[smallEnemyCounter++]               = s;
        }
	}
	
  /**
        * move method
        * 
        * @implNote move method to make the small enemies move inside the grid
        *
        * @param (none)
        * @return (none)
      */
	public void move() 
    {
        //  'Navigate' into the small enemies array
		for (SmallEnemy s : smallEnemies) 
        {
            //  Call the smallEnemy move method for
            //  each one of the small enemies in the array
			s.move();
		}
	}
	

  /**
        * getSmallEnemies method
        * 
        * @implNote getSmallEnemies getter method to return the array of small enemies
        *
        * @param (none)
        * @return (SmallEnemy[])
        */
	public SmallEnemy[] getSmallEnemies() 
    {
		return smallEnemies;
	}
}