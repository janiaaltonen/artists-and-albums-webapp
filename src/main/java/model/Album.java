package model;

public class Album {

    private long albumId;
    private String title;
    private long artistId;
    private int number;
    private int tracks;

    public Album(long albumId, String title, long artistId, int number, int tracks) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.number = number;
        this.tracks = tracks;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }
}
