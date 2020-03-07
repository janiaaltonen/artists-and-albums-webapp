package model;

public class Album {

    private long albumId;
    private String title;
    private long ArtistId;

    public Album(long albumId, String title, long artistId) {
        this.albumId = albumId;
        this.title = title;
        ArtistId = artistId;
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
        return ArtistId;
    }

    public void setArtistId(long artistId) {
        ArtistId = artistId;
    }
}
