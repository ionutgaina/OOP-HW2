package utilities.filters;

import database.Movie;

import java.util.ArrayList;

public interface IFilter {
    ArrayList<Movie> doFilter(final ArrayList<Movie> movies, final ArrayList<String> filters);
}
