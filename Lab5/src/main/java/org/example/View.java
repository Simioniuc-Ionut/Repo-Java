package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class View extends Command{

    public View(String path){
        super(path);
    }
    @Override
    public void execute() throws InvalidExecuteException {
        try {
            System.out.println(isValidPath() );
            if(isValidPath()) {
                System.out.println(" path : " + path);
                Desktop.getDesktop().open(new File(path));
            }
        }catch (InvalidExecuteException e){
            throw new InvalidExecuteException(new IOException(e.getMessage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new InvalidExecuteException(new IOException(e.getMessage()));
        }

    }
}
