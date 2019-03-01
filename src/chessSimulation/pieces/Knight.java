/**
 * 
 */
package chessSimulation.pieces;

import java.util.ArrayList;

import chessSimulation.Board;
import chessSimulation.Move;

/**
 * @author Gunnar Atli
 *
 */
public class Knight extends Piece {

	/**
	 * 
	 */
	public Knight(boolean color) {
		super(color);
		value = 3;
	}
	
	public Knight clone() {
		return new Knight(color);
	}

	public String toString() {
		if(color == WHITE)
			return "N";
		else
			return "n";
	}
	
	public ArrayList<Move> getMoves(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		// NNE
		if(valid(x+1, y+2) && 
				(!b.getSquare(x+1, y+2).isOccupied() ||
						(b.getSquare(x+1, y+2).isOccupied() && b.getSquare(x+1, y+2).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x+1, y+2));
		
		// ENE
		if(valid(x+2, y+1) && 
				(!b.getSquare(x+2, y+1).isOccupied() ||
						(b.getSquare(x+2, y+1).isOccupied() && b.getSquare(x+2, y+1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x+2, y+1));
		
		// ESE
		if(valid(x+2,y-1) && 
				(!b.getSquare(x+2,y-1).isOccupied() ||
						(b.getSquare(x+2,y-1).isOccupied() && b.getSquare(x+2,y-1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x+2,y-1));
		
		
		// SSE
		if(valid(x+1,y-2) && 
				(!b.getSquare(x+1,y-2).isOccupied() ||
						(b.getSquare(x+1,y-2).isOccupied() && b.getSquare(x+1,y-2).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x+1,y-2));
		
		// SSW
		if(valid(x-1,y-2) && 
				(!b.getSquare(x-1,y-2).isOccupied() ||
						(b.getSquare(x-1,y-2).isOccupied() && b.getSquare(x-1,y-2).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x-1,y-2));
		
		// WSW
		if(valid(x-2,y-1) && 
				(!b.getSquare(x-2,y-1).isOccupied() ||
						(b.getSquare(x-2,y-1).isOccupied() && b.getSquare(x-2,y-1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x-2,y-1));
		
		// WNW
		if(valid(x-2,y+1) && 
				(!b.getSquare(x-2,y+1).isOccupied() ||
						(b.getSquare(x-2,y+1).isOccupied() && b.getSquare(x-2,y+1).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x-2,y+1));
		
		// NNW
		if(valid(x-1,y+2) && 
				(!b.getSquare(x-1,y+2).isOccupied() ||
						(b.getSquare(x-1,y+2).isOccupied() && b.getSquare(x-1,y+2).getPiece().getColour() != color)))
			moves.add(new Move(x,y,x-1,y+2));
		
		return moves;
	}
}
