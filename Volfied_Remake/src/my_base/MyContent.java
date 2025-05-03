package my_base;

import java.awt.Color;

import base.Game;
import base.GameCanvas;
import base.GameContent;
import my_game.Field;
import my_game.SpacePilot;
import my_game.Board;
import my_game.GameControl;
import my_game.SmallEnemies;
import my_game.Score;
import my_game.StatusLine;

public class MyContent extends GameContent {

    private final int   	QUANTITY_OF_SMALL_ENEMIES   = 10;

	private Field 			field;
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
		field 			= new Field(board);
		spacePilot		= new SpacePilot("spacePilot", field);
		smallEnemies	= new SmallEnemies();
		smallEnemies.initSmallEnemies(field);
		score 			= new Score();
		statusLine 		= new StatusLine();
		statusLine.showText("Good Luck!", Color.GREEN, 3000);
		gameControl = new GameControl(this);
	}	
	
	public Field field() {
		return field;
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
