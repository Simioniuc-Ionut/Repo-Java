package org.example;

public record Token(int num1, int num2) {

    @Override
    public String toString() {
        return "(" + num1 + ", " + num2 + ")";
    }
}
