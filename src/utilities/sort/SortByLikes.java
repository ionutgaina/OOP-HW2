package utilities.sort;

import database.Movie;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByLikes implements ISort {
    @Override
    public void doSort(final ArrayList<Movie> movies, final String sortType) {
        if (sortType.equals("asc")) {
            movies.sort(Comparator.comparing(Movie::getNumLikes));
        } else if(sortType.equals("desc")) {
            movies.sort(Comparator.comparing(Movie::getNumLikes).reversed());
        } else {
            throw new IllegalArgumentException("Invalid sort type");
        }
    }
}
