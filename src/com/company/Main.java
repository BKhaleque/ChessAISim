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


/**
 * @author Gunnar Atli
 *
 */
public class Main {
	
	public static void main(String[] args) {

		Evolver e = new Evolver();
		GameRules someGame =e.evolve();
		System.out.println(someGame);


}
	
	/** Returns 1 if player1 wins
	 * Returns 0 if draw
	 * Returns -1 if player2 wins
	 */


}
