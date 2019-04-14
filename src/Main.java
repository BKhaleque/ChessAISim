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
		GameRules someGame =e.evolve(100,10); //get final evolved child

		try{
			PrintWriter writer = new PrintWriter("rules.txt", "UTF-8");
			writer.println("Pawns: " + someGame.getPawns());
			writer.println("Knights: " + someGame.getKnights());
			writer.println("Bishops: " + someGame.getBishops());
			writer.println("Rooks: " + someGame.getRooks());
			writer.println("Queens: " + someGame.getQueens());
			writer.println("Kings: " + someGame.getKings());
			writer.println("KingLostLast: " + someGame.getKingLostLast());
			writer.println("CanStepOnDifferentColor: " + someGame.getCanStepOnDifferentColor());
			writer.println("LossOnCheckmate: " + someGame.getLossOnCheckmate());
			writer.println("Starting rows: " + someGame.startingRows);
			writer.close();//Write child rules to file

		}catch (Exception x){
			System.out.println("Could not write to file!");
		}
	*/
		GameRules test = new GameRules();
		test.setPawns(6);
		test.setKings(1);
		test.setQueens(1);
		test.setRooks(0);
		test.setBishops(2);
		test.setKnights(4);
		test.startingRows = 3;
		Board testBoard = new Board(test,test.startingRows);
		testBoard.canStepOnDifferentColor = true;
		testBoard.lossOnCheckmate = true;
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
