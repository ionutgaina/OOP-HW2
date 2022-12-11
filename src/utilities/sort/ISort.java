package utilities.sort;

import database.Movie;

import java.util.ArrayList;

public interface ISort {
    void doSort(final ArrayList<Movie> movies, final String sortType);
}
