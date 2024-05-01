package org.example;

import java.sql.*;

import org.apache.commons.dbcp2.BasicDataSource;
public class GenreDAO extends Library{
    private final String insertGenre = "insert into genres (name) values (?)";
    private final String idOfGenre = "select id from genres where name = ?";
    private final BasicDataSource dataSource = ConnectionPool.getDataSource();
    public Integer create(String tragedy) throws SQLException {
       // Connection con = DatabaseConnection.getConnection();
        Connection con = dataSource.getConnection();
        con.setAutoCommit(false);
        try (PreparedStatement pstmt = con.prepareStatement(insertGenre, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1,tragedy);
            pstmt.executeUpdate();

            con.commit();
            System.out.println("Genre created"+ tragedy);

            ResultSet rs= pstmt.getGeneratedKeys();
           if(rs.next()){
               return rs.getInt(1);   // returnează ID-ul noului gen creat
           }
           return null;

        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e + " cannot close connection in GenreDAO");

            }
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
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e + " cannot close connection in GenreDAO");

                }
            }
        }
    }

}
