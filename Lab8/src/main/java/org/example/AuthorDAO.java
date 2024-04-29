package org.example;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class AuthorDAO extends Library {
    private final String idOfAuthor = "select id from authors where name = ?";
    private final String nameOfAuthor = "select name from authors where id = ?";
    private final String insertAnAuthor = "insert into authors (name) values (?)";
    private BasicDataSource dataSource = ConnectionPool.getDataSource();

    public void create(String name) throws SQLException {
        //Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        try(PreparedStatement pstmt = con.prepareStatement(insertAnAuthor)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
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
        }
    }

}