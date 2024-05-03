package org.example;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReadingList {
    private String name;
    private List<Book> books;
    private Timestamp timestamp;
    public ReadingList(String name) {
        this.name = name;
        books = new ArrayList<Book>();
        timestamp = new Timestamp(System.currentTimeMillis());
    }
    public List<Book> getBooks(){
        return books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void removeBook(Book book) {
        books.remove(book);
    }

    public void setListOfBooks(List<Book> books) {
        this.books = books;
    }
    public int getSize(){
        return books.size();
    }
    public void addBook(Book book) {
        books.add(book);
    }
    public void printListOfBooks(){
        System.out.println("----List of Books : " + this.name + "----");
        int index=0;
        for(Book book : books){
            System.out.println("Number " + index + ": " + book);
            index++;
        }
        System.out.println("Timestamp : " + timestamp.toString());
    }
}
