package utilities.sort;

import database.Movie;

import java.util.ArrayList;
import java.util.Comparator;

public final class SortByRating implements ISort {
    private final String sortType;

    SortByRating(final String sortType) {
        this.sortType = sortType;
    }

    @Override
    public void doSort(final ArrayList<Movie> movies) {
        if (sortType.equals("increasing")) {
            movies.sort(Comparator.comparing(Movie::getRating));
        } else if (sortType.equals("desc")) {
            movies.sort(Comparator.comparing(Movie::getRating)
                                  .reversed());
        } else {
            throw new IllegalArgumentException("Invalid sort type");
        }
    }
}
