package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag;
    private final List<Player> players = new ArrayList<>();
    public Game(int lengthOfNumbers){
        this.bag=new Bag(lengthOfNumbers);

    }
    public void addPlayer(String name) {
        Player player = new Player(name ,bag);
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
    }
    public static void main(String[] args) {
      Game game = new Game(20);
      game.addPlayer("Player 1");
      game.addPlayer("Player 2");
      game.addPlayer("Player 3");
      game.play();
    }
}
