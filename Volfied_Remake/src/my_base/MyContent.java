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

import my_base.MainMenuGUI;

import java.awt.Color;

import base.Game;
import base.GameContent;
import my_game.Grid;
import my_game.SpacePilot;
import my_game.Board;
import my_game.BoardPoint;
import my_game.GameControl;
import my_game.SmallEnemies;
import my_game.Messages;
import my_game.StatusLine;
import shapes.Image;
import shapes.Shape;
import base.AudioPlayer.MusicStatus;

public class MyContent extends GameContent 
{
//  Private constants for the class
    //  Quantity of small enemies to place in  the board
    private final int   	QUANTITY_OF_SMALL_ENEMIES   = MainMenuGUI.selectedDifficultyInMainMenuGUI();
    private final int       MILLISECONDS_IN_SECOND      = 1000;

//  Private variables for the class
	private Grid 			grid                = null;
    private SpacePilot  	spacePilot          = null;
	private SmallEnemies	smallEnemies        = null;
	private Messages 		messages            = null;
	private StatusLine 		statusLine          = null;
    private StatusLine 		tipLine             = null;
	private Board           board               = null;
	private GameControl     gameControl         = null;
    private Image           gameImage           = null;

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

        gameImage           = new Image("gameImage", "resources/OpeningPicture1.png", 1024, 1024, 0,0);
        gameImage.setzOrder(15);
        gameImage.setStatus(Shape.STATUS.SHOW);

		if (Game.audioPlayer().getStatus() == MusicStatus.STOPPED) 
        {
		    Game.audioPlayer().play("resources/audio/Lunar Lander - Jupiter.wav", 1);
        }

		messages 			= new Messages();
		statusLine 		    = new StatusLine();
		statusLine.showText("Good Luck!", Color.GREEN, 5 * MILLISECONDS_IN_SECOND);
        tipLine             = new StatusLine();
		gameControl         = new GameControl(this);
	}	

    public int getQuantityOfEnemies()
    {
        return QUANTITY_OF_SMALL_ENEMIES;
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
	
	public Messages messages() 
    {
		return this.messages;
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
        * gameImage method
        * 
        * @implNote This method returns the game image sign to be shown
        *
        * @param () (No parameters)
        * @return (Image)
        */
	public Image gameImage() {
		return this.gameImage;
	}

    /**
        * gameImageHide method
        * 
        * @implNote This method hides the game image sign
        *
        * @param () (No parameters)
        * @return (No return value)
        */
	public void gameImageHide() {
		gameImage.setStatus(Shape.STATUS.HIDE);
	}

    /**
        * gameOverShow method
        * 
        * @implNote This method shows the game over image sign
        *
        * @param (int x) (No parameters)
        * @return (No return value)
        */
	public void gameOverShow(int x, int y) {
        gameImage.setImage("resources/gameOver.png", 225, 225);
        gameImage.moveToLocation(x, y);
        gameImage.setzOrder(15);
		gameImage.setStatus(Shape.STATUS.SHOW);
	}	

    /**
        * youWinShow method
        * 
        * @implNote This method shows the "you win" image sign
        *
        * @param (int x) (No parameters)
        * @return (No return value)
        */
	public void youWinShow(int x, int y) {
        gameImage.setImage("resources/youWin.png", 462, 240);
        gameImage.moveToLocation(x, y);
        gameImage.setzOrder(15);
		gameImage.setStatus(Shape.STATUS.SHOW);
	}	

    /**
        * youFinishedShow method
        * 
        * @implNote This method shows the "you finished" image sign
        *
        * @param (int x) (No parameters)
        * @return (No return value)
        */
	public void youFinishedShow(int x, int y) {
        gameImage.setImage("resources/youFinished.png", 257, 307);
        gameImage.moveToLocation(x, y);
        gameImage.setzOrder(15);
		gameImage.setStatus(Shape.STATUS.SHOW);
	}	
}