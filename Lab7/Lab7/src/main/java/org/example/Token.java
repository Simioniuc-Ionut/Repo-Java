package org.example;

import java.util.Objects;

public record Token(int num1, int num2) {

    @Override
    public String toString() {
        return "(" + num1 + ", " + num2 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return num1 == token.num1 && num2 == token.num2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num1, num2);
    }
}
