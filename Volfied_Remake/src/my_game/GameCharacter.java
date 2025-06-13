package my_game;
import ui_elements.ScreenPoint;
import base.Intersectable;
import my_game.Grid.Direction;

/**
 * GameCharacter class
 * 
 * @implNote This class implements a new GameCharacter that implements the Intersectable interfaces 
 *              to detect the 'collision' with another objets.
 *
 *           <p>
 *           Bugs: (a list of bugs and other problems)
 * 
 * @author (YuvalYossiPablo)
 */
public class GameCharacter implements Intersectable {
//  Private constants for the class
	private final int       IMAGE_WIDTH             = 18;
	private final int       IMAGE_HEIGHT            = 18;

//  Private variables for the class
	private     BoardPoint 		location            = null;
	private     String 			name                = "";
	private     int         	imageWidth          = 0;
	private     int         	imageHeight         = 0;
    protected   Grid       	    grid                = null;
	protected   Direction 		directionPolicy     = Direction.STOPPED;
	protected   Direction 		currentDirection    = Direction.STOPPED;


    /**
        * GameCharacter constructor method
        * 
        * @implNote Constructor method to construct one GameCharacter object with
        *               the initial position.
        *
        * @param (String gameCharacterId) (Game Character ID)
        * @return (GameCharacter)
        */
	public GameCharacter(String gameCharacterId, Grid grid) {
		this.name           = gameCharacterId;
        this.grid           = grid;

        //  Sets the image dimensions
        this.imageWidth     = this.IMAGE_WIDTH;
        this.imageHeight    = this.IMAGE_HEIGHT;
//		setLocation(new ScreenPoint(gameCharacterId.x, gameCharacterId.y));
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

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(Direction direction) {
		currentDirection    = direction;
	}

	public int getImageWidth() {
		return imageWidth;
	}
	
	public int getImageHeight() {
		return imageHeight;
	}

	public String name() {
		return this.name;
	}

	protected void move() {

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

        ScreenPoint[] vertices = {
                new ScreenPoint(leftX, topY),
                new ScreenPoint(leftX + intersectionWidth, topY),
                new ScreenPoint(leftX + intersectionWidth, topY + intersectionHeight),
                new ScreenPoint(leftX, topY + intersectionHeight)
        };

        return vertices;
    }
}	
