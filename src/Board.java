/**
 * 
 */

//import GameRules;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Gunnar Atli
 *
 */
public class Board {
	public static final int a=0, b=1, c=2, d=3, e=4, f=5, g=6, h=7;
	public boolean kingLostLast;
	public boolean canStepOnDifferentColor;
	public boolean lossOnCheckmate;
	public ArrayList<Piece> pieces;
	public int startingRowsAvailable;
	
	private Square[][] squares;
	/**	
	 * 	 8	r n b q k b n r 
	 *	 7	p p p p p p p p 
	 *	 6	. . . . . . . . 
	 *	 5	. . . . . . . . 
	 *	 4	. . . . . . . . 
	 *	 3	. . . . . . . . 
	 *	 2	P P P P P P P P 
	 *	 1  R N B Q K B N R
	 *    	
	 *    	a b c d e f g h
	 *    
	 * P=pawn, K=king, Q=queen, R=rook, N=knight, B=Bishop
	 * Uppercase is white
	 * Lowercase is black
	 */
	
	public Board(Square[][] squares) {
		this.squares = squares;
	}

	Random r = new Random();
	/**
	 * 
	 */
	public Board(GameRules gameRules, int startingRowsAvailable) {
		// initialize board
		boolean co = Piece.WHITE;

		this.startingRowsAvailable = startingRowsAvailable ;
        this.squares = new Square[8][8];

		//create first row of board
		for(int i = 0; i<8; i++){
			if(i%2 == 0){
				squares[i][0] = new Square(false);
				squares[i][0].setX(i);
				squares[i][0].setY(0);

			}else {
				squares[i][0] = new Square(true);

			}
		}

		//create second row of board

		for(int i = 0; i <8; i++){
			if(i%2 == 0){
				squares[i][1] = new Square(true);
				squares[i][1].setX(i);
				squares[i][1].setY(0);
			}else {
				squares[i][1] = new Square(false);
				squares[i][1].setX(i);
				squares[i][1].setY(0);

			}
		}

        //set middle of board

        for(int i = 2; i < 8; i++) {
            if(i%2==0){
                for(int j = 0; j < 8; j++) {
                    if(j%2 == 0) {
                        squares[j][i] = new Square(false);
                        squares[j][i].setX(j);
                        squares[j][i].setY(i);
                    }
                    else {
                        squares[j][i] = new Square(true);
                        squares[j][i].setX(j);
                        squares[j][i].setY(i);
                    }
                }
            }else {
                for(int j = 0; j < 8; j++) {
                    if(j%2 == 0) {
                        squares[j][i] = new Square(true);
                        squares[j][i].setX(j);
                        squares[j][i].setY(i);
                    }
                    else {
                        squares[j][i] = new Square(false);
                        squares[j][i].setX(j);
                        squares[j][i].setY(i);
                    }

                }
            }

        }


        for(int i = 0; i<=h; i++){
            if(i%2 == 0){
                squares[i][7] = new Square(false);
                squares[i][7].setX(i);
                squares[i][7].setY(7);

            }else {
                squares[i][7] = new Square(true);
                squares[i][7].setX(i);
                squares[i][7].setY(7);
            }
        }

        for(int i = 0; i <8; i++){
            if(i%2 == 0){
                squares[i][6] = new Square(true);
                squares[i][6].setX(i);
                squares[i][6].setY(6);

            }else {
                squares[i][6] = new Square(false);
                squares[i][6].setX(i);
                squares[i][6].setY(6);

            }
        }
		Square oldSquare;
		//initialise rooks in random place
		for(int i = 0; i<gameRules.getRooks(); i++){
			int xVal = r.nextInt(8);
			int yVal = Evolver.getRandomNumberInRange(0,startingRowsAvailable);
			oldSquare = squares[xVal][yVal];
			squares[xVal][yVal] = new Square(new Rook(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);

		}
	//System.out.println("Set rooks!");

		for(int i = 0; i<gameRules.getKnights(); i++){
			int xVal = r.nextInt(8);
			int yVal =Evolver.getRandomNumberInRange(0,startingRowsAvailable);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				 xVal = r.nextInt(8);
				 yVal = Evolver.getRandomNumberInRange(0,startingRowsAvailable);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new Knight(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
		//System.out.println("set Knights!");

		for(int i = 0; i<gameRules.getBishops(); i++){
			int xVal = r.nextInt(8);
			int yVal = Evolver.getRandomNumberInRange(0,startingRowsAvailable);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				xVal = r.nextInt(8);
				yVal = Evolver.getRandomNumberInRange(0,startingRowsAvailable);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new Bishop(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);

		}
		//System.out.println("set Bishops!");

		for(int i = 0; i<gameRules.getQueens(); i++){
			int xVal = r.nextInt(8);
			//System.out.println(xVal);
			int yVal = Evolver.getRandomNumberInRange(0,startingRowsAvailable);
			//System.out.println(yVal);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				xVal = r.nextInt(8);
				yVal = Evolver.getRandomNumberInRange(0,startingRowsAvailable);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new Queen(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
		//System.out.println("set Queens!");


		for(int i = 0; i<gameRules.getKings(); i++){
			int xVal = r.nextInt(8);
			int yVal =Evolver.getRandomNumberInRange(0,startingRowsAvailable);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				xVal = r.nextInt(8);
				yVal = Evolver.getRandomNumberInRange(0,startingRowsAvailable);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new King(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
		//System.out.println("set Kings!");

		//squares[a][0] = new Square(new Rook(co),false);
		//squares[b][0] = new Square(new Knight(co),true);
		//squares[c][0] = new Square(new Bishop(co),false);
		//squares[d][0] = new Square(new Queen(co),true);
		//squares[e][0] = new Square(new King(co),false);
		//squares[f][0] = new Square(new Bishop(co),true);
		//squares[g][0] = new Square(new Knight(co),false);
		//squares[h][0] = new Square(new Rook(co),true);

		for(int i = 0; i<gameRules.getPawns(); i++){
			int xVal = r.nextInt(8);
			int yVal = Evolver.getRandomNumberInRange(0,startingRowsAvailable);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				xVal = r.nextInt(8);
				yVal = Evolver.getRandomNumberInRange(0,startingRowsAvailable);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new Pawn(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
	//	System.out.println("set Pawns!");


		co = Piece.BLACK;

		for(int i = 0; i<gameRules.getRooks(); i++){
			int xVal = r.nextInt(8);
			int yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
			oldSquare = squares[xVal][yVal];
			squares[xVal][yVal] = new Square(new Rook(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
		//System.out.println("set Rooks 2!");

		for(int i = 0; i<gameRules.getKnights(); i++){
			int xVal = r.nextInt(8);
			int yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				xVal = r.nextInt(8);
				yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new Knight(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
		//System.out.println("set Knights 2!");

		for(int i = 0; i<gameRules.getBishops(); i++){
			int xVal = r.nextInt(8);
			int yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				xVal = r.nextInt(8);
				yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new Bishop(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
		//System.out.println("set Bishops 2!");


		for(int i = 0; i<gameRules.getQueens(); i++){
			int xVal = r.nextInt(8);
			int yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				xVal = r.nextInt(8);
				yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new Queen(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
	//	System.out.println("set Queens 2!");


		for(int i = 0; i<gameRules.getKings(); i++){
			int xVal = r.nextInt(8);
			int yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				xVal = r.nextInt(8);
				yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new King(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
		//System.out.println("set Kings 2!");

		//squares[a][7] = new Square(new Rook(co),true);
		//squares[b][7] = new Square(new Knight(co),false);
		//squares[c][7] = new Square(new Bishop(co),true);
		//squares[d][7] = new Square(new Queen(co),false);
		//squares[e][7] = new Square(new King(co),true);
		//squares[f][7] = new Square(new Bishop(co),false);
		//squares[g][7] = new Square(new Knight(co),true);
		//squares[h][7] = new Square(new Rook(co),false);
		for(int i = 0; i<gameRules.getPawns(); i++){
			int xVal = r.nextInt(8);
			int yVal = Evolver.getRandomNumberInRange(7-startingRowsAvailable,7);
			oldSquare = squares[xVal][yVal];
			while (oldSquare.isOccupied()){
				xVal = r.nextInt(8);
				yVal = Evolver.getRandomNumberInRange((7-startingRowsAvailable ),7);
				oldSquare = squares[xVal][yVal];
			}
			squares[xVal][yVal] = new Square(new Pawn(co), oldSquare.WHITE);
			squares[xVal][yVal].setX(xVal);
			squares[xVal][yVal].setY(yVal);
		}
		//System.out.println("set Pawns 2!");

	}


	//public static void main(String[] args) {
		//Board b = new Board();
		//System.out.println(b);
	//}
	
	public String toString() {
		String str = "";
		for(int i = 7; i >= 0; i--) {
			str += (i+1) + "  ";
			for(int j = 0; j < 8; j++) {
				str += squares[j][i] + " ";
			}
			str += "\n";
		}
		
		str += "\n   a b c d e f g h";
		
		return str;
	}
	
	public ArrayList<Move> getMoves(boolean colour) {
		return getMoves(colour, true);
	}
	
	
	/**
	 * Checks if player colour is under check
	 * 
	 * @param colour
	 * @return
	 */
	public boolean isCheck(boolean colour) {



		int x = -1, y = -1;


		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(squares[i][j].isOccupied() &&
						squares[i][j].getPiece().getColour() == colour &&
						squares[i][j].getPiece().toString().equalsIgnoreCase("K") ) {
					x = i; y = j;
				}
			}

		if(kingLostLast){
			for(int i = 0; i<8; i ++){
				for(int j = 0; j<8;j++){
					if(squares[i][j].isOccupied() && squares[i][j].getPiece().getColour() == colour && !squares[i][j].getPiece().toString().equalsIgnoreCase("K")){
						return false;

					}
				}
			}
		}
		// check a move if after making this move the king can be killed (moving into check)
		ArrayList<Move> opponentMoves = getMoves(!colour, false);
		
		// check all opponent moves if they kill king (opponent moves in next round)
		for(int j = 0; j < opponentMoves.size(); j++) {
			if(opponentMoves.get(j).getX2() == x && opponentMoves.get(j).getY2() == y)
				return true;
		}
		
		return false;	
	}
	
	/**
	 * Checks if player colour is under check
	 * 
	 * @param colour
	 * @return
	 */
	public boolean isCheckAfter(boolean colour, ArrayList<Move> moves) {
		
		Square[][] newSquares = getSquaresAfter(moves);

		if(kingLostLast){
			for(int i = 0; i<8; i ++){
				for(int j = 0; j<8;j++){
					if(squares[i][j].isOccupied() && squares[i][j].getPiece().getColour() == colour && !squares[i][j].getPiece().toString().equalsIgnoreCase("K")){
						return false;

					}
				}
			}
		}

		int x = -1, y = -1;
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(newSquares[i][j].isOccupied() &&
						newSquares[i][j].getPiece().getColour() == colour &&
						newSquares[i][j].getPiece().toString().equalsIgnoreCase("K")) {
					x = i; y = j;
				}
			}
		
		// check a move if after making this move the king can be killed (moving into check)
		ArrayList<Move> opponentMoves = getMovesAfter(!colour, moves, false);
		
		// check all opponent moves if they kill king (opponent moves in next round)
		for(int j = 0; j < opponentMoves.size(); j++) {
			if(opponentMoves.get(j).getX2() == x && opponentMoves.get(j).getY2() == y)
				return true;
		}
		
		return false;	
	}
	
	public ArrayList<Move> getMoves(boolean colour, boolean checkCheck) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				if(squares[i][j].isOccupied() &&
						squares[i][j].getPiece().getColour() == colour) {
					moves.addAll(squares[i][j].getPiece().getMoves(this, i, j));
				}
			}
		
		// check if move is valid (must not be check after move) and throw away erroneous moves
		if(checkCheck) {
			// find king (of correct colour)
			int x = -1, y = -1;
			for(int i = 0; i < 8; i++)
				for(int j = 0; j < 8; j++) {
					if(squares[i][j].isOccupied() &&
							squares[i][j].getPiece().getColour() == colour &&
							squares[i][j].getPiece().toString().equalsIgnoreCase("K")) {
						x = i; y = j;
					}
				}
			
			ArrayList<Move> removeThese = new ArrayList<Move>();
			for(int i = 0; i < moves.size(); i++) {
				// check a move if after making this move the king can be killed (moving into check)
				ArrayList<Move> checkThis = new ArrayList<Move>(moves.subList(i, i+1));
				ArrayList<Move> opponentMoves = getMovesAfter(!colour, checkThis, false);
				
				int xUpdated = x, yUpdated = y;
				if(checkThis.get(0).getX1() == x && checkThis.get(0).getY1() == y) { // get updated king position
					xUpdated = checkThis.get(0).getX2();
					yUpdated = checkThis.get(0).getY2();
				}
				
				// check all opponent moves if they kill king (opponent moves in next round)
				for(int j = 0; j < opponentMoves.size(); j++) {
					if(opponentMoves.get(j).getX2() == xUpdated && opponentMoves.get(j).getY2() == yUpdated)
						removeThese.add(checkThis.get(0));
				}
			}
			
			moves.removeAll(removeThese); // remove invalid moves
		}
		
		return moves;
	}
	
	public ArrayList<Move> getMovesAfter(boolean color, ArrayList<Move> moves) {
		return getMovesAfter(color, moves, true);
	}
	
	public ArrayList<Move> getMovesAfter(boolean color, ArrayList<Move> moves, boolean checkCheck) {
		
		Square[][] temp = new Square[8][8];
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				temp[x][y] = new Square(this.squares[x][y]);
				temp[x][y].setX(x);
				temp[x][y].setY(y);
			}
		}
		
		Board b = new Board(temp);
		b.kingLostLast = this.kingLostLast;
		b.canStepOnDifferentColor = this.canStepOnDifferentColor;
		b.lossOnCheckmate = this.lossOnCheckmate;

		for(int i = 0; i < moves.size(); i++)
			b.makeMove(moves.get(i));
		
		ArrayList<Move> futureMoves = b.getMoves(color, checkCheck);
		
		return futureMoves;
	}
	
	public Square[][] getSquaresAfter(ArrayList<Move> moves) {
		
		Square[][] temp = new Square[8][8];
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				temp[x][y] = new Square(this.squares[x][y]);
				temp[x][y].setX(x);
				temp[x][y].setY(y);
			}
		}
		
		Board b = new Board(temp);

		b.kingLostLast = this.kingLostLast;
		b.canStepOnDifferentColor = this.canStepOnDifferentColor;
		b.lossOnCheckmate = this.lossOnCheckmate;

		for(int i = 0; i < moves.size(); i++)
			b.makeMove(moves.get(i));
		
		Square[][] temp2 = new Square[8][8];
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				temp2[x][y] = new Square(b.getSquare(x, y));
				temp2[x][y].setX(x);
				temp2[x][y].setY(y);
			}
		}
		
		return temp2;
	}
	
	/**
	 * @param m
	 * @return -1 if black wins
	 * 			1 if white wins
	 * 			0 if game continues
	 */
	public int makeMove(Move m) {
		Square oldSquare1 = squares[m.getX1()][m.getY1()];
		Square oldSquare2 = squares[m.getX2()][m.getY2()];


		squares[m.getX2()][m.getY2()] = squares[m.getX1()][m.getY1()];
		squares[m.getX2()][m.getY2()].setX(oldSquare2.getX());
		squares[m.getX2()][m.getY2()].setY(oldSquare2.getY());
		squares[m.getX1()][m.getY1()] = new Square(oldSquare1.WHITE);
		squares[m.getX1()][m.getY1()].setX(oldSquare1.getX());
		squares[m.getX1()][m.getY1()].setY(oldSquare1.getY());

		if(!canStepOnDifferentColor){
			if(squares[m.getX2()][m.getY2()].WHITE == !oldSquare1.getPiece().getColour() ){
				return -1;
			}
		}

		if(m.isCastling()) {
			if(m.getX2() == g && m.getY2() == 0) {
				squares[f][0] = squares[h][0];
				squares[h][0] = new Square(oldSquare2.WHITE);
				squares[h][0].setY(0);
				squares[h][0].setX(h);
			}
			if(m.getX2() == c && m.getY2() == 0) {
				squares[d][0] = squares[a][0];
				squares[a][0] = new Square(oldSquare2.WHITE);
				squares[a][0].setY(0);
				squares[a][0].setX(a);
			}
			if(m.getX2() == g && m.getY2() == 7) {
				squares[f][7] = squares[h][7];
				squares[h][7] = new Square(oldSquare2.WHITE);
				squares[h][7].setY(0);
				squares[h][7].setX(h);
			}
			if(m.getX2() == c && m.getY2() == 7) {
				squares[d][7] = squares[a][7];
				squares[a][7] = new Square(oldSquare2.WHITE);
				squares[a][7].setY(0);
				squares[a][7].setX(a);
			}
		}
		
		// pawn at top?

			if (oldSquare1.getPiece().toString().equals("P") && m.getY2() == 7) {
				squares[m.getX2()][m.getY2()] = new Square(new Queen(Piece.WHITE), oldSquare2.WHITE);
				squares[m.getX2()][m.getY2()].setX(m.getX2());
				squares[m.getX2()][m.getY2()].setY(m.getY2());
			}
			if (oldSquare1.getPiece().toString().equals("p") && m.getY2() == 0) {
				squares[m.getX2()][m.getY2()] = new Square(new Queen(Piece.BLACK), oldSquare2.WHITE);
				squares[m.getX2()][m.getY2()].setX(m.getX2());
				squares[m.getX2()][m.getY2()].setY(m.getY2());
			}





		return 0;
	}


	
	public Square getSquare(int x, int y) {
		return squares[x][y];
	}

	public boolean isKingLostLast() {
		return kingLostLast;
	}

	public void setKingLostLast(boolean kingLostLast) {
		this.kingLostLast = kingLostLast;
	}

	public boolean isCanStepOnDifferentColor() {
		return canStepOnDifferentColor;
	}

	public void setCanStepOnDifferentColor(boolean canStepOnDifferentColor) {
		this.canStepOnDifferentColor = canStepOnDifferentColor;
	}

	public boolean isLossOnCheckmate() {
		return lossOnCheckmate;
	}

	public void setLossOnCheckmate(boolean lossOnCheckmate) {
		this.lossOnCheckmate = lossOnCheckmate;
	}


	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}


}
