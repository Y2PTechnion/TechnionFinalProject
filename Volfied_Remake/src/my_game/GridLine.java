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

public class GridLine 
{
//  Private variables for the class
	private BoardPoint  p1  = null;
    private BoardPoint  p2  = null;

    /**
        * GridLine() constructor method
        * 
        * @implNote Constructor method with two BoardPoint parameters
        *
        * @param (BoardPoint p1) (First point of the line)
        * @param (BoardPoint p2) (Second point of the line)
        * @return (none) 
        */
	public GridLine(BoardPoint p1, BoardPoint p2) 
    {
		this.p1 = p1;
		this.p2 = p2;
	}
	
    /**
        * GridLine() constructor method
        * 
        * @implNote Constructor method with two BoardPoint parameters
        *
        * @param (int x1) (Column of the first point of the line)
        * @param (int y1) (Row of the first point of the line)
        * @param (int x2) (Column of the second point of the line)
        * @param (int y2) (Row of the second point of the line)
        * @return (none) 
        */
	public GridLine(int x1, int y1, int x2, int y2) 
    {
		this.p1 = new BoardPoint(x1, y1);
		this.p2 = new BoardPoint(x2, y2);
	}

    /**
        * p1 method
        * 
        * @implNote First point of the line getter method
        *
        * @param (none)
        * @return (BoardPoint) 
        */
    public BoardPoint p1() 
    {
        return this.p1;
    }

    /**
        * p2 method
        * 
        * @implNote Second point of the line getter method
        *
        * @param (none)
        * @return (BoardPoint) 
        */
    public BoardPoint p2() 
    {
        return this.p2;
    }

	public boolean blocksMove(BoardPoint p1, BoardPoint p2) 
    {
		int minX = Math.min(this.p1.getX(), this.p2.getX());
		int maxX = Math.max(this.p1.getX(), this.p2.getX());
		int minY = Math.min(this.p1.getY(), this.p2.getY());
		int maxY = Math.max(this.p1.getY(), this.p2.getY());
		
		//  both points are left to the line
		if (p1.getX() < minX && p2.getX() < minX) 
        {
			return false;
        }

		//  both points are right to the line (the modification to >= was done to include ALL the points in the grid)
		if (p1.getX() >= maxX && p2.getX() >= maxX) 
        {
			return false;
        }

		//  both points are above the line
		if (p1.getY() < minY && p2.getY() < minY) 
        {
			return false;
        }

		//  both points are below the line (the modification to >= was done to include ALL the points in the grid)
		if (p1.getY() >= maxY && p2.getY() >= maxY) 
        {
			return false;
        }
		
		//  If reached here, the points are within the range of line from both of its sides
		return true;
	}
	
	//  Checks whether the point x,y is on the line
	//  The check is simple because it assumes only horizontal and vertical lines.
	public boolean isOnLine(int x, int y) 
    {

		int minX = Math.min(this.p1.getX(), this.p2.getX());
		int maxX = Math.max(this.p1.getX(), this.p2.getX());
		int minY = Math.min(this.p1.getY(), this.p2.getY());
		int maxY = Math.max(this.p1.getY(), this.p2.getY());

		return (x >= minX && x <= maxX && y >= minY && y <= maxY);
	}
}