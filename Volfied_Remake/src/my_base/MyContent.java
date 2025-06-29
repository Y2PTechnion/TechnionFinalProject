/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            MyContent.java
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
import base.GameDashboard;
import my_game.Grid;
import my_game.SpacePilot;
import my_game.Board;
import my_game.BoardPoint;
import my_game.GameControl;
import my_game.SmallEnemies;
import my_game.Score;
import my_game.StatusLine;
import shapes.Image;
import shapes.Shape;

public class MyContent extends GameContent 
{
//  Private constants for the class
    //  Quantity of small enemies to place in  the board
    private final int   	QUANTITY_OF_SMALL_ENEMIES   = 10;

//  Private variables for the class
	private Grid 			grid                = null;
    private SpacePilot  	spacePilot          = null;
	private SmallEnemies	smallEnemies        = null;
	private Score 			score               = null;
	private StatusLine 		statusLine          = null;
    private StatusLine 		tipLine             = null;
	private Board           board               = null;
	private GameControl     gameControl         = null;
    private Image           gameOverImage       = null;
    private Image           youWinImage         = null;
    private Image           youFinishedImage    = null;

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
        spacePilot.setLocation(new BoardPoint(Grid.getTotalGameCellsInYPerColumn()-1, 
                                                Grid.getTotalGameCellsInXPerRow()/2));

        //  Creates the small enemies and sets their initial locations
        //  The small enemies are the enemies of the game
		smallEnemies	    = new SmallEnemies(QUANTITY_OF_SMALL_ENEMIES);
		smallEnemies.initSmallEnemies(grid);

        gameOverImage       = new Image("gameOverImage", "resources/gameOver.png", 225, 225, 200,200);
        gameOverImage.setzOrder(15);
        youWinImage         = new Image("youWinImage", "resources/youWin.jpg", 770, 400, 200,200);
        youWinImage.setzOrder(15);
        youFinishedImage    = new Image("youFinishedImage", "resources/youFinishedImage.png", 1718, 2048, 200,200);
        youFinishedImage.setzOrder(15);

		score 			    = new Score();
		statusLine 		    = new StatusLine();
		statusLine.showText("Good Luck!", Color.GREEN, 3000);
        tipLine             = new StatusLine();
 //       tipLine.showText("Nothing", Color.WHITE, 3000);
		gameControl         = new GameControl(this);
	}	

	public Grid grid() 
    {
		return this.grid;
	}
	
	public SpacePilot spacePilot() 
    {
		return this.spacePilot;
	}
	
	public SmallEnemies smallEnemies() 
    {
		return this.smallEnemies;
	}
	
	public Score score() 
    {
		return this.score;
	}

	public StatusLine statusLine() 
    {
		return this.statusLine;
	}

	public StatusLine tipLine() 
    {
		return this.tipLine;
	}

	public Board getBoard() 
    {
		return this.board;
	}

	public GameControl gameControl() 
    {
		return this.gameControl;
	}

/**
        * gameOver method
        * 
        * @implNote This method returns the gameOver sign to be shown
        *
        * @param () (No parameters)
        * @return (Image)
        */
	public Image gameOver() {
		return this.gameOverImage;
	}

    /**
        * youWinHide method
        * 
        * @implNote This method hides the gameOver sign
        *
        * @param () (No parameters)
        * @return (No return value)
        */
	public void gameOverHide() {
		gameOverImage.setStatus(Shape.STATUS.HIDE);
	}

    /**
        * gameOverShow method
        * 
        * @implNote This method shows the gameOver sign
        *
        * @param (int x) (No parameters)
        * @return (No return value)
        */
	public void gameOverShow(int x, int y) {
        gameOverImage.moveToLocation(x, y);
        gameOverImage.setzOrder(15);
		gameOverImage.setStatus(Shape.STATUS.SHOW);
	}	


/**
        * youWin method
        * 
        * @implNote This method returns the youWin sign to be shown
        *
        * @param () (No parameters)
        * @return (Image)
        */
	public Image youWin() {
		return this.youFinishedImage;
	}

    /**
        * youWinHide method
        * 
        * @implNote This method hides the youWinImage sign
        *
        * @param () (No parameters)
        * @return (No return value)
        */
	public void youWinHide() {
		youWinImage.setStatus(Shape.STATUS.HIDE);
	}

    /**
        * youWinShow method
        * 
        * @implNote This method shows the youWinImage sign
        *
        * @param (int x) (No parameters)
        * @return (No return value)
        */
	public void youWinShow(int x, int y) {
        youWinImage.moveToLocation(x, y);
        youWinImage.setzOrder(15);
		youWinImage.setStatus(Shape.STATUS.SHOW);
	}	

/**
        * youFinished method
        * 
        * @implNote This method returns the youFinished sign to be shown
        *
        * @param () (No parameters)
        * @return (Image)
        */
	public Image youFinished() {
		return this.youFinishedImage;
	}

    /**
        * youFinishedHide method
        * 
        * @implNote This method hides the youFinished sign
        *
        * @param () (No parameters)
        * @return (No return value)
        */
	public void youFinishedHide() {
		youFinishedImage.setStatus(Shape.STATUS.HIDE);
	}

    /**
        * youFinishedShow method
        * 
        * @implNote This method shows the youFinished sign
        *
        * @param (int x) (No parameters)
        * @return (No return value)
        */
	public void youFinishedShow(int x, int y) {
        youFinishedImage.moveToLocation(x, y);
        youFinishedImage.setzOrder(15);
		youFinishedImage.setStatus(Shape.STATUS.SHOW);
	}	
}