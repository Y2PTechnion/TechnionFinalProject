/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            BoardPoint.java
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
 * BoardPoint class
 * 
 * @implNote BoardPoint is used for point in Board coordinates, which is the grid. 
 *              Transformation to pixels (ScreenPoints) will be done by the board class.
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class BoardPoint 
{
//  Private variables for the class
    private int row     = 0;
    private int column  = 0;

    public BoardPoint(BoardPoint boardPoint) 
    {
        this.row    = boardPoint.getRow();
        this.column = boardPoint.getColumn();
    }

    public BoardPoint(int row, int column) 
    {
        this.row    = row;
        this.column = column;
    }

    public int getColumn() 
    {
        return this.column;
    }

    public int getRow() 
    {
        return this.row;
    }

    public void set(BoardPoint boardPoint) 
    {
        this.column = boardPoint.column;
        this.row    = boardPoint.row;
    }

    public void set(int row, int column) 
    {
        this.column = column;
        this.row    = row;
    }

    public void setColumn(int column) 
    {
        this.column = column;
    }

    public void setRow(int row) 
    {
        this.row    = row;
    }

    public boolean isEqual(BoardPoint boardPoint) 
    {
        boolean bIsEqual    = false;
        if (this.column == boardPoint.getColumn()
            && this.row == boardPoint.getRow())
        {
            bIsEqual    = true;
        }

        return bIsEqual;
    }
}