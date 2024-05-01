package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;


public class BookDAO extends Library {

    private final  String insertIntoBooks = "insert into books (year ,language,page, title) values (?,?,?,?)";
    private final  String insertIntoBook_authors = "insert into book_authors (book_id,authors_id) values (?, ?)";
    private final  String insertIntoBook_genres = "insert into book_genres (book_id,genres_id) values (?, ?)";


    private final  String selectAllBooks = "select * from books";
    private final  String idOfBook = "select id from books where title= ?";
    private BasicDataSource dataSource = ConnectionPool.getDataSource();

    public void create(int year,String language,int page, String title,List<String> authors, List<String> genres) throws SQLException {

        //Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        try  {
            con.setAutoCommit(false); // start transaction

            PreparedStatement prstmt = con.prepareStatement(insertIntoBooks, PreparedStatement.RETURN_GENERATED_KEYS);
            prstmt.setInt(1,year);
            prstmt.setString(2,language);
            prstmt.setInt(3,page);
            prstmt.setString(4,title);
            prstmt.executeUpdate();

            ResultSet rs = prstmt.getGeneratedKeys(); //iau intr un obiect cheia generata automat dupa executarea interogarii de mai sus
            rs.next(); //mut cursorul la linia unde se ia cheia generata

            //am luat id ul
            int bookId = rs.getInt(1); //cursorul rs se afla pe linia unde regasim cheia generata,din tabelul books, si iau prima valoare din prima coloana,adica id ul


            for (String authorName : authors){
                var authorDAO = new AuthorDAO();
                Integer authorId= authorDAO.findByName(authorName);

                //DACA autorul nu exista il cream .
                if(authorId == null){
                    authorId=authorDAO.create(authorName);
                    con.commit();
                    //authorId = authorDAO.findByName(author_name);
                }

//                prstmt = con.prepareStatement(insertIntoBook_authors);
//                prstmt.setInt(1,bookId);
//                prstmt.setInt(2,authorId);
//                prstmt.executeUpdate();

                // Verifica daca asocierile carte-autor exista deja
                String checkQuery = "SELECT COUNT(*) FROM book_authors WHERE book_id = ? AND authors_id = ?";
                PreparedStatement checkStmt = con.prepareStatement(checkQuery);
                checkStmt.setInt(1, bookId);
                checkStmt.setInt(2, authorId);

                ResultSet checkRs = checkStmt.executeQuery();
                checkRs.next();

                int count = checkRs.getInt(1);
                if (count == 0) {
                    // Dacă asocierile carte-autor nu există, le inserează
                    prstmt = con.prepareStatement(insertIntoBook_authors);
                    prstmt.setInt(1,bookId);
                    prstmt.setInt(2,authorId);
                    prstmt.executeUpdate();
                }

            }





            if(genres != null){
            for (String genre_name : genres){
                var genreDAO = new GenreDAO();
                Integer genreId = genreDAO.findByName(genre_name);
                //DACA genul nu exista il cream .
                if(genreId == null){
                    genreId = genreDAO.create(genre_name);
                   // con.commit();
                   // genreId = genreDAO.findByName(genre_name);
                }
                prstmt = con.prepareStatement(insertIntoBook_genres);
                prstmt.setInt(1,bookId);
                prstmt.setInt(2,genreId);
                prstmt.executeUpdate();
            }}

           con.commit();
        } catch (SQLException e){
            //con.rollback();
           System.out.println("Erro books");

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e + " cannot close connection in BookDAO");

                }
            }
        }
    }
    public Integer findByName(String title) throws SQLException{
       // Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(idOfBook)) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e + " cannot close connection in BookDAO");

                }
            }
        }
    }

    public void insertBook(int id , int year , String title) throws SQLException {
        String query = "insert into books (id,year,title,) values (?,?)";

        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query)){
            pstmt.setInt(1, id);
            pstmt.setInt(2, year);
            pstmt.setString(3, title);

            pstmt.executeUpdate();
        }

    }
    public void printAllBooks() throws SQLException {
        //Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        try(PreparedStatement prstmt = con.prepareStatement(selectAllBooks)){

            ResultSet rs = prstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3) + " " + rs.getInt(4) + " " + rs.getString(5));
            }
        } catch (SQLException e ){
            throw new SQLException(e + " Error in print all books");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e + " cannot close connection in BookDAO");

                }
            }
        }
    }
}
