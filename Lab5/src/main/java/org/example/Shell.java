package org.example;

import org.example.Exception.InvalidExecuteException;
import org.example.Exception.InvalidRepositoryException;
import org.example.Exception.MasterRepositoryFailException;

import java.io.*;

public class Shell {
    public static void main(String[] args) throws IOException {

        //partea de repository
        try {
            Repository repo = new Repository("C:\\Users\\Asus\\Documents\\Facultate\\Anul2\\Sem2\\Java\\Repo-Java_vechi\\Lab5\\src\\main\\resources\\Master");
            repo.findSubdirectoare();
            repo.loadDocumets();
            repo.printContentOfRepository();
            System.out.println("----content of employers :----");
            repo.printContentsOfEmployers();
        }catch (MasterRepositoryFailException | InvalidRepositoryException e){
            System.out.println("Eroare: " + e.getMessage());
        }
        //partea de comenzi din shell
        BufferedReader shell = new BufferedReader(new InputStreamReader(System.in));
        String commandLine;

        while(true){
            System.out.print("shell> ");
            commandLine = shell.readLine();

            if(commandLine.equals("exit"))
                break;
            String[] partsOfCommand = commandLine.split(" ",2);
            String command = partsOfCommand[0];
            String path = "";


            if(partsOfCommand.length>1){
                //iau si calea,in caz ca exista
                path = partsOfCommand[1];
                System.out.println("Path : " + path);
            }
            try{
                if(command.equals("help")){
                    System.out.println("""
                            shell> : commands : \

                            | view /path/to/file | \

                            | report /path/to/file |\

                            | export /path/to/file |\

                            | read-Excel |""");
                    String currentDirectory = System.getProperty("user.dir");
                    System.out.println("Directorul curent este: " + currentDirectory);

                }
                if(command.equals("view")){
                    System.out.println("view command called");
                  //C:/Users/Asus/Documents/Facultate/Anul2/Sem2/Java/Repo-Java_vechi/Lab5/src/main/resources/Master/Andrei_34567/SaraFile.txt
                    if(partsOfCommand.length>1){
                        //inseamna ca avem calea
                        View viewFile = new View(path);
                        viewFile.execute();

                    }else {
                        throw new InvalidExecuteException(new IOException(" No path "));
                    }
                }
                if(command.equals("report")){
                    System.out.println("report command called");
                    //C:/Users/Asus/Documents/Facultate/Anul2/Sem2/Java/Repo-Java_vechi/Lab5/src/main/resources/Master

                    if(partsOfCommand.length>1){
                        Report report = new Report(path);
                        report.setRepo();
                        report.execute();

                    }else {
                        throw new InvalidExecuteException(new IOException(" No path "));
                    }
                }
                if(command.equals("export")){
                    System.out.println("export command called");
                    //C:/Users/Asus/Documents/Facultate/Anul2/Sem2/Java/Repo-Java_vechi/Lab5/src/main/resources/Master

                    if(partsOfCommand.length>1){
                    Export export = new Export(path);
                    export.execute();

                    }else {
                        throw new InvalidExecuteException(new IOException(" No path "));
                    }
                }
                if(command.equals("read-Excel")){
                //C:/Users/Asus/Documents/Facultate/Anul2/Sem2/Java/Repo-Java_vechi/Lab5/src/main/resources
                    ExcelReader reader = new ExcelReader(path);
                    reader.execute();
                    System.out.println("------");
                    reader.printAbilitiesOfPeson();
                    System.out.println("------");
                    //cream repository ul nontrivial
                    reader.createNonTrivialRepository();
                    //afisam grupurile;
                    reader.createGroups();
                    reader.printMaximalGroups();

                }
            }catch (Exception err){
                System.out.println("Error: " + err.getMessage());
            }
        }

    }
}
//jar comand:
//java -jar .\Lab5-1.0-SNAPSHOT.jar
