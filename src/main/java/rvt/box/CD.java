package rvt.box;

public class CD implements Packable {
    private String artist;
    private String name;
    private int publicationYear;

    public CD(String artist, String name, int publicationYear) {
        this.artist = artist;
        this.name = name;
        this.publicationYear = publicationYear;
    }

    public double weight() {
        return 0.1;
    }

    public String toString() {
        return this.artist + ": " + this.name + " (" + this.publicationYear + ")";
    }
}