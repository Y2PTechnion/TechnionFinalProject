/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            GameControl.java
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

import java.awt.Color;
import java.util.LinkedList;

import javax.lang.model.util.ElementScanner6;

import base.Game;
import base.PeriodicLoop;
import base.KeyboardListener.Direction;
import my_base.MyContent;
import my_game.Region.RegionStatus;
import my_ui_elements.EndButton;
import my_ui_elements.GetNameButton;
import my_base.MyGame;

public class GameControl 
{
    //  Private constants for the class
    private final int               GRID_PERCENTAGE_TO_CONQUER                  = 80; //  Percentage of the grid to conquer to win the game
    private final int               MILLISECONDS_IN_SECOND                      = 1000;
    private final int               OPENING_PICTURE_TO_BE_SHOWN_TIME_IN_SECONDS = 15;
    private final int               OPENING_PICTURE_TO_BE_SHOWN_TIME            = OPENING_PICTURE_TO_BE_SHOWN_TIME_IN_SECONDS * MILLISECONDS_IN_SECOND;
    //  Private variables for the class
    private MyContent               content                                     = null;
	private Board                   board                                       = null;
    private TipManager              tipManager                                  = null;
    static private boolean          getSpacePilotIsOutsideSafeZone              = false;
    static private BoardPoint       boardPointInsideThePotentialConqueredZone   = new BoardPoint(0, 0);
    static private int              spacePilotLeftistColumnWhenOutsideSafeZone  = Grid.getTotalGameCellsInXPerRow();
    static private int              spacePilotRightestColumnWhenOutsideSafeZone = 0;
    static private int              spacePilotSouthestRowWhenOutsideSafeZone    = 0;
    static private int              spacePilotNorthestRowWhenOutsideSafeZone    = Grid.getTotalGameCellsInYPerColumn();
    static private boolean          isGameOver                                  = false;
    static private boolean          isOpeningPictureShown                       = true;
    private BoardPoint              lastInsideOfSafeZonePoint                   = null;
    private BoardPoint              firstOutsideOfSafeZonePoint                 = null;
    private LinkedList<BoardPoint>  tripLinkedList                              = null;

    public GameControl(MyContent content) 
    {
        this.content                            = content;
        this.board                              = content.getBoard();
        this.tipManager                         = new TipManager(this.board.getCanvas());
        this.lastInsideOfSafeZonePoint          = new BoardPoint(0, 0);
        this.firstOutsideOfSafeZonePoint        = new BoardPoint(0, 0);
        this.tripLinkedList                     = new LinkedList<>();
        content.messages().setInitialTime(OPENING_PICTURE_TO_BE_SHOWN_TIME);
        content.messages().setLives(content.spacePilot().getLives());
    }

    public int getTargetPercentageToWin()
    {
        return GRID_PERCENTAGE_TO_CONQUER;
    }

    /**
        * gameStep() method
        * 
        * @implNote this method is the "cyclic" game step called by MyPeriodicLoop class
        *               to perform the mechanics of the game
        *
        * @param (none)
        * @return (none) 
        */
	public void gameStep() 
    {
        final BoardPoint        SOURCE_LOCATION         = content.spacePilot().getLocation();
        Region                  region                  = conquerCurrentRegion(content.spacePilot().getLocation());
        Region[][]              regionsAfterFloodFill   = null;

        if (false == isOpeningPictureShown)
        {
            //  Logic section of gameStep()         
                //  Space pilot moving method
                boolean     regionsWereConquered    = false;
                if (false == isGameOver)
                {
                    //  If the game is over, don't let the player move the space pilot
                    regionsWereConquered    = spacePilotMoving(SOURCE_LOCATION, regionsAfterFloodFill);
                }

                final BoardPoint    destinationLocation     = content.spacePilot().getLocation();
                //  Try to move the small enemies randomally
                content.smallEnemies().move();
                
                if (false == isGameOver)
                {
                    //  Handle collisions between small enemies and space pilot
                    if ((true == handleCollisionBetweenSmallEnemyAndSpacePilot())
                        //  Handle collisions between small enemies and space pilot green lines
                        || (true == handleCollisionBetweenSmallEnemyAndGreenLines()))
                    {
                        //  Reduce the space pilot lives
                        content.spacePilot().reduceLives();
                        content.messages().setLives(content.spacePilot().getLives());

                        if (0 == content.spacePilot().getLives())
                        {
                            isGameOver  = true;
                        }
                        else
                        {
                            final BoardPoint    LAST_SPACE_PILOT_POINT_IN_SAFE_ZONE =   content.grid().getLastSpacePilotPointInSafeZone();

                            content.grid().hideUnusedGridLines();
                            content.grid().resetConqueringRegions();
                            //  Reset the limits of space pilot when outside safe zone
                            resetLimitsOfSpacePilotWhenOutsideSafeZone();
                            content.spacePilot().setLocation(LAST_SPACE_PILOT_POINT_IN_SAFE_ZONE);
                            regionsAfterFloodFill           = null;
                            getSpacePilotIsOutsideSafeZone  = false;
                            content.youLostLifeShow(320, 260);
                            content.statusLine().showText("You lost one life", Color.ORANGE, 2 * MILLISECONDS_IN_SECOND);
                            Game.audioPlayer().play("resources/audio/Buzz.wav", 1);
                        }
                    }

                    if (true == isGameOver)
                    {
                        content.gameOverShow(350, 320);
                        content.statusLine().showText("Oops " + GetNameButton.getPlayerName() + " you LOST...", Color.RED, 60 * MILLISECONDS_IN_SECOND);
                        Game.audioPlayer().play("resources/audio/aw_crap.wav", 1);
                        content.grid().hideUnusedGridLines();
                    }
                }

            //  Graphics (canvas) section of gameStep()
            //      Update space pilot in canvas
            board.updateSpacePilotInCanvas();

            if ((SOURCE_LOCATION.getColumn() != destinationLocation.getColumn())
                || (SOURCE_LOCATION.getRow() != destinationLocation.getRow()))
            {
                final RegionStatus  DESTINATION_REGION_STATUS   
                    = content.grid().regions()[destinationLocation.getRow()][destinationLocation.getColumn()].getRegionStatus();

                if (DESTINATION_REGION_STATUS != RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT)
                {
                    //  Update grid space pilot lines in canvas (but only if it not in border conquered)
                    content.grid().addGridSpacePilotLines(SOURCE_LOCATION, destinationLocation);
                }
            }

            if (true == regionsWereConquered)
            {
                content.grid().zoneCaptureGameCompletionAlgorithm();
                //  Update the number of conquered regions
                final int NUMBER_OF_CONQUERED_REGIONS   = content.grid().updateNumberOfConqueredRegions();
                content.grid().regions()[0][0].setNumberOfConqueredRegions(NUMBER_OF_CONQUERED_REGIONS);

                content.messages().setNumberOfConqueredRegions(NUMBER_OF_CONQUERED_REGIONS);
                content.messages().setConqueredRegionsPercentage(content.grid().getPercentageOfConqueredRegions());

                //  It will hide the space pilot grid lines when one of the areas is conquered
                content.grid().hideUnusedGridLines();
            }

            //      Update small enemies in canvas
            board.updateSmallEnemiesInCanvas();

            //  Update section of gameStep()
            if (null != region) 
            {
                System.out.println("Space pilot at: " + region.getLocation().getRow() + ", " 
                    + region.getLocation().getColumn() + ", " + region.getGuid() + ", " + region.getRegionStatus());
            }

            board.updateStatusLine();
            board.updateTipLine();
            if (false == isGameOver)
            {
                //  Only update messages during game
                board.updateMessages();
            }
            tipManager.update();
            content.statusLine().refresh();
            content.tipLine().refresh();

    //		content.historyRecorder().recordState();
            //  Verify if game is over
            if ((false == isGameOver) && (true == checkGameOver()))
            {
                isGameOver  = true;
    //            Game.endGame();
            }

            if ("" == content.statusLine().getText())
            {
                //  If the status line is "", then also hide the image
                content.gameImageHide();
            }
        }
        else if (PeriodicLoop.elapsedTime() > OPENING_PICTURE_TO_BE_SHOWN_TIME)
        {
            isOpeningPictureShown   = false;
            content.gameImageHide();
            Game.audioPlayer().stop();
            MyGame.addButtonsToDashboard();
        }
	}

