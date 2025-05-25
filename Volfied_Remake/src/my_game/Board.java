package my_game;

import java.awt.Color;

import base.GameCanvas;
import my_base.MyContent;
import my_game.Grid.Direction;
import shapes.Circle;
import shapes.Image;
import shapes.Rectangle;
import shapes.Text;

/**
 * The Board class is responsible for the display of the elements.
 * It has the role of the View in the Model-View-Controller paradigm we learned 
 * in the system architectures lesson.
 * It is designed so that all of the game elements leave in the simple board grids
 * and all the conversions to canvas pixels are done only here.
 */
public class Board {
	
	/**
	 * The maze does not take all the canvas, so the board defines the three configuration params:
	 * X & Y Offsets - the top left point of the maze in the canvas
	 * Scale - the multiplication factor that to convert a grid cell into pixels.
	 */
	private final int       BOARD_X_OFFSET  = 80;
	private final int       BOARD_Y_OFFSET  = 120;
	public static final int BOARD_SCALE     = 36;
	private GameCanvas canvas;
	private MyContent content;
	
	public void setCanvas(GameCanvas canvas) {
		this.canvas = canvas;
	}

	public void setContent(MyContent content) {
		this.content = content;
	}

	public void initBoard() {
		canvas.deleteAllShapes();
		Grid grid	= content.grid();
		canvas.setBackground(Color.BLACK);
//		canvas.addShape(new Image("title", "resources/PacmanTitle.png", 372, 123, 300, -10));
		addScore(content.score());
		grid.addGridToBoard();		
        addSpacePilot(content.spacePilot());
		addSmallEnemies();
		addStatusLine();
//		content.historyIndication().addToCanvas();
	}

	/**
	 * Every maze line is drawn as a very thin rectangle
	 */
	public void addLine(GridLine line, int lineIndex) {
		int minX = Math.min(line.p1.x, line.p2.x);
		int maxX = Math.max(line.p1.x, line.p2.x);
		int minY = Math.min(line.p1.y, line.p2.y);
		int maxY = Math.max(line.p1.y, line.p2.y);

		// Represent each line as a thin 4-pixel wide rectangle
		Rectangle rect = new Rectangle("ml"+lineIndex, transX(minX) - 2, transY(minY) - 2, BOARD_SCALE*(maxX-minX) + 4, BOARD_SCALE*(maxY-minY)+4);
		rect.setColor(Color.BLUE);
		rect.setWeight(2);
		canvas.addShape(rect);
	}
	
		public void addRegion(Region rg) {
		Circle circle = new Circle(rg.getGuid(), transX(rg.getLocation().x), transY(rg.getLocation().y), 3);
		circle.setColor(Color.WHITE);
		circle.setFillColor(Color.WHITE);
		circle.setIsFilled(true);
		canvas.addShape(circle);
	}
	
	private void addSpacePilot(SpacePilot spacePilot) {
        Image image = new Image(spacePilot.getImageID(), "resources/" + spacePilot.getImageName()+".jpg", 
            spacePilot.getImageWidth(),spacePilot.getImageHeight(), 
            transX(spacePilot.getLocation().x)-18, transY(spacePilot.getLocation().y)-18);
//		image.setShapeListener(this);
		image.setzOrder(3);

//        spacePilot.setLocation(467, 620);
//		Image image = new Image("pacman", "resources/pacman_right.png", 48,48, transX(pacman.getLocation().x)-24, transY(pacman.getLocation().y)-24);
		canvas.addShape(image);
//		Text t2 = new Text("policy", pacman.getDirectionPolicy().toString() , BOARD_X_OFFSET,70);
//		t2.setColor(Color.YELLOW);
//		t2.setFontSize(40);
//		canvas.addShape(t2);
	}
	
	private void addSmallEnemies() {
		Image image;
		for (SmallEnemy s: content.smallEnemies().getSmallEnemies()) {
			image = new Image(s.name(), "resources/" + s.name() + ".png", 36,36, transX(s.getLocation().x)-18, transY(s.getLocation().y)-18);
			canvas.addShape(image);
		}
    }

	private void addScore(Score score) {
		Text t2 = new Text(score.guid(), score.getText() , 70,70);
		t2.setColor(Color.WHITE);
		t2.setFontSize(40);
		canvas.addShape(t2);
	}

	private void addStatusLine() {
		StatusLine status = content.statusLine();
		Text t2 = new Text(status.guid(), status.getText() , BOARD_X_OFFSET + 400, 740);
		t2.setColor(status.getColor());
		t2.setFontSize(30);
		canvas.addShape(t2);
	}

	public void updateSpacePilot() {
		SpacePilot spacePilot   = content.spacePilot();

//		if (spacePilot.changedDirection()) {
			switch (spacePilot.getCurrentDirection()) {
                case RIGHT: {
    				canvas.changeImage(spacePilot.getImageID(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                        spacePilot.getImageWidth(), spacePilot.getImageHeight());
                    break;
                }

                case LEFT: {
    				canvas.changeImage(spacePilot.getImageID(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                        spacePilot.getImageWidth(), spacePilot.getImageHeight());
                    break;
                }

                case UP: {
    				canvas.changeImage(spacePilot.getImageID(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                        spacePilot.getImageWidth(), spacePilot.getImageHeight());
                    break;
                }

                case DOWN: {
    				canvas.changeImage(spacePilot.getImageID(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                        spacePilot.getImageWidth(), spacePilot.getImageHeight());
                    break;
                }

                case STOPPED:
                default: {
    				canvas.changeImage(spacePilot.getImageID(), "resources/" + spacePilot.getImageName()  + ".jpg", 
                        spacePilot.getImageWidth(), spacePilot.getImageHeight());
                    break;
                }
			}
//		}
		
		canvas.moveShapeToLocation(spacePilot.getImageID(), transX(spacePilot.getLocation().x)-24, transY(spacePilot.getLocation().y)-24);
        // Reset the direction to stopped, so that the next move will be according to the policy
        spacePilot.setCurrentDirection(Direction.STOPPED);
//		Text t1 = (Text) canvas.getShape("policy");
//		t1.setText(pacman.getDirectionPolicy().toString());
	}
	
	public void updateSmallEnemies() {
		for (SmallEnemy s: content.smallEnemies().getSmallEnemies()) {
			canvas.moveShapeToLocation(s.name(), transX(s.getLocation().x)-24, transY(s.getLocation().y)-24);
		}
		
	}

	public void updateRegion(Region rg) {
		canvas.deleteShape(rg.getGuid());
	}
	
	public void updateScore() {
		Text t1 = (Text) canvas.getShape(content.score().guid());
		t1.setText(content.score().getText());
	}

	public void updateStatusLine() {
		Text t1 = (Text) canvas.getShape(content.statusLine().guid());
		t1.setText(content.statusLine().getText());
		t1.setColor(content.statusLine().getColor());
	}

	//transform an X coordinate from the maze coordinates to the canvas coordinates
	private int transX(int x) {
		return BOARD_X_OFFSET + x*BOARD_SCALE;
	}
	//transform a Y coordinate from the maze coordinates to the canvas coordinates
	private int transY(int y) {
		return BOARD_Y_OFFSET + y*BOARD_SCALE;
	}
}
