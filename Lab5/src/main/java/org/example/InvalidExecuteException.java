package org.example;

import java.io.IOException;

public class InvalidExecuteException extends IOException {
    public InvalidExecuteException(Exception e) {
        super("Can't open file ", e);
    }
}
