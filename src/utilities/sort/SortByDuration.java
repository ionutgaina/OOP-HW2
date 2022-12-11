package utilities.sort;

import database.Movie;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByDuration implements ISort {
    @Override
    public void doSort(final ArrayList<Movie> movies, final String sortType) {
        if (sortType.equals("asc")) {
            movies.sort(Comparator.comparing(Movie::getDuration));
        } else if(sortType.equals("desc")) {
            movies.sort(Comparator.comparing(Movie::getDuration).reversed());
        } else {
            throw new IllegalArgumentException("Invalid sort type");
        }
    }
}
