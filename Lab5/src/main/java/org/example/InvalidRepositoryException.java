package org.example;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class InvalidRepositoryException extends  Exception {
    public InvalidRepositoryException(Exception ex) {
        super("Invalid repository.", ex);
    }
}
