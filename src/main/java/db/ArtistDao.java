package db;

import model.Artist;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArtistDao implements ArtistList {

    MySQLConnector mySql = new MySQLConnector();
    private Connection connection = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artistList = new ArrayList<>();
        AlbumDao albumDao = new AlbumDao();
        long num = 1;
        try {
            connection = mySql.getConnection();
            String sql = "SELECT * FROM Artist ORDER BY name";
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()) {
                long artistId = resultSet.getLong("ArtistId");
                int albums = albumDao.getAllAlbums(artistId).size();
                Artist artist = new Artist(artistId, resultSet.getString("Name"), num, albums);
                artistList.add(artist);
                num ++;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        } finally {
            mySql.closeResources(connection, prepStatement, resultSet);
        }
        return artistList;
    }

    /*
       * Method checks if db table column already contains name that include user inputted name.
       * If  yes, then check if name in db is equal to name inputted. Comparison is not case-sensitive.
       * if no matches then new artist is added to db.
       * At this point i.e. "The" -prefix is not checked -> The Police and Police are dealt as different Artists
    */
    @Override
    public boolean addNewArtist(Artist newArtist) {
        boolean successful = false;
        boolean noMatches = false;
        int rowsAffected;
        String sql;
        try {
            connection = mySql.getConnection();
            sql = "SELECT * FROM Artist WHERE name LIKE concat('%', ?, '%')";
            prepStatement = connection.prepareStatement(sql);
            String artistName = newArtist.getName().trim();
            prepStatement.setString(1, artistName);
            resultSet = prepStatement.executeQuery();
            if (!resultSet.next()){
                noMatches = true;
            } else {
                while (resultSet.next()) {
                    noMatches = !resultSet.getString("Name").toLowerCase().equals(artistName.toLowerCase());
                }
            }
            if (noMatches) {
                sql = "INSERT INTO Artist(Name) VALUES(?)";
                prepStatement = connection.prepareStatement(sql);
                prepStatement.setString(1, artistName);
                rowsAffected = prepStatement.executeUpdate();
                if (rowsAffected > 0) {
                    successful = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception " + e.getMessage());
        } finally {
            mySql.closeResources(connection, prepStatement, resultSet);
        }
        return successful;
    }
}
