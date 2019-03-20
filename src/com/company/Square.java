/**
 * 
 */
package com.company;

/**
 * @author Gunnar Atli
 *
 */
public class Square {
	private boolean occupied;
	private Piece piece;
	public boolean WHITE;
	private int x;
	private int y;

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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
