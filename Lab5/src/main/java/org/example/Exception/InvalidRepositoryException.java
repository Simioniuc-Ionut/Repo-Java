package org.example.Exception;

public class InvalidRepositoryException extends  Exception {
    public InvalidRepositoryException(Exception ex) {

        super("Invalid Repository : " + ex, ex);
    }
}
