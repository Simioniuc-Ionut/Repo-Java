package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Player extends ReentrantLock implements Runnable {
     private String name;
     private Bag bag;
     private List<Token> playerTokens = new ArrayList<Token>();
     private int points = 0;

     private final Object lock;
     private final AtomicInteger round;
     private final int numberOfPlayers;
     private AtomicInteger threadTurn;
     private  TimeKeeper running; //il fac static ca sa se modifice in toate celelalte instante,si volatile pt a fi thread safe
     private boolean isSmart;
    public Player(String name, Bag bag, Object lock, AtomicInteger round, int numberOfPlayers, AtomicInteger threadTurn, TimeKeeper running,boolean isSmart) {
         this.name = name;
         this.bag = bag;
         this.lock = lock;
         this.round = round; //initializam variabila partajata cu 1
         this.numberOfPlayers=numberOfPlayers;
         this.threadTurn = threadTurn;
         this.running=running;
         this.isSmart=isSmart;
        //nu e nevoie sa l oprim deoarece este setat ca demon ,se va opri singur
    }
    @Override
    public void run(){

        synchronized(lock){ //vor putea intra cate un tread pe rand

            while(running.isRunning()){

           // System.out.println("ceva ------- " + running.isRunning());
             if(!running.isRunning()){
                 break;
             }
                //verific daca mai exista jetoane
             if(bag.extractTokens(1) == null){
                 //inseamna ca bagul este gol
                 //running = false;
                // running.set(false);
                 running.stopGame();
                 break;
             }
                // Extragem un jeton ,per turn
                 System.out.println(name + " is playing in round " + round.get());

                List<Token> extractedTokens;
                //extragerea normala
                extractedTokens = bag.extractTokens(1);
                /*partea de smart
                if(!isSmart) {
                    // extractedTokens = bag.extractTokens(1);
                }else{
                    //extragerea smart
                }*/
                
                // Verificăm dacă mai există jetoane
                if (extractedTokens.isEmpty()) {
                    // Înseamnă că sacul este gol
                    System.out.println("---Game Over : bag has no more tokens---");
                   // running = false;
                   // running.set(false);
                    running.stopGame();
                    break;
                }
                 Token tokenExtractedFromBag = extractedTokens.get(0);

                 //adaug jetonul in propria lista de jetoane a playerylui
                  if(validSequence(tokenExtractedFromBag)) {
                     //System.out.println(name + " a extras " + tokenExtractedFromBag);
                     updateSequence(tokenExtractedFromBag);
                     //inseamna ca incrementam punctele;
                     this.points++;
                 }

                  //daca jucatorul ajunge la lungime = cu n ,ma opresc
                  if(playerTokens.size() == bag.getLengthOfSequence()){
                    System.out.println("player " + name + " has tokens size equal with " + bag.getLengthOfSequence());
                    //running =false;
                     // running.set(false);
                     running.stopGame();
                      break;
                  }
                      try {
                           threadTurn.incrementAndGet();
                           if(threadTurn.get() == numberOfPlayers){
                               lock.notifyAll();
                               threadTurn.set(0);
                               round.incrementAndGet(); //incrementam runda
                               System.out.println("---");
                           }else {
                               lock.wait();

                           }
                      } catch (InterruptedException e) {
                          System.out.println("Thread lock interrupted");
                      }
             }
            lock.notifyAll(); //notificam ultimul thread care asteapta in ultima runda
         }
    }
    public boolean validSequence(Token token){
         if(playerTokens.isEmpty()){
             return true;
         }
     for(Token myToken : playerTokens){
         if(myToken.num1() == token.num2() || myToken.num2() == token.num1()){
             return true;
         }
     }
     return false;
    }
    public void updateSequence(Token token){
        if(playerTokens.isEmpty()){
            playerTokens.add(token);

        }else {
            for(int index=0; index<playerTokens.size(); index++){
                if(playerTokens.get(index).num2() == token.num1() && index+1 == playerTokens.size()){
                    //inserez dupa ultimul element.
                   // System.out.println("Am inserat dupa "+ token);
                    playerTokens.add(index+1,token);
                    break;
                }else if(playerTokens.get(0).num1() == token.num2()){
                        //verific daca pot insera inaintea primului element
                     //   System.out.println("Am inserat " + token);
                        playerTokens.add(index,token);
                        break;
                    }
            }
        }

    }
    public int getPoints() {
        return points;
    }
    public String getName() {
        return name;
    }
    public String printPlayerTokens(){
         String allTokens = "";
         for(Token token : playerTokens){
            allTokens += token + " ";
         }
         return allTokens;
    }
}
