/**
 * 
 */
package chessSimulation;

import chessSimulation.pieces.Piece;

/**
 * @author Gunnar Atli
 *
 */
public class Square {
	private boolean occupied;
	private Piece piece;
	public boolean WHITE;

	/**
	 * 
	 */
	public Square(boolean WHITE) {
		occupied = false;
		this.WHITE = WHITE;
	}
	
	public Square(Square square) {
		this.occupied = square.isOccupied();
		this.piece = square.isOccupied() ? square.getPiece().clone() : null;
		this.WHITE = square.WHITE;
	}
	
	public Square(Piece piece,boolean WHITE) {
		occupied = true;
		this.piece = piece;
	}
	
	public String toString() {
		if(occupied)
			return piece.toString();
		else
			return ".";
	}

	public Piece getPiece() {
		return piece;
	}
	
	public boolean isOccupied() {
		return occupied;
	}

}
