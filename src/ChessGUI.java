import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class ChessGUI {
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    public Board board;
    private Square source;
    private Square dest;
    private Piece movedPiece;
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    private  static String chessImgPath = "Art/";
    private boolean currentColor = Piece.WHITE;
    private AlphaBetaPlayer opponent = new AlphaBetaPlayer(Piece.BLACK,0);
    private   int rows;
    private   int cols;
    boolean sucessfulMove;



    public ChessGUI(Board board, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.gameFrame = new JFrame("JChess");
        final JMenuBar tableMenuBar = popMenuBar();
        this.gameFrame.setLayout(new BorderLayout());

        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.boardPanel = new BoardPanel();
        this.boardPanel.drawBoard(board);
        this.board = board;
        this.boardPanel.setVisible(true);
        this.gameFrame.add(this.boardPanel);
        this.gameFrame.setVisible(true);


        /*
        while(!board.isCheck(currentColor)){
            if(currentColor == Piece.BLACK){
                System.out.println("AI Calculating move...");
                Move move = opponent.getNextMove(board);
                board.makeMove(move);
                currentColor = Piece.WHITE;

                this.boardPanel.drawBoard(board);
                System.out.println(board.toString());
            }else {
                continue;
            }
        }

    */
    }

    private JMenuBar popMenuBar(){
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu(){
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem open = new JMenuItem("Open File"); //menu item open file to load previously generated game
         open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.printf("Opening file...");
            }
        });

         final JMenuItem exit = new JMenuItem("Exit");
         exit.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.exit(0);
             }
         });
         fileMenu.add(exit);
        return fileMenu;
    }

    private class BoardPanel extends JPanel{
        final List<TilePanel> tiles = new ArrayList<>();
        BoardPanel(){
            super( new GridLayout(8,8));
            for(int i = cols; i>=0;i--) {
                //row++;
                for (int j = 0; j <8; j++) {
                    final TilePanel tp = new TilePanel(this, i);
                    this.tiles.add(tp);
                    tp.assignColor(j);
                    add(tp);
                }
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);

            validate();
        }

        //redraws/draws all components on board
        public void drawBoard(final Board board){
            removeAll();
            System.out.println(board.toString());
            for(final TilePanel tp: tiles){
                tp.drawTile(board,tp.row,tp.col);
                add(tp);
            }
            validate();
        }



    }

    private class TilePanel extends JPanel{
        private int col;
        private int row;

        TilePanel(final BoardPanel bp, final int col)
        {
            super(new GridLayout());
            this.col = col;
            setPreferredSize(TILE_PANEL_DIMENSION);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {

                    //human player is white, only allow mouse clicks when current colour is white
                    if (currentColor == Piece.WHITE) {
                        sucessfulMove = false;

                        if (isRightMouseButton(e)) {
                            //on first click
                            source = null;
                            dest = null;
                            movedPiece = null;
                        } else if (isLeftMouseButton(e)) {
                            if (source == null) {
                                source = board.getSquare(row, col);
                                movedPiece = source.getPiece();
                                if (movedPiece == null) {
                                    source = null;
                                }
                            } else {
                                //second click
                                dest = board.getSquare(row, col);
                                Move move = new Move(source.getX(), source.getY(), dest.getX(), dest.getY());
                                if (checkIfValidMove(source, move)) {
                                    board.makeMove(move);
                                    System.out.println("Valid move!");
                                    sucessfulMove = true;
                                } else{
                                    System.out.println("Invalid move");
                                    source = null;
                                    dest = null;
                                    movedPiece = null;
                                }


                                source = null;
                                dest = null;
                                movedPiece = null;
                                if(sucessfulMove){
                                    currentColor = Piece.BLACK;

                                }
                                if(board.isCheck(currentColor) && board.getMoves(currentColor).size() == 0){
                                    System.out.println("Game over black has lost!");
                                    System.exit(0);

                                }

                            }

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    bp.removeAll();

                                    bp.drawBoard(board);
                                    bp.setVisible(true);
                                    bp.repaint();
                                    bp.revalidate();

                                }
                            });


                        }

                   }else {
                        if (isLeftMouseButton(e)) {
                            board.makeMove(opponent.getNextMove(board));
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {

                                    bp.removeAll();

                                    bp.drawBoard(board);
                                    bp.setVisible(true);
                                    bp.repaint();
                                    bp.revalidate();

                                }
                            });
                            currentColor = Piece.WHITE;

                            if(board.isCheck(currentColor) && board.getMoves(currentColor).size() == 0){
                                System.out.println("Game over white has lost!");
                                System.exit(0);

                            }
                        }
                    }
                }

                @Override
                public void mousePressed(final MouseEvent e) {

                }

                @Override
                public void mouseReleased(final MouseEvent e) {

                }

                @Override
                public void mouseEntered(final MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            validate();
        }

        public void assignColor(int row){
            if(row ==0 ||row ==2||row ==4||row ==6){
                setBackground(this.col %2==0 ? Color.WHITE:Color.BLACK);

            }else if(row ==1||row ==3||row ==5||row ==7){
                setBackground(this.col %2!=0 ? Color.WHITE:Color.BLACK);
            }
            this.row=row;
        }

        public void drawTile(final Board board, int row,int col){
            assignColor(row);
            this.col = col;
            this.row = row;
            assignTilePieceIcon(board);
            validate();
            repaint();
        }

        public boolean checkIfValidMove(Square source, Move move){

            ArrayList<Move> validMoves = source.getPiece().getMoves(board,source.getX(),source.getY());
            for(Move move1: validMoves){
                if(move1.equals(move)) {
                    return true;
                }
            }
        return false;
        }
        private void assignTilePieceIcon(final Board board){
            this.removeAll();

                    if (board.getSquare(this.row,this.col).isOccupied()){
                        try{
                            //System.out.println(board.toString());
                            if(board.getSquare(this.row,this.col).getPiece().getColour() ){
                                final BufferedImage img = ImageIO.read(new File(chessImgPath + "white_"+board.getSquare(this.row,this.col).getPiece().toString() + ".png") );
                                add(new JLabel(new ImageIcon(img)));

                            }else {
                                final BufferedImage img = ImageIO.read(new File(chessImgPath + "black_"+board.getSquare(this.row,this.col).getPiece().toString() + ".png") );
                                add(new JLabel(new ImageIcon(img)));

                            }
                        }catch (IOException e){
                            System.out.println("Image not found!");
                        }
                    }


        }

        public void highlightMoves(){
            //for(final Move move: board.getMoves()){
              //  if(move.getY2() == this.row && move.getX2() == this.col)
                //{
//
              //  }
           // }
        }


        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }
    }
}