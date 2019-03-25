/**
 * 
 */

import java.io.PrintWriter;


/**
 * @author Gunnar Atli
 *
 */
public class Main {
	
	public static void main(String[] args) {

/*
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
*/
		GameRules test = new GameRules();
		test.setPawns(8);
		test.setKings(1);
		test.setQueens(1);
		test.setRooks(2);
		test.setBishops(2);
		test.setKnights(2);
		test.startingRows = 1;
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
		new ChessGUI(testBoard,8,8);


}
	
	/** Returns 1 if player1 wins
	 * Returns 0 if draw
	 * Returns -1 if player2 wins
	 */


}
