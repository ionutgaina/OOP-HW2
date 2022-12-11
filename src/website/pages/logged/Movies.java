package website.pages.logged;

import database.Database;
import database.Movie;
import database.User;
import database.filters.Contains;
import database.filters.Filters;
import database.filters.Sort;
import utilities.filters.*;
import utilities.sort.ISort;
import utilities.sort.SortByDurationRating;
import utilities.sort.SortFactory;
import website.CurrentDatabase;
import website.CurrentPage;
import website.CurrentUser;

import java.util.ArrayList;

public class Movies {
    public static boolean changePage() {
        CurrentPage currentPage = CurrentPage.getInstance();
        User currentUser = CurrentUser.getInstance().getUser();
        Database database = CurrentDatabase.getInstance().getDatabase();
        switch (currentPage.getPage()) {
            case "home" -> {
                if (currentUser != null) {
                    initPage(currentUser, currentPage, database);
                    return true;
                }
            }
            case "see details", "upgrades", "movies" -> {
                initPage(currentUser, currentPage, database);
                return true;
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    private static void initPage(User currentUser, CurrentPage currentPage, Database database) {
        currentPage.setPage("movies");
        ArrayList<String> countryFilter = new ArrayList<>();
        countryFilter.add(currentUser.getCredentials().getCountry());
        IFilter filter = new FilterByCountry();
        ArrayList<Movie> movies = filter.doFilter(database.getMovies(), countryFilter);
        currentPage.setCurrentMoviesList(movies);
    }

    // search for movies
    public static boolean search(String startsWith) {
        CurrentPage currentPage = CurrentPage.getInstance();
        String page = currentPage.getPage();
        if (!page.equals("movies")) {
            return false;
        }

        User user = CurrentUser.getInstance().getUser();
        Database database = CurrentDatabase.getInstance().getDatabase();

        // check the user country and filter the movies
        ArrayList<String> filterFields = new ArrayList<>();
        filterFields.add(user.getCredentials().getCountry());
        IFilter countryFilter = new FilterByCountry();
        ArrayList<Movie> movies = countryFilter.doFilter(database.getMovies(), filterFields);

        // check if the movie starts with the given string
        filterFields = new ArrayList<>();
        filterFields.add(startsWith);
        IFilter nameFilter = new FilterByName();
        movies = nameFilter.doFilter(movies, filterFields);

        currentPage.setCurrentMoviesList(movies);
        return true;
    }

    // filter movies
    public static boolean filter(Filters filters) {
        CurrentPage currentPage = CurrentPage.getInstance();
        String page = currentPage.getPage();
        if (!page.equals("movies")) {
            return false;
        }

        User user = CurrentUser.getInstance().getUser();
        Database database = CurrentDatabase.getInstance().getDatabase();

        // check the user country and filter the movies
        ArrayList<String> filterFields = new ArrayList<>();
        filterFields.add(user.getCredentials().getCountry());
        IFilter countryFilter = new FilterByCountry();
        ArrayList<Movie> movies = countryFilter.doFilter(database.getMovies(), filterFields);

        Contains contains = filters.getContains();
        if (contains != null) {
            ArrayList<String> filtersFields = contains.getActors();
            IFilter filter = new FilterByActors();
            movies = filter.doFilter(movies, filtersFields);

            filtersFields = contains.getGenre();
            filter = new FilterByGenre();
            movies = filter.doFilter(movies, filtersFields);
        }

        Sort sort = filters.getSort();
        if (sort != null) {
            if (sort.getDuration() != null && sort.getRating() != null) {
                SortByDurationRating sortByDurationRating =
                        new SortByDurationRating(sort.getDuration(), sort.getRating());
                sortByDurationRating.doSort(movies);
            } else {
                String sortCriteria = sort.getDuration() != null ? "duration" : "rating";
                String sortType = sort.getDuration() != null ? sort.getDuration() : sort.getRating();
                ISort sortBy = new SortFactory().createSort(sortCriteria, sortType);
                sortBy.doSort(movies);
            }
        }

        currentPage.setCurrentMoviesList(movies);
        return true;
    }
}
