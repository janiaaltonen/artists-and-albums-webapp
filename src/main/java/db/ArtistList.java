package db;

import model.Artist;

import java.util.List;

public interface ArtistList {

    List<Artist> getAllArtistsWithAlbums();

    List<Artist> getAllArtists();

    Artist getArtist(long id);

    boolean addNewArtist(Artist newArtist);

    Artist removeArtist(long id);

}
