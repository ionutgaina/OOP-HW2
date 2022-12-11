package utilities.filters;

import database.Movie;

import java.util.ArrayList;

public interface IFilter {
    // Strategy pattern for filter - interface

    /**
     * @param movies  - list of movies to be filtered
     * @param filters - list of filters to be applied
     * @return - list of movies after applying filters
     */
    ArrayList<Movie> doFilter(ArrayList<Movie> movies, ArrayList<String> filters);
}
