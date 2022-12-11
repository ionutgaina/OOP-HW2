package utilities.sort;

import database.Movie;

import java.util.ArrayList;
import java.util.Comparator;

public final class SortByLikes implements ISort {

    private final String sortType;

    SortByLikes(final String sortType) {
        this.sortType = sortType;
    }

    @Override
    public void doSort(final ArrayList<Movie> movies) {
        if (this.sortType.equals("increasing")) {
            movies.sort(Comparator.comparing(Movie::getNumLikes));
        } else if (this.sortType.equals("decreasing")) {
            movies.sort(Comparator.comparing(Movie::getNumLikes)
                                  .reversed());
        } else {
            throw new IllegalArgumentException("Invalid sort type");
        }
    }
}
