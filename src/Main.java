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
		/*

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the number of generations you would like to evolve for:");
		int generations = sc.nextInt();
		System.out.println("Please enter the size of the initial population:");
		int popSize = sc.nextInt();


		Evolver e = new Evolver();
		GameRules someGame =e.evolve(generations,popSize); //get final evolved child

		//Write child rules to file

		try{
			PrintWriter writer = new PrintWriter("rules.txt", "UTF-8");
			writer.println("Pawns: " + someGame.getPawns());
			writer.println("Knights: " + someGame.getKnights());
			writer.println("Bishops: " + someGame.getBishops());
			writer.println("Rooks: " + someGame.getRooks());
			writer.println("Queens: " + someGame.getQueens());
			writer.println("Kings: " + someGame.getKings());
			writer.println("KingLostLast: " + someGame.getKingLostLast());
			writer.println("LossOnCheckmate: " + someGame.getLossOnCheckmate());
			writer.println("Starting rows: " + someGame.startingRows);
			writer.close();
			System.out.println("Pawns: " + someGame.getPawns());
			System.out.println("Knights: " + someGame.getKnights());
			System.out.println("Bishops: " + someGame.getBishops());
			System.out.println("Queens: " + someGame.getQueens());
			System.out.println("Kings: " + someGame.getKings());
			System.out.println("KingLostLast: " + someGame.getKingLostLast());
			System.out.println("LossOnCheckmate: " + someGame.getLossOnCheckmate());
			System.out.println("Starting rows: " + someGame.startingRows);

		}catch (Exception x){
			System.out.println("Could not write to file!");
		}
		*/



		GameRules test = new GameRules();
		test.setPawns(1);
		test.setKings(1);
		test.setQueens(1);
		test.setRooks(1);
		test.setBishops(1);
		test.setKnights(1);
		test.startingRows = 3;
		Board testBoard = new Board(test,test.startingRows);
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
