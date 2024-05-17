package repository.JDBC;

import model.BookEntity;
import repository.Interfaces.BookRepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCBookRepository implements BookRepositoryInterface {
    private final String url = "jdbc:mysql://localhost:3305/db";
    private final String user = "root";
    private final String password = "Bananageo2bucati";

    @Override
    public void create(BookEntity a) {
        String sql = "INSERT INTO books (year,language,page,title) VALUES (?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, a.getYear());
            pstmt.setString(2, a.getLanguage());
            pstmt.setInt(3, a.getPage());
            pstmt.setString(4, a.getTitle());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public BookEntity findById(Integer id) {
        String sql = "SELECT * FROM authors WHERE id = ?";
        BookEntity books = null;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                books = new BookEntity( rs.getInt("id"),9999,"en_test",999,rs.getString("title"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }

    @Override
    public List<BookEntity> findByName(String namePattern) {
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        List<BookEntity> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(4, "%" + namePattern + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                books.add(new BookEntity(rs.getInt("id"),9999,"en_test",999,rs.getString("title")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }
    public List<BookEntity> findByFirstLetterFromNameAndYear(int Kbooks,String firstLetter, int SameYear , int differenceYearExceed) {
        String sql = "SELECT * FROM books WHERE title LIKE ? AND ABS(year - ?) <= ? LIMIT ?";
        List<BookEntity> books = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url,user,password);
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, firstLetter + "%");
            pstmt.setInt(2, SameYear);
            pstmt.setInt(3, differenceYearExceed);
            pstmt.setInt(4, Kbooks);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BookEntity book = new BookEntity();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setYear(rs.getInt("year"));

                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}
