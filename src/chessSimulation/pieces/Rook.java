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
public class Rook extends Piece {

	/**
	 * 
	 */
	public Rook(boolean color) {
		super(color);
		value = 5;
	}
	public String toString() {
		if(color == WHITE)
			return "R";
		else
			return "r";
	}
	public Rook clone() {
		return new Rook(color);
	}
	
	
	public ArrayList<Move> getMoves(Board b, int x, int y) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		// up
		for(int i = 1; i < 8; i++) {
			if(valid(x, y+i)) {
				if(b.getSquare(x, y+i).isOccupied()) {
					if(b.getSquare(x, y+i).getPiece().color != color)
						moves.add(new Move(x,y,x,y+i));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x,y+i));	
			}
		}
		
		// down
		for(int i = 1; i < 8; i++) {
			if(valid(x, y-i)) {
				if(b.getSquare(x, y-i).isOccupied()) {
					if(b.getSquare(x, y-i).getPiece().color != color)
						moves.add(new Move(x,y,x,y-i));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x,y-i));	
			}
		}
		
		// left
		for(int i = 1; i < 8; i++) {
			if(valid(x-i, y)) {
				if(b.getSquare(x-i, y).isOccupied()) {
					if(b.getSquare(x-i, y).getPiece().color != color)
						moves.add(new Move(x,y,x-i,y));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x-i,y));	
			}
		}
		
		// right
		for(int i = 1; i < 8; i++) {
			if(valid(x+i, y)) {
				if(b.getSquare(x+i, y).isOccupied()) {
					if(b.getSquare(x+i, y).getPiece().color != color)
						moves.add(new Move(x,y,x+i,y));	
					
					break;
				}
				else
					moves.add(new Move(x,y,x+i,y));	
			}
		}
		
		
		return moves;
	}
}
