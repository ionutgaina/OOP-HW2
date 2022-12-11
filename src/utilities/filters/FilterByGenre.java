package utilities.filters;

import database.Movie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class FilterByGenre implements IFilter {
    @Override
    public ArrayList<Movie> doFilter(final ArrayList<Movie> movies,
                                     final ArrayList<String> filters) {
        if (filters == null) {
            return movies;
        }

        return movies
                .stream()
                .filter(movie -> filters
                        .stream()
                        .anyMatch(genre -> movie.getGenres()
                                                .contains(genre)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
