package org.example;
import org.example.DAO.BookDAO;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


public class BookPartition {
    public static void main(String[] args) {
        try {
            // Crearea unui graf nou
            Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
            // Adaugarea cartilor ca varfuri in graf
            // Presupunem ca avem o lista de carti si fiecare carte are un ID unic
            var books = new BookDAO();
            List<Integer> bookIds = books.getAllBooksId();
            for (Integer bookId : bookIds) {
                g.addVertex(bookId);
            }

            // Adaugarea muchiilor intre carti daca ele sunt legate
            // Presupunem ca avem o functie `areRelated` care verifica daca doua carti sunt legate
            for (Integer bookId1 : bookIds) {
                for (Integer bookId2 : bookIds) {
                    if (!bookId1.equals(bookId2) && books.areRelated(bookId1, bookId2)) {
                        g.addEdge(bookId1, bookId2);
                    }
                }
            }

            // Aplicarea algoritmului de colorare
            VertexColoringAlgorithm<Integer> coloring = new GreedyColoring<>(g);
            VertexColoringAlgorithm.Coloring<Integer> result = coloring.getColoring();

            // Afisarea rezultatului
            System.out.println("Numarul de liste de lectura este: " + result.getNumberColors());
            System.out.println("Distributia cartilor in liste este: " + result.getColors());


            // Crearea unui HashMap pentru a stoca listele de lectura
            Map<Integer, ReadingList> readingLists = new LinkedHashMap<>();

            // Crearea listelor de lectura in functie de rezultatul algoritmului de colorare
            for (Integer bookId : result.getColors().keySet()) {
                Integer listId = result.getColors().get(bookId);
                // Daca lista de lectura nu exista deja, o cream
                if (!readingLists.containsKey(listId)) {
                    readingLists.put(listId, new ReadingList("Lista de lectura " + listId));
                }
                // Adaugam cartea la lista de lectura corespunzatoare
                readingLists.get(listId).addBook(books.getBookById(bookId));
            }


            // Afisarea listelor de lectura nesortate
            System.out.println("\n----Listele nesortate ---- \n");
            for (ReadingList readingList : readingLists.values()) {
//                readingList.printListOfBooks();
                System.out.println("Size of book " + readingList.getBooks().size());
            }

            //crearea sortarii
            System.out.println("\n----Listele sortate ---- \n");

            // Sortarea listelor de lectura in functie de numarul de carti din fiecare lista, in ordine descrescatoare
            List<ReadingList> sortedReadingListsDescending = new ArrayList<>(readingLists.values().stream()
                    .sorted((list1, list2) -> Integer.compare(list2.getBooks().size(), list1.getBooks().size()))
                    .toList());

            // Afisarea listelor de lectura sortate descrescator
            for (ReadingList readingList : sortedReadingListsDescending) {
               // readingList.printListOfBooks();
                System.out.println("Size of book " + readingList.getBooks().size());
            }

            // Obtinerea dimensiunii maximale (cea mai mica valoare) din lista sortata
            int minSize = sortedReadingListsDescending.get(sortedReadingListsDescending.size() - 1).getBooks().size();

// Itereaza prin listele de lectura sortate descrescator

                for (int i = 0; i < sortedReadingListsDescending.size()-1 ; i++) {
                    ReadingList currentList = sortedReadingListsDescending.get(i);
                    int currentSize = currentList.getBooks().size();

                    // Calculeaza diferenta de dimensiune fata de dimensiunea minima
                    int sizeDiff = currentSize - (minSize + 1);
                    //System.out.println("current size " + currentSize + " minsize " + minSize + " " + sizeDiff + " size dif");
                    // Itereaza prin listele de lectura cu dimensiune mai mica decat dimensiunea maxima

                        for (int j = i+1; j < sortedReadingListsDescending.size(); j++) {
                            ReadingList smallerList = sortedReadingListsDescending.get(j);

                            // Verifica daca diferenta de dimensiune permite mutarea cartilor
                             if (sizeDiff > 0) {
                                 // Obtine o copie a cartilor din lista curenta si cea mai mica
                                 List<Book> currentBooksCopy = new ArrayList<>(currentList.getBooks());
                                 List<Book> smallerBooksCopy = new ArrayList<>(smallerList.getBooks());

                                 // Itereaza prin copia cartilor din lista curenta
                                 for (Book book : currentBooksCopy) {
                                     // Verifica daca cartea nu este legata de nicio carte din lista mai mica
                                     boolean isRelated = smallerBooksCopy.stream()
                                             .anyMatch(smallerBook -> {
                                                 try {
                                                     return books.areRelated(book.getId(), smallerBook.getId());
                                                 } catch (SQLException e) {
                                                     System.out.println("Eroare la partitionare");
                                                     throw new RuntimeException(e);
                                                 }
                                             });

                                     if (!isRelated) {
                                         // Daca nu este legata  mut cartea in lista mai mica și ajustează dimensiunea
                                         currentList.removeBook(book);
                                         smallerList.addBook(book);
                                         sizeDiff--;

                                         // Actualizeaza lista mare
                                         sortedReadingListsDescending.get(i).setListOfBooks(currentList.getBooks());
                                         sortedReadingListsDescending.get(j).setListOfBooks(smallerList.getBooks());

                                         break;
                                     }
                                 }
                             }
                    }
                    // Verific daca toate listele au o dimensiune care este cu cel putin 1 mai mare decat minSize
                    int finalMinSize = minSize;
                    boolean allListsAreBalanced = sortedReadingListsDescending.stream()
                            .allMatch(list -> list.getBooks().size() > finalMinSize);

                    //System.out.println("Final minsize" + minSize);
                    if (allListsAreBalanced) {
                        minSize++;
                        i = -1;
                        //debug
//                        System.out.println("--inainte de sort ---");
//                        for (ReadingList readingList : sortedReadingListsDescending) {
//                            //readingList.printListOfBooks();
//                            System.out.println("Size of book " + readingList.getBooks().size());
//                        }

                        // Sortează din nou lista după ce dimensiunea sa a fost modificată
                        sortedReadingListsDescending.sort((list1, list2) -> list2.getBooks().size() - list1.getBooks().size());

                        //debug
//                        System.out.println("--dupa sort ---");
//                        for (ReadingList readingList : sortedReadingListsDescending) {
//                            //readingList.printListOfBooks();
//                            System.out.println("Size of book " + readingList.getBooks().size());
//                        }

                    }

                }


            System.out.println("\n----Listele reechilibrate ---- \n");

            for (ReadingList readingList : sortedReadingListsDescending) {

                //System.out.println("Size of book " + readingList.getBooks().size());

                readingList.printListOfBooks();
            }


        }catch (SQLException e){
            System.out.println(e.getMessage() + "Book partition error");
        }
    }
}

