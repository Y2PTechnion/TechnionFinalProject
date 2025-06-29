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
import base.Game;
import base.GameContent;
import base.GameCanvas;
import base.GameDashboard;
import my_ui_elements.GetNameButton;
import my_ui_elements.HelpButton;
import shapes.Shape.STATUS;
import shapes.Image;
import shapes.Text;
import my_ui_elements.EndButton;
public class MyGame extends Game 
{	
	private MyContent content;

	@Override
	protected void initCanvas() 
    {
		/**
		 * DO NOT TOUCH FIRST 2 ROWS !!!
		 */
		GameCanvas canvas   = gameUI.canvas();
		canvas.setMouseHandler(Game.MouseHandler());
		
		content.getBoard().setCanvas(canvas);
		content.getBoard().initBoard();

        final int       textDeltaInY        = 25;
        final int       textSize            = 20;
        final Color     textColor           = Color.WHITE;
        final String    textFont            = new String("Helvetica");

        //  Add the text to the canvas
        Text    textPlayerName      = new Text("PlayerName", "Player: Not login yet", 700, 50);     
        textPlayerName.setColor(textColor);
        textPlayerName.setFontName(textFont);
        textPlayerName.setFontSize(textSize);
        textPlayerName.setStatus(STATUS.SHOW);
        canvas.addShape(textPlayerName);

        Text    textSpeed       = new Text("Speed", "Speed: 1 pixels per cycle", 700, 50 + textDeltaInY);     
        textSpeed.setColor(textColor);
        textSpeed.setFontName(textFont);
        textSpeed.setFontSize(textSize);
        textSpeed.setStatus(STATUS.SHOW);
        canvas.addShape(textSpeed);

        Text    textYossi    = new Text("Yossi", "Yossi Huttner             ID: 0-1183208-6", 20, 720 );
        textYossi.setColor(textColor);
        textYossi.setFontName(textFont);
        textYossi.setFontSize(textSize);
        canvas.addShape(textYossi);

        Text    textYuval    = new Text("Yuval", "Yuval Shechter          ID: 0-3170874-6", 20, 720 + 1 * textDeltaInY);
        textYuval.setColor(textColor);
        textYuval.setFontName(textFont);
        textYuval.setFontSize(textSize);
        canvas.addShape(textYuval);

        Text    textPablo    = new Text("Pablo", "Pablo Daniel Jelsky ID: 3-2093823-6", 20, 720 + 2 * textDeltaInY);
        textPablo.setColor(textColor);
        textPablo.setFontName(textFont);
        textPablo.setFontSize(textSize);
        canvas.addShape(textPablo);

        //  Add the you signs to the canvas, invisibles
        Image youFinished   = content.youFinished();
        canvas.addShape(youFinished);
        content.youFinishedHide();
        Image youWin        = content.youWin();
        canvas.addShape(youWin);
        content.youWinHide();
        Image gameOver      = content.gameOver();
        canvas.addShape(gameOver);
        content.gameOverHide();
	}
	
	@Override
	protected void initDashboard() 
    {
		super.initDashboard();
		GameDashboard dashboard = gameUI.dashboard();
		
		dashboard.setBackground(Color.BLACK);
        dashboard.addUIElement(new EndButton("btnEND", "END", 130, 40, 800, 50));
        dashboard.addUIElement(new GetNameButton("btnName", "Get Name", 130, 40, 800, 100));  
        dashboard.addUIElement(new HelpButton("btHelp", "Help", 130, 40, 500, 50));
	}
	
	@Override
	public void setGameContent(GameContent content) 
    {
		// Call the Game superclass to set its content 
		super.setGameContent(content);
		// point to the content with a variable of type MyContent so we have access to all
		// our game specific data
		this.content    = (MyContent) content;
	}
	
	public MyContent getContent() 
    {
		return this.content;
	}
	
	public static void main(String[] args) 
    {
		MyGame game = new MyGame();

		game.setGameContent(new MyContent());
		MyPeriodicLoop periodicLoop = new MyPeriodicLoop();
		periodicLoop.setContent(game.getContent());
		game.setPeriodicLoop(periodicLoop);
		game.setMouseHandler(new MyMouseHandler());
		game.setKeyboardListener(new MyKeyboardListener());
		game.initGame();
	}
}
