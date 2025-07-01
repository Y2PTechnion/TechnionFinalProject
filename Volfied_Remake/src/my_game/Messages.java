/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            Messages.java
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

import base.PeriodicLoop;

/**
 * This is a simple class that manages the messages.
 * It is responsible for resetting and increasing the score, as well managing all the screen messages.
 */
public class Messages 
{
//  Private variables for the class
	private int     score                       = 0;
    private double  conqueredRegionsPercentage  = 0.0;
    private long    initialTimeInMilliseconds   = 0;
	
	public void reset() 
    {
		score                       = 0;
        conqueredRegionsPercentage  = 0.0;
	}
	
	public void setNumberOfConqueredRegions(int numberOfConqueredRegions) 
    {
		this.score  = numberOfConqueredRegions;
	}

    public void setConqueredRegionsPercentage(double conqueredRegionsPercentage) 
    {
        this.conqueredRegionsPercentage = conqueredRegionsPercentage;
    }
	
	public int points() 
    {
		return score;
	}
	
	public String guidScore() 
    {
		return "score";
	}

	public String guidPercentage() 
    {
		return "percentage";
	}

	public String guidTime() 
    {
		return "time";
	}
	
	public String getText() 
    {
		return "Zones: " + score;
	}

	public String getPercentage() 
    {
        String  stringConquredRegionsPercentage = String.format("%.2f", conqueredRegionsPercentage);
		return (stringConquredRegionsPercentage + "  %") ;
	}

    public void setInitialTime(long initialTimeInMilliseconds)
    {
        this.initialTimeInMilliseconds   = initialTimeInMilliseconds;
    }

    public String getTime()
    {
        final int   SECONDS_PER_MINUTE  = 60;
        final int   MINUTES_PER_HOUR    = 60;
        final long  SECONDS             = (long) ((PeriodicLoop.elapsedTime() - initialTimeInMilliseconds) / 1000.0);
        int     seconds                 = (int) SECONDS % SECONDS_PER_MINUTE;
        int     minutes                 = ((int) SECONDS / SECONDS_PER_MINUTE);
        int     hours                   = ((int) SECONDS / (SECONDS_PER_MINUTE * MINUTES_PER_HOUR));
        String  stringTime  = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		return ("Elapsed: " + stringTime);
    }
}