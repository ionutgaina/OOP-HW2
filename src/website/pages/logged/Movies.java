package website.pages.logged;

import database.Database;
import database.Movie;
import database.User;
import utilities.filters.FilterByCountry;
import utilities.filters.FilterByName;
import utilities.filters.IFilter;
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
            case "see details", "upgrades" -> {
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

        CurrentUser currentUser = CurrentUser.getInstance();
        User user = currentUser.getUser();
        CurrentDatabase currentDatabase = CurrentDatabase.getInstance();
        Database database = currentDatabase.getDatabase();

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
}
