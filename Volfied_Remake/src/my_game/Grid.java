package my_game;

import java.util.ArrayList;

public class Grid {
	
	public enum Direction{
		RIGHT (1,0, 0),
		LEFT(-1,0, 1),
		UP (0,-1, 2),
		DOWN(0,1, 3),
        STOPPED(0,0, 4);
		
		private final int xVec, yVec, index;
		private Direction(int xVec, int yVec, int index) {
			this.xVec   = xVec;
			this.yVec   = yVec;
            this.index  = index;
		}
		public int xVec() {
			return this.xVec;
		}
		public int yVec() {
			return this.yVec;
		}
		public int index() {
			return this.index;
		}
	}
	
	public static final int GRID_X_SIZE = 21;
	public static final int GRID_Y_SIZE = 14;	
	
	private Board board;
	private ArrayList<GridLine> lines = new ArrayList<GridLine>();
	
	public Grid(Board board) {
		this.board  = board;
		initGridLines();
	}

	private void initGridLines() {
		// Frame
		lines.add(new GridLine(0,0,GRID_X_SIZE,0));
		lines.add(new GridLine(0,0,0,GRID_Y_SIZE));
		lines.add(new GridLine(0,GRID_Y_SIZE,GRID_X_SIZE,GRID_Y_SIZE));
		lines.add(new GridLine(GRID_X_SIZE,0,GRID_X_SIZE,GRID_Y_SIZE));
		
		// Inner lines
		


	}
	public void addGridToBoard() {
		int i = 0;
		for (GridLine line: lines()) {
			board.addLine(line, ++i);
		}
	}
	public boolean blocksMove(BoardPoint p1, BoardPoint p2) {
		
		//Check if any of the lines blocks the move and if so, return true
		for (GridLine line: lines) {
			if (line.blocksMove(p1, p2)) {
				return true;
			}
		}
		//If reached here, there is no blocking line
		return false;
	}

	public boolean isOnGridLine(int x, int y) {
		
		//Check if the point is on any of the lines and if so, return true
		for (GridLine line: lines) {
			if (line.isOnLine(x, y)) {
				return true;
			}
		}
		//If reached here, it is not on any line
		return false;
	}
	
	public ArrayList<GridLine> lines() {
		return this.lines;
	}
}
