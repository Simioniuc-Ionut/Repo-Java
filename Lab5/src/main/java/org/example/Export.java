package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Export extends Command{
    public Export(String path)throws InvalidExecuteException {
        super(path);
    }

    @Override
    public void execute() throws InvalidExecuteException {
        ObjectMapper mapper = new ObjectMapper();

    }
}
