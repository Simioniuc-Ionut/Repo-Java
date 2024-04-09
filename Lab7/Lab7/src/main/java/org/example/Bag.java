package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bag {
        private final Queue<Token> tokens;


    public Bag(int n) {
        //offer = add ,dar in loc sa arunce exception ,returneaza null sau false
        this.tokens = new LinkedList<>();
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n; j++){
                    tokens.add(new Token(i, j));
            }
        }
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
    public int getSize(){
        return tokens.size();
    }
}
