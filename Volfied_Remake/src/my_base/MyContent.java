package my_base;

import java.awt.Color;


import base.GameContent;
import my_game.Grid;
import my_game.SpacePilot;
import my_game.Board;
import my_game.BoardPoint;
import my_game.GameControl;
import my_game.SmallEnemies;
import my_game.Score;
import my_game.StatusLine;

public class MyContent extends GameContent {

    private final int   	QUANTITY_OF_SMALL_ENEMIES   = 10;

	private Grid 			grid;
    private SpacePilot  	spacePilot;
	private SmallEnemies	smallEnemies;
	private Score 			score;
	private StatusLine 		statusLine;
	private Board           board;
	private GameControl     gameControl;
	
	@Override
	public void initContent() {
		board 			= new Board();
		board.setContent(this);
		grid 			= new Grid(board);
		spacePilot		= new SpacePilot("spacePilot", grid);
        spacePilot.setLocation(new BoardPoint(1,13));
		smallEnemies	= new SmallEnemies();
		smallEnemies.initSmallEnemies(grid);
		score 			= new Score();
		statusLine 		= new StatusLine();
		statusLine.showText("Good Luck!", Color.GREEN, 3000);
		gameControl = new GameControl(this);
	}	
	
	public Grid grid() {
		return grid;
	}
	
	public SpacePilot spacePilot() {
		return spacePilot;
	}
	
	public SmallEnemies smallEnemies() {
		return smallEnemies;
	}
	
	public Score score() {
		return score;
	}

	public StatusLine statusLine() {
		return statusLine;
	}

	public Board getBoard() {
		return this.board;
	}

	public GameControl gameControl() {
		return this.gameControl;
	}
}
