import java.util.ArrayList;

public abstract class Piece {
	public static final boolean WHITE = true, BLACK = false;
	protected boolean color;
	protected int value;
	public boolean paralysed;

	
	public boolean getColour() {
		return color;
	}

	public Piece(boolean color) {
		this.color = color;
		value = 0;
		this.paralysed = false;
	}
	
	public int getValue() {
		return value;
	}
	
	public abstract Piece clone();
	
	public abstract ArrayList<Move> getMoves(Board b, int x, int y);
	
	/**
	 * @param b Board
	 * @param x x location of piece
	 * @param y y location of piece
	 * @return
	 */
	static public boolean valid(int x, int y) {
		if(x < 0 || x > 7 || y < 0 || y > 7)
			return false;
		else
			return true;
	}

	public boolean isParalysed() {
		return paralysed;
	}

	public void setParalysed(boolean paralysed) {
		this.paralysed = paralysed;
	}
}
