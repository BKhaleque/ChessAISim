/**
 * 
 */
package chessSimulation.minimax;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import chessSimulation.Board;
import chessSimulation.Move;
import chessSimulation.Square;
import chessSimulation.pieces.Piece;

/**
 * @author Gunnar Atli
 *
 */
public class MinimaxAlphaBeta {

	boolean colour;
	int maxDepth;
	Random rand;
	/**
	 * 
	 */
	public MinimaxAlphaBeta(boolean colour, int maxDepth) {
		this.colour = colour;
		this.maxDepth = maxDepth;
		rand = new Random();
	}
	
	private float maxValue(Board b, ArrayList<Move> state, float alpha, float beta, int depth) {
		if(depth > maxDepth)
			return eval1(b, state, colour);
		
		ArrayList<Move> moves = b.getMovesAfter(colour, state);
		if(moves.size() == 0)
			return Float.NEGATIVE_INFINITY;
		
		for(int i = 0; i < moves.size(); i++) {
			state.add(moves.get(i));
			float tmp = minValue(b, state, alpha, beta, depth + 1);
			state.remove(state.lastIndexOf(moves.get(i)));
			if(tmp > alpha) {
				alpha = tmp;
			}
			
			if(beta <= alpha)
				break;
			
			//if (max >= beta)
			//	return max;
			
			//if (max > alpha)
			//	alpha = max;
		}
		
		return alpha;
	}
	
	private float minValue(Board b, ArrayList<Move> state, float alpha, float beta, int depth) {
		if(depth > maxDepth)
			return eval1(b, state, !colour);
		
		ArrayList<Move> moves = b.getMovesAfter(!colour, state);
		if(moves.size() == 0)
			return Float.POSITIVE_INFINITY;
		
		for(int i = 0; i < moves.size(); i++) {
			state.add(moves.get(i));
			float tmp = maxValue(b, state, alpha, beta, depth + 1);
			state.remove(state.lastIndexOf(moves.get(i)));
			if(tmp < beta) {
				beta = tmp;
			}
			
			if(beta <= alpha)
				break;


		}
		
		return beta;
	}
	
	public Move decision(final Board b) {
		// get maximum move
		
		final ArrayList<Move> moves = b.getMoves(colour);
		if(moves.size() == 0)
			return null;
 		
		Vector<Future<Float>> costs = new Vector<>(moves.size());
		costs.setSize(moves.size());
		
 		ExecutorService exec = Executors.newFixedThreadPool(moves.size());
 		try {
 		    for (int i = 0; i < moves.size(); i++) {
 		    	final Move move = moves.get(i);
 		        Future<Float> result = exec.submit(new Callable<Float>() {

 		            @Override
 		            public Float call() {
 		            	ArrayList<Move> state = new ArrayList<Move>();
 		            	state.add(move);
 		            	
 		            	float tmp = minValue(b, state, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1);
 		            	return tmp;
 		            }
 		        });
 		        costs.set(i, result);
 		    }
 		} finally {
 		    exec.shutdown();
 		}

 		// max
 		int maxi = -1;
		float max = Float.NEGATIVE_INFINITY;
 		for(int i = 0; i < moves.size(); i++) {
 			float cost;
			try {
				cost = costs.get(i).get();
			} catch (Exception e) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
				}
				continue;
			}
 			if(cost >= max) {
 				if(Math.abs(cost-max) < 0.1) // add a little random element
 					if(rand.nextBoolean())
 						continue;

 				max = cost;
 				maxi = i;
 			}
 		}
 		
 		return moves.get(maxi);
	}
	/*
	public Move SingleThreadDecision(Board b) {
		// get maximum move
		
		ArrayList<Move> moves = b.getMoves(colour);
		ArrayList<Move> state = new ArrayList<Move>();
		float[] costs = new float[moves.size()];
 		for(int i = 0; i < moves.size(); i++) {
			state.add(moves.get(i));
			float tmp = minValue(b, state, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1);
			costs[i] = tmp;
			state.remove(state.lastIndexOf(moves.get(i)));
		}
 		
 		// max
 		int maxi = -1;
		float max = Float.NEGATIVE_INFINITY;
 		for(int i = 0; i < moves.size(); i++) {
 			if(costs[i] >= max) {
 				if(Math.abs(costs[i]-max) < 0.1) // add a little random element
 					if(rand.nextBoolean())
 						continue;

 				max = costs[i];
 				maxi = i;
 			}
 		}
 		
 		if(maxi == -1)
 			return null;
 		else
 			return moves.get(maxi);
	}
	*/
	/*
	private float eval2(Board b, ArrayList<Move> moves, boolean currentColor) {
		Square[][] squares = b.getSquaresAfter(moves);
		
		// check if king missing
		boolean blackKing = false, whiteKing = false;
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(squares[i][j].isOccupied()) {
					if(squares[i][j].getPiece().toString().equals("K")) {
						whiteKing = true;
					}
					if(squares[i][j].getPiece().toString().equals("k")) {
						blackKing = true;
					}
				}
			}
		
		if(colour == Piece.WHITE) {
			if(whiteKing == false)
				return Float.NEGATIVE_INFINITY;
			if(blackKing == false)
				return Float.POSITIVE_INFINITY;
		}
		else {
			if(whiteKing == false)
				return Float.POSITIVE_INFINITY;
			if(blackKing == false)
				return Float.NEGATIVE_INFINITY;
		}

			
		
		int whiteScore = 0;
		int blackScore = 0;
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(squares[i][j].isOccupied())
					if(squares[i][j].getPiece().getColour() == Piece.WHITE)
						whiteScore += squares[i][j].getPiece().getValue();
					else
						blackScore += squares[i][j].getPiece().getValue();
			}
		
		
		if(colour == Piece.WHITE)
			return whiteScore - blackScore;
		else
			return blackScore - whiteScore;
	}
	*/
	
	private float eval1(Board b, ArrayList<Move> moves, boolean currentColor) {
		Square[][] squares = b.getSquaresAfter(moves);
		
		if(b.getMoves(currentColor).size() == 0) {
			if(b.isCheckAfter(currentColor, moves) && b.lossOnCheckmate)
				return (currentColor == this.colour) ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
			else
				return Float.NEGATIVE_INFINITY; // we don't like draws
		}
		
		int whiteScore = 0;
		int blackScore = 0;
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(squares[i][j].isOccupied())
					if(squares[i][j].getPiece().getColour() == Piece.WHITE)
						whiteScore += squares[i][j].getPiece().getValue();
					else
						blackScore += squares[i][j].getPiece().getValue();
			}
		
		
		if(colour == Piece.WHITE)
			return whiteScore - blackScore;
		else
			return blackScore - whiteScore;
	}

}
