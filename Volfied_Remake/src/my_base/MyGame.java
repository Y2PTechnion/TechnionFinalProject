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
import ui_elements.GameText;

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

//		canvas.setBackground(Color.WHITE);
//		canvas.setBackgroundImage("resources/background1.jpg");
//		canvas.addShape(content.polygon().getVisualPolygon());
//		Circle c = new Circle("circle", 300, 300, 50);
//		c.setDraggable(false);
//		canvas.addShape(c);
		/**
		 * This is a use of a shape button.
		 * Note that it uses the addToCanvas method of the button and is not added in the regular way,
		 * since it includes multiple ui elements.
		 */
//		SlowDownButton slow = new SlowDownButton("SlowDown", 800, 600);
//		slow.addToCanvas();
	}
	
	@Override
	protected void initDashboard() 
    {
		super.initDashboard();
		GameDashboard dashboard = gameUI.dashboard();
		
		dashboard.setBackground(Color.BLACK);

        dashboard.addUIElement(new GameText("e1", "%", 60, 40, 400, 10));
 //       dashboard.addUIElement(new Text("score", "Score: 0", 20, 20, 200, 40, Color.GREEN));

		// Add a the Polygon buttons
//		dashboard.addUIElement(new EditPolygonButton("editButton", "Edit", 60, 40));
//		dashboard.addUIElement(new RotatePolygonButton("rotateButton", "Rotate", 60, 100));

		// Add a the Circle drag checkbox
//		dashboard.addUIElement(new DragCircleCB("dragCB", "Drag Circle", 280, 80, 200, 40, false));

		// Add a the direction list combo
//		dashboard.addUIElement(new DirectionCombo(280, 40));

		// Add a the AddButton button
//		dashboard.addUIElement(new AddButton("addButton", "Add", 540, 40));
		
		// Add the ChangeButton button to the dashboard
//		dashboard.addUIElement(new ChangeButton("changeButton", "Change", 540, 100));
//		dashboard.addUIElement(new MusicButton("musicButton", "Play", 700, 40));

//		dashboard.addUIElement(new EndButton("btnEND", "END", 130, 40, 800, 50));
//		dashboard.addUIElement(new GetNameButton("btnName", "Get Name", 130, 40, 800, 100));
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

        //  Set the size of the game UI to 1000 x 1000 pixels
  //      gameUI.setSize(1000, 1000);

		game.setGameContent(new MyContent());
		MyPeriodicLoop periodicLoop = new MyPeriodicLoop();
		periodicLoop.setContent(game.getContent());
		game.setPeriodicLoop(periodicLoop);
		game.setMouseHandler(new MyMouseHandler());
		game.setKeyboardListener(new MyKeyboardListener());
		game.initGame();
	}
}
