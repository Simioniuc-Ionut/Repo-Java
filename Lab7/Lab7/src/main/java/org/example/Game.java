package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    private final Bag bag;
    private final List<Player> players = new ArrayList<>();
    public Game(int lengthOfNumbers){
        this.bag=new Bag(lengthOfNumbers);

    }
    public void addPlayer(String name, Object lock , AtomicInteger round, int numberOfPlayers, AtomicInteger threadTurn, TimeKeeper running,boolean isSmart) {
        Player player = new Player(name ,bag,lock,round,numberOfPlayers,threadTurn,running,isSmart);
        players.add(player);
    }
    public void play(){
        List<Thread> allActiveThreads= new ArrayList<>();
        for(Player player : players){
            //start new thread

            Thread threadOfEachPlayer = new Thread(player);
            threadOfEachPlayer.start();
            allActiveThreads.add(threadOfEachPlayer);
        }
        for(Thread thread : allActiveThreads){
            try{
                thread.join();
            }catch ( InterruptedException e){
                System.out.println("Thread interrupted");
            }
        }
        int maxValueIsWiner=-1;
        String Winner = "";
        for(Player player : players) {
            System.out.print(player.getName() + " has points: " + player.getPoints()+ " -- ");
            if (player.getPoints() > maxValueIsWiner) {
                maxValueIsWiner = player.getPoints();
                Winner = player.getName();
            }
            System.out.println( player.getName() + " has extracted :" + player.printPlayerTokens());
        }
        if (!Objects.equals(Winner, "")) {
            printWinner(Winner);
        }

        System.out.println("\n-------- Part with Graphs --------\n");
        calculateOreCondition();

    }
    public void calculateOreCondition(){
        for(Player player : players){
            System.out.println("-----"+player.getName()+"----");
            player.setGraphRepresentation();
            player.printGraph();
            player.satisfiesOreCondition();

            System.out.println("\n-------- Hamiltonian Finding --------\n");
           // player.setGraph4J();
           // player.printGraph4j();
            if(player.satisfiesOreCondition()){ //daca conditia lui Ore este satifactua atunci stiu sigur ca exista un circuit hamiltonian
                List<Node> path = new ArrayList<>();
                int pos=0;
                player.getHamlitonianCycle(path,pos);
                System.out.print("Hamiltonin path is : ");
                for(Node node : path){
                    System.out.print( node.getToken() + " , ");
                }
                System.out.println();
            }
        }

        //hamiltonian test
        System.out.println("== : Hamlitonian test : ==");
        List<Node> path = new ArrayList<>();
        int pos=0;
        players.get(0).setHamiltonianGraphForTest();
        players.get(0).getHamlitonianCycle(path,pos);
        players.get(0).printGraph();
        System.out.print("Hamiltonin path is : ");
        for(Node node : path){
            System.out.print( node.getToken() + " , ");
        }
    }
    public static void printWinner(String Winner){
        System.out.println("The winner is player " + Winner);
    }
    public static void main(String[] args) {
      Game game = new Game(6);

      Object lock = new Object();
      AtomicInteger round = new AtomicInteger(1);
      int numberOfPlayers = 1;
      AtomicInteger threadTurn= new AtomicInteger(0);

      //partea de timeKeeper
      long timeLimit = 100;//10 milisecunde
      AtomicBoolean running = new AtomicBoolean(true);
      TimeKeeper timeKeeper = new TimeKeeper(timeLimit,running);
      timeKeeper.start();

    //  game.addPlayer("Player 1",lock,round,numberOfPlayers,threadTurn,timeKeeper,false);
    //  game.addPlayer("Player 2",lock,round,numberOfPlayers,threadTurn,timeKeeper,false);
   //   game.addPlayer("Player 3",lock,round,numberOfPlayers,threadTurn,timeKeeper,false);

      //SMART player;
        game.addPlayer("Player 4",lock,round,numberOfPlayers,threadTurn,timeKeeper,true);
      //  game.addPlayer("Player 4",lock,round,numberOfPlayers,threadTurn,timeKeeper,true);


        game.play();
    }
}
