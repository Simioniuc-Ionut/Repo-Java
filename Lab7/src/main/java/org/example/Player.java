package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
     private String name;
     private Bag bag;
     private boolean running;
     private List<Token> tokens = new ArrayList<Token>();

     public Player(String name,Bag bag) { this.name = name; this.bag = bag; this.running = true; }

    @Override
    public void run(){
         while(running){

             //verific daca mai exista jetoane
             if(bag.extractTokens(1) == null){
                 //inseamna ca bagul este gol
                 running =false;
                 break;
             }
             // Extragem un jeton
             List<Token> extractedTokens = bag.extractTokens(1);

             // Verificăm dacă mai există jetoane
             if (extractedTokens == null || extractedTokens.isEmpty()) {
                 // Înseamnă că sacul este gol
                // System.out.println(name + " has no more tokens");
                 running = false;
                 break;
             }

             Token tokenExtractedFromBag = extractedTokens.get(0);

                 //adaug jetonul in propria lista de jetoane a playerylui
                 if(validSequence(tokenExtractedFromBag)) {
                     System.out.println(name + " a extras " + tokenExtractedFromBag);
                     updateSequence(tokenExtractedFromBag);
                 }

             //daca jucatorul ajunge la lungime = cu n ,ma opresc
             if(tokens.size() == bag.getSize()){

                 running =false;
                 break;
             }

         }
    }
    public boolean validSequence(Token token){
         if(tokens.isEmpty()){
             return true;
         }
     for(Token myToken : tokens){
         if(myToken.num1() == token.num2() || myToken.num2() == token.num1()){
             return true;
         }
     }
     return false;
    }
    public void updateSequence(Token token){
        if(tokens.isEmpty()){
            tokens.add(token);

        }else {
            for(int index=0; index<tokens.size(); index++){
                if(tokens.get(index).num1() == token.num2()){
                    //trebuie sa inserez in fata elementului curent
                    tokens.add(index,token);
                    break;
                }else if(tokens.get(index).num2() == token.num1()){
                    //inserez dupa elementul curent
                    tokens.add(index+1,token);
                    break;
                }
            }
        }

    }
}
