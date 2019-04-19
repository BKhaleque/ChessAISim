/**
 * 
 */

import java.io.PrintWriter;
import java.util.Scanner;


/*
*@startuml
*
* @enduml
*/
public class Main {
	
	public static void main(String[] args) {

		//Scanner sc = new Scanner(System.in);
		//System.out.println("Please enter the number of generations you would like to evolve for:");
		//int generations = sc.nextInt();
		//System.out.println("Please enter the size of the initial population:");
		//int popSize = sc.nextInt();

		/*

		Evolver e = new Evolver();
		GameRules generatedGame =e.evolve(generations,popSize); //get final evolved child

		try{
			PrintWriter writer = new PrintWriter("rules.txt", "UTF-8");
			writer.println("Pawns: " + generatedGame.getPawns());
			writer.println("Knights: " + generatedGame.getKnights());
			writer.println("Bishops: " + generatedGame.getBishops());
			writer.println("Rooks: " + generatedGame.getRooks());
			writer.println("Queens: " + generatedGame.getQueens());
			writer.println("Kings: " + generatedGame.getKings());
			writer.println("KingLostLast: " + generatedGame.getKingLostLast());
			writer.println("CanStepOnDifferentColor: " + generatedGame.getCanStepOnDifferentColor());
			writer.println("LossOnCheckmate: " + generatedGame.getLossOnCheckmate());
			writer.println("Starting rows: " + generatedGame.startingRows);
			writer.close();//Write child rules to file

		}catch (Exception x){
			System.out.println("Could not write to file!");
		}
	*/

		GameRules test = new GameRules();
		test.setPawns(5);
		test.setKings(1);
		test.setQueens(3);
		test.setRooks(0);
		test.setBishops(0);
		test.setKnights(0);
		test.startingRows = 2;
		Board testBoard = new Board(test,test.startingRows);
		testBoard.canStepOnDifferentColor = false;
		testBoard.lossOnCheckmate = false;
		testBoard.kingLostLast = false;

		Square[][] squares = testBoard.getSquares();
		//for(int i = 0; i <8; i++){
		//	for (int j = 0; j<8; j++){
		//		System.out.println("X: " + squares[i][j].getX());
		//		System.out.println("Y: " + squares[i][j].getY());
		//	}
		//}
		new ChessGUI(testBoard,7,7);


}
	
	/** Returns 1 if player1 wins
	 * Returns 0 if draw
	 * Returns -1 if player2 wins
	 */


}
