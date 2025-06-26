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
package my_base;

import java.awt.Color;


import base.GameContent;
import my_game.Grid;
import my_game.SpacePilot;
import my_game.Board;
import my_game.BoardPoint;
import my_game.GameControl;
import my_game.SmallEnemies;
import my_game.Score;
import my_game.StatusLine;

public class MyContent extends GameContent 
{
//  Private constants for the class
    //  Quantity of small enemies to place in  the board
    private final int   	QUANTITY_OF_SMALL_ENEMIES   = 10;

//  Private variables for the class
	private Grid 			grid;
    private SpacePilot  	spacePilot;
	private SmallEnemies	smallEnemies;
	private Score 			score;
	private StatusLine 		statusLine;
	private Board           board;
	private GameControl     gameControl;
	
	@Override
	public void initContent() 
    {
		board 			= new Board();
		board.setContent(this);
		grid 			= new Grid(board);

        //  Create the space pilot and set its initial location
        //  The space pilot is the main character of the game
		spacePilot		= new SpacePilot("spacePilot", grid);
        //  Set the space pilot in the middle of last row of the grid
        spacePilot.setLocation(new BoardPoint(Grid.getTotalGameCellsInXPerRow()/2,Grid.getTotalGameCellsInYPerColumn()-1));

        //  Creates the small enemies and sets their initial locations
        //  The small enemies are the enemies of the game
		smallEnemies	= new SmallEnemies(QUANTITY_OF_SMALL_ENEMIES);
		smallEnemies.initSmallEnemies(grid);

		score 			= new Score();
		statusLine 		= new StatusLine();
		statusLine.showText("Good Luck!", Color.GREEN, 3000);
		gameControl     = new GameControl(this);
	}	
	
	public Grid grid() 
    {
		return grid;
	}
	
	public SpacePilot spacePilot() 
    {
		return spacePilot;
	}
	
	public SmallEnemies smallEnemies() 
    {
		return smallEnemies;
	}
	
	public Score score() 
    {
		return score;
	}

	public StatusLine statusLine() 
    {
		return statusLine;
	}

	public Board getBoard() 
    {
		return this.board;
	}

	public GameControl gameControl() 
    {
		return this.gameControl;
	}
}