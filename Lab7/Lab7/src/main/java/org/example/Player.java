package org.example;

import org.graph4j.*;

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
     private final boolean isSmart;

     private List<Node> graphRepresentation;
    // private Graph<Token,Integer> G ;
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
        synchronized(lock) { //vor putea intra cate un tread pe rand
            while (running.isRunning()) {

                // System.out.println("ceva ------- " + running.isRunning());
                if (!running.isRunning()) {
                    break;
                }
                //verific daca mai exista jetoane
                if (bag.getSizeOfBag() == 0) {
                    //inseamna ca bagul este gol
                    //running = false;
                    // running.set(false);
                    running.stopGame();
                    break;
                }

                // Extragem un jeton ,per turn
                if (!isSmart)
                    System.out.println(name + " is playing in round " + round.get());
                else
                    System.out.println("Smart " + name + " is playing in round " + round.get());


                if (!isSmart) {
                    List<Token> extractedSingleToken;
                    //extragerea normala
                    extractedSingleToken = bag.extractTokens(1);

                    Token tokenExtractedFromBag = extractedSingleToken.get(0);

                    //adaug jetonul in propria lista de jetoane a playerylui
                    if (validSequence(tokenExtractedFromBag)) {
                        //System.out.println(name + " a extras " + tokenExtractedFromBag);
                        if (updateSequence(tokenExtractedFromBag)) {//daca s a updatat cu succe
                            //inseamna ca incrementam punctele;
                            this.points++;
                        }

                    }
                } else
                {
                    List<Token> extractAllBagOfTokens;
                    extractAllBagOfTokens = bag.extractIntelligentTokens(); //luam tot sacul si ne uitam in el

                    //extragerea normala
                    boolean isValidExtractedFromBag = false;
                    //doar pt prima extragere,luam cea mai buna decizie

                    if (playerTokens.isEmpty()) {
                        //va incerca sa extraga cel mai bun token.

                        int betterSecondDisponibleOption = 2;
                        int betterFirstDisponibleOption = 1;

                        while (!isValidExtractedFromBag) {
                            //cauta cel mai bun first pick
                            for (int i = 0; i < bag.getSizeOfBag(); i++) {
                                if (extractAllBagOfTokens.get(i).num1() == betterFirstDisponibleOption && extractAllBagOfTokens.get(i).num2() == betterSecondDisponibleOption) {
                                    playerTokens.add(0, extractAllBagOfTokens.get(i));

                                    bag.extractIsGood(extractAllBagOfTokens.get(i));
                                    extractAllBagOfTokens.remove(i);
                                    isValidExtractedFromBag = true;
                                    this.points++;
                                    break;
                                }
                            }
                            betterSecondDisponibleOption++;
                            //in cazul extrem in care fiecare player dinainte ia tokenul (1,2) , (1,3) , ... (1,n)
                            if (betterFirstDisponibleOption > bag.getLengthOfSequence()) {
                                betterFirstDisponibleOption++;
                                betterSecondDisponibleOption = betterFirstDisponibleOption + 1;
                            }

                        }
                    } else {
                        //extractia generala
                        //strategia pe care o urmareste e sa extraga jetoanele foarte apropiate,de forma(i,i+1) respecrtiv(i,i-1)
                        //astfel avand ocazia sa creeze secvente cat mai lungi
                        while (!isValidExtractedFromBag) {

                            for (int i = 0; i < bag.getSizeOfBag(); i++) {
                                if (extractAllBagOfTokens.get(i).num1() == playerTokens.get(playerTokens.size() - 1).num2()) {
                                    playerTokens.add(playerTokens.size(), extractAllBagOfTokens.get(i));
                                    bag.extractIsGood(extractAllBagOfTokens.get(i));
                                    extractAllBagOfTokens.remove(extractAllBagOfTokens.get(i));
                                    isValidExtractedFromBag = true;
                                    this.points++;
                                    break;
                                }
                                if(i==bag.getSizeOfBag()){
                                    break;//in caz ca nu mai exista piese (i1,i2 sau i3 sau ... in)
                                }
                            }
                        }

                    }
                }

                //daca jucatorul ajunge la lungime = cu n ,ma opresc
                if (playerTokens.size() == bag.getLengthOfSequence()) {
                    System.out.println("player " + name + " has tokens size equal with " + bag.getLengthOfSequence());
                    //running =false;
                    // running.set(false);
                    running.stopGame();
                    break;
                }

//                // Verificăm dacă mai există jetoane
                if (bag.getSizeOfBag() == 0) {
                    // Înseamnă că sacul este gol
                    System.out.println("---Game Over : bag has no more tokens---");
                    // running = false;
                    // running.set(false);
                    running.stopGame();
                    break;
                }

                try {
                    threadTurn.incrementAndGet();
                    if (threadTurn.get() == numberOfPlayers) {
                        lock.notifyAll();
                        threadTurn.set(0);
                        round.incrementAndGet(); //incrementam runda
                        System.out.println("---");
                    } else {
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
             return token.num1() < token.num2();
         }
     for(Token myToken : playerTokens){
         if(token.num2() == playerTokens.get(0).num1() && token.num1() == playerTokens.get(playerTokens.size()-1).num2())
          return true;
         if(myToken.num1() == token.num2()
                 || myToken.num2() == token.num1() && token.num2() < token.num1()){
             return true;
         }
     }
     return false;
    }
    public boolean updateSequence(Token token) {
    if (playerTokens.isEmpty() && token.num1()<token.num2()) {
        // Dacă lista este goală, adăugăm pur și simplu tokenul
        playerTokens.add(token);
        return true;
    } else if (playerTokens.get(0).num1() == token.num2() && token.num1() < token.num2()) {
        // Dacă tokenul poate fi adăugat la începutul listei
        playerTokens.add(0, token);
       // System.out.println(" I ");
        return true;
    } else if (playerTokens.get(playerTokens.size() - 1).num2() == token.num1() ) {
        // Dacă tokenul poate fi adăugat la sfârșitul listei
        playerTokens.add(playerTokens.size(), token);
       // System.out.println(" D ");
        return true;
    }else if(token.num1() < token.num2() && playerTokens.get(0).num1() == token.num2()){
        // Dacă tokenul poate fi adăugat la sfârșitul listei ca token de inchidere
        playerTokens.add(playerTokens.size(), token);
       // System.out.println(" DF ");
        return true;
    }
    return false;

}

    public void setGraphRepresentation(){
        graphRepresentation = new ArrayList<>();
         for(Token t :  playerTokens){
             graphRepresentation.add(new Node(t));
         }
         for(int i1 = 0; i1<playerTokens.size(); i1++){
             for(int i2 = 0; i2<playerTokens.size(); i2++) {
                 if (i1 != i2 && playerTokens.get(i1).num2() == playerTokens.get(i2).num1()) {
                     graphRepresentation.get(i1).addEdge(playerTokens.get(i2));
                     //graphRepresentation.addEdge(t.num2(), t2.num1());
                 } else if (i1 != i2 && playerTokens.get(i1).num1() == playerTokens.get(i2).num2()) {
                     graphRepresentation.get(i1).addEdge(playerTokens.get(i2));
                 }
             }
         }
    }

    public boolean satisfiesOreCondition() {
        //logica pt verificarea conditeit lui orn(oricare 2 nod neadiacente,suma gradelor >= |V(G)|
        for(Node n : graphRepresentation){
            for(Node m : graphRepresentation){
                //daca oricare 2 noduri neadiacente suma gradelor lor sa fie mai mica < nr de norudi din graf => nu respecta Ore Condition
                if(n != m && !n.getEdges().contains(m) && !m.getEdges().contains(n) && n.getNumEdges() + m.getNumEdges() < playerTokens.size()){
                    //System.out.println(name + " cu suma : " + n.getNumEdges() + " + " +  m.getNumEdges() + " < " + playerTokens.size() + " nodes: " +  n.getNameOfNode() +" - " + m.getNameOfNode());
                    return false;
                }
            }
        }
        return true;
     }

    public boolean getHamlitonianCycle(List<Node> path,int pos){
        if( pos == playerTokens.size()){
            if(path.get(pos-1).getEdges().contains(playerTokens.get(0))) {
                path.add(path.get(0));
                return true;
            }else
                return false;
        }


        for (Node n : new ArrayList<>(graphRepresentation)) {
            if (path.isEmpty() || path.get(pos - 1).getEdges().contains(n.getToken())) {
                if (!path.contains(n)) {
                    path.add(n);
                    graphRepresentation.remove(n);

                    if (getHamlitonianCycle(path, pos + 1)) {
                        return true;
                    } else {
                        path.remove(n);
                        graphRepresentation.add(n);
                    }
                }
            }
         }
        return  false;
     }

     public void setHamiltonianGraphForTest(){
        graphRepresentation = new ArrayList<>();
        playerTokens = new ArrayList<>();

        Node a1 = new Node(new Token(1,2));
        Node a2 = new Node(new Token(2,3));
        Node a3 = new Node(new Token(3,4));
        Node a4 = new Node(new Token(4,1));

        a1.addEdge(new Token(2,3));
        a1.addEdge(new Token(4,1));
        a2.addEdge(new Token(3,4));
        a2.addEdge(new Token(1,2));
        a3.addEdge(new Token(4,1));
        a3.addEdge(new Token(2,3));
        a4.addEdge(new Token(1,2));
        a4.addEdge(new Token(3,4));

        graphRepresentation.add(a1);
        graphRepresentation.add(a2);
        graphRepresentation.add(a3);
        graphRepresentation.add(a4);

        //modific si player tokens pt a putea face testul corect
        playerTokens.add(a1.getToken());
        playerTokens.add(a2.getToken());
        playerTokens.add(a3.getToken());
        playerTokens.add(a4.getToken());
    }
//    public void setGraph4J(){
//
//        for(Node n : graphRepresentation) {
//            System.out.println(" aa " + n.getToken());
//            int i = G.addVertex(n.getToken());
//            G.getVertexLabel(1);
//            for(int m=0 ; m<n.getEdges().size(); m++){
//                //    G.addEdge(n.getToken(), n.getEdges().get(m));
//            G.addEdge(n.getToken(),n.getEdges().get(m));
//            }
//
//        }
//    }

//    public void printGraph4j(){
//         System.out.println(G);
//
//    }

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
    public void printGraph(){

        for(Node n : graphRepresentation){
            System.out.println("Vertex : " + n.getToken() + " vecini : " + n.getEdges());
        }
    }

}
