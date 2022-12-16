package website.pages.logged;

import database.Database;
import database.Movie;
import database.User;
import database.Contains;
import database.Filters;
import database.Sort;
import utilities.filters.IFilter;
import utilities.filters.FilterByName;
import utilities.filters.FilterByGenre;
import utilities.filters.FilterByCountry;
import utilities.filters.FilterByActors;
import utilities.sort.ISort;
import utilities.sort.SortByDurationRating;
import utilities.sort.SortFactory;
import website.CurrentDatabase;
import website.CurrentPage;
import website.CurrentUser;

import java.util.ArrayList;

public final class Movies {
    private static Movies instance = null;

    private Movies() {
    }

    /**
     * @return the instance of the class Movies
     */
    public static Movies getInstance() {
        if (instance == null) {
            instance = new Movies();
        }
        return instance;
    }
    /**
     * handle the request to change to movies page
     *
     * @return if the page was changed
     */
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

    /**
     * initialize the movies page
     *
     * @param currentUser the current user
     * @param currentPage the current page
     * @param database    the database
     */
    private static void initPage(final User currentUser, final CurrentPage currentPage,
                                 final Database database) {
        currentPage.setPage("movies");
        ArrayList<String> countryFilter = new ArrayList<>();
        countryFilter.add(currentUser.getCredentials().getCountry());
        IFilter filter = new FilterByCountry();
        ArrayList<Movie> movies = filter.doFilter(database.getMovies(), countryFilter);
        currentPage.setCurrentMoviesList(movies);
    }

    /**
     * search for movies
     *
     * @param startsWith the search string
     * @return if the search was successful
     */
    public static boolean search(final String startsWith) {
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

    /**
     * filter movies
     *
     * @param filters the filters
     * @return if the filter was successful
     */
    public static boolean filter(final Filters filters) {
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
                String sortType = sort.getDuration() != null ? sort.getDuration()
                                                             : sort.getRating();
                ISort sortBy = new SortFactory().createSort(sortCriteria, sortType);
                sortBy.doSort(movies);
            }
        }

        currentPage.setCurrentMoviesList(movies);
        return true;
    }
}
