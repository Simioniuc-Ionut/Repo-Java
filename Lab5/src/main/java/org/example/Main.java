package org.example;

public class Main {
    public static void main(String[] args)  {
        System.out.println("-----Compulsory-----");
        try {
            var repo = new Repository("C:\\Users\\Asus\\Documents\\Facultate\\Anul2\\Sem2\\Java\\Repo-Java\\Lab5\\src\\main\\resources\\Master");
            repo.findSubdirectoare();
            repo.printContentOfRepository();
        } catch (InvalidRepositoryException e) {
          System.out.println("Eroare: " + e.getMessage());
        }
    }
}