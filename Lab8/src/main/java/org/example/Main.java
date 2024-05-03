package org.example;

import org.example.DAO.BookDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionPool.getDataSource().getConnection();
        try {

//            var author = new AuthorDAO();
//            author.create("William Shakespeare");
//
//            var author2 = new AuthorDAO();
//            author2.create("Douglas Adams");

            var books = new BookDAO(); //findByName

            List<String> authors1 = new ArrayList<>();
            authors1.add("William Shakespeare");

            List<String> genres1= new ArrayList<>();
            genres1.add("Tragedy");

            String title1 = "Romeo and Juliet";

            if(books.findByName(title1) == null) {
             //System.out.println("IF1");
                books.create(1597,"eng",800, title1, authors1, genres1);
            }
            //la fel

            List<String> authors2 = new ArrayList<>();
            authors2.add("Douglas Adams");

            List<String> genres2= new ArrayList<>();
            genres2.add("Science fiction");
            genres2.add("Comedy");
            genres2.add("Adventure");

            String title2= "The Hitchhiker's Guide to the Galaxy";

            if(books.findByName(title2) == null) {
                //System.out.println("IF2");
                books.create(1979,"eng",500, title2, authors2, genres2);
            }
            //TODO: print all the books in the database
            // books.printAllBooks();


            //List of reading
            ReadingList myList = new ReadingList("MyList");

            Book book1 = books.getBookById(1);
            Book book2 = books.getBookById(2);
            Book book3 = books.getBookById(3);
            myList.addBook(book1);
            myList.addBook(book2);
            myList.addBook(book3);

            myList.printListOfBooks();
            //System.out.println("Book : " + book);

            connection.commit();

        } catch (SQLException e) {
                System.err.println(e);
                System.out.println("Error");
               // DatabaseConnection.getConnection().rollback(); //anulez tranzactia in cazul unei exceptii
            }finally {
                connection.close();
               // DatabaseConnection.closeConnection();
        }
    }
}
