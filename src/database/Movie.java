package database;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
public final class Movie {
    @JsonIgnore
    private ArrayList<Double> ratings = new ArrayList<>();
    private String name;
    @JsonFormat(shape= JsonFormat.Shape.STRING)
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

    public Movie(final Movie movie) {
        this.name = movie.getName();
        this.year = movie.getYear();
        this.duration = movie.getDuration();
        this.genres = movie.getGenres();
        this.actors = movie.getActors();
        this.countriesBanned = movie.getCountriesBanned();
        this.numLikes = movie.getNumLikes();
        this.numRatings = movie.getNumRatings();
        this.rating = movie.getRating();
        this.ratings = movie.getRatings();
    }

    /**
     * operations to be performed when a user rate a movie
     *
     * @param rate the rating given by the user
     */
    public void addRate(final double rate) {
        ratings.add(rate);
        numRatings++;
        this.rating = ratings.stream()
                             .collect(Collectors.averagingDouble(Double::doubleValue));
    }

    public void removeRate(final double rate) {
        ratings.remove(rate);
        numRatings--;
        this.rating = ratings.stream()
                             .collect(Collectors.averagingDouble(Double::doubleValue));
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
}
