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
    public List<Artist> getAllArtistsWithAlbums() {
        List<Artist> artistList = new ArrayList<>();
        long num = 1;
        try {
            connection = mySql.getConnection();
            String sql = "SELECT Artist.ArtistId As ArtistId, Name, Count(title) As Albums " +
                    "FROM Artist LEFT JOIN Album ON Artist.ArtistId = Album.ArtistId " +
                    "GROUP BY Artist.ArtistId, Name " +
                    "ORDER BY name";
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next()) {
                Artist artist = new Artist(resultSet.getLong("ArtistId"), resultSet.getString("Name"), num, resultSet.getInt("Albums"));
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

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artistList = new ArrayList<>();
        long num = 1;
        try {
            connection = mySql.getConnection();
            String sql = "SELECT * FROM Artist";
            prepStatement = connection.prepareStatement(sql);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                Artist artist = new Artist(resultSet.getLong("ArtistId"), resultSet.getString("Name"), num, 0);
                artistList.add(artist);
                num++;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        } finally {
            mySql.closeResources(connection, prepStatement, resultSet);
        }
        return artistList;
    }

    // If db_table gets much bigger, might be more efficient to query db with WHERE ID = ? clause to return only one item
    @Override
    public Artist getArtist(long id) {
        List<Artist> artistList = getAllArtists();

        return artistList.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
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
            if (!resultSet.next()) {
                noMatches = true;
            } else {
                // moves the resultSet cursor back to start because previous operation consumed the first row
                resultSet.beforeFirst();
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

    @Override
    public Artist removeArtist(long id) {
        Artist artist = getArtist(id);
        int rows = 0;
        try {
            connection = mySql.getConnection();
            String sql = "DELETE FROM Artist WHERE ArtistId = ?";
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setLong(1, id);
            rows = prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        } finally {
            mySql.closeResources(connection, prepStatement);
        }
        if (rows > 0) {
            return artist;
        }
        return null;
    }
}
