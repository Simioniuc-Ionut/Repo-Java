package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Repository {
    private final String directory;
    private final Map<Person, List<Document>> documents = new HashMap<>();
    private File[] subdirectoare;
    public Repository(String directory) throws InvalidRepositoryException{
        File dir = new File(directory);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new InvalidRepositoryException(new Exception("Directory " + directory + " does not exist or is not a directory"));
        }
        this.directory=directory;
    }
    public void loadDocumets() throws InvalidRepositoryException,MasterRepositoryFailException {
        File masterDir = new File(directory);
        Set<Integer> allIds = new HashSet<>();

        for (File fileEmploys : Objects.requireNonNull(masterDir.listFiles())) {
            if (fileEmploys.isDirectory()) {
                int idEmplyer = Integer.parseInt(fileEmploys.getName().split("_")[1]);
                String nameEmployer = fileEmploys.getName().split("_")[0];
                Person employer = new Person(idEmplyer,nameEmployer);
                //daca exista un angajat cu acelasi id, arunc exceptia
                if(allIds.contains(idEmplyer)) {
                    throw new MasterRepositoryFailException(new Exception("Already exist an employer with the same id"));
                }else{
                    allIds.add(idEmplyer); //adaug id ul in lista cu toate idurile
                }
                List<Document> employerDocuments = new ArrayList<>();

                for(File filesFromEmployerDirectory : Objects.requireNonNull(fileEmploys.listFiles())){
                    //verific daca nu am alte directoare in directorul emplyer
                    if(filesFromEmployerDirectory.isDirectory()){
                        System.out.println("aici ");
                        throw new InvalidRepositoryException(new Exception("Directory exist in employer folder"));
                    }

                    String format = filesFromEmployerDirectory.getName().split("\\.")[1];//ia dupa punct . ".txt"
                    String fileName = filesFromEmployerDirectory.getName().split("\\.")[0];
                    Document employerDocument = new Document(fileName,format,employer);

                    employerDocuments.add(employerDocument);
                }
                documents.put(employer, employerDocuments);

            }else{
                throw new InvalidRepositoryException(new Exception("Invalid employer directory"));
            }

        }


    }

    public void findSubdirectoare() throws InvalidRepositoryException{
        //ne asiguram in caz ca nu a fost creata lista de subdirectoare
        File director = new File(this.directory);
        this.subdirectoare = director.listFiles(File::isDirectory);
        if(subdirectoare==null || subdirectoare.length==0){
            //daca subidercotrul nu este valid ,va returna null. arunc o exceptie
            throw new InvalidRepositoryException(new Exception("Invalid Directory" + this.directory));
        }
    }
    public File[] getSubdirectoare(){
        return this.subdirectoare;
    }
    public void printContentsOfEmployers(){
        for(Person employer : documents.keySet()){
             System.out.println("Employer: " + employer.name() + " id : " + employer.id() + "\n" +
                    " Documents: " + "\n" +
                     documents.get(employer).stream()
                             .map(Objects::toString)
                             .collect(Collectors.joining("\n")));
        }
    }
    public void printContentOfRepository() throws InvalidRepositoryException  {
        for (File subdirector : this.subdirectoare) {
            System.out.println(subdirector.getName());
        }
    }
}
