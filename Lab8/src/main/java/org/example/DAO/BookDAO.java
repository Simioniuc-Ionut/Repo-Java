package org.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbcp2.BasicDataSource;
import org.example.Book;
import org.example.ConnectionPool;
import org.example.Library;


public class BookDAO extends Library {

    private final  String insertIntoBooks = "insert into books (year ,language,page, title) values (?,?,?,?)";
    private final  String insertIntoBook_authors = "insert into book_authors (book_id,authors_id) values (?, ?)";
    private final  String insertIntoBook_genres = "insert into book_genres (book_id,genres_id) values (?, ?)";

    //comenzi din getBookById
    private final  String findById = "select * from books where id = ?";
    private final  String findByIdBookAuthors = "select * from book_authors where book_id = ?";
    private final  String findByIdBookGenres = "select * from book_genres where book_id = ?";
    private final  String findByIDAuthor = "select * from authors where id = ?";
    private final  String findByIDGenre = "select * from genres where id = ?";


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
    public void insertBook(int id , int year ,String language,int page, String title) throws SQLException {
        String query = "insert into books (id,year,language,page,title,) values (?,?)";

        try(PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query)){
            pstmt.setInt(1, id);
            pstmt.setInt(2, year);
            pstmt.setString(3,language);
            pstmt.setInt(4,page);
            pstmt.setString(5, title);

            pstmt.executeUpdate();
        }

    }
    public Book getBookById(int id) throws SQLException {
        Connection con = dataSource.getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(findById)){

            PreparedStatement pstmt2 = con.prepareStatement(findByIdBookAuthors);
            PreparedStatement pstmt3 = con.prepareStatement(findByIdBookGenres);
            PreparedStatement pstmt4 = con.prepareStatement(findByIDAuthor);
            PreparedStatement pstmt5 = con.prepareStatement(findByIDGenre);

            //interoghez baza de date books
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();;

            if(rs.next()) {
                int bookId = rs.getInt(1);
                int year = rs.getInt(2);
                String language = rs.getString(3);
                int page = rs.getInt(4);
                String title = rs.getString(5);

                //interoghez baza ded ate book_authors
                pstmt2.setInt(1, id);
                ResultSet rs2 = pstmt2.executeQuery();
                List<Integer> idOfAuthors = new ArrayList<>();
                while (rs2.next()) {
                    idOfAuthors.add(rs2.getInt(1));
                }

                //interoghez baza de date authors pt a lua  autorii
                List<String> allAuthors = new ArrayList<>();
                for (Integer idOfAuthor : idOfAuthors) {
                    pstmt4.setInt(1, idOfAuthor);
                    ResultSet rs4 = pstmt4.executeQuery();
                    if(rs4.next()) {
                        allAuthors.add(rs4.getString(2));
                    }else{
                        System.out.println("No authors found");
                    }
                }

                //interoghez baza de date pt a lua book_genres
                pstmt3.setInt(1, id);
                ResultSet rs3 = pstmt3.executeQuery();
                List<Integer> idOfGenres = new ArrayList<>();
                while (rs3.next()) {
                    idOfGenres.add(rs3.getInt(1));
                }

                //iau genurile din genres
                List<String> allGenres = new ArrayList<>();
                for (Integer idOfGenre : idOfGenres) {
                    pstmt5.setInt(1, idOfGenre);
                    ResultSet rs5 = pstmt5.executeQuery();
                    if(rs5.next()) {
                        allGenres.add(rs5.getString(2));
                    }else{
                        System.out.println("No genres found");
                    }
                }

                Book book = new Book(bookId, year, language, page, title, allAuthors, allGenres);
                con.close();

                return book;
            }else{
                return null;
            }
        }catch (SQLException e ){
            throw new SQLException(e.getMessage());
        }

    }
    public void printAllBooks() throws SQLException     {

        //Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        try (PreparedStatement prstmt = con.prepareStatement(selectAllBooks)) {

            ResultSet rs = prstmt.executeQuery();
            System.out.println("----Print All Books----");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getString(5));
            }
        } catch (SQLException e) {
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
    public List<Integer> getAllBooksId() throws SQLException     {
        Connection con = dataSource.getConnection();
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement prstmt = con.prepareStatement(selectAllBooks)) {
            ResultSet rs = prstmt.executeQuery();
            int numbterOfLines = 30;
            int count=1;
            while (rs.next() && count < numbterOfLines) {
                ids.add(rs.getInt(1));
                count++;
            }
            return ids;
        }
    }
    public boolean areRelated(Integer id1 ,Integer id2)throws SQLException  {
        Connection con = dataSource.getConnection();
        Set<Integer> relatedIds = new HashSet<>();
        try  {
            PreparedStatement pstmt1 = con.prepareStatement(findByIdBookAuthors);
            pstmt1.setInt(1, id1);
            ResultSet rs = pstmt1.executeQuery();
            while (rs.next()) {
                relatedIds.add(rs.getInt(1));
            }
            pstmt1.setInt(1,id2);
            rs=pstmt1.executeQuery();
            while (rs.next()) {
                if(relatedIds.contains(rs.getInt(1))){
                    return true;
                }
            }
            return false;
        }finally {
            con.close();
        }

    }
}
