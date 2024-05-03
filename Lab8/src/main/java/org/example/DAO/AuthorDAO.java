package org.example.DAO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.example.ConnectionPool;
import org.example.Library;

import java.sql.*;

public class AuthorDAO extends Library {
    private final String idOfAuthor = "select id from authors where name = ?";
    private final String nameOfAuthor = "select name from authors where id = ?";
    private final String insertAnAuthor = "insert into authors (name) values (?)";
    private final BasicDataSource dataSource = ConnectionPool.getDataSource();

    public Integer create(String name) throws SQLException {
        //Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        con.setAutoCommit(false);
        try(PreparedStatement pstmt = con.prepareStatement(insertAnAuthor,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            con.commit();
            System.out.println("Auhor created " + name);

            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);// returnează ID-ul noului gen creat
            }
            return null;
        } finally {
            con.close();
        }
    }

    public Integer findByName(String name) throws SQLException {
       // Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(idOfAuthor)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }finally {
            con.close();
        }
    }

    public String findById(int id) throws SQLException {
       // Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(nameOfAuthor)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString(1) : null;
            }
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e + " cannot close connection in AuthorDAO");
                    e.printStackTrace();
                }
            }
        }
    }

}