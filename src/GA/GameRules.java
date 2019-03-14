package GA;


public class GameRules {
    int kings;



    int totalPieces;
    int pawns;
    int queens;
    int knights;
    int rooks;
    int bishops;
    boolean canStepOnDifferentColor;
    boolean lossOnCheckmate;
    boolean kingLostLast;
   // boolean paralysedOnAttack;
    boolean feasible;
    float fitness;

    float probability;

    public int startingRows;

    public GameRules(int pawns, int queens, int knights, int rooks, int bishops, boolean canStepOnDifferntColor, boolean lossOnCheckmate, boolean kingLostLast, boolean paralysedOnAttack, boolean feasible) {
        this.pawns = pawns;
        this.queens = queens;
        this.knights = knights;
        this.rooks = rooks;
        this.bishops = bishops;
        this.canStepOnDifferentColor = canStepOnDifferntColor;
        this.lossOnCheckmate = lossOnCheckmate;
        this.kingLostLast = kingLostLast;
        //this.paralysedOnAttack = paralysedOnAttack;
        this.feasible = feasible;
        this.totalPieces = 0;

    }

    public GameRules() {
        this.totalPieces = 0;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(int totalPieces) {
        this.totalPieces = totalPieces;
    }

    public int getPawns() {
        return pawns;
    }

    public void setPawns(int pawns) {
        this.totalPieces -=this.pawns;
        this.pawns = pawns;
        this.totalPieces +=pawns;
    }

    public int getQueens() {
        return queens;
    }

    public void setQueens(int queens) {
        this.totalPieces -=this.queens;

        this.queens = queens;
        this.totalPieces +=queens;
    }

    public int getKnights() {
        return knights;
    }

    public void setKnights(int knights) {
        this.totalPieces -=this.knights;

        this.knights = knights;
        this.totalPieces+=knights;
    }

    public int getRooks() {
        return rooks;
    }

    public void setRooks(int rooks) {
        this.totalPieces -=this.rooks;

        this.rooks = rooks;
        this.totalPieces+=rooks;
    }

    public int getBishops() {
        return bishops;
    }

    public void setBishops(int bishops) {
        this.totalPieces -=this.bishops;

        this.bishops = bishops;
        this.totalPieces+=bishops;
    }

    public int getKings() {
        return kings;
    }

    public void setKings(int kings) {
        this.totalPieces -=this.kings;

        this.kings = kings;
        this.totalPieces +=totalPieces;
    }

    public Boolean getCanStepOnDifferentColor() {
        return canStepOnDifferentColor;
    }

    public void setCanStepOnDifferentColor(Boolean canStepOnDifferentColor) {
        this.canStepOnDifferentColor = canStepOnDifferentColor;
    }

    public Boolean getLossOnCheckmate() {
        return lossOnCheckmate;
    }

    public void setLossOnCheckmate(Boolean lossOnCheckmate) {
        this.lossOnCheckmate = lossOnCheckmate;
    }

    public Boolean getKingLostLast() {
        return kingLostLast;
    }

    public void setKingLostLast(Boolean kingLostLast) {
        this.kingLostLast = kingLostLast;
    }

   // public Boolean getParalysedOnAttack() {
    //    return paralysedOnAttack;
   // }

  //  public void setParalysedOnAttack(Boolean paralysedOnAttack) {
   //     this.paralysedOnAttack = paralysedOnAttack;
   // }

    public Boolean getFeasible() {
        return feasible;
    }

    public void setFeasible(Boolean feasible) {
        this.feasible = feasible;
    }

/*
    public void determineQuality(){
        //create new mcts players and have them play against each other in a chess simulation
        MCTSPlayer player1 = new MCTSPlayer(0);
        MCTSPlayer player2 = new MCTSPlayer(1);

        Game game;
        for(int i = 0 ; i<1; i++){
            game = new Game(this,player1,player2);

            //get total moves, get games won, get time of each game;
        }



        //measure no of moves/number of games ome

    }

    */

    //Game needs state with all current pieces, checkmate status and etc for mcts controller to simulate


    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public int getStartingRows() {
        return startingRows;
    }

    public void setStartingRows(int startingRows) {
        this.startingRows = startingRows;
    }
}
