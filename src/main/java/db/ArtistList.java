package db;

import model.Artist;

import java.util.List;

public interface ArtistList {

    List<Artist> getAllArtists();

    boolean addNewArtist(Artist newArtist);

}
