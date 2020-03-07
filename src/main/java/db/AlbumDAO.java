package db;

import model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class AlbumDAO implements AlbumList {

    MySQLConnector mySql = new MySQLConnector();
    private Connection connection = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;

    

    @Override
    public List<Album> getAllAlbums() {
        String sql = "SELECT * FROM Album WHERE ArtistId = ?";

    }
}
