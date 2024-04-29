package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
public class GenreDAO extends Library{
    private final String insertGenre = "insert into genres (name) values (?)";
    private final String idOfGenre = "select id from genres where name = ?";
    private BasicDataSource dataSource = ConnectionPool.getDataSource();
    public void create(String tragedy) throws SQLException {
       // Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(insertGenre)) {
            pstmt.setString(1,tragedy);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
       // Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(idOfGenre)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }

}
