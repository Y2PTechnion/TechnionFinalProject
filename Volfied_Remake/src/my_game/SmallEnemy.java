package my_game;

import my_game.Grid.Direction;


/**
 * SmallEnemy class
 * 
 * @implNote This class implements a new SmallEnemy that implements the Intersectable interfaces 
 *              to detect the 'collision' with another objets.
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class SmallEnemy {
	
	private BoardPoint 	location;
	private String 		name                 	= "";
	private int         imageWidth      		= 0;
	private int         imageHeight     		= 0;
	private int         speedPixelsPerCycle		= 1;
	private Direction 	directionPolicy 		= Direction.DOWN;
	private Direction 	currentDirection 		= Direction.RIGHT;
	private Grid 	    grid;	
	
    /**
        * SmallEnemy constructor method
        * 
        * @implNote Constructor method to construct one SmallEnemy object with
        *               the initial position.
        *
        * @param (String smallEnemyId) (Small Enemy ID)
        * @param (ScreenPoint smallEnemyLocation) (Small Enemy first location into the canvas)
		* @param (Grid grid) (grid)
        * @return (SmallEnemy)
        */
	public SmallEnemy(String smallEnemyId, Grid grid) {
		this.name          	= smallEnemyId;
		this.grid 			= grid;
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

	public Direction getPolicy() {
		return directionPolicy;
	}
	
	public String name() {
		return name;
	}

	public void move() {
						
		// First try to move according to policy
		BoardPoint desired = new BoardPoint(location.x + directionPolicy.xVec(), location.y + directionPolicy.yVec());
		// if move is possible, i.e., maze does not block
		if (!grid.blocksMove(location, desired)) {
			currentDirection = directionPolicy;
			location.x = desired.x;
			location.y = desired.y;
			// After moving to next location, update movement direction randomly for next movement
			updateDirectionPolicy();
			return;
		}

		// If reached here, desired policy is not applicable, move in current direction
		BoardPoint next = new BoardPoint(location.x + currentDirection.xVec(), location.y + currentDirection.yVec());
		if (grid.blocksMove(location, next)) {
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
			updateDirectionPolicy();
			// recalculate next point according to new direction
			next = new BoardPoint(location.x + currentDirection.xVec(), location.y + currentDirection.yVec());
		}

		// move to next point
		location.x = next.x;
		location.y = next.y;
	}
	
	private void updateDirectionPolicy() {
		switch (currentDirection) {
		case RIGHT:
			directionPolicy = (Math.random() < 0.5 ? Direction.DOWN : Direction.UP);
			break;

		case LEFT:
			directionPolicy = (Math.random() < 0.5 ? Direction.DOWN : Direction.UP);
			break;

		case UP:
			directionPolicy = (Math.random() < 0.5 ? Direction.RIGHT : Direction.LEFT);
			break;

		case DOWN:
			directionPolicy = (Math.random() < 0.5 ? Direction.RIGHT : Direction.LEFT);
			break;

		}
	}
}
		