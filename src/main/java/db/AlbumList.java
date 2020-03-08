package db;

import model.Album;

import java.util.List;

public interface AlbumList {

    List<Album> getAllAlbums(long artistId);

}
