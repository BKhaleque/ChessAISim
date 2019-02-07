/**
 * 
 */
package com.company;

import chessSimulation.Board;
import chessSimulation.Move;
import chessSimulation.player.*;
import chessSimulation.pieces.*;


/**
 * @author Gunnar Atli
 *
 */
public class Main {
	
	public static void main(String[] args) {
		int iter = 1;
		float player1Score = 0;
		int draw = 0;
		for(int i = 0; i < iter; i++) {
			Board board = new Board();
			board.kingLostLast = true;
			//System.out.println(board.toString());
			Player player1 = new AlphaBetaPlayer(Piece.WHITE,3);
			//Player player2 = new RandomPlayer(Piece.BLACK);
			Player player2 = new RandomPlayer(Piece.BLACK);
			//Player player2 = new DeterministicPlayer(Piece.BLACK);
			
			int winner = play(player1, player2, board);

			if(winner == 1)
				player1Score++;
			else if(winner == 0) {
				player1Score += 0.5f;
				draw++;
			}else {
				player1Score--;
			}
			System.out.println(winner);

		}
		
		System.out.println(player1Score);
	}
	
	/** Returns 1 if player1 wins
	 * Returns 0 if draw
	 * Returns -1 if player2 wins
	 */
	public static int play(Player player1, Player player2, Board b) {
		Move move;
		int result;
		int turn = 0;
		while(true) {
			if(turn++ > 2000000)
				return 0;
			
			move = player1.getNextMove(b);
			if(move == null && b.isCheck(player1.getColour()) && b.lossOnCheckmate) // check and can't move
				return -1;

			if(move == null && b.isCheck(player1.getColour()) && !b.lossOnCheckmate) // check and can't move
				return 1;

			if(move == null) // no check but can't move
				return 0;
			
			result = b.makeMove(move);
			System.out.println(b);
			//if(result == -1) return (player1.getColour() == Piece.WHITE) ? -1 : 1; // black wins
			//if(result == 1) return (player1.getColour() == Piece.WHITE) ? 1 : -1; // white wins


			move = player2.getNextMove(b);
			if(move == null && b.isCheck(player2.getColour()) && b.lossOnCheckmate && !b.kingLostLast) // check and can't move
				return 1;

			if(move == null && b.isCheck(player2.getColour()) && !b.lossOnCheckmate && !b.kingLostLast)
				return -1;

			if(move == null) // no check but can't move
				return 0;
			
			result = b.makeMove(move);
			System.out.println(b);
			//if(result == -1) return (player1.getColour() == Piece.WHITE) ? 1 : -1; // black wins
			//if(result == 1) return (player1.getColour() == Piece.WHITE) ? -1 : 1; // white wins
			
		} 
	}

}
