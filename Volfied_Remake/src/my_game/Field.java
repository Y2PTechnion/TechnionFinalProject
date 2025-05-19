package my_game;

import java.util.ArrayList;

public class Field {
	
	public enum Direction{
		RIGHT (1,0, 0),
		LEFT(-1,0, 1),
		UP (0,-1, 2),
		DOWN(0,1, 3);
		
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
	
	public static final int FIELD_X_SIZE = 21;
	public static final int FIELD_Y_SIZE = 14;	
	
	private Board board;
	private ArrayList<FieldLine> lines = new ArrayList<FieldLine>();
	
	public Field(Board board) {
		this.board  = board;
		initFieldLines();
	}

	private void initFieldLines() {
		// Frame
		lines.add(new FieldLine(0,0,FIELD_X_SIZE,0));
		lines.add(new FieldLine(0,0,0,FIELD_Y_SIZE));
		lines.add(new FieldLine(0,FIELD_Y_SIZE,FIELD_X_SIZE,FIELD_Y_SIZE));
		lines.add(new FieldLine(FIELD_X_SIZE,0,FIELD_X_SIZE,FIELD_Y_SIZE));
		
		// Inner lines
		


	}
	public void addFieldToBoard() {
		int i = 0;
		for (FieldLine line: lines()) {
			board.addLine(line, ++i);
		}
	}
	public boolean blocksMove(BoardPoint p1, BoardPoint p2) {
		
		//Check if any of the lines blocks the move and if so, return true
		for (FieldLine line: lines) {
			if (line.blocksMove(p1, p2)) {
				return true;
			}
		}
		//If reached here, there is no blocking line
		return false;
	}

	public boolean isOnFieldLine(int x, int y) {
		
		//Check if the point is on any of the lines and if so, return true
		for (FieldLine line: lines) {
			if (line.isOnLine(x, y)) {
				return true;
			}
		}
		//If reached here, it is not on any line
		return false;
	}
	
	public ArrayList<FieldLine> lines() {
		return this.lines;
	}
}
