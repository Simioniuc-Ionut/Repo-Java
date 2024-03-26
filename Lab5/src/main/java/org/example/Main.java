package org.example;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("-----Compulsory-----");
        try {
            var repo = new Repository("C:\\Users\\simio\\OneDrive\\Documents\\Facultate\\Anul2\\Sem2\\Java\\Repo-Java\\Lab5\\src\\main\\resources\\Master");
            repo.printContentOfRepository();
        } catch (IOException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}