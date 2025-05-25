package my_game;

import my_base.MyContent;

public class GameControl {
    private MyContent   content;
	private Board       board;

    public GameControl(MyContent content) {
        this.content    = content;
        this.board      = content.getBoard();
    }

	public void gameStep() {
		
		Region rg = conquerCurrentRegion(content.spacePilot().getLocation());
		// If history is played, restore locations from history.
		// Otherwise, move space pilot and small enemies according to
//		if (content.historyPlayer().isPlaying()) {
//			content.historyPlayer().playState();
//		} else {
			content.spacePilot().move();
			content.smallEnemies().move();
//		}
		board.updateSpacePilot();
		board.updateSmallEnemies();
		if (null != rg) {
			board.updateRegion(rg);
		}
//		handleCollisions();
		board.updateScore();
		content.statusLine().refresh();
		board.updateStatusLine();
//		content.historyRecorder().recordState();
//        checkGameOver();
		
	}

	private void handleCollisions() {
		SpacePilot 		spacePilot   	= content.spacePilot();
		SmallEnemies	smallEnemies	= content.smallEnemies();
		
		for (SmallEnemy s: smallEnemies.getSmallEnemies()) {
			if (s.getLocation().x == spacePilot.getLocation().x &&
					s.getLocation().y == spacePilot.getLocation().y) {
//				content.score().reset();
//				content.statusLine().showText("Oops ...", Color.RED, 2000);
				return;
			}
		}
	}


	public Region conquerCurrentRegion(BoardPoint location) {
		Region rg = content.grid().regions()[location.x][location.y];
		if (rg.isShown()) {
			rg.hide();
//			content.score().add(1);
			content.grid().decreaseRegions();
			// if (content.maze().currentLollies() == 0) {
			// 	content.statusLine().showText("Great JOB !!!", Color.YELLOW, 5000);
			// }
			return rg; 
		}
		return null;
	}

    private void conquerRegion(BoardPoint startPoint, int currentLine) {
//  In the game Volfied, the algorithm to determine if a region is conquered involves several steps. 
//  Here's a simplified version of how it works:

//  1)  Start Drawing: When the player starts drawing a line, the game keeps track of the starting point and the current position of the line.
//  2)  Detect Line Closure: The game continuously checks if the line has closed a loop by intersecting with itself or the boundary of the play area.
//  3)  Flood Fill Algorithm: Once a loop is detected, the game uses a flood fill algorithm to determine the enclosed area. This algorithm works by starting from a point inside the loop and "filling" outwards until it hits the boundary of the loop.
//  4)  Check for Enemies: After filling the area, the game checks if any enemies are present within that area. If there are no enemies, the area is considered conquered.
//  5)  Update Game State: If the area is conquered, the game updates the map to reflect the new territory and adjusts the player's score accordingly.

    //  if lineClosesLoop(currentLine) {
    //      enclosedArea = floodFill(startPoint, currentLine);
    //       if noEnemiesInArea(enclosedArea) {
    //            markAreaAsConquered(enclosedArea);
    //            updateScore();
    //        }
    //}
    }

}
