package utilities.filters;

import database.Movie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterByActors implements IFilter{
    @Override
    public ArrayList<Movie> doFilter(ArrayList<Movie> movies, ArrayList<String> filters) {
        if(filters == null) {
            return movies;
        }

        return (ArrayList<Movie>) movies
                .stream()
                .filter(movie -> filters
                        .stream()
                        .anyMatch( actor-> movie.getActors().contains(actor)))
                .collect(Collectors.toList());
    }
}
