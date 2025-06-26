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

import base.Game;
import base.MouseHandler;
import shapes.Shape;

public class MyMouseHandler extends MouseHandler 
{
	// set content to point to myContent so we can get to all characters in the game
	MyContent content = (MyContent) Game.Content();

	@Override
	public void screenClicked(int x, int y) 
    {
		super.screenClicked(x, y);
		// Enter your specific code here
	}

	@Override
	public void screenRightClicked(int x, int y) 
    {
		super.screenRightClicked(x, y);
		// Enter your specific code here
	}
	
	@Override
	public void shapeClicked(Shape shape, int x, int y) 
    {
		super.shapeClicked(shape, x, y);
		// Enter your specific code here
	}
	
	@Override
	public void shapeRightClicked(Shape shape, int x, int y) 
    {
		super.shapeRightClicked(shape, x, y);
		// Enter your specific code here
	}

	@Override
    public void mouseMovedOverShape(Shape shape, int x, int y) 
    {
		super.mouseMovedOverShape(shape, x, y);
		// Enter your specific code here
    }
	
	@Override
    public void mouseDraggedOverShape(Shape shape, int x, int y) 
    {
		super.mouseDraggedOverShape(shape, x, y);
		// Enter your specific code here
	}
	
	@Override 
	public void mouseMovedOverScreen(int x, int y) 
    {
		super.mouseMovedOverScreen(x, y);
	}
	
	@Override 
	public void mouseDraggedOverScreen(int x, int y) 
    {
		super.mouseDraggedOverScreen(x, y);
	}

	@Override 
	public void shapePressed(Shape shape, int x, int y) 
    {
		super.shapePressed(shape, x, y);
	}    
	
	@Override 
	public void shapeReleased(Shape shape, int x, int y) 
    {
		super.shapeReleased(shape, x, y);
	}

	@Override 
	public void screenPressed(int x, int y) 
    {
		super.screenPressed(x,y);
	}

	@Override 
	public void screenReleased(int x, int y) 
    {
		super.screenReleased(x,y);
	}
}