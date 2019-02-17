/**
 * 
 */
package com.company;

import GA.Evolver;
import GA.GameRules;
import chessSimulation.Board;
import chessSimulation.Move;
import chessSimulation.player.*;
import chessSimulation.pieces.*;

import java.io.PrintWriter;


/**
 * @author Gunnar Atli
 *
 */
public class Main {
	
	public static void main(String[] args) {

		Evolver e = new Evolver();
		GameRules someGame =e.evolve(); //get final evolved child

		try{
			PrintWriter writer = new PrintWriter("rules.txt", "UTF-8");
			writer.println("Pawns: " + someGame.getPawns());
			writer.println("Knights: " + someGame.getKnights());
			writer.println("Bishops: " + someGame.getBishops());
			writer.println("Rooks: " + someGame.getRooks());
			writer.println("Queens: " + someGame.getQueens());
			writer.println("KingLostLast: " + someGame.getKingLostLast());
			writer.println("CanStepOnDifferentColor: " + someGame.getCanStepOnDifferentColor());
			writer.println("LossOnCheckmate: " + someGame.getLossOnCheckmate());
			writer.close();//Write child rules to file

		}catch (Exception x){
			System.out.println("Could not write to file!");
		}


}
	
	/** Returns 1 if player1 wins
	 * Returns 0 if draw
	 * Returns -1 if player2 wins
	 */


}
