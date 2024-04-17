package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class Bag{
        private final Queue<Token> tokens;
        private final int lengthOfSequence ;
        public int turn;
    public Bag(int n) {
        //offer = add ,dar in loc sa arunce exception ,returneaza null sau false
        this.tokens = new LinkedList<>();
        this.turn=0;
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                    tokens.add(new Token(i, j));
            }
        }
        //amestec tokkeni pt a crea extragerea aleatoare;
        Collections.shuffle((List<?>) tokens);
        this.lengthOfSequence=n;
    }

    public synchronized List<Token> extractTokens(int howMany){
        List<Token> extracted = new ArrayList<>();
        for(int i=0; i<howMany; i++){
            if(tokens.isEmpty()){
                break;
            }
            extracted.add(tokens.poll());
        }
        return extracted;
    }
    public synchronized Token extractIntelligentTokens(){
        if(!tokens.isEmpty()){
            return tokens.peek();
        }else{
            return null;
        }
    }
    public synchronized void extractIsGood(Token token){
        for(Token t : tokens){
            if(t.equals(token)){
                tokens.remove(t);
                break;
            }
        }
        System.out.println("Token hasn't been extracted yet");
    }
    public int getLengthOfSequence() {
        return lengthOfSequence;
    }
    public int getSizeOfBag() {
        return tokens.size();
    }
}
