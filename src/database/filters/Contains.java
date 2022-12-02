package database.filters;


import java.util.ArrayList;

public final class Contains {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    public Contains() { }

    public Contains(ArrayList<String> actors, ArrayList<String> genre) {
        this.actors = actors;
        this.genre = genre;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }
}
