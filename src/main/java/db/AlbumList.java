package db;

import model.Album;
import model.Artist;

import java.util.List;

public interface AlbumList {

    List<Album> getAllAlbums(long artistId);

}
