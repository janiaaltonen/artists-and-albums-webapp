package db;

import model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao implements AlbumList {

    MySQLConnector mySql = new MySQLConnector();
    private Connection connection = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;


    @Override
    public List<Album> getAllAlbums(long artistId) {
        List<Album> albumList = new ArrayList<>();
        int num = 1;
        try {
            connection = mySql.getConnection();
            String sql = "SELECT * FROM Album WHERE ArtistId = ?";
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setLong(1, artistId);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                Album album = new Album(resultSet.getLong("AlbumId"), resultSet.getString("Title"), resultSet.getLong("ArtistId"), num);
                albumList.add(album);
                num++;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        } finally {
            mySql.closeResources(connection, prepStatement, resultSet);
        }
        return albumList;
    }
}
