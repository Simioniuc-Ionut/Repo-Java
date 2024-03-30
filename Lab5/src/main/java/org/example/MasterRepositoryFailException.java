package org.example;

public class MasterRepositoryFailException extends Exception{
    public MasterRepositoryFailException(Exception e){
        super("Error at Master Directory: " + e ,e);
    }
}
