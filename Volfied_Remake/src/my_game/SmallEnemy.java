package my_game;

import base.Game;
import base.GameCanvas;
import ui_elements.ScreenPoint;
import base.Intersectable;


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
public class SmallEnemy implements Intersectable {
	
	private ScreenPoint location;
	private String 		imageId                 = "";
	private int         imageWidth      		= 0;
	private int         imageHeight     		= 0;
	private int         speedPixelsPerCycle		= 1;
	
    /**
        * SmallEnemy constructor method
        * 
        * @implNote Constructor method to construct one SmallEnemy object with
        *               the initial position.
        *
        * @param (String smallEnemyId) (Small Enemy ID)
        * @param (ScreenPoint smallEnemyLocation) (Small Enemy first location into the canvas)
        * @return (SmallEnemy)
        */
	public SmallEnemy(String smallEnemyId, ScreenPoint smallEnemyLocation) {
		imageId             = smallEnemyId;
		setLocation(new ScreenPoint(smallEnemyLocation.x, smallEnemyLocation.y));
	}	

	public ScreenPoint getLocation() {
		return this.location;
	}
	
	public void setLocation(ScreenPoint location) {
		this.location = location;
	}
	
	public void setImageID(String id) {
		this.imageId = id;
	}
	
	public String getImageID() {
		return this.imageId;
	}

	public void addToCanvas() {
		GameCanvas canvas = Game.UI().canvas();
		//TODO
		//Create the character's graphical elements and add them to the canvas
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
