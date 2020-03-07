package model;

public class Album {

    private long albumId;
    private String title;
    private long artistId;
    private int number;

    public Album(long albumId, String title, long artistId, int number) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.number = number;
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
}
