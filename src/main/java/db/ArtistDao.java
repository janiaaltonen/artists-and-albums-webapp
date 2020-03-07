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
        int rowsAffected = 0;
        String sql;
        try {
            connection = getConnection();
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
            closeResources(connection, prepStatement, resultSet);
        }
        return successful;
    }
}
