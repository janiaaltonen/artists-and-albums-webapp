package db;

import model.Album;
import model.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDao implements TrackList {

    MySQLConnector mySql = new MySQLConnector();
    private Connection connection = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;

    @Override
    public List<Track> getAllTracks(long albumId) {
        List<Track> trackList = new ArrayList<>();
        int numberInAlbum = 1;
        try {
            connection = mySql.getConnection();
            String sql = "SELECT * FROM Track WHERE AlbumId = ? ORDER BY TrackId";
            prepStatement = connection.prepareStatement(sql);
            prepStatement.setLong(1, albumId);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                Track track = new Track(resultSet.getLong(1), resultSet.getString(2), albumId, resultSet.getInt(4),
                                        resultSet.getInt(5), resultSet.getString(6), resultSet.getInt(7),
                                        resultSet.getInt(8), resultSet.getDouble(9), numberInAlbum);
                trackList.add(track);
                numberInAlbum++;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception" + e.getMessage());
        } finally {
            mySql.closeResources(connection, prepStatement, resultSet);
        }
        return trackList;
    }
}
