/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            MyKeyboardListener.java
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

import my_game.Grid;
import java.awt.event.KeyEvent;
import base.KeyboardListener;

public class MyKeyboardListener extends KeyboardListener
{
	private MyContent myContent;
	
	public MyKeyboardListener() 
    {
		super();
		myContent = (MyContent) this.content;
	}

	@Override
	public void directionalKeyPressed(Direction direction) 
    {
		switch (direction) 
        {
            case RIGHT: 
            {
                //  If the direction is right, then move west
			    myContent.spacePilot().directionalKeyPressed(Grid.Direction.WEST);
			    break;
			}

		    case LEFT: 
            {
                //  If the direction is left, then move east
		  	    myContent.spacePilot().directionalKeyPressed(Grid.Direction.EAST);
			    break;
			}

		    case UP: 
            {
                //  If the direction is up, then move north
		        myContent.spacePilot().directionalKeyPressed(Grid.Direction.NORTH);
			    break;
			}

		    case DOWN: 
            {
                //  If the direction is down, then move south
		        myContent.spacePilot().directionalKeyPressed(Grid.Direction.SOUTH);
			    break;
            }

            default: 
            {
                //  If the direction is not recognized or allowed, do nothing
                System.out.println("Unrecognized direction: " + direction);
                break;
            }
		}
	}
	
	@Override
	public void characterTyped(char c) 
    {
		System.out.println("key character = '" + c + "'" + " pressed.");
	}
	
	@Override
	public void enterKeyPressed() 
    {
		System.out.println("enter key pressed.");
	}
	
	@Override
	public void backSpaceKeyPressed() 
    {
		System.out.println("backSpace key pressed.");
	}
	
	@Override
	public void spaceKeyPressed() 
    {
		System.out.println("space key pressed.");
	}
	
	public void otherKeyPressed(KeyEvent e) 
    {
		System.out.println("other key pressed. type:" + e);
	}
}