package database;

import lombok.Data;

import java.util.ArrayList;

@Data
public final class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    private int numLikes = 0;
    private int numRatings = 0;
    private double rating = 0;

    public Movie() {
    }

    public Movie(Movie movie) {
        this.name = movie.getName();
        this.year = movie.getYear();
        this.duration = movie.getDuration();
        this.genres = movie.getGenres();
        this.actors = movie.getActors();
        this.countriesBanned = movie.getCountriesBanned();
        this.numLikes = movie.getNumLikes();
        this.numRatings = movie.getNumRatings();
        this.rating = movie.getRating();
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    @Override
    public String toString() {
        return "Movie{" +
               "name='" + name + '\'' +
               ", year=" + year +
               ", duration=" + duration +
               ", genres=" + genres +
               ", actors=" + actors +
               ", countriesBanned=" + countriesBanned +
               '}';
    }
}
