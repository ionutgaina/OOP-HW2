package utilities.sort;

import database.Movie;

import java.util.ArrayList;

public interface ISort {
    // Strategy pattern for Sort - interface
    void doSort(final ArrayList<Movie> movies);
}
