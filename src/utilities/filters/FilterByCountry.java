package utilities.filters;

import database.Movie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class FilterByCountry implements IFilter {
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
                        .anyMatch(country -> !movie.getCountriesBanned()
                                                   .contains(country)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
