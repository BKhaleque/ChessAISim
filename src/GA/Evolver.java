package GA;

import chessSimulation.Board;
import chessSimulation.Move;
import chessSimulation.Square;
import chessSimulation.pieces.Piece;
import chessSimulation.player.AlphaBetaPlayer;
import chessSimulation.player.Player;
import chessSimulation.player.RandomPlayer;
import GA.GameRules;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Evolver {

    Random randomGenerator = new Random();



    public GameRules evolve() {
        //Declare arraylist population of feasible and infeasible game objects
        ArrayList<GameRules> feasible = new ArrayList<>();
        ArrayList<GameRules> infeasible = new ArrayList<>();
        ArrayList<GameRules> gen0 = new ArrayList<>();
        //declare how many generations to evolve for
        int generations = 1000;
        float minFitness = 0.4f;
        int initialPopSize = 100;

        System.out.println("Making initial pop!");
        //declare rulespace of inital games for generation 0 in game objects, traits must be assigned with random values and add to gen0 population
        for (int i = 0; i <initialPopSize; i++) {
            gen0.add(generateGame());
            //determine generated games fitness
            gen0.get(i).fitness = determineFitness(gen0.get(i));
        }
        System.out.println("Determined inital pop fitness!");


        GameRules gen0Parent1 = pickParent1(gen0);
        GameRules gen0Parent2 = pickParent2(gen0,gen0Parent1);

        GameRules child = mate(gen0Parent1,gen0Parent2);

        if(child.getKings() ==0){
            child.feasible = false;
            infeasible.add(child);
        } //If there are no kings then a game is infeasible

        for(int i =0; i < gen0.size(); i++){
            if(gen0.get(i).fitness >= 0.5f){
                feasible.add(gen0.get(i));
            }
            if(gen0.get(i).fitness <= 0.5f){
                infeasible.add(gen0.get(i));
            }
        }

        System.out.println("Created first infeasible and feasible pop!");

        GameRules feasibleChild = new GameRules();
        feasibleChild.fitness = 0; //init feasible child fitness for comparasion at end
        for(int i = 0; i<generations; i ++){
            ///put in s loop to generate new population
            ArrayList<GameRules> newFeasiblePop = new ArrayList<>();
            ArrayList<GameRules> newInfeasiblePop = new ArrayList<>();
            System.out.println("Starting generation: " + i);
            System.out.println("Current feasible pop size: "+ feasible.size());
            System.out.println("Current infeasible pop size: "+ infeasible.size());
            while (feasible.size() <2){
                GameRules gameRules = generateGame();
                feasible.add(gameRules);
            }
            while ( infeasible.size() <2){
                GameRules gameRules = generateGame();
                infeasible.add(gameRules);
            }

            for(int z = 0; z<10; z++) {
                GameRules feasibleParent1 = pickParent1(feasible); //remember to add elitism (copying small portion of fittest individuals into next generation) and tournament for parent selection by getting 2 or 3 objects and comparing fitness
                GameRules feasibleParent2 = pickParent2(feasible, feasibleParent1);
                feasibleChild = mate(feasibleParent1, feasibleParent2);
                GameRules infeasibleParent1 = pickParent1(infeasible); //remember to add elitism (copying small portion of fittest individuals into next generation) and tournament for parent selection by getting 2 or 3 objects and comparing fitness
                GameRules infeasibleParent2 = pickParent2(infeasible, infeasibleParent1);
                GameRules infeasibleChild = mate(infeasibleParent1, infeasibleParent2);
                feasibleChild.fitness = determineFitness(feasibleChild);
                infeasibleChild.fitness = determineFitness(infeasibleChild);

                if(child.getKings() ==0){
                    child.feasible = false;
                    infeasible.add(child);
                } //If there are no kings then a game is infeasible

                if (feasibleChild.fitness  > 0.5f) {
                    newFeasiblePop.add(feasibleChild);
                } else {
                    newInfeasiblePop.add(feasibleChild);
                }

                if (infeasibleChild.fitness <= 0.5f) {
                    newFeasiblePop.add(infeasibleChild);
                } else {
                    newInfeasiblePop.add(infeasibleChild);
                }

                //elitism checks, take only top value N, each time






            }
            newFeasiblePop.add(feasible.get(0)); //elitism carry 0th individual each time
            newInfeasiblePop.add(infeasible.get(0)); //elitism
            System.out.println("Making new pops!");
            feasible = newFeasiblePop;
            infeasible = newInfeasiblePop;
            float avgFeasibleFitness = determineAvgFitness(feasible);
            float avgInFeasibleFitness = determineAvgFitness(infeasible);
            System.out.println("The average fitness of the feasible population in generation " + i + " is " + avgFeasibleFitness);
            System.out.println("The average fitness of the infeasible population in generation " + i + " is " + avgInFeasibleFitness);
            //if infeasible 2 low, generate random games to add to infeasible population

            //take best individual and carry over, make other 9 through mating


        }

        for(int i=0; i<feasible.size(); i++){
            if(feasibleChild.fitness < feasible.get(i).fitness){
                feasibleChild = feasible.get(i);
            }
        }

        return feasibleChild;

    }

    public GameRules pickParent1(ArrayList<GameRules> pop){
        Random r = new Random();
        GameRules game1 = pop.get(r.nextInt(pop.size()));
        GameRules game2 = pop.get(r.nextInt(pop.size()));
        GameRules game3 = pop.get(r.nextInt(pop.size()));
        if(game1.fitness>= game2.fitness && game1.fitness>=game3.fitness){
            return game1;
        }else if(game2.fitness>= game1.fitness && game2.fitness>=game3.fitness){
            return game2;
        }

            return game3;






    }
    public GameRules pickParent2(ArrayList<GameRules> pop, GameRules parent1){
        Random r = new Random();
        GameRules game1 = pop.get(r.nextInt(pop.size()));
        GameRules game2 = pop.get(r.nextInt(pop.size()));
        GameRules game3 = pop.get(r.nextInt(pop.size()));
        if (pop.size() > 0) {
            while (game1.equals(parent1) || game2.equals(parent1) || game3.equals(parent1)) {
                game1 = pop.get(r.nextInt(pop.size()));
                game2 = pop.get(r.nextInt(pop.size()));
                game3 = pop.get(r.nextInt(pop.size()));


            }
        }

        if(game1.fitness>= game2.fitness && game1.fitness>=game3.fitness){
            return game1;
        }else if(game2.fitness>= game1.fitness && game2.fitness>=game3.fitness){
            return game2;
        }

            return game3;

    }

    public float determineFitness(GameRules gameRules){
        //add piece count  stable piece count higher fitness
        //if oe plyer loses a lot of pieces quickly
        //play with a mix of strong and weap bots, strong vs strong must be more ven, weak vs strong must favour strong
        //maybe view move list each turn determine avg number of actions through all turns, higher avg determines higher ftiness
        int iter = 4;
        float player1Score = 0;
        int draw = 0;
        int whiteWins = 0;
        int blackWins = 0;
        float fitness = 0;
        int numPieces = 0;

        for(int i = 0; i < iter; i++) {
            Board board = new Board(gameRules);

            long startTime = System.currentTimeMillis();
            board.kingLostLast = gameRules.kingLostLast;
            board.canStepOnDifferentColor = gameRules.canStepOnDifferentColor;
            board.lossOnCheckmate = gameRules.lossOnCheckmate;

           // System.out.println("Playing!");
            Player player1 = new AlphaBetaPlayer(Piece.WHITE,1);
            //Player player2 = new RandomPlayer(Piece.BLACK);
            Player player2 = new AlphaBetaPlayer(Piece.BLACK,1);
            //Player player2 = new DeterministicPlayer(Piece.BLACK);
            int noOfMoves = 0;
            int winner = play(player1, player2, board,noOfMoves);
            if(noOfMoves<100){
                fitness +=0.1f;
            }else {
                fitness -=0.2f;
            }

            if(winner == 1)
                player1Score++;
            whiteWins++;
             if(winner == 0) {
                player1Score += 0.5f;
                draw++;
            }else {
                player1Score--;
                blackWins ++;
            }
            long endTime = System.currentTimeMillis() -startTime;
             if (endTime >20000){
                 fitness -=0.25;
             }
            numPieces = checkNumPieces(board);


        }

        if (blackWins == whiteWins){
            fitness +=0.5f;
        }
        if(draw>blackWins+whiteWins){
            fitness +=0.1f;
        }
        if(whiteWins>blackWins||blackWins>whiteWins){
            fitness +=0.05f;
        }
        //remember to keep a track of time

        //check no of killer moves
        Board board = new Board(gameRules);
        int killerMoves = board.checkKillerMoves();

        System.out.println("The fitness of this generated game is: " + fitness);
        return fitness;

    }

    public int checkNumPieces(Board board){
        Square[][] squares = board.getSquares();
        int numOfPieces = 0;
        for (int i=0; i<squares.length; i++){
            for (int j = 0; j<squares[i].length; j++){
                if (squares[i][j].isOccupied()){
                    numOfPieces++;
                }

            }
        }
        return numOfPieces;
    }

    public  GameRules generateGame(){
        GameRules game1= new GameRules();
        ArrayList<Integer> noOfPieces = new ArrayList<>();
        int pieces = 0;
        int totalSpaces = 15;

        for(int i =0; i<6; i++){
            if(totalSpaces<=0){
                noOfPieces.add(0);
                continue;
            }
            pieces = getRandomNumberInRange(0, totalSpaces);
            noOfPieces.add(pieces);
            totalSpaces -= pieces;

        }

        game1.setKings(noOfPieces.get(0));
        game1.setPawns(noOfPieces.get(1));
        game1.setQueens(noOfPieces.get(2));
        game1.setKnights(noOfPieces.get(3));
        game1.setRooks(noOfPieces.get(4));
        game1.setBishops(noOfPieces.get(5));
        if (getRandomNumberInRange(0,1) == 1)
            game1.setCanStepOnDifferentColor(true);
        else
            game1.setCanStepOnDifferentColor(false);

        if (getRandomNumberInRange(0,1) == 1)
            game1.setLossOnCheckmate(true);
        else
            game1.setLossOnCheckmate(false);

        if (getRandomNumberInRange(0,1) == 1)
            game1.setKingLostLast(true);
        else
            game1.setKingLostLast(false);

       // if (getRandomNumberInRange(0,1) == 1)
        //    game1.setParalysedOnAttack(true);
       // else
        //    game1.setParalysedOnAttack(false);

        return game1;
    }

    public static int play(Player player1, Player player2, Board b, int noOfMoves) {
        Move move;
        int turn = 0;
        while(true) {
            if(turn++ > 200)
                return 0;
          //  System.out.println("Making turn: " + turn);
            //System.out.println(b);
            move = player1.getNextMove(b);
            if(move == null && b.isCheck(player1.getColour()) && b.lossOnCheckmate) // check and can't move
                return -1;

            if(move == null && b.isCheck(player1.getColour()) && !b.lossOnCheckmate) // check and can't move
                return 1;

            if(move == null) // no check but can't move
                return 0;

            b.makeMove(move);
          //  System.out.println(b);


            move = player2.getNextMove(b);
            if(move == null && b.isCheck(player2.getColour()) && b.lossOnCheckmate && !b.kingLostLast) // check and can't move
                return 1;

            if(move == null && b.isCheck(player2.getColour()) && !b.lossOnCheckmate && !b.kingLostLast)
                return -1;

            if(move == null) // no check but can't move
                return 0;

            b.makeMove(move);
         //   System.out.println(b);
            noOfMoves++;


        }
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public GameRules mate(GameRules parent1, GameRules parent2 ){

        GameRules child = new GameRules();
        Random randomGenerator = new Random();

        //random int to determine
      //  int length1= randomGenerator.nextInt(8);



        //set traits of random length from parent1 and 2

        for(int i = 0; i<10; i++){
            if (randomGenerator.nextInt(1) == 1){
                setTraits(parent1,i,child);
            }else {
                setTraits(parent2,i,child);
            }
        }

        //Mutate after crossover with prob 1/N

        for(int i =0; i <10; i++){
            if(randomGenerator.nextInt(9) == 1){
                mutate(i,child);
            }
        }

        return child;





    }

    public void  setTraits(GameRules parent, int number, GameRules child){

        switch (number) {
            case 0:
                child.setPawns(parent.getPawns());
                break;

            case 1:
                child.setQueens(parent.getQueens());
                break;
            case 2:
                child.setKnights(parent.getKnights());
                break;

            case 3:
                child.setRooks(parent.getRooks());
                break;
            case 4:
                child.setBishops(parent.getBishops());
                break;
            case 5:
                child.setCanStepOnDifferentColor(parent.getCanStepOnDifferentColor());
                break;
            case 6:
                child.setLossOnCheckmate(parent.getLossOnCheckmate());
                break;
            case 7:
                child.setKingLostLast(parent.getKingLostLast());
                break;

          //  case 8:
             //   child.setParalysedOnAttack(parent.getParalysedOnAttack());
            //    break;
          //  case 9:
           //     child.setKings(parent.getKings());
           //     break;
        }
    }
    public void mutate(int trait, GameRules child){
        //need to adjust mutation with different values
        Random random = new Random();
        switch (trait) {
            case 0:
                if(random.nextInt(1) == 1) {
                    child.setPawns(child.getPawns() + 1);
                    break;
                }else {
                    child.setPawns(child.getPawns() - 1);
                    break;
                }
            case 1:
                if(random.nextInt(1)== 1) {
                    child.setPawns(child.getQueens() + 1);
                    break;
                }else {
                    child.setPawns(child.getQueens() - 1);
                    break;
                }
            case 2:
                if(random.nextInt(1) == 1) {
                    child.setPawns(child.getKnights() + 1);
                    break;
                }else {
                    child.setPawns(child.getKnights() - 1);
                    break;
                }

            case 3:
                if(random.nextInt(1) == 1) {
                    child.setRooks(child.getRooks() + 1);
                    break;
                }else {
                    child.setRooks(child.getRooks() -1);
                    break;
                }
            case 4:
                if(random.nextInt(1) == 1) {
                    child.setRooks(child.getBishops() + 1);
                    break;
                }else {
                    child.setRooks(child.getBishops() -1);
                    break;
                }
            case 5:
                if(child.canStepOnDifferentColor){
                    child.canStepOnDifferentColor = false;
                }else{
                    child.canStepOnDifferentColor = true;
                }
                break;
            case 6:
                if(child.lossOnCheckmate){
                    child.lossOnCheckmate = false;
                }else{
                    child.lossOnCheckmate = true;
                }                break;
            case 7:
                if(child.kingLostLast){
                    child.kingLostLast = false;
                }else{
                    child.kingLostLast = true;
                }                break;

           // case 8:
           ////     if(child.paralysedOnAttack){
              //      child.paralysedOnAttack = false;
              //  }else{
              //      child.paralysedOnAttack = true;
              //  }
              //  break;

        }

    }

    public float determineAvgFitness(ArrayList<GameRules> pop){
        float avgFitness =0;

        for (int  i = 0; i<pop.size(); i++){
            avgFitness += pop.get(i).fitness;
        }
        avgFitness= avgFitness/ pop.size();
        return avgFitness;
    }

}



