package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public abstract class Command {
   protected String path;

    public Command(String path) throws InvalidExecuteException{
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new InvalidExecuteException(new Exception(" : Invalid path"));
        }
        this.path = path;
    }

    public boolean isValidPath() throws Exception {
        File file = new File(path);
        if(file == null || !file.exists()){
            throw new Exception("Invalid path");

        }
        return true;
    }
    public abstract void execute() throws InvalidExecuteException;

}
