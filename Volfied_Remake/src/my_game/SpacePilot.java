package my_game;
import ui_elements.ScreenPoint;
import base.Intersectable;
import my_game.Grid.Direction;

/**
 * SpacePilot class
 * 
 * @implNote This class implements a new SpacePilot that implements the Intersectable interfaces 
 *              to detect the 'collision' with another objets.
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class SpacePilot implements Intersectable {
//  Private constants for the class
	private final int       IMAGE_WIDTH             = 18;
	private final int       IMAGE_HEIGHT            = 18;

	private BoardPoint 		location;
	private String 			imageId                 = "";
    private int             imageIndex              = 0;
	private int         	imageWidth      		= 0;
	private int         	imageHeight     		= 0;
	private int         	speedPixelsPerCycle		= 1;
	private Direction 		directionPolicy 		= Direction.STOPPED;
	private Direction 		currentDirection 		= Direction.STOPPED;
	private Direction 		prevDirection 			= Direction.STOPPED;
    private Grid       	    grid;
	private final String[]	images = {"YellowSpaceshipRight", "YellowSpaceshipLeft",
			"YellowSpaceshipUp", "YellowSpaceshipDown", "YellowSpaceshipUp"};


    /**
        * SpacePilot constructor method
        * 
        * @implNote Constructor method to construct one SpacePilot object with
        *               the initial position.
        *
        * @param (String spacePilotId) (Space Pilot ID)
        * @return (SpacePilot)
        */
	public SpacePilot(String spacePilotId, Grid grid) {
		imageId             = spacePilotId;
        this.grid           = grid;

        this.imageIndex     = this.directionPolicy.index();

        //  Sets the image dimensions
        this.imageWidth     = this.IMAGE_WIDTH;
        this.imageHeight    = this.IMAGE_HEIGHT;
//		setLocation(new ScreenPoint(spacePilotLocation.x, spacePilotLocation.y));
	}	

	public BoardPoint getLocation() {
		return this.location;
	}
	
	public void setLocation(BoardPoint location) {
		this.location = location;
	}

    public void setLocation(int x, int y) {
    	this.location.x = x;
		this.location.y = y;
    }

	public void moveLocation(int dx, int dy) {
		this.location.x += dx;
		this.location.y += dy;
	}

	public void directionalKeyPressed(Direction direction) {
        imageIndex          = direction.index();
		directionPolicy     = direction;
        currentDirection    = direction;
	}
	
	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(Direction direction) {
		currentDirection    = direction;
	}

	public Direction getDirectionPolicy() {
		return directionPolicy;
	}
		
	public void setImageID(String id) {
		this.imageId    = id;
	}
	
	public String getImageName() {
		return images[imageIndex];
	}

	public int getImageWidth() {
		return imageWidth;
	}
	
	public int getImageHeight() {
		return imageHeight;
	}

	public String getImageID() {
		return this.imageId;
	}

	public void move() {
		
		prevDirection = currentDirection;
				
		// First try to move according to policy
		BoardPoint desired = new BoardPoint(location.x + currentDirection.xVec(), location.y + currentDirection.yVec());

		// if move is possible, i.e., maze does not block
		if (!grid.blocksMove(location, desired)) {
			directionPolicy = currentDirection;
			location.x = desired.x;
			location.y = desired.y;
			return;
		}
		// If reached here, desired policy is not applicable, move in opposite direction
	//	BoardPoint next = new BoardPoint(location.x + currentDirection.xVec(), location.y + currentDirection.yVec());
	//	if (grid.blocksMove(location, next)) {
	//		switch (currentDirection) {
	//			case RIGHT:
	//				currentDirection = Direction.LEFT;
	//				break;

	//			case LEFT:
	//				currentDirection = Direction.RIGHT;
	//				break;

	//			case UP:
	//				currentDirection = Direction.DOWN;
	//				break;

	//			case DOWN:
	//				currentDirection = Direction.UP;
	//				break;

	//		}
			// recalculate next BoardPoint according to new direction
	//		next = new BoardPoint(location.x + currentDirection.xVec(), location.y + currentDirection.yVec());
	//	}
		// move to next BoardPoint
	//	location.x = next.x;
	//	location.y = next.y;
	}
	
	public boolean changedDirection() {
		return (currentDirection != prevDirection);
	}
			
    //  Intersectable base class method to be implemented
    //  Intersectable base class method to be implemented
    //  Intersectable base class method to be implemented
    @Override
    public ScreenPoint[] getIntersectionVertices() {
        int intersectionWidth   = this.imageWidth;
        int intersectionHeight  = this.imageHeight;

        int leftX               = this.location.x;
        int topY                = this.location.y;

        // ScreenPoint[] vertices = {
        //         new ScreenPoint(centerX - intersectionWidth / 2, centerY - intersectionHeight / 2),
        //         new ScreenPoint(centerX + intersectionWidth / 2, centerY - intersectionHeight / 2),
        //         new ScreenPoint(centerX + intersectionWidth / 2, centerY + intersectionHeight / 2),
        //         new ScreenPoint(centerX - intersectionWidth / 2, centerY + intersectionHeight / 2)
        // };
        ScreenPoint[] vertices = {
                new ScreenPoint(leftX, topY),
                new ScreenPoint(leftX + intersectionWidth, topY),
                new ScreenPoint(leftX + intersectionWidth, topY + intersectionHeight),
                new ScreenPoint(leftX, topY + intersectionHeight)
            //new ScreenPoint(leftX, topY),
            //new ScreenPoint(leftX, topY),
            //new ScreenPoint(leftX, topY)
        };

        return vertices;
    }
}	
