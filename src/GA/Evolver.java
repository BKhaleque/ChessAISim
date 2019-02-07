package GA;

import chessSimulation.Board;
import chessSimulation.Move;
import chessSimulation.pieces.Piece;
import chessSimulation.player.AlphaBetaPlayer;
import chessSimulation.player.Player;
import chessSimulation.player.RandomPlayer;
import GA.GameRules;
import javafx.scene.Parent;

import java.util.ArrayList;
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

        //declare rulespace of inital games for generation 0 in game objects, traits must be assigned with random values and add to gen0 population
        for (int i = 0; i < 10; i++) {
            gen0.add(generateGame());
            //determine generated games fitness
            gen0.get(i).fitness = determineFitness(gen0.get(i));
        }



        GameRules gen0Parent1 = pickParent1(gen0);
        GameRules gen0Parent2 = pickParent2(gen0,gen0Parent1);

        GameRules child = mate(gen0Parent1,gen0Parent2);

        if(child.getKings() ==0){
            child.feasible = false;
            infeasible.add(child);
        }

        for(int i =0; i < gen0.size(); i++){
            if(gen0.get(i).fitness >= 0.4){
                feasible.add(gen0.add(i));
            }
            if(gen0.get(i).fitness <= 0.1){
                infeasible.add(gen0.get(i));
            }
        }




        int index1;
        int index2;

        //mate gen0 parents outside of loop
        index1 = randomGenerator.nextInt(gen0.size());
        index2 = randomGenerator.nextInt(gen0.size());


        for(int i = 0; i<10; i ++){
            GameRules feasibleParent1; //remember to add elitism (copying small portion of fittest individuals into next generation) and tournament for parent selection by getting 2 or 3 objects and comparing fitness
            GameRules feasibleParent2;
        }

        //GameRules infeasibleParent1 = pickParent();
        //GameRules infeasibleParent2 = pickParent();



       // feasibleParent1 = feasible.get(0);
       // feasibleParent2 = feasible.get(0);

       // for(int i = 0; i< feasible.size(); i++){

        //    if (feasible.get(i).getFitness() > feasibleParent1.getFitness() ){
        //        feasibleParent1 = feasible.get(i);
        //    }

       // }

      //  for(int i = 0; i< feasible.size(); i++){

         //   if (feasible.get(i).getFitness() > feasibleParent2.getFitness() && !feasible.get(i).equals(feasibleParent1) ){
        //        feasibleParent2 = feasible.get(i);
        //    }

       // }


       // GameRules child = new GameRules();

       // child = mate(parent1, parent2);

        //todo implement tournament for parent selection, check fitness and compare, pick highest fitness parent

        //then determine quality

        //child.determineQuality();


        /*
        for (int i = 0; i<generations; i++) {
            feasibleOrNot = randomGenerator.nextInt(2);
            if(feasibleOrNot == 0) {
                index1 = randomGenerator.nextInt(feasible.size());
                index2 = randomGenerator.nextInt(feasible.size());
                parent1 = feasible.get(index1);
                parent2 = feasible.get(index2);
            }else if(feasibleOrNot == 1){
                index1 = randomGenerator.nextInt(infeasible.size());
                index2 = randomGenerator.nextInt(feasible.size());
                parent1 = infeasible.get(index1);
                parent2 = feasible.get(index2);
            }else{
                index1 = randomGenerator.nextInt(infeasible.size());
                index2 = randomGenerator.nextInt(infeasible.size());
                parent1 = infeasible.get(index1);
                parent2 = infeasible.get(index2);
            }
            child = mate(parent1, parent2);
            feasible.add(child);
            //determineFitness(child);
            //if child is fit, add to feasible pop, if not add to unfeasible pop
        }
        */

        //debugging purposes
        /*
        System.out.println(child.getKings());
        System.out.println(child.getKnights());
        System.out.println(child.getPawns());
        System.out.println(child.getRooks());
        System.out.println(child.getParalysedOnAttack());
        System.out.println(child.getKingLostLast());
        System.out.println(child.getQueens());
        System.out.println(child.getBishops());
        System.out.println(child.getCanStepOnDifferentColor());
        */
    }

    public GameRules pickParent1(ArrayList<GameRules> pop){
        GameRules parent1 = pop.get(0);

        for(int i =0; i<pop.size(); i++){
            if(parent1.fitness < pop.get(i).fitness){
                parent1 = pop.get(i);
            }
        }

        return parent1;
    }
    public GameRules pickParent2(ArrayList<GameRules> pop, GameRules parent1){
        GameRules parent2 = pop.get(0);
        for(int i =0; i<pop.size(); i++){
            if(parent2.fitness<pop.get(i).fitness && !pop.get(i).equals(parent1)){
                parent2 = pop.get(i);
            }
        }
        return parent2;
    }

    public float determineFitness(GameRules gameRules){
        int iter = 2;
        float player1Score = 0;
        int draw = 0;
        int whiteWins = 0;
        int blackWins = 0;
        float fitness = 0;
        for(int i = 0; i < iter; i++) {
            Board board = new Board(gameRules);
            board.kingLostLast = gameRules.kingLostLast;
            board.canStepOnDifferentColor = gameRules.canStepOnDifferentColor;
            board.lossOnCheckmate = gameRules.lossOnCheckmate;

            //System.out.println(board.toString());
            Player player1 = new AlphaBetaPlayer(Piece.WHITE,3);
            //Player player2 = new RandomPlayer(Piece.BLACK);
            Player player2 = new AlphaBetaPlayer(Piece.BLACK,1);
            //Player player2 = new DeterministicPlayer(Piece.BLACK);

            int winner = play(player1, player2, board);

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

        return fitness;

    }

    public  GameRules generateGame(){
        GameRules game1= new GameRules();
        ArrayList<Integer> noOfPieces = new ArrayList<>();
        int pieces = 0;
        int totalSpaces = 16;

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

        if (getRandomNumberInRange(0,1) == 1)
            game1.setParalysedOnAttack(true);
        else
            game1.setParalysedOnAttack(false);

        return game1;
    }

    public static int play(Player player1, Player player2, Board b) {
        Move move;
        int result;
        int turn = 0;
        while(true) {
            if(turn++ > 200)
                return 0;

            move = player1.getNextMove(b);
            if(move == null && b.isCheck(player1.getColour()) && b.lossOnCheckmate) // check and can't move
                return -1;

            if(move == null && b.isCheck(player1.getColour()) && !b.lossOnCheckmate) // check and can't move
                return 1;

            if(move == null) // no check but can't move
                return 0;

            result = b.makeMove(move);
            System.out.println(b);
            //if(result == -1) return (player1.getColour() == Piece.WHITE) ? -1 : 1; // black wins
            //if(result == 1) return (player1.getColour() == Piece.WHITE) ? 1 : -1; // white wins


            move = player2.getNextMove(b);
            if(move == null && b.isCheck(player2.getColour()) && b.lossOnCheckmate && !b.kingLostLast) // check and can't move
                return 1;

            if(move == null && b.isCheck(player2.getColour()) && !b.lossOnCheckmate && !b.kingLostLast)
                return -1;

            if(move == null) // no check but can't move
                return 0;

            result = b.makeMove(move);
            System.out.println(b);
            //if(result == -1) return (player1.getColour() == Piece.WHITE) ? 1 : -1; // black wins
            //if(result == 1) return (player1.getColour() == Piece.WHITE) ? -1 : 1; // white wins

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
        int length1= randomGenerator.nextInt(8);

        float mutationChance = 0.05f;


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

            case 8:
                child.setParalysedOnAttack(parent.getParalysedOnAttack());
                break;
            case 9:
                child.setKings(parent.getKings());
                break;
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

            case 8:
                if(child.paralysedOnAttack){
                    child.paralysedOnAttack = false;
                }else{
                    child.paralysedOnAttack = true;
                }
                break;
            case 9:
                child.setKings(child.getKings()+1);
                break;
        }

    }

}