    private boolean checkGameOver() 
    {
        if (content.grid().getPercentageOfConqueredRegions() >= GRID_PERCENTAGE_TO_CONQUER) 
        {
            content.youWinShow(300,320);
            content.statusLine().showText("You WON !!!", Color.GREEN, 60 * MILLISECONDS_IN_SECOND);
            content.grid().hideUnusedGridLines();
            Game.audioPlayer().play("resources/audio/Applause.wav", 1);

            return true;
        }
        else    
        {
            if (true == EndButton.endButtonPushed())
            {
                content.youFinishedShow(320, 260);
                content.statusLine().showText("You decided to finish !!!, Whyyyyy ????", Color.ORANGE, 60 * MILLISECONDS_IN_SECOND);
                content.grid().hideUnusedGridLines();
                Game.audioPlayer().play("resources/audio/aw_crap.wav", 1);

                return true;
            }
        }

        return false;
    }   

    /**
     * handleCollisionBetweenSmallEnemyAndSpacePilot method
     * 
     * @implNote handleCollisionBetweenSmallEnemyAndSpacePilot method to check if the space pilot 
     *              collides with any small enemy
     *
     * @param none
     * @return (boolean) true if collision occurred, false otherwise
     */
	private boolean handleCollisionBetweenSmallEnemyAndSpacePilot() 
    {
		SpacePilot 		spacePilot   	= content.spacePilot();
		SmallEnemies	smallEnemies	= content.smallEnemies();
		
		for (SmallEnemy s : smallEnemies.getSmallEnemies()) 
        {
			if (s.getLocation().isEqual(spacePilot.getLocation())) 
            {
                if (true == s.isSmallEnemyRunning())
                {
                    //  Only if the small enemy is running (not frozen)
				    return true;
                }
			}
		}

        return false;
	}

    /**
     * handleCollisionBetweenSmallEnemyAndGreenLines method
     * 
     * @implNote handleCollisionBetweenSmallEnemyAndGreenLines method to check if one of the small 
     *              enemies collides with the green line of the space pilot
     *
     * @param none
     * @return (boolean) true if collision occurred, false otherwise
     */
	private boolean handleCollisionBetweenSmallEnemyAndGreenLines() 
    {
		SmallEnemies	smallEnemies	= content.smallEnemies();
		
		for (SmallEnemy s : smallEnemies.getSmallEnemies()) 
        {
            final RegionStatus    CURRENT_ENEMY_REGION_STATUS 
                = content.grid().regions()[s.getLocation().getRow()][s.getLocation().getColumn()].getRegionStatus();

            if (CURRENT_ENEMY_REGION_STATUS == RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING)
            {
                //  If the region is a conquering region
                return true;
            }
		}

        return false;
	}

	public Region conquerCurrentRegion(BoardPoint location) 
    {
        final   int locationRow     = location.getRow();
        final   int locationColumn  = location.getColumn();

		Region      region          = content.grid().regions()[locationRow][locationColumn]; 

		if (true == region.isShown()) 
        {
			region.hide();
//			content.score().add(1);
//			content.grid().setRegionAsConquered(region);

            //  Next lines won't be relevant later, only for fun by now
			if (0 == content.grid().getCurrentNumberOfUnconqueredRegions()) 
            {
//				content.statusLine().showText("Great JOB !!!", Color.YELLOW, 5000);
			}

			return region; 
		}

		return null;
	}

