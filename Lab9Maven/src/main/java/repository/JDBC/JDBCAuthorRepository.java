package repository.JDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

import model.AuthorEntity;
import repository.Interfaces.AuthorRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class JDBCAuthorRepository  implements AuthorRepositoryInterface {
    private final String url = "jdbc:mysql://localhost:3305/db";
    private final String user = "root";
    private final String password = "Bananageo2bucati";

    @Override
    public void create(AuthorEntity a) {
        String sql = "INSERT INTO authors (name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, a.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public AuthorEntity findById(Integer id){
        String sql = "SELECT * FROM authors WHERE id = ?";
        AuthorEntity author = null;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                author = new AuthorEntity( rs.getString("name"),rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return author;
    }

    @Override
    public List<AuthorEntity> findByName(String namePattern){
        String sql = "SELECT * FROM authors WHERE name LIKE ?";
        List<AuthorEntity> authors = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + namePattern + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                authors.add(new AuthorEntity(rs.getString("name"),rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return authors;
    }

}
