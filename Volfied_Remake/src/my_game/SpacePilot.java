package my_game;

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
public class SpacePilot extends GameCharacter {
//  Private variables for the class
    private int             imageIndex              = 0;
	private int         	speedPixelsPerCycle		= 1;
	private Direction 		prevDirection 			= Direction.STOPPED;
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
        super(spacePilotId, grid);
	    directionPolicy     = Direction.STOPPED;
	    currentDirection    = Direction.STOPPED;
	    prevDirection 	    = Direction.STOPPED;
        this.imageIndex     = this.directionPolicy.index();
//		setLocation(new ScreenPoint(spacePilotLocation.x, spacePilotLocation.y));
	}	

	public void directionalKeyPressed(Direction direction) {
        imageIndex          = direction.index();
		directionPolicy     = direction;
        currentDirection    = direction;
	}
	
		
	public String getImageName() {
		return images[imageIndex];
	}

    @Override
	public void move() {
		
		prevDirection = currentDirection;
				
		// First try to move according to policy
		BoardPoint desired = new BoardPoint(getLocation().x + currentDirection.xVec(), getLocation().y + currentDirection.yVec());

		// if move is possible, i.e., maze does not block
		if (!grid.blocksMove(getLocation(), desired)) {
			directionPolicy = currentDirection;
            setLocation(desired);
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
}	