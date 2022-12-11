package utilities.sort;

import database.Movie;

import java.util.ArrayList;

public interface ISort {
    // Strategy pattern for Sort - interface

    /**
     * Sorts the movies by the given criteria
     *
     * @param movies - the list of movies to be sorted
     */
    void doSort(ArrayList<Movie> movies);
}
