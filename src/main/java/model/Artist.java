package model;

public class Artist {

    private long id;
    private String name;
    private long number;
    private int albums;

    public Artist(long id, String name, long number, int albums) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.albums = albums;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getAlbums() {
        return albums;
    }

    public void setAlbums(int albums) {
        this.albums = albums;
    }
}
