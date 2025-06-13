package my_base;

import base.Game;
import base.PeriodicLoop;
import my_game.BoardPoint;

public class MyPeriodicLoop extends PeriodicLoop {

	private MyContent content;

	public void setContent(MyContent content) {
		this.content = content;
	}

//    private void redrawSpacePilot() {
//        content.spacePilot().move();
        // After changing the space pilot self location, move also its image in the canvas accordingly.
 //       BoardPoint boardPoint = content.spacePilot().getLocation();
 //       if (null != boardPoint) {
 //           Game.UI().canvas().moveShapeToLocation(content.spacePilot().name(), 
  //          boardPoint.x, boardPoint.y);
  //      }
//}

	@Override
	public void execute() {
		// Let the super class do its work first
		super.execute();



		if (null != content.gameControl()) {
			content.gameControl().gameStep();
            //  Do someting only if game control was initialized.
            //  Redraw the space pilot periodically by calling the correct method
  //          redrawSpacePilot();
		}			
		

	}

}
