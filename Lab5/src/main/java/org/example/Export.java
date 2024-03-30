package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Export extends Command{
    public Export(String path) {
        super(path);
    }

    @Override
    public void execute() throws InvalidExecuteException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Repository repo = new Repository(path);
            repo.loadDocumets();
            Map<Person, List<Document>> allDocuments = new HashMap<>(repo.getDocuments());
            Path parentPath = Paths.get(path).getParent();//luam calea parinte(resources) pt a nu crea in Master fisierul json
            objectMapper.writeValue(new File(parentPath.toString() + "/repository.json"), allDocuments);

        } catch (IOException e) {
           throw new InvalidExecuteException(e);
        } catch (InvalidRepositoryException | MasterRepositoryFailException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}
