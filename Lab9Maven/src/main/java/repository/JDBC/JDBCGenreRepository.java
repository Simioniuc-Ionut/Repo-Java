package repository.jdbc;

import model.GenreEntity;
import repository.interfaces.GenreRepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCGenreRepository implements GenreRepositoryInterface {
    private final String url = "jdbc:mysql://localhost:3305/db";
    private final String user = "root";
    private final String password = "Bananageo2bucati";

    @Override
    public void create(GenreEntity a) {
        String sql = "INSERT INTO genres (name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, a.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public GenreEntity findById(Integer id) {
        String sql = "SELECT * FROM genres WHERE id = ?";
        GenreEntity genre = null;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                genre = new GenreEntity( rs.getInt("id"),rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return genre;

    }

    @Override
    public List<GenreEntity> findByName(String namePattern) {
        String sql = "SELECT * FROM genres WHERE name LIKE ?";
        List<GenreEntity> genres = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + namePattern + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                genres.add(new GenreEntity(rs.getInt("id"),rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return genres;
    }
}
