package GUI;

import chessSimulation.Board;
import chessSimulation.Move;
import chessSimulation.Square;
import chessSimulation.pieces.Piece;

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
    private final Board board;
    private Square source;
    private Square dest;
    private Piece movedPiece;
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400,350);
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);
    private  static String chessImgPath = "Art/";

    public ChessGUI(Board board) {
        this.gameFrame = new JFrame("JChess");
        final JMenuBar tableMenuBar = popMenuBar();
        this.gameFrame.setLayout(new BorderLayout());

        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.boardPanel = new BoardPanel();
        this.board = board;
        this.boardPanel.setVisible(true);
        this.gameFrame.add(this.boardPanel);
        this.gameFrame.setVisible(true);



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
            int row = 0;
            for(int j = 0; j<8;j++) {
                //row++;
                for (int i = 0; i <8; i++) {
                    final TilePanel tp = new TilePanel(this, i);
                    this.tiles.add(tp);
                    tp.assignColor(row);
                    add(tp);
                }
                row++;
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);

            validate();
        }

        //redraws/draws all components on board
        public void drawBoard(final Board board){
            removeAll();
            for(final TilePanel tp: tiles){
                tp.drawTile(board,tp.row);
                add(tp);
            }
            validate();
        }



    }

    private class TilePanel extends JPanel{
        private final int col;
        private int row;

        TilePanel(final BoardPanel bp, final int col)
        {
            super(new GridLayout());
            this.col = col;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTilePieceIcon(board);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {


                    if(isRightMouseButton(e)){
                        //on first click
                        source = null;
                        dest = null;
                        movedPiece = null;

                    }else if(isLeftMouseButton(e)){
                        if(source == null){
                            source = board.getSquare(row,col);
                            movedPiece = source.getPiece();
                            if(movedPiece == null){
                                source = null;
                            }
                        }else {
                            //second click
                            dest = board.getSquare(row,col);
                            final Move move = new Move(source.getX(),source.getY(), dest.getX(),dest.getY());
                            board.makeMove(move);
                        }

                        source = null;
                        dest=null;
                        movedPiece=null;
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            bp.drawBoard(board);
                        }
                    });

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

        public void drawTile(final Board board, int row){
            assignColor(row);
            assignTilePieceIcon(board);
            validate();
            repaint();
        }
        private void assignTilePieceIcon(final Board board){
            this.removeAll();

            for (int x =0; x<8; x++){
                for(int y =0; y<8;y++){
                    if (board.getSquare(x,y).isOccupied()){
                        try{
                            if(board.getSquare(x,y).WHITE){
                                final BufferedImage img = ImageIO.read(new File(chessImgPath + "white_"+board.getSquare(x,y).getPiece().toString() + ".png") );
                                add(new JLabel(new ImageIcon(img)));

                            }else {
                                final BufferedImage img = ImageIO.read(new File(chessImgPath + "black_"+board.getSquare(x,y).getPiece().toString() + ".png") );
                                add(new JLabel(new ImageIcon(img)));

                            }
                        }catch (IOException e){
                            System.out.println("Image not found!");
                        }
                    }
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



    }
}