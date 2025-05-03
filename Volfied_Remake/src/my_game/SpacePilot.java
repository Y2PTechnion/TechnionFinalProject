package my_game;

import base.Game;
import base.GameCanvas;
import ui_elements.ScreenPoint;
import base.Intersectable;
import my_game.Field.Direction;

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
	private BoardPoint 	location;
	private String 		imageId                 = "";
	private int         imageWidth      		= 0;
	private int         imageHeight     		= 0;
	private int         speedPixelsPerCycle		= 1;
	private Direction 	directionPolicy 		= Direction.RIGHT;
	private Direction 	currentDirection 		= Direction.RIGHT;
	private Direction 	prevDirection 			= Direction.RIGHT;
    private Field       field;
	
    /**
        * SpacePilot constructor method
        * 
        * @implNote Constructor method to construct one SpacePilot object with
        *               the initial position.
        *
        * @param (String spacePilotId) (Space Pilot ID)
        * @return (SpacePilot)
        */
	public SpacePilot(String spacePilotId, Field field) {
		imageId             = spacePilotId;
        this.field          = field;
//		setLocation(new ScreenPoint(spacePilotLocation.x, spacePilotLocation.y));
	}	

	public BoardPoint getLocation() {
		return this.location;
	}
	
	public void setLocation(BoardPoint location) {
		this.location = location;
	}
	
	public void setDirectionPolicy(Direction direction) {
		directionPolicy = direction;
	}
	
	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public Direction getDirectionPolicy() {
		return directionPolicy;
	}
		
	public void setImageID(String id) {
		this.imageId = id;
	}
	
	public String getImageID() {
		return this.imageId;
	}

	public void move() {
		
		prevDirection = currentDirection;
				
		// First try to move according to policy
		BoardPoint desired = new BoardPoint(location.x + directionPolicy.xVec(), location.y + directionPolicy.yVec());
		// if move is possible, i.e., maze does not block
		if (!field.blocksMove(location, desired)) {
			currentDirection = directionPolicy;
			location.x = desired.x;
			location.y = desired.y;
			return;
		}
		// If reached here, desired policy is not applicable, move in opposite direction
		BoardPoint next = new BoardPoint(location.x + currentDirection.xVec(), location.y + currentDirection.yVec());
		if (field.blocksMove(location, next)) {
			switch (currentDirection) {
				case RIGHT:
					currentDirection = Direction.LEFT;
					break;
				case LEFT:
					currentDirection = Direction.RIGHT;
					break;
				case UP:
					currentDirection = Direction.DOWN;
					break;
				case DOWN:
					currentDirection = Direction.UP;
					break;
			}
			// recalculate next BoardPoint according to new direction
			next = new BoardPoint(location.x + currentDirection.xVec(), location.y + currentDirection.yVec());
		}
		// move to next BoardPoint
		location.x = next.x;
		location.y = next.y;
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
