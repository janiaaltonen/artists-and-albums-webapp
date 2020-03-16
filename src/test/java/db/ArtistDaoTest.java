package db;

import model.Artist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistDaoTest {

    ArtistDao artistDao = new ArtistDao();
    MySQLConnector mySql = new MySQLConnector();

    @BeforeEach
    void setUp() throws SQLException {
        Connection connection = mySql.getConnection();
        connection.prepareStatement("DELETE FROM Album").executeUpdate();
        connection.prepareStatement("DELETE FROM Artist").executeUpdate();
        String sql = "INSERT INTO Artist(ArtistId, Name) VALUES(1, 'Kirka'), (2, 'Popeda'), (3, 'Hector')";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate();
        sql = "INSERT INTO Album(AlbumId, Title, `ArtistId`) VALUES(1, 'Hectorock I', 3), (2, 'Nostalgia', 3), (3, 'H.E.C.', 3), " +
                "(4, 'Hidas', 3), (5, 'Kaksi puolta', 1), (6, 'Lauantaiyö', 1)";
        ps = connection.prepareStatement(sql);
        ps.executeUpdate();
        mySql.closeResources(connection, ps);
    }


    @Test
    void getNumberOfArtists() {
        List<Artist> allArtists = artistDao.getAllArtists();
        int expected = 3;
        assertEquals(expected, allArtists.size());
    }

    @Test
    void getArtistNamesInList() {
        List<String> names = new ArrayList<>();
        for (Artist artist : artistDao.getAllArtists()) {
            names.add(artist.getName());
        }
        List<String> expected = Arrays.asList("Kirka", "Popeda", "Hector");
        assertLinesMatch(expected, names);
    }

    @Test
    void getArtistNameById() {
        String name = artistDao.getArtist(2).getName();
        assertEquals("Popeda", name);
    }

    @Test
    void successfullyAddNewArtists() {
        Artist artist = new Artist(4, "PMMP", 0, 0);
        boolean inserted = artistDao.addNewArtist(artist);
        assertTrue(inserted);

        Artist artist2 = new Artist(4, "PMM", 0, 0);
        inserted = artistDao.addNewArtist(artist2);
        assertTrue(inserted);
    }
    @Test
    void unsuccessfullyAddNewArtists() {
        Artist artist = new Artist(1, "kirka", 0, 1);
        boolean inserted = artistDao.addNewArtist(artist);
        assertFalse(inserted);

        artist = new Artist(100, "KiRKa", 0, 0);
        inserted = artistDao.addNewArtist(artist);
        assertFalse(inserted);
    }

    @Test
    void getNumberOfAlbums() {
        List<Artist> allArtists = artistDao.getAllArtistsWithAlbums();
        int albums = 0;
        for (Artist a : allArtists) {
            albums += a.getAlbums();
        }
        assertEquals(6, albums);
    }
    
}