/////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Final Project
// Files:            Board.java
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

import base.Game;
import base.GameCanvas;
import my_base.MyContent;
import my_game.Grid.Direction;
import my_game.Region.RegionStatus;
import my_ui_elements.GetNameButton;
import shapes.Image;
import shapes.Line;
import shapes.Rectangle;
import shapes.Text;

/**
 * The Board class is responsible for the display of the elements.
 * It has the role of the View in the Model-View-Controller paradigm we learned 
 * in the system architectures lesson.
 * It is designed so that all of the game elements leave in the simple board grids
 * and all the conversions to canvas pixels are done only here.
 */
public class Board 
{
	
	/**
	 * The grid does not take all the canvas, so the board defines the three configuration params:
	 * X & Y Offsets - the top left point of the grid in the canvas
	 * Scale - the multiplication factor that to convert a grid cell into pixels.
	 */
//  Private constants for the class
	private final       int         BOARD_X_OFFSET                                  = 20;
	private final       int         BOARD_Y_OFFSET                                  = 120;
	private static final int        BOARD_SCALE                                     = 18;
    private final       int         QUANTITY_OF_DIFFERENT_SMALL_ENEMIES_GRAPHICS    = 3;

    private final       int         GRID_LINE_Z_ORDER                               = 11;   // Highest (on top of everything)
    private final       int         GRID_BACKGROUND_Z_ORDER                         = 5;    // In the middle

//  Private variables for the class
	private             GameCanvas  canvas;
	private             MyContent   content;
    private             int         gridBorderLine                                  = 0;
    private             int         gridSpacePilotLine                              = 0;
	
	public void setCanvas(GameCanvas canvas) 
    {
		this.canvas = canvas;
	}

	public GameCanvas getCanvas() 
    {
		return this.canvas;
	}

	public void setContent(MyContent content) 
    {
		this.content = content;
	}

	public void initBoard() 
    {
		canvas.deleteAllShapes();
		Grid grid	= content.grid();
		canvas.setBackground(Color.BLACK);

		// Represent each line as a thin 4-pixel wide rectangle
		Rectangle   background   = new Rectangle("background", 
            transX(0) + BOARD_SCALE/2, 
            transY(0) + + BOARD_SCALE/2, 
            ((Grid.getTotalGameCellsInXPerRow()-1) * BOARD_SCALE), ((Grid.getTotalGameCellsInYPerColumn()-1) * BOARD_SCALE));

		background.setColor(Color.GRAY);
        background.setFillColor(Color.GRAY);
        background.setIsFilled(true);
		background.setWeight(2);
        background.setzOrder(GRID_BACKGROUND_Z_ORDER);
		canvas.addShape(background);

        //		canvas.addShape(new Image("title", "resources/PacmanTitle.png", 372, 123, 300, -10));
		addMessages(content.messages());
		grid.addGridToBoard();	

        //  Add the space pilot and small enemies to the canvas
        addSpacePilotToCanvas(content.spacePilot());
		addSmallEnemiesToCanvas();

        addTipLine();
		addStatusLine();

//		content.historyIndication().addToCanvas();
	}

