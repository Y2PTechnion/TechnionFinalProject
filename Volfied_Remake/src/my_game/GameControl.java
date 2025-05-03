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
		
//		Lollipop lp = eatCurrentLolly(content.pacman().getLocation());
		// If history is played, restore locations from history.
		// Otherwise, move pacman and ghosts according to
//		if (content.historyPlayer().isPlaying()) {
//			content.historyPlayer().playState();
//		} else {
//			content.spacePilot().move();
			content.smallEnemies().move();
//		}
//		board.updateSpacePilot();
		board.updateSmallEnemies();
//		if (lp != null) {
//			board.updateLolly(lp);
//		}
//		handleCollisions();
//		board.updateScore();
//		content.statusLine().refresh();
//		board.updateStatusLine();
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
}
