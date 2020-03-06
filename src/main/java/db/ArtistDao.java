package db;

import model.Artist;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ArtistDao implements ArtistList{

    private Connection connection = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;

    protected Connection getConnection() throws SQLException {
        String url = System.getenv("URL_DB");
        String dbUser = System.getenv("USER_DB").split(":")[0];
        String dbPw = System.getenv("USER_DB").split(":")[1];
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl(url);
        return ds.getConnection(dbUser, dbPw);
    }

    private void closeResources(AutoCloseable... sqlResources) {
        for (AutoCloseable a : sqlResources) {
            if (a != null) {
                try {
                    a.close();
                } catch (Exception e) {
                    System.out.println("Exception " + e.getMessage());
                }
            }
        }
    }

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artistList = new ArrayList<>();
        long num = 1;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM Artist ORDER BY name";
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()) {
                Artist artist = new Artist(resultSet.getLong("ArtistId"), resultSet.getString("Name"), num);
                artistList.add(artist);
                num ++;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        } finally {
            closeResources(connection, prepStatement, resultSet);
        }
        return artistList;
    }

    // currently db table doesn't support auto_increment ->
    // id must be assigned every time
    @Override
    public boolean addNewArtist(Artist newArtist) {
        boolean successful = false;
        int rowsAffected = 0;
        String sql;
        try {
            connection = getConnection();
            sql = "SELECT * FROM Artist WHERE name = ?";
            prepStatement = connection.prepareStatement(sql);
            String artistName = newArtist.getName();
            int nameLength = artistName.length();
            String name = artistName.substring(0, 1).toUpperCase() + artistName.substring(1, nameLength);
            prepStatement.setString(1, name);
            resultSet = prepStatement.executeQuery();
            if (!resultSet.next()){
                sql = "INSERT INTO Artist(ArtistId, Name) VALUES(500, ?)";
                prepStatement = connection.prepareStatement(sql);
                prepStatement.setString(1, name);
                rowsAffected = prepStatement.executeUpdate();
            }
            if (rowsAffected > 0) {
                successful = true;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception " + e.getMessage());
        } finally {
            closeResources(connection, prepStatement, resultSet);
        }
        return successful;
    }
}