	/**
	 * Every grid line is drawn as a very thin rectangle
	 */
	public void addGridBorderLine(GridLine line) 
    {
		final int MIN_X = Math.min(line.p1().getColumn(), line.p2().getColumn());
		final int MAX_X = Math.max(line.p1().getColumn(), line.p2().getColumn());
		final int MIN_Y = Math.min(line.p1().getRow(), line.p2().getRow());
		final int MAX_Y = Math.max(line.p1().getRow(), line.p2().getRow());

		// Represent each line as a thin 2-pixel wide
        Line    lineShape   = null;

        if (MIN_X == MAX_X)   
        {
            //  Vertical line
            if (0 == MIN_X)
            {
                //  Vertical left line
                lineShape   = new Line("bl" + gridBorderLine++, transX(line.p1().getColumn()) + BOARD_SCALE/2, 
                transY(line.p1().getRow()) + BOARD_SCALE/2, 
                transX(line.p2().getColumn()) + BOARD_SCALE/2, 
                transY(line.p2().getRow()-1) + BOARD_SCALE/2);
            }
            else
            {
                //  Vertical right line
                lineShape   = new Line("bl" + gridBorderLine++, transX(line.p1().getColumn()-1) + BOARD_SCALE/2, 
                transY(line.p1().getRow()) + BOARD_SCALE/2, 
                transX(line.p2().getColumn()-1) + BOARD_SCALE/2, 
                transY(line.p2().getRow()-1) + BOARD_SCALE/2);
            }
        }  
        else if (MIN_Y == MAX_Y) 
        {
            //  Horizontal line
            if (0 == MIN_Y)
            {
                //  Horizonal upper line
                lineShape   = new Line("bl" + gridBorderLine++, transX(line.p1().getColumn()) + BOARD_SCALE/2, 
                transY(line.p1().getRow()) + BOARD_SCALE/2, 
                transX(line.p2().getColumn()-1) + BOARD_SCALE/2, 
                transY(line.p2().getRow()) + BOARD_SCALE/2);  
            }
            else
            {
                //  Horizonal bottom line
                lineShape   = new Line("bl" + gridBorderLine++, transX(line.p1().getColumn()) + BOARD_SCALE/2, 
                transY(line.p1().getRow()-1) + BOARD_SCALE/2, 
                transX(line.p2().getColumn()-1) + BOARD_SCALE/2, 
                transY(line.p2().getRow()-1) + BOARD_SCALE/2);  
            }
        }

        lineShape.setColor(Color.GREEN);
        lineShape.setWeight(2); 
        lineShape.setzOrder(GRID_LINE_Z_ORDER);    

        //  Add the line to the canvas
        canvas.addShape(lineShape); 
	}

	public void addGridSpacePilotLine(GridLine line) 
    {
		final int MIN_X = Math.min(line.p1().getColumn(), line.p2().getColumn());
		final int MAX_X = Math.max(line.p1().getColumn(), line.p2().getColumn());
		final int MIN_Y = Math.min(line.p1().getRow(), line.p2().getRow());
		final int MAX_Y = Math.max(line.p1().getRow(), line.p2().getRow());

	    // Represent each line as a thin 2-pixel wide
        Line    lineShape   = null;

        if (MIN_X == MAX_X)   
        {
            //  Vertical line
            lineShape   = new Line("ml" + gridSpacePilotLine++, transX(line.p1().getColumn()) + BOARD_SCALE/2, 
            transY(line.p1().getRow()) + BOARD_SCALE/2, 
            transX(line.p2().getColumn()) + BOARD_SCALE/2, 
            transY(line.p2().getRow()) + BOARD_SCALE/2);
        }  
        else if (MIN_Y == MAX_Y) 
        {
            //  Horizontal line
            lineShape   = new Line("ml" + gridSpacePilotLine++, transX(line.p1().getColumn()) + BOARD_SCALE/2, 
            transY(line.p1().getRow()) + BOARD_SCALE/2, 
            transX(line.p2().getColumn()) + BOARD_SCALE/2, 
            transY(line.p2().getRow()) + BOARD_SCALE/2);
        }

        lineShape.setColor(Color.GREEN);
        lineShape.setWeight(2); 
        lineShape.setzOrder(GRID_LINE_Z_ORDER); 

        //  Add the line to the canvas
        canvas.addShape(lineShape);
	}

