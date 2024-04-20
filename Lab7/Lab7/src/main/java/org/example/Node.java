package org.example;

import java.util.ArrayList;
import java.util.List;

    class Node {
        private Token token;
        private List<Token> edges;

    public Node(Token token) {
        this.token = token;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Token node) {
        edges.add(node);
    }

    public Token getToken() {
        return token;
    }

    public List<Token> getEdges() {
        return edges;
    }

    public int getNumEdges() {
        return edges.size();
    }
    public String getNameOfNode(){
       String s = "{"+token.num1() + ","+token.num2()+"}";
        return  s;
    }
}