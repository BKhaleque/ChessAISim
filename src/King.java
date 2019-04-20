import java.util.ArrayList;
public class King extends Piece {
	boolean hasMoved = false;

	public King(boolean color) {
		super(color);
		value = 0;
	}
	
	public King(boolean color, boolean hasMoved) {
		super(color);
		this.hasMoved = hasMoved;
		value = 0;
	}
	
	public King clone() {
		return new King(color, hasMoved);
	}

	public String toString() {
		if(color == WHITE)
			return "K";
		else
			return "k";
	}
	public ArrayList<Move> getMoves(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		// N
		if(valid(x, y+1) && 
				(!b.getSquare(x, y+1).isOccupied() ||
						(b.getSquare(x, y+1).isOccupied() && b.getSquare(x, y+1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x,y+1));

		// NE
		if(valid(x+1, y+1) &&
				(!b.getSquare(x+1, y+1).isOccupied() ||
						(b.getSquare(x+1, y+1).isOccupied() && b.getSquare(x+1, y+1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x+1,y+1));

		// E
		if(valid(x+1,y) &&
				(!b.getSquare(x+1,y).isOccupied() ||
						(b.getSquare(x+1,y).isOccupied() && b.getSquare(x+1,y).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x+1,y));


		// SE
		if(valid(x+1,y-1) &&
				(!b.getSquare(x+1,y-1).isOccupied() ||
						(b.getSquare(x+1,y-1).isOccupied() && b.getSquare(x+1,y-1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x+1,y-1));

		// S
		if(valid(x,y-1) &&
				(!b.getSquare(x,y-1).isOccupied() ||
						(b.getSquare(x,y-1).isOccupied() && b.getSquare(x,y-1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x,y-1));

		// SW
		if(valid(x-1,y-1) &&
				(!b.getSquare(x-1,y-1).isOccupied() ||
						(b.getSquare(x-1,y-1).isOccupied() && b.getSquare(x-1,y-1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x-1,y-1));

		// W
		if(valid(x-1,y) &&
				(!b.getSquare(x-1,y).isOccupied() ||
						(b.getSquare(x-1,y).isOccupied() && b.getSquare(x-1,y).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x-1,y));

		// NW
		if(valid(x-1,y+1) &&
				(!b.getSquare(x-1,y+1).isOccupied() ||
						(b.getSquare(x-1,y+1).isOccupied() && b.getSquare(x-1,y+1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x-1,y+1));

		// Castling
		if(color == Piece.WHITE) {
			if(!hasMoved && x == Board.e && y == 1-1) {
				if(!b.getSquare(Board.f, 1-1).isOccupied() &&
						!b.getSquare(Board.g, 1-1).isOccupied() &&
						b.getSquare(Board.h, 1-1).isOccupied() &&
						b.getSquare(Board.h, 1-1).getPiece().toString().equals("R"))
					moves.add(new Move(x,y,x+2,y));


			}
			else
				hasMoved = true;
		}
		else { // colour == Piece.BLACK
			if(!hasMoved && x == Board.e && y == 8-1) {

			}
			else
				hasMoved = true;
		}



		
		return moves;
	}
}
