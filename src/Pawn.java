
import java.util.ArrayList;


public class Pawn extends Piece {


	private boolean firstMove;
	private Pawn(boolean color) {
		super(color);
		firstMove = true;
		value = 1;
	}
	
	public Pawn clone() {
		Pawn newP = new Pawn(color);
		newP.firstMove = this.firstMove;
		return newP;
	}

	public String toString() {
		if(color == WHITE)
			return "P";
		else
			return "p";
	}
	
	/**
	 * @param b Board
	 * @param x x location of piece
	 * @param y y location of piece
	 * @return
	 */
	public ArrayList<Move> getMoves(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		if(color == Piece.WHITE) {
			// forward
			if(valid(x,y+1) && !b.getSquare(x, y+1).isOccupied())
				moves.add(new Move(x,y,x,y+1));
			//if(valid(x,y+1) && !b.getSquare(x, y+2).isOccupied() && firstMove){
			//	firstMove = false;
			//	moves.add(new Move(x,y,x,y+2));

			//}
			
			// kill diagonally
			if(valid(x+1,y+1) && b.getSquare(x+1, y+1).isOccupied() && b.getSquare(x+1, y+1).getPiece().getColour() != color)
				moves.add(new Move(x,y,x+1,y+1));
			
			// kill diagonally
			if(valid(x-1,y+1) && b.getSquare(x-1, y+1).isOccupied() && b.getSquare(x-1, y+1).getPiece().getColour() != color)
				moves.add(new Move(x,y,x-1,y+1));
		}
		else {
			// forward
			if(valid(x,y-1) && !b.getSquare(x, y-1).isOccupied())
				moves.add(new Move(x,y,x,y-1));
			
			// kill diagonally
			if(valid(x+1,y-1) && b.getSquare(x+1, y-1).isOccupied() && b.getSquare(x+1, y-1).getPiece().getColour() != color)
				moves.add(new Move(x,y,x+1,y-1));
			
			// kill diagonally
			if(valid(x-1,y-1) && b.getSquare(x-1, y-1).isOccupied() && b.getSquare(x-1, y-1).getPiece().getColour() != color)
				moves.add(new Move(x,y,x-1,y-1));
		}
		
		return moves;
	}
}