	public void hideUnusedGridLines() 
    {
        for (int gridSpacePilotSpecificLine = 0;  gridSpacePilotSpecificLine < gridSpacePilotLine; gridSpacePilotSpecificLine++)
        {
            //  Check all the green grid space pilot lines
            Line line = (Line) canvas.getShape("ml" + gridSpacePilotSpecificLine);
            boolean     isLineToBeHidden    = false;
            final int   STARTING_ROW        = reverseTransY((line.getY1() > line.getY2()) 
                                                ? (line.getY2() - BOARD_SCALE/2) 
                                                : (line.getY1() - BOARD_SCALE/2));
            final int   ENDING_ROW          = reverseTransY((line.getY1() <= line.getY2()) 
                                                ? (line.getY2() - BOARD_SCALE/2) 
                                                : (line.getY1() - BOARD_SCALE/2));
            final int   STARTING_COLUMN     = reverseTransX((line.getX1() > line.getX2()) 
                                                ? (line.getX2() - BOARD_SCALE/2) 
                                                : (line.getX1() - BOARD_SCALE/2));
            final int   ENDING_COLUMN       = reverseTransX((line.getX1() <= line.getX2()) 
                                            ? (line.getX2() - BOARD_SCALE/2) 
                                            : (line.getX1() - BOARD_SCALE/2));

            for (int row = STARTING_ROW; row <= ENDING_ROW; row++)
            {
                for (int column = STARTING_COLUMN; column <= ENDING_COLUMN; column++)
                {
                    if (RegionStatus.REGION_STATUS_CONQUERED_BY_SPACE_PILOT == content.grid().regions()[row][column].getRegionStatus())
                    {
                        //  The line has a point at least conquered by space pilot
                        isLineToBeHidden    = true;
                    }
                }
            }

            //  For now until it will be solved more elengatly
            isLineToBeHidden    = true;

            if (true == isLineToBeHidden)
            {
                canvas.hideShape("ml" + gridSpacePilotSpecificLine);
            }
        }
    }
	
	public void addRegion(Region rg) 
    {
//		Circle circle   = new Circle(rg.getGuid(), transX(rg.getLocation().x), transY(rg.getLocation().y), 3);
//		circle.setColor(Color.WHITE);
//		circle.setFillColor(Color.WHITE);
//		circle.setIsFilled(true);
//		canvas.addShape(circle);

        Rectangle rectangle = new Rectangle(rg.getGuid(), 
            transX(rg.getLocation().getColumn()), 
            transY(rg.getLocation().getRow()), 
            16, 
            16);
        rectangle.setColor(Color.WHITE);
        rectangle.setFillColor(Color.WHITE);
        rectangle.setIsFilled(true);
        rectangle.setzOrder(3);
//		canvas.addShape(rectangle);
	}
	
	private void addMessages(Messages messages) 
    {
		Text t1     = new Text("ZoneCapture", "Zone Capture" , 250,70);
		t1.setColor(Color.ORANGE);
		t1.setFontSize(60);
		canvas.addShape(t1);

		Text t2     = new Text(messages.guidScore(), messages.getText() , 30,120);
		t2.setColor(Color.WHITE);
		t2.setFontSize(40);
		canvas.addShape(t2);

        Text t3     = new Text(messages.guidPercentage(), messages.getPercentage(), 400, 120); 
		t3.setColor(Color.GREEN);
		t3.setFontSize(40);
		canvas.addShape(t3);

        Text t4     = new Text(messages.guidTime(), messages.getTime(), 700, 120);
		t4.setColor(Color.WHITE);
		t4.setFontSize(30);
		canvas.addShape(t4);

        Text t5     = new Text(messages.guidLives(), messages.getLives(), 250, 120);
		t5.setColor(Color.WHITE);
		t5.setFontSize(30);
		canvas.addShape(t5);
	}

	private void addStatusLine() 
    {
		StatusLine  status  = content.statusLine();
		Text        t2      = new Text(status.guid(), status.getText() , BOARD_X_OFFSET + 400, 720);
		t2.setColor(status.getColor());
		t2.setFontSize(30);
		canvas.addShape(t2);
	}

	private void addTipLine() 
    {
		StatusLine  status  = content.tipLine();
		Text        t8      = new Text(status.tip(), status.getText() , BOARD_X_OFFSET + 320, 770);
		t8.setColor(status.getColor());
		t8.setFontSize(15);
		canvas.addShape(t8);
	}

