
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the number of generations you would like to evolve for:");
		int generations = sc.nextInt();
		System.out.println("Please enter the size of the initial population:");
		int popSize = sc.nextInt();

		GameRules someGame;


		Evolver e = new Evolver();
		if(generations ==0 && popSize == 0 ){
			someGame = e.generateGame();
		}else {
			someGame =e.evolve(generations,popSize); //get final evolved child
		}

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

		GameRules exampleGame1 = new GameRules();
		exampleGame1.pawns = 5;
		exampleGame1.kings = 1;
		exampleGame1.bishops = 0;
		exampleGame1.queens = 3;
		exampleGame1.startingRows = 2;
		exampleGame1.knights = 0;
		exampleGame1.rooks = 0;
		exampleGame1.lossOnCheckmate = false;
		exampleGame1.kingLostLast = false;
		GameRules exampleGame2 = new GameRules();
		exampleGame2.pawns = 0;
		exampleGame2.kings = 4;
		exampleGame2.bishops = 0;
		exampleGame2.queens = 4;
		exampleGame2.startingRows = 3  ;
		exampleGame2.knights = 0;
		exampleGame2.rooks = 0;
		exampleGame2.lossOnCheckmate = false;
		exampleGame2.kingLostLast = false;


		Board testBoard = new Board(exampleGame1,exampleGame1.startingRows);
		testBoard.lossOnCheckmate = exampleGame1.lossOnCheckmate;
		testBoard.kingLostLast = exampleGame1.kingLostLast;

		new ChessGUI(testBoard,7,7);


}

}
