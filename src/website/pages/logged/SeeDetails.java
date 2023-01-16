package website.pages.logged;

import database.Movie;
import database.User;
import website.CurrentPage;
import website.CurrentUser;

import java.util.ArrayList;
import java.util.Collections;

import static utilities.Constants.MIN_RATE;
import static utilities.Constants.MAX_RATE;
import static utilities.Constants.MOVIE_PRICE;

public final class SeeDetails {
    private static SeeDetails instance = null;

    private SeeDetails() {
    }

    /**
     * @return the instance of the class SeeDetails
     */
    public static SeeDetails getInstance() {
        if (instance == null) {
            instance = new SeeDetails();
        }
        return instance;
    }

    /**
     * handle the change page request for the see details page
     * @param movieName the name of the movie to see the details
     * @return if the page was changed
     */
    public static boolean changePage(final String movieName) {
        CurrentPage currentPage = CurrentPage.getInstance();

        if (!currentPage.getPage().equals("movies")) {
            return false;
        }

        ArrayList<Movie> currentMoviesList = currentPage.getCurrentMoviesList();
        Movie seeDetailsMovie = currentMoviesList.stream()
                                                 .filter(movie -> movie.getName()
                                                                       .equals(movieName))
                                                 .findAny().orElse(null);

        if (seeDetailsMovie == null) {
            return false;
        }

        currentPage.setCurrentMoviesList(
                new ArrayList<>(Collections.singletonList(seeDetailsMovie)));
        currentPage.setPage("see details");
        return true;
    }

    /**
     * handle the subscribe request
     * @param subscribedGenre the genre to subscribe
     * @return if to subscribe was successful
     */
    public static boolean subscribe(final String subscribedGenre) {
        User currentUser = CurrentUser.getInstance().getUser();
        CurrentPage currentPage = CurrentPage.getInstance();

        // verify if the page is see details
        if ( !currentPage.getPage().equals("see details"))
        {
            return false;
        }

        // verify if the user is already subscribed to the genre
        if (currentUser.getSubscribedGenres().contains(subscribedGenre)) {
            return false;
        }

        // verify if the genre is valid
        Movie currentMovie = currentPage.getCurrentMoviesList().get(0);
        if (!currentMovie.getGenres().contains(subscribedGenre)) {
            return false;
        }

        // subscribe the user to the genre
        currentUser.getSubscribedGenres().add(subscribedGenre);
        return true;
    }

    /**
     * handle the purchase request for the see details page
     * @return if the purchase was successful
     */
    public static boolean purchase() {
        CurrentPage currentPage = CurrentPage.getInstance();
        if (!currentPage.getPage().equals("see details")) {
            return false;
        }

        CurrentUser currentUser = CurrentUser.getInstance();
        User user = currentUser.getUser();
        Movie movie = currentPage.getCurrentMoviesList().get(0);

        // verify if the user already purchased the movie
        if (user.getPurchasedMovies().contains(movie)) {
            return false;
        }


        String accountType = user.getCredentials().getAccountType();

        // verify if the user can buy the movie
        if (user.getNumFreePremiumMovies() > 0 && accountType.equals("premium")) {
            // buy the movie
            user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
        } else if (user.getTokensCount() >= MOVIE_PRICE) {
            // buy the movie
            user.setTokensCount(user.getTokensCount() - MOVIE_PRICE);
        } else {
            // user can't buy the movie
            return false;
        }

        user.getPurchasedMovies().add(movie);
        return true;
    }

    /**
     * handle the watch request for the see details page
     * @return if the user watched the movie
     */
    public static boolean watch() {
        CurrentPage currentPage = CurrentPage.getInstance();
        if (!currentPage.getPage().equals("see details")) {
            return false;
        }

        CurrentUser currentUser = CurrentUser.getInstance();
        User user = currentUser.getUser();
        Movie movie = currentPage.getCurrentMoviesList().get(0);

        // verify if the user already purchased the movie
        if (!user.getPurchasedMovies().contains(movie)) {
            return false;
        }

        // verify if the user already watched the movie
        if (user.getWatchedMovies().contains(movie)) {
            return true;
        }

        user.getWatchedMovies().add(movie);
        return true;
    }

    /**
     * handle the like request for the see details page
     * @return if the user liked the movie
     */
    public static boolean like() {
        CurrentPage currentPage = CurrentPage.getInstance();
        if (!currentPage.getPage().equals("see details")) {
            return false;
        }

        CurrentUser currentUser = CurrentUser.getInstance();
        User user = currentUser.getUser();
        Movie movie = currentPage.getCurrentMoviesList().get(0);

        // verify if the user already watched the movie
        if (!user.getWatchedMovies().contains(movie)) {
            return false;
        }

        // verify if the user already liked the movie
        if (user.getLikedMovies().contains(movie)) {
            return false;
        }

        user.getLikedMovies().add(movie);
        movie.setNumLikes(movie.getNumLikes() + 1);
        return true;
    }

    /**
     * handle the rate request for the see details page
     * @param rateValue the rate value
     * @return if the user rated the movie
     */
    public static boolean rate(final int rateValue) {
        CurrentPage currentPage = CurrentPage.getInstance();
        if (!currentPage.getPage().equals("see details")) {
            return false;
        }

        if (rateValue < MIN_RATE || rateValue > MAX_RATE) {
            return false;
        }

        CurrentUser currentUser = CurrentUser.getInstance();
        User user = currentUser.getUser();
        Movie movie = currentPage.getCurrentMoviesList().get(0);

        // verify if the user already watched the movie
        if (!user.getWatchedMovies().contains(movie)) {
            return false;
        }

        // verify if the user already rated the movie
        if (user.getRatedMovies().contains(movie)) {
            int index = user.getRatedMovies().indexOf(movie);
            movie.removeRate(user.getRatings().get(index));

            user.getRatings().set(index, rateValue);
            user.getRatedMovies().get(index).setRating(rateValue);
            movie.addRate(rateValue);
            return true;
        }

        user.getRatedMovies().add(movie);
        user.getRatings().add(rateValue);
        movie.addRate(rateValue);
        return true;
    }
}
