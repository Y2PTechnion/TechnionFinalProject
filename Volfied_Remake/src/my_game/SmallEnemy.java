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
public class SmallEnemy extends GameCharacter {
//  Private variables for the class
	private int         speedPixelsPerCycle		= 1;
	
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
        super(smallEnemyId, grid);

        directionPolicy     = Direction.DOWN;
	    currentDirection    = Direction.RIGHT;
	}	

    @Override
	public void move() {
						
		// First try to move according to policy
		BoardPoint desired = new BoardPoint(getLocation().x + directionPolicy.xVec(), getLocation().y + directionPolicy.yVec());
		// if move is possible, i.e., maze does not block
		if (!grid.blocksMove(getLocation(), desired)) {
			currentDirection = directionPolicy;
            setLocation(desired);

			// After moving to next location, update movement direction randomly for next movement
			updateDirectionPolicy();
			return;
		}

		// If reached here, desired policy is not applicable, move in current direction
		BoardPoint next = new BoardPoint(getLocation().x + currentDirection.xVec(), getLocation().y + currentDirection.yVec());
		if (grid.blocksMove(getLocation(), next)) {
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
			next = new BoardPoint(getLocation().x + currentDirection.xVec(), getLocation().y + currentDirection.yVec());
		}

		// move to next point
        setLocation(next);
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
		