    private boolean spacePilotMoving(BoardPoint sourceLocation, Region[][] regionsAfterFloodFill)
    {
        boolean     regionsWereConquered                        = false;

        //  Try to move the space pilot, if it was moved
        boolean     SPACE_PILOT_HAS_MOVED                       = content.spacePilot().move();

        if (true == SPACE_PILOT_HAS_MOVED)
        {
                //  If the space pilot has moved
        //  Get space pilot destination location
            final BoardPoint    DESTINATION_LOCATION        = content.spacePilot().getLocation();
            Region              destinationRegion           = content.grid().regions()[DESTINATION_LOCATION.getRow()][DESTINATION_LOCATION.getColumn()]; 
            final RegionStatus  DESTINATION_REGION_STATUS   = destinationRegion.getRegionStatus(); 

            switch (DESTINATION_REGION_STATUS)
            {
                case REGION_STATUS_CONQUERED_BY_SPACE_PILOT:
                case REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT:
                case REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT:
                case REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT_BUT_NOT_IN_USE_ANYMORE:
                {
                    //  The space pilot has reached a region that is already conquered (safe zone)

                    if (true == getSpacePilotIsOutsideSafeZone)
                    {
                        //  Sets all the info needed to know the 'trip' the space pilot is doing
                        if (!DESTINATION_LOCATION.isEqual(sourceLocation))
                        {
                            //  Only if the space pilot has moved
                            tripLinkedList.add(DESTINATION_LOCATION);
                        }

                        final int       MAXIMUM_POTENTIAL_POINTS_INSIDE_THE_CONQUERED_ZONE_SIZE = 8;
                        BoardPoint[]    potentialPointsInsideTheConqueredZone                   = new BoardPoint[]
                        {
                            new BoardPoint(0, 0), 
                            new BoardPoint(0, 0),
                            new BoardPoint(0, 0), 
                            new BoardPoint(0, 0),
                            new BoardPoint(0, 0), 
                            new BoardPoint(0, 0),
                            new BoardPoint(0, 0), 
                            new BoardPoint(0, 0)
                        };  

                        //  Find a board point inside the potential conquered zone 
                        //  to start the flood fill algorithm
                            final int POTENTIAL_POINTS_FOUND_INSIDE_THE_CONQUERED_ZONE = 
                            getBoardPointInsideThePotentialConqueredZone(tripLinkedList, 
                            MAXIMUM_POTENTIAL_POINTS_INSIDE_THE_CONQUERED_ZONE_SIZE, 
                            potentialPointsInsideTheConqueredZone);
    
                        if (POTENTIAL_POINTS_FOUND_INSIDE_THE_CONQUERED_ZONE >= 1)
                        {
                            //  The space pilot has reached one of the safe places
                            //  after conquering
                            //  Perform flood fill algorithm
                            final int CONQUERED_REGIONS_UNTIL_NOW = content.grid().calculateNumberOfConqueredRegions();

                            Grid[] floodFillAlgorithmTestCasesGrid      = new Grid[] 
                            {
                                new Grid(content.grid()),
                                new Grid(content.grid()),
                                new Grid(content.grid()),
                                new Grid(content.grid()),
                                new Grid(content.grid()),
                                new Grid(content.grid()),
                                new Grid(content.grid()),
                                new Grid(content.grid())
                            };
                            int floodFillAlgorithmTestCasesConqueredRegions[] = new int[POTENTIAL_POINTS_FOUND_INSIDE_THE_CONQUERED_ZONE];

                            for (int testCase = 0; testCase < POTENTIAL_POINTS_FOUND_INSIDE_THE_CONQUERED_ZONE; testCase++)
                            {
                                regionsAfterFloodFill   = floodFillAlgorithmTestCasesGrid[testCase].floodFillAlgorithm(
                                    floodFillAlgorithmTestCasesGrid[testCase].regions(), 
                                    potentialPointsInsideTheConqueredZone[testCase], 
                                    RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
                                floodFillAlgorithmTestCasesConqueredRegions[testCase] = 
                                    (null != regionsAfterFloodFill) ? 
                                        floodFillAlgorithmTestCasesGrid[testCase].calculateNumberOfConqueredRegions()-CONQUERED_REGIONS_UNTIL_NOW 
                                        : -1;
                            }

                            //  Nullify the references to the grid to make them eligible for garbage collection
                            for (int numberOfGrid = 0; numberOfGrid < floodFillAlgorithmTestCasesGrid.length; numberOfGrid++) 
                            {
                                floodFillAlgorithmTestCasesGrid[numberOfGrid] = null; // Nullify the reference
                            }

                            //  Find the best flood fill algorithm test case
                            int bestFloodFillAlgorithmTestCase  = -1;

                            //  Find the lowest
                            for (int testCase = 0; testCase < POTENTIAL_POINTS_FOUND_INSIDE_THE_CONQUERED_ZONE; testCase++)
                            {
                                if (-1 != floodFillAlgorithmTestCasesConqueredRegions[testCase])
                                {
                                    //  If the test case is valid
                                    if (-1 == bestFloodFillAlgorithmTestCase)
                                    {
                                        //  If this is the first valid one
                                        bestFloodFillAlgorithmTestCase  = testCase;
                                    }
                                    else
                                    {

                                        if (floodFillAlgorithmTestCasesConqueredRegions[testCase] <
                                            floodFillAlgorithmTestCasesConqueredRegions[bestFloodFillAlgorithmTestCase])
                                        {
                                            bestFloodFillAlgorithmTestCase = testCase;
                                        }
                                    }
                                }
                            }

                            if (-1 != bestFloodFillAlgorithmTestCase)
                            {
                                regionsAfterFloodFill = content.grid().floodFillAlgorithm(
                                                    content.grid().regions(), 
                                                    potentialPointsInsideTheConqueredZone[bestFloodFillAlgorithmTestCase], 
                                                    RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT);
                            }

                            //  Nullify the references to the BoardPoint to make them eligible for garbage collection
                            for (int potentialPoint = 0; potentialPoint < POTENTIAL_POINTS_FOUND_INSIDE_THE_CONQUERED_ZONE; potentialPoint++) 
                            {
                                potentialPointsInsideTheConqueredZone[potentialPoint] = null; // Nullify the reference
                            }
                        }
                        else if (1 == POTENTIAL_POINTS_FOUND_INSIDE_THE_CONQUERED_ZONE)
                        {
                            System.out.println("Should not happen");
                        }
                        else    
                        {
                            System.out.println("Should not happen");
                        }
                    
                        //  Resets the info about the 'trip' the space pilot did
                        tripLinkedList.clear();
                        lastInsideOfSafeZonePoint.set(new BoardPoint(0,0));
                        firstOutsideOfSafeZonePoint.set(new BoardPoint(0,0));

                        if (null != regionsAfterFloodFill)
                        {
                            regionsWereConquered                    = true;
                        }
                        else
                        {
                            //  If one of the region status is SMALL_ENEMY_OVER
                            regionsWereConquered                    = false;
                            //  Reverse all what should be done
                        }

                        //  Reset the limits of space pilot when outside safe zone
                        resetLimitsOfSpacePilotWhenOutsideSafeZone();
                    }
                    else
                    {
                        //  Reset the limits of space pilot when outside safe zone
                        resetLimitsOfSpacePilotWhenOutsideSafeZone();
                        //  Update the limits of space pilot when even when inside safe zone
                        updateLimitsOfSpacePilotWhenOutsideSafeZone(sourceLocation);
                        updateLimitsOfSpacePilotWhenOutsideSafeZone(DESTINATION_LOCATION);
                    }

                    //  The space pilot has reached a region that is already conquered
                    getSpacePilotIsOutsideSafeZone  = false;
                    break;
                }

                case REGION_STATUS_SPACE_PILOT_CONQUERING:
                {
                    if ((true == getSpacePilotIsOutsideSafeZone)
                        && (!sourceLocation.isEqual(DESTINATION_LOCATION)))
                    {
                        //  Because of Yuval's comments, when making loops within
                        //  conquering regions, then reset the limits, if we 
                        //  were moving between different locations.
                        //  Reset the limits of space pilot when outside safe zone
                        resetLimitsOfSpacePilotWhenOutsideSafeZone();
                            //  Update the limits of space pilot when even when inside safe zone
                        updateLimitsOfSpacePilotWhenOutsideSafeZone(sourceLocation);
                        updateLimitsOfSpacePilotWhenOutsideSafeZone(DESTINATION_LOCATION);
                    }   
                    else
                    {
                        //  Do nothing, the region is being conquered
                    }
                    break;
                }

                case REGION_STATUS_EMPTY:
                {
                    if (false == getSpacePilotIsOutsideSafeZone)
                    {
                        //  The space pilot has in this movement
                        //  go out from the safe zone
                        //  The space pilot has reached a region that is NOT already conquered
                        getSpacePilotIsOutsideSafeZone          = true;

                        //  Sets all the info needed to know the 'trip' the space pilot is doing
                        lastInsideOfSafeZonePoint               = sourceLocation;
                        firstOutsideOfSafeZonePoint             = DESTINATION_LOCATION;
                        tripLinkedList.clear();
                        tripLinkedList.add(lastInsideOfSafeZonePoint);
                    }
                    else
                    {
                        //  Sets all the info needed to know the 'trip' the space pilot is doing
                        if (!DESTINATION_LOCATION.isEqual(sourceLocation))
                        {
                            //  Only if the space pilot has moved
                            tripLinkedList.add(DESTINATION_LOCATION);
                        }
                    }

                    //  Set the region as a region being conquered
                    destinationRegion.setRegionStatus(RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING);

                    //  Update the limits of space pilot when outside safe zone
                    updateLimitsOfSpacePilotWhenOutsideSafeZone(sourceLocation);
                    updateLimitsOfSpacePilotWhenOutsideSafeZone(DESTINATION_LOCATION);
                    
                    //  Sets all the info needed to know the 'trip' the space pilot is doing
                    tripLinkedList.add(firstOutsideOfSafeZonePoint);
                    break;
                }

                case REGION_STATUS_SPACE_PILOT_OVER:
                case REGION_STATUS_SMALL_ENEMY_OVER:
                default:
                {
                    System.out.println("In gameStep() should not happen");
                }
            }

        }
 
        return regionsWereConquered;
    }

    private void updateLimitsOfSpacePilotWhenOutsideSafeZone(BoardPoint boardPoint)
    {
        if (boardPoint.getColumn() < spacePilotLeftistColumnWhenOutsideSafeZone)
        {
            spacePilotLeftistColumnWhenOutsideSafeZone  = boardPoint.getColumn();
        }
        if (boardPoint.getColumn() > spacePilotRightestColumnWhenOutsideSafeZone)
        {
            spacePilotRightestColumnWhenOutsideSafeZone = boardPoint.getColumn();
        }       
        if (boardPoint.getRow() > spacePilotSouthestRowWhenOutsideSafeZone)
        {
            spacePilotSouthestRowWhenOutsideSafeZone    = boardPoint.getRow();
        }   
        if (boardPoint.getRow() < spacePilotNorthestRowWhenOutsideSafeZone)
        {
            spacePilotNorthestRowWhenOutsideSafeZone    = boardPoint.getRow();
        }  
    }

    private void resetLimitsOfSpacePilotWhenOutsideSafeZone()
    {
        spacePilotLeftistColumnWhenOutsideSafeZone  = Grid.getTotalGameCellsInXPerRow();
        spacePilotRightestColumnWhenOutsideSafeZone = 0;
        spacePilotSouthestRowWhenOutsideSafeZone    = 0;
        spacePilotNorthestRowWhenOutsideSafeZone    = Grid.getTotalGameCellsInYPerColumn();
    }


    /**
        * findBoardPointInsideThePotentialConqueredZone algorithm method
        * 
        * @implNote This function is critical for the game logic, as it finds a point inside the potential conquered zone.
        *           This point is used to start the flood fill algorithm, which will change the status of the regions
        *           connected to the space pilot's current position to "conquered" status.
        *           The algorithm is designed to find a point that is surrounded by regions that are being conquered by the space pilot.
        *           Failure to find such a point will result in the flood fill algorithm not being able to proceed, conquering 100% of the grid.
        *
        * @implNote This implementation is not 'generic', it is adapted to the Volfied Remake game
        *
        * @param (LinkedList<BoardPoint> tripLinkedList) ('trip' complete linked list of board points)
        * @param (int maximumPotentialZonesInsideConqueredZoneSize) (maximum number of potential points to return to application)
        * @param (BoardPoint[] boardPointInsideThePotentialConqueredZone) array of board points that will be filled with the found points
        * @return (int) (number of found potential points)
        */
    private int findBoardPointInsideThePotentialConqueredZone(LinkedList<BoardPoint> tripLinkedList,
        int maximumPotentialZonesInsideConqueredZoneSize, 
        BoardPoint[] boardPointInsideThePotentialConqueredZone)
    {
        int quantityOfPotentialPointsInsideTheConqueredZoneFound    = 0;
        int             numberOfElementsInList                      = 0;
        BoardPoint      lastPointInSafeZone                         = null;
        BoardPoint      firstPointOutsideSafeZone                   = null;
        BoardPoint      previousPointInList                         = null;
        Grid.Direction  currentDirection                            = null;
        Grid.Direction  previousDirection                           = null;
        BoardPoint      foundPoint                                  = null;

        for (BoardPoint linkedListBoardPoint : tripLinkedList) 
        { // Enhanced for loop
                //  Calculate the sum of rows and columns of the linked list points
            if (0 == numberOfElementsInList)
            {
                lastPointInSafeZone         = new BoardPoint(linkedListBoardPoint);
                System.out.println("Linked list 1st: " + lastPointInSafeZone.getRow() + ", " + lastPointInSafeZone.getColumn());
            } 
            else if (1 == numberOfElementsInList)
            {
                firstPointOutsideSafeZone   = new BoardPoint(linkedListBoardPoint);
                previousDirection           = content.grid().moveDirection(lastPointInSafeZone, firstPointOutsideSafeZone);
                previousPointInList         = new BoardPoint(linkedListBoardPoint);
                System.out.println("Linked list 2nd: " + firstPointOutsideSafeZone.getRow() + ", " + firstPointOutsideSafeZone.getColumn());
            }
            else
            {
                if ((!linkedListBoardPoint.isEqual(previousPointInList)) 
                    //  Next line because of bug in linked list
                    && (!linkedListBoardPoint.isEqual(firstPointOutsideSafeZone)))
                {
                    final int CURRENT_INDEX = numberOfElementsInList + 1;
                    System.out.println("Linked list " + CURRENT_INDEX + ": " + linkedListBoardPoint.getRow() + ", " + linkedListBoardPoint.getColumn());
                    //  Only if the linked list point is different from the previous one
                    currentDirection            = content.grid().moveDirection(previousPointInList, linkedListBoardPoint);
                    if (currentDirection != previousDirection)
                    {
                        BoardPoint  potentialBoardPoint 
                            = new BoardPoint(
                                linkedListBoardPoint.getRow() - previousDirection.yVector(), 
                                linkedListBoardPoint.getColumn() - previousDirection.xVector());

                        RegionStatus    POTENTIAL_REGION_STATUS   
                            = content.grid().regions()[potentialBoardPoint.getRow()][potentialBoardPoint.getColumn()].getRegionStatus();

                        if (RegionStatus.REGION_STATUS_EMPTY == POTENTIAL_REGION_STATUS)
                        {
                            if (quantityOfPotentialPointsInsideTheConqueredZoneFound < maximumPotentialZonesInsideConqueredZoneSize)
                            {
                                foundPoint          = new BoardPoint(potentialBoardPoint);
                                boardPointInsideThePotentialConqueredZone[quantityOfPotentialPointsInsideTheConqueredZoneFound].set(foundPoint);
                                quantityOfPotentialPointsInsideTheConqueredZoneFound++;
                            }
                            foundPoint          = null;
                        }

                        potentialBoardPoint.set(linkedListBoardPoint.getRow() + previousDirection.yVector(), 
                                linkedListBoardPoint.getColumn() + previousDirection.xVector());

                        POTENTIAL_REGION_STATUS   
                            = content.grid().regions()[potentialBoardPoint.getRow()][potentialBoardPoint.getColumn()].getRegionStatus();

                        if (RegionStatus.REGION_STATUS_EMPTY == POTENTIAL_REGION_STATUS)
                        {
                            if (quantityOfPotentialPointsInsideTheConqueredZoneFound < maximumPotentialZonesInsideConqueredZoneSize)
                            {
                                foundPoint          = new BoardPoint(potentialBoardPoint);
                                boardPointInsideThePotentialConqueredZone[quantityOfPotentialPointsInsideTheConqueredZoneFound].set(foundPoint);
                                quantityOfPotentialPointsInsideTheConqueredZoneFound++;
                            }
                            foundPoint          = null;
                        }

                        potentialBoardPoint     = null;
                        previousDirection       = currentDirection;
                    }

                    previousPointInList.set(linkedListBoardPoint);
                }
            }  
            numberOfElementsInList++;
        }

        for (int notFoundPoint = quantityOfPotentialPointsInsideTheConqueredZoneFound; 
                notFoundPoint < maximumPotentialZonesInsideConqueredZoneSize; notFoundPoint++)
        {
            boardPointInsideThePotentialConqueredZone[notFoundPoint].set(new BoardPoint(-1, -1));
        }

        return quantityOfPotentialPointsInsideTheConqueredZoneFound;
    }

    /**
        * getBoardPointInsideThePotentialConqueredZone algorithm method
        * 
        * @implNote This function is critical for the game logic, as it finds a point inside the potential conquered zone.
        *           This point is used to start the flood fill algorithm, which will change the status of the regions
        *           connected to the space pilot's current position to "conquered" status.
        *           The algorithm is designed to find a point that is surrounded by regions that are being conquered by the space pilot.
        *           Failure to find such a point will result in the flood fill algorithm not being able to proceed, conquering 100% of the grid.
        *
        * @implNote This implementation is not 'generic', it is adapted to the Volfied Remake game
        *
        * @param (LinkedList<BoardPoint> tripLinkedList) ('trip' complete linked list of board points)
        * @param (int maximumPotentialZonesInsideConqueredZoneSize) (maximum number of potential points to return to application)
        * @param (BoardPoint[] boardPointInsideThePotentialConqueredZone) array of board points that will be filled with the found points
        * @return (int) (number of found potential points)
        */
    private int getBoardPointInsideThePotentialConqueredZone(LinkedList<BoardPoint> tripLinkedList, 
            int maximumPotentialZonesInsideConqueredZoneSize, 
            BoardPoint[] boardPointInsideThePotentialConqueredZone)
    {
        //  Find a board point inside the potential conquered zone 
        //  to start the flood fill algorithm
        int centerRow               = -1;
        int centerColumn            = -1;
        int AVERAGE_ROW             = (int) ((spacePilotNorthestRowWhenOutsideSafeZone + spacePilotSouthestRowWhenOutsideSafeZone) / 2.0);
        int AVERAGE_COLUMN          = (int) ((spacePilotLeftistColumnWhenOutsideSafeZone + spacePilotRightestColumnWhenOutsideSafeZone) / 2.0);

        final int QUANTITY_OF_POTENTIAL_POINTS_FOUND = findBoardPointInsideThePotentialConqueredZone(tripLinkedList,
            maximumPotentialZonesInsideConqueredZoneSize, 
            boardPointInsideThePotentialConqueredZone);

        if (0 != QUANTITY_OF_POTENTIAL_POINTS_FOUND)
        {
            for (int point = 0; point < QUANTITY_OF_POTENTIAL_POINTS_FOUND; point++)
            {
                System.out.println("Point found" + point + ": "  + 
                boardPointInsideThePotentialConqueredZone[point].getRow() + ", " 
                + boardPointInsideThePotentialConqueredZone[point].getColumn());

                if ((-1 != boardPointInsideThePotentialConqueredZone[point].getRow())
                    && (-1 != boardPointInsideThePotentialConqueredZone[point].getColumn()))
                {
                }
            }

            return QUANTITY_OF_POTENTIAL_POINTS_FOUND;         
        }

        //  Verify first rows, later columns
        for (int row = spacePilotNorthestRowWhenOutsideSafeZone; row <= spacePilotSouthestRowWhenOutsideSafeZone; row++)
        {
            int leftConqueringColumn    = -1;
            int emptyColumn             = -1;
            int rightConqueringColumn   = -1;

            if ((-1 != centerRow) && (-1 != centerColumn))
            {
                break;
            }

            for (int column = spacePilotLeftistColumnWhenOutsideSafeZone; column <= spacePilotRightestColumnWhenOutsideSafeZone; column++)
            {
                if ((-1 != centerRow) && (-1 != centerColumn))
                {
                    break;
                }

                Region  currentRegion   = this.content.grid().regions()[row][column];
                if (RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus())
                {
                    //  We found a conquering region
                    if (-1 == leftConqueringColumn)
                    {
                        leftConqueringColumn    = column;
                    }
                    else if ((-1 == rightConqueringColumn) && (-1 != emptyColumn))  
                    {
                        rightConqueringColumn   = column;
                        centerRow               = row;
                        centerColumn            = emptyColumn;
                        break;
                    }
                } 
                else if (RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus())
                {
                    if ((spacePilotNorthestRowWhenOutsideSafeZone != row)
                        && (spacePilotSouthestRowWhenOutsideSafeZone != row)
                        && (spacePilotLeftistColumnWhenOutsideSafeZone != column)
                        && (spacePilotRightestColumnWhenOutsideSafeZone != column))
                    {
                        if ((-1 != leftConqueringColumn) && (-1 == emptyColumn))
                        {
                            emptyColumn    = column;
                        } 
                    }
                }
            }
        }  

        if ((-1 == centerRow) || (-1 == centerColumn))
        {
            //  Verify first columns, later rows
            for (int column = spacePilotLeftistColumnWhenOutsideSafeZone; column <= spacePilotRightestColumnWhenOutsideSafeZone; column++)
            {
                int leftConqueringRow       = -1;
                int emptyRow                = -1;
                int rightConqueringRow      = -1;

                if ((-1 != centerRow) && (-1 != centerColumn))
                {
                    break;
                }

                for (int row = spacePilotNorthestRowWhenOutsideSafeZone; row <= spacePilotSouthestRowWhenOutsideSafeZone; row++)
                {
                    if ((-1 != centerRow) && (-1 != centerColumn))
                    {
                        break;
                    }

                    Region  currentRegion   = this.content.grid().regions()[row][column];
                    if (RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus())
                    {
                        //  We found a conquering region
                        if (-1 == leftConqueringRow)
                        {
                            leftConqueringRow       = row;
                        }
                        else if ((-1 == rightConqueringRow) && (-1 != emptyRow))  
                        {
                            rightConqueringRow      = row;
                            centerRow               = emptyRow;
                            centerColumn            = column;
                            break;
                        }
                    } 
                    else if (((-1 != leftConqueringRow) && (-1 == emptyRow))
                                && RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus())
                    {
                        if ((spacePilotNorthestRowWhenOutsideSafeZone != row)
                            && (spacePilotSouthestRowWhenOutsideSafeZone != row)
                            && (spacePilotLeftistColumnWhenOutsideSafeZone != column)
                            && (spacePilotRightestColumnWhenOutsideSafeZone != column))
                        {
                            emptyRow    = row;
                        }
                    }
                }
            } 
        }

        if ((-1 == centerRow) || (-1 == centerColumn))
        {
            //  Verify first rows, later columns (more flexible)
            for (int row = spacePilotNorthestRowWhenOutsideSafeZone; row <= spacePilotSouthestRowWhenOutsideSafeZone; row++)
            {
                int leftConqueringColumn    = -1;
                int emptyColumn             = -1;
                int rightConqueringColumn   = -1;

                if ((-1 != centerRow) && (-1 != centerColumn))
                {
                    break;
                }

                for (int column = spacePilotLeftistColumnWhenOutsideSafeZone; column <= spacePilotRightestColumnWhenOutsideSafeZone; column++)
                {
                    if ((-1 != centerRow) && (-1 != centerColumn))
                    {
                        break;
                    }

                    Region  currentRegion   = this.content.grid().regions()[row][column];
                    if ((-1 == leftConqueringColumn)
                        && ((RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT == currentRegion.getRegionStatus())))
                    {
                        //  We found a conquering region
                        leftConqueringColumn    = column;
                    }
                    else if ((-1 == rightConqueringColumn) && (-1 != emptyColumn) 
                                && (RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus()))  
                    {
                        rightConqueringColumn   = column;
                        centerRow               = row;
                        centerColumn            = emptyColumn;
                        break;
                    }
                    else if (((-1 != leftConqueringColumn) && (-1 == emptyColumn))
                                && (RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus()))
                    {
                        if ((spacePilotNorthestRowWhenOutsideSafeZone != row)
                            && (spacePilotSouthestRowWhenOutsideSafeZone != row)
                            && (spacePilotLeftistColumnWhenOutsideSafeZone != column)
                            && (spacePilotRightestColumnWhenOutsideSafeZone != column))
                        {
                            emptyColumn    = column;
                        } 
                    }
                }
            }
        }

        if ((-1 == centerRow) || (-1 == centerColumn))
        {
            //  Verify first columns, later rows (more flexible)
            for (int column = spacePilotLeftistColumnWhenOutsideSafeZone; column <= spacePilotRightestColumnWhenOutsideSafeZone; column++)
            {
                int leftConqueringRow       = -1;
                int emptyRow                = -1;
                int rightConqueringRow      = -1;

                if ((-1 != centerRow) && (-1 != centerColumn))
                {
                    break;
                }

                for (int row = spacePilotNorthestRowWhenOutsideSafeZone; row <= spacePilotSouthestRowWhenOutsideSafeZone; row++)
                {
                    if ((-1 != centerRow) && (-1 != centerColumn))
                    {
                        break;
                    }

                    Region  currentRegion   = this.content.grid().regions()[row][column];
                    if ((-1 == leftConqueringRow)
                        && ((RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT == currentRegion.getRegionStatus())))
                    {
                        //  We found a conquering region
                        leftConqueringRow       = row;
                    }
                    else if ((-1 == rightConqueringRow) && (-1 != emptyRow) 
                            && (RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus()))  
                    {
                        rightConqueringRow      = row;
                        centerRow               = emptyRow;
                        centerColumn            = column;
                        break;
                    }
                    else if (((-1 != leftConqueringRow) && (-1 == emptyRow))
                                && (RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus()))
                    {
                        if ((spacePilotNorthestRowWhenOutsideSafeZone != row)
                            && (spacePilotSouthestRowWhenOutsideSafeZone != row)
                            && (spacePilotLeftistColumnWhenOutsideSafeZone != column)
                            && (spacePilotRightestColumnWhenOutsideSafeZone != column))
                        {
                            emptyRow    = row;
                        }
                    }
                }
            } 
        }

     if ((-1 == centerRow) || (-1 == centerColumn))
        {
            //  Verify first rows, later columns (more flexible)
            for (int row = spacePilotNorthestRowWhenOutsideSafeZone; row <= spacePilotSouthestRowWhenOutsideSafeZone; row++)
            {
                int leftConqueringColumn    = -1;
                int emptyColumn             = -1;
                int rightConqueringColumn   = -1;

                if ((-1 != centerRow) && (-1 != centerColumn))
                {
                    break;
                }

                for (int column = spacePilotLeftistColumnWhenOutsideSafeZone; column <= spacePilotRightestColumnWhenOutsideSafeZone; column++)
                {
                    if ((-1 != centerRow) && (-1 != centerColumn))
                    {
                        break;
                    }

                    Region  currentRegion   = this.content.grid().regions()[row][column];
                    if ((-1 == leftConqueringColumn)
                        && (RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus()))
                    {
                        //  We found a conquering region
                        leftConqueringColumn    = column;
                    }
                    else if ((-1 == rightConqueringColumn) && (-1 != emptyColumn) 
                        && ((RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT == currentRegion.getRegionStatus())))  
                    {
                        rightConqueringColumn   = column;
                        centerRow               = row;
                        centerColumn            = emptyColumn;
                        break;
                    }
                    else if (((-1 != leftConqueringColumn) && (-1 == emptyColumn))
                                && (RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus()))
                    {
                        if ((spacePilotNorthestRowWhenOutsideSafeZone != row)
                            && (spacePilotSouthestRowWhenOutsideSafeZone != row)
                            && (spacePilotLeftistColumnWhenOutsideSafeZone != column)
                            && (spacePilotRightestColumnWhenOutsideSafeZone != column))
                        {
                            emptyColumn    = column;
                        }
                    }
                }
            }  
        }

        if ((-1 == centerRow) || (-1 == centerColumn))
        {
            //  Verify first columns, later rows (more flexible)
            for (int column = spacePilotLeftistColumnWhenOutsideSafeZone; column <= spacePilotRightestColumnWhenOutsideSafeZone; column++)
            {
                int leftConqueringRow       = -1;
                int emptyRow                = -1;
                int rightConqueringRow      = -1;

                if ((-1 != centerRow) && (-1 != centerColumn))
                {
                    break;
                }

                for (int row = spacePilotNorthestRowWhenOutsideSafeZone; row <= spacePilotSouthestRowWhenOutsideSafeZone; row++)
                {
                    if ((-1 != centerRow) && (-1 != centerColumn))
                    {
                        break;
                    }

                    Region  currentRegion   = this.content.grid().regions()[row][column];
                    if ((-1 == leftConqueringRow)
                        && (RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus()))
                    {
                        //  We found a conquering region
                        leftConqueringRow       = row;
                    }
                    else if ((-1 == rightConqueringRow) && (-1 != emptyRow) 
                        && ((RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT == currentRegion.getRegionStatus())))  
                    {
                        rightConqueringRow      = row;
                        centerRow               = emptyRow;
                        centerColumn            = column;
                        break;
                    }
                    else if (((-1 != leftConqueringRow) && (-1 == emptyRow))
                                && (RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus()))
                    {
                        if ((spacePilotNorthestRowWhenOutsideSafeZone != row)
                            && (spacePilotSouthestRowWhenOutsideSafeZone != row)
                            && (spacePilotLeftistColumnWhenOutsideSafeZone != column)
                            && (spacePilotRightestColumnWhenOutsideSafeZone != column))
                        {
                            emptyRow    = row;
                        }
                    }
                }
            } 
        }

        if ((-1 == centerRow) || (-1 == centerColumn))
        {
            //  Verify first rows, later columns (much more flexible)
            for (int row = spacePilotNorthestRowWhenOutsideSafeZone; row <= spacePilotSouthestRowWhenOutsideSafeZone; row++)
            {
                int leftConqueringColumn    = -1;
                int emptyColumn             = -1;
                int rightConqueringColumn   = -1;

                if ((-1 != centerRow) && (-1 != centerColumn))
                {
                    break;
                }

                for (int column = spacePilotLeftistColumnWhenOutsideSafeZone; column <= spacePilotRightestColumnWhenOutsideSafeZone; column++)
                {
                    if ((-1 != centerRow) && (-1 != centerColumn))
                    {
                        break;
                    }

                    Region  currentRegion   = this.content.grid().regions()[row][column];
                    if ((-1 == leftConqueringColumn) && ((RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT == currentRegion.getRegionStatus())))
                    {
                        //  We found a conquering region
                        leftConqueringColumn    = column;
                        }
                    else if (((-1 == rightConqueringColumn) && (-1 != emptyColumn))  
                                && RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus())
                    {
                        rightConqueringColumn   = column;
                        centerRow               = row;
                        centerColumn            = emptyColumn;
                        break;
                    }
                    else if (((-1 != leftConqueringColumn) && (-1 == emptyColumn))
                                && (RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus()))
                    {
                        if ((spacePilotNorthestRowWhenOutsideSafeZone != row)
                            && (spacePilotSouthestRowWhenOutsideSafeZone != row)
                            && (spacePilotLeftistColumnWhenOutsideSafeZone != column)
                            && (spacePilotRightestColumnWhenOutsideSafeZone != column))
                        {
                            emptyColumn    = column;
                        }
                    }
                }
            }  
        }

        if ((-1 == centerRow) || (-1 == centerColumn))
        {
            //  Verify first columns, later rows (much more flexible)
            for (int column = spacePilotLeftistColumnWhenOutsideSafeZone; column <= spacePilotRightestColumnWhenOutsideSafeZone; column++)
            {
                int leftConqueringRow       = -1;
                int emptyRow                = -1;
                int rightConqueringRow      = -1;

                if ((-1 != centerRow) && (-1 != centerColumn))
                {
                    break;
                }

                for (int row = spacePilotNorthestRowWhenOutsideSafeZone; row <= spacePilotSouthestRowWhenOutsideSafeZone; row++)
                {
                    if ((-1 != centerRow) && (-1 != centerColumn))
                    {
                        break;
                    }

                    Region  currentRegion   = this.content.grid().regions()[row][column];
                    if ((-1 == leftConqueringRow) 
                        && ((RegionStatus.REGION_STATUS_SPACE_PILOT_CONQUERING == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_CONQUERED_BY_SPACE_PILOT == currentRegion.getRegionStatus())
                        || (RegionStatus.REGION_STATUS_BORDER_ONLY_FOR_SPACE_PILOT == currentRegion.getRegionStatus())))
                    {
                        //  We found a conquering region
                        leftConqueringRow       = row;
                    }
                    else if (((-1 == rightConqueringRow) && (-1 != emptyRow)) 
                                && RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus())
                    {
                        rightConqueringRow      = row;
                        centerRow               = emptyRow;
                        centerColumn            = column;
                        break;
                    }
                    else if (((-1 != leftConqueringRow) && (-1 == emptyRow))
                                && (RegionStatus.REGION_STATUS_EMPTY == currentRegion.getRegionStatus()))
                    {
                        if ((spacePilotNorthestRowWhenOutsideSafeZone != row)
                            && (spacePilotSouthestRowWhenOutsideSafeZone != row)
                            && (spacePilotLeftistColumnWhenOutsideSafeZone != column)
                            && (spacePilotRightestColumnWhenOutsideSafeZone != column))
                        {
                            emptyRow    = row;
                        }
                    }
                }
            } 
        }

        if ((-1 == centerRow) || (-1 == centerColumn))
        {
            if (spacePilotRightestColumnWhenOutsideSafeZone == Grid.getTotalGameCellsInXPerRow()-2)
            {
                //  We reached the rightest column
                int row     = AVERAGE_ROW < (int)((Grid.getTotalGameCellsInYPerColumn()) / 2.0) 
                    ? AVERAGE_ROW-1 : AVERAGE_ROW+1;
                int column  = AVERAGE_COLUMN;
                if (RegionStatus.REGION_STATUS_EMPTY == this.content.grid().regions()[row][column].getRegionStatus())
                {
                    centerRow       = row;
                    centerColumn    = column;
                }
                else
                {
                    centerRow       = AVERAGE_ROW;
                    centerColumn    = AVERAGE_COLUMN;
                    System.out.println("getBoardPointInsideThePotentialConqueredZone() - We should have not reach this point");
                }
            } 
            else if (spacePilotLeftistColumnWhenOutsideSafeZone == 1)
            {
                //  We reached the leftist column
                int row     = AVERAGE_ROW < (int)((Grid.getTotalGameCellsInYPerColumn()) / 2.0) 
                    ? AVERAGE_ROW-1 : AVERAGE_ROW+1;
                int column  = AVERAGE_COLUMN;
                if (RegionStatus.REGION_STATUS_EMPTY == this.content.grid().regions()[row][column].getRegionStatus())
                {   
                    centerRow       = row;
                    centerColumn    = column;
                }
                else
                {
                    centerRow       = AVERAGE_ROW;
                    centerColumn    = AVERAGE_COLUMN;
                    System.out.println("getBoardPointInsideThePotentialConqueredZone() - We should have not reach this point");
                }
            }
            else if (spacePilotSouthestRowWhenOutsideSafeZone == Grid.getTotalGameCellsInYPerColumn()-2)
            {
                //  We reached the southest row
                int row     = AVERAGE_ROW;
                int column  = AVERAGE_COLUMN < (int)((Grid.getTotalGameCellsInXPerRow()) / 2.0) 
                    ? AVERAGE_COLUMN-1 : AVERAGE_COLUMN+1;
                if (RegionStatus.REGION_STATUS_EMPTY == this.content.grid().regions()[row][column].getRegionStatus())
                {
                    centerRow       = row;
                    centerColumn    = column;
                }
                else
                {
                    centerRow       = AVERAGE_ROW;
                    centerColumn    = AVERAGE_COLUMN;
                    System.out.println("getBoardPointInsideThePotentialConqueredZone() - We should have not reach this point");
                }
            }
            else if (spacePilotNorthestRowWhenOutsideSafeZone == 1)
            {
                //  We reached the northest row
                int row     = AVERAGE_ROW;
                int column  = AVERAGE_COLUMN < (int)((Grid.getTotalGameCellsInXPerRow()) / 2.0) 
                    ? AVERAGE_COLUMN-1 : AVERAGE_COLUMN+1;
                if (RegionStatus.REGION_STATUS_EMPTY == this.content.grid().regions()[row][column].getRegionStatus())
                {
                    centerRow       = row;
                    centerColumn    = column;
                }
                else
                {
                    centerRow       = AVERAGE_ROW;
                    centerColumn    = AVERAGE_COLUMN;
                    System.out.println("getBoardPointInsideThePotentialConqueredZone() - We should have not reach this point");
                }
            }
            else if (spacePilotNorthestRowWhenOutsideSafeZone == spacePilotSouthestRowWhenOutsideSafeZone)
            {
                //  We reached a horizontal line
                int row         = AVERAGE_ROW < (int)((Grid.getTotalGameCellsInYPerColumn()) / 2.0) 
                    ? AVERAGE_ROW-1 : AVERAGE_ROW+1;
                int column  = AVERAGE_COLUMN;

                if (RegionStatus.REGION_STATUS_EMPTY == this.content.grid().regions()[row][column].getRegionStatus())
                {
                    centerRow       = row;
                    centerColumn    = column;
                }
                else
                {
                    centerRow       = AVERAGE_ROW;
                    centerColumn    = AVERAGE_COLUMN;
                    System.out.println("getBoardPointInsideThePotentialConqueredZone() - We should have not reach this point");
                }
            }
            else if (spacePilotLeftistColumnWhenOutsideSafeZone == spacePilotRightestColumnWhenOutsideSafeZone)
            {
                //  We reached a vertical line
                int row         = AVERAGE_ROW;
                int column      = AVERAGE_COLUMN < (int)((Grid.getTotalGameCellsInXPerRow()) / 2.0) 
                    ? AVERAGE_COLUMN-1 : AVERAGE_COLUMN+1;
                if (RegionStatus.REGION_STATUS_EMPTY == this.content.grid().regions()[row][column].getRegionStatus())
                {
                    centerRow       = row;
                    centerColumn    = column;
                }
                else
                {
                    centerRow       = AVERAGE_ROW;
                    centerColumn    = AVERAGE_COLUMN;
                    System.out.println("getBoardPointInsideThePotentialConqueredZone() - We should have not reach this point");
                }
            }
            else
            {
                //  We tried everything....
                centerRow       = AVERAGE_ROW;
                centerColumn    = AVERAGE_COLUMN;
            }
        }

        boardPointInsideThePotentialConqueredZone[0].setRow(centerRow);
        boardPointInsideThePotentialConqueredZone[0].setColumn(centerColumn);
        System.out.println("Point inside: " + centerRow + ", " + centerColumn);

        return 1;
    } 
}