	public void updateRegion(Region rg) 
    {
        //  Remove the old region rectangle
		canvas.deleteShape(rg.getGuid());

        //  Add a new rectangle for the region
        Rectangle rectangle = new Rectangle(rg.getGuid(), 
            transX(rg.getLocation().getColumn()), 
            transY(rg.getLocation().getRow()), 
            18, 
            18);
        rectangle.setColor(Color.BLACK);
        rectangle.setFillColor(Color.BLACK);
        rectangle.setIsFilled(true);
        rectangle.setzOrder(10);
        canvas.addShape(rectangle);
	}

    public void updateSpacePilotGridLine(Region rg) 
    {
	    Grid grid	= content.grid();
        grid.addGridSpacePilotLines(rg.getLocation(), content.spacePilot().getLocation());
    }

	public void updateMessages() 
    {
		Text        t1              = (Text) canvas.getShape(content.messages().guidScore());
		t1.setText(content.messages().getText());
		Text        t2              = (Text) canvas.getShape(content.messages().guidPercentage());
		t2.setText(content.messages().getPercentage());
	    Text        t3              = (Text) canvas.getShape(content.messages().guidTime());
		t3.setText(content.messages().getTime());
        final int FROZEN_ENEMIES    = content.getQuantityOfEnemies() - content.smallEnemies().getQuantityOfSmallEnemiesRunning();
	    Text        t4              = (Text) canvas.getShape("QuantityOfEnemies");
		t4.setText("Enemies:" + content.getQuantityOfEnemies() + ", " + FROZEN_ENEMIES + " frozen");
	    Text        t5              = (Text) canvas.getShape(content.messages().guidLives());
		t5.setText(content.messages().getLives());

        String  playerName      = GetNameButton.getPlayerName();
        if (null != playerName && !playerName.isEmpty()) 
        {
            Text textPlayerName   = (Text) (Game.UI().canvas().getShape("PlayerName"));
            textPlayerName.setText("Player: " + playerName);
        }
	}

	public void updateStatusLine() 
    {
		Text    t1  = (Text) canvas.getShape(content.statusLine().guid());
		t1.setText(content.statusLine().getText());
		t1.setColor(content.statusLine().getColor());
	}

	public void updateTipLine() 
    {
		Text    t8  = (Text) canvas.getShape(content.tipLine().tip());
		t8.setText(content.tipLine().getText());
		t8.setColor(content.tipLine().getColor());
	}

	//  transform an X coordinate from the grid coordinates to the canvas coordinates
	private int transX(int x) 
    {
		return BOARD_X_OFFSET + (x * BOARD_SCALE);
	}

	//  transform a Y coordinate from the grid coordinates to the canvas coordinates
	private int transY(int y) 
    {
		return BOARD_Y_OFFSET + (y * BOARD_SCALE);
	}

	//  transform an X coordinate from the canvas coordinates to the grid coordinates 
	private int reverseTransX(int x) 
    {
		return (int) ((x - BOARD_X_OFFSET) / BOARD_SCALE);
	}

	//  transform a Y coordinate ffrom the canvas coordinates to the grid coordinates 
	private int reverseTransY(int y) 
    {
		return (int) ((y - BOARD_Y_OFFSET) / BOARD_SCALE);
	}

  /**
    * addSpacePilotToCanvas method
    * 
    * @implNote addSpacePilotToCanvas method to add the space pilot into the canvas
    *
    * @param (SpacePilot spacePilot)
    * @return (none)
    */
    private void addSpacePilotToCanvas(SpacePilot spacePilot) 
    {
        //  Create the graphic image for the space pilot
        Image   image   = new Image(spacePilot.name(), 
            "resources/" + spacePilot.getImageName() + ".jpg", 
            spacePilot.getImageWidth(),spacePilot.getImageHeight(), 
            transX(spacePilot.getLocation().getColumn()), 
            transY(spacePilot.getLocation().getRow()));
        //  Set the image into the lower Z order (1) - z order - more positive is over
		image.setzOrder(1);
        //  Add the space pilot graphic into the canvas
		canvas.addShape(image);
	}
	
