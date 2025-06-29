/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            Region.java
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

public class Region 
{
    //  Region status enumerator
	public enum RegionStatus 
    {
		REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT(0),
        REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE(1),
		REGION_STATUS_EMPTY (2),
		REGION_STATUS_CONQUERED_BY_SPACE_PILOT(3),
        REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT(4),
        REGION_STATUS_SPACE_PILOT_OVER(6),
        REGION_STATUS_SMALL_ENEMY_OVER(7),
        REGION_STATUS_SPACE_PILOT_CONQUERING(8);
		
		private final int status;
		private RegionStatus(int status) 
        {
            this.status  = status;
		}

		public int getStatus() 
        {
			return this.status;
		}
	}

//  Private variables for the class
	private         BoardPoint      location                                        = null;
	private         boolean         visible                                         = true;
    private         RegionStatus    regionStatus                                    = RegionStatus.REGION_STATUS_EMPTY;
    // the id of the graphic element that represents this entity
	private         String          guid                                            = ""; 
    private static  int             MAXIMUM_NUMBER_OF_RELEVANT_GAME_REGIONS_IN_GRID = 0;
    private static  int             currentNumberOfConqueredRegionsInGrid           = 0;
	
	public Region(int row, int column) 
    {
        this.location                               = new BoardPoint(row, column);
		this.guid                                   = "region_"+ row + "_" + column;
        this.regionStatus                           = RegionStatus.REGION_STATUS_EMPTY;
        this.visible                                = true;
        currentNumberOfConqueredRegionsInGrid       = 0;
	}
	
    public static void setMaximumNumberOfRelevantRegionsForGameInGrid(int maximumNumberOfRegionsInGame) 
    {
        MAXIMUM_NUMBER_OF_RELEVANT_GAME_REGIONS_IN_GRID = maximumNumberOfRegionsInGame;
    }

    public static int getMaximumNumberOfRelevantRegionsForGameInGrid() 
    {
        return (MAXIMUM_NUMBER_OF_RELEVANT_GAME_REGIONS_IN_GRID);
    }

    public static void resetConqueredRegions() 
    {
        currentNumberOfConqueredRegionsInGrid   = 0;
    }

    public void setRegionStatus(RegionStatus regionStatus) 
    {
        this.regionStatus   = regionStatus;
    }

    public RegionStatus getRegionStatus() 
    {
        return this.regionStatus;
    }

    public static int getNumberOfConqueredRegions() 
    {
        return (currentNumberOfConqueredRegionsInGrid);
    }

    public void setNumberOfConqueredRegions(int numberOfConqueredRegions) 
    {
        currentNumberOfConqueredRegionsInGrid   = numberOfConqueredRegions;
    }

    public static int getNumberOfUnconqueredRegions() 
    {
        return (MAXIMUM_NUMBER_OF_RELEVANT_GAME_REGIONS_IN_GRID - currentNumberOfConqueredRegionsInGrid);
    }

	public void hide() 
    {
		visible = false;
	}

	public void show() 
    {
		visible = true;
	}
	
	public String getGuid() 
    {
		return guid;
	}
	
	public BoardPoint getLocation() 
    {
		return location;
	}
	
	public boolean isShown() 
    {
		return visible;
	}
}