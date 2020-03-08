package db;

import model.Track;

import java.util.List;

public interface TrackList {

    List<Track> getAllTracks(long albumId);
}