  /**
    * updateSpacePilotInCanvas method
    * 
    * @implNote updateSpacePilotInCanvas method to update the space pilot position (in canvas)
    *
    * @param (none)
    * @return (none)
    */
	public void updateSpacePilotInCanvas() 
    {
		SpacePilot spacePilot   = content.spacePilot();

        switch (spacePilot.getCurrentDirection()) 
        {
            case WEST: 
            {
                canvas.changeImage(spacePilot.name(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                    spacePilot.getImageWidth(), spacePilot.getImageHeight());
                break;
            }

            case EAST: 
            {
                canvas.changeImage(spacePilot.name(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                    spacePilot.getImageWidth(), spacePilot.getImageHeight());
                break;
            }

            case NORTH: 
            {
                canvas.changeImage(spacePilot.name(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                    spacePilot.getImageWidth(), spacePilot.getImageHeight());
                break;
            }

            case SOUTH: 
            {
                canvas.changeImage(spacePilot.name(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                    spacePilot.getImageWidth(), spacePilot.getImageHeight());
                break;
            }

            case STOPPED:
            default: 
            {
                canvas.changeImage(spacePilot.name(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                    spacePilot.getImageWidth(), spacePilot.getImageHeight());
                break;
            }
        }
    
        //  Move the space pilot shape (graphic image) to the new location
		canvas.moveShapeToLocation(spacePilot.name(), 
                transX(spacePilot.getLocation().getColumn()),
                transY(spacePilot.getLocation().getRow()));

        //  Reset the direction to stopped, so that the next move will be according to the policy
        spacePilot.setCurrentDirection(Direction.STOPPED);
	}

  /**
    * addSmallEnemiesToCanvas method
    * 
    * @implNote addSmallEnemiesToCanvas method to add all the small enemies'
    *               into the canvas
    *
    * @param (none)
    * @return (none)
    */
	private void addSmallEnemiesToCanvas() 
    {
		Image       image                           = null;
        int         smallEnemyCounter               = 0;
        String[]    smallEnemyGraphicsNameArray     = new String[QUANTITY_OF_DIFFERENT_SMALL_ENEMIES_GRAPHICS];

        //  'Navigate' into the array of small enemies
		for (SmallEnemy s : content.smallEnemies().getSmallEnemies()) 
        {
            String      smallEnemyGraphicsName          = s.name();
            if (smallEnemyCounter < QUANTITY_OF_DIFFERENT_SMALL_ENEMIES_GRAPHICS)
            {
                //  If there is a graphic for the specific small enemy
                smallEnemyGraphicsNameArray[smallEnemyCounter] = smallEnemyGraphicsName;
            }
            else
            {
                //  If there is NO a graphic for the specific small enemy,
                //  then 'reuse' one of the previous graphics
                smallEnemyGraphicsName  = smallEnemyGraphicsNameArray[smallEnemyCounter % QUANTITY_OF_DIFFERENT_SMALL_ENEMIES_GRAPHICS];
            }
  
            //  Create a specific graphic image for this small enemy
			image   = new Image(s.name(), "resources/" + smallEnemyGraphicsName + ".png", 
                s.getImageWidth(), 
                s.getImageHeight(), 
                transX(s.getLocation().getColumn()), 
                transY(s.getLocation().getRow()));
            //  Set the image into the lowest Z order (0)
            image.setzOrder(0);
            //  Add the graphics of the specific small enemy into the canvas
            canvas.addShape(image);
            smallEnemyCounter++;
		}
    }

  /**
    * updateSmallEnemiesInCanvas method
    * 
    * @implNote updateSmallEnemiesInCanvas method to update all the small enemies'
    *               positions (in canvas)
    *
    * @param (none)
    * @return (none)
    */
	public void updateSmallEnemiesInCanvas() 
    {
		for (SmallEnemy s : content.smallEnemies().getSmallEnemies()) 
        {
            //  Move the small enemy shape (graphic image) to the new location
   			canvas.moveShapeToLocation(s.name(), 
                transX(s.getLocation().getColumn()), 
                transY(s.getLocation().getRow()));
		}
	}